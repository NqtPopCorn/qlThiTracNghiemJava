/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.TestDAO;
import com.example.qlthitracnghiem.DAO.ExamDAO;
import com.example.qlthitracnghiem.DAO.QuestionDAO;
import java.util.ArrayList;
import java.util.Map;

import com.example.qlthitracnghiem.DTO.TestDTO;
import com.example.qlthitracnghiem.utils.DBConnection;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;

public class TestBUS {
  public static final int ACTION_SUCCESS = 1;
  public static final int ACTION_ERROR = -9999;

  private TestDAO testDAO;

  public TestBUS() {
    testDAO = new TestDAO();
  }

  public static TestBUS getInstance() {
    return new TestBUS();
  }

  // public ArrayList<TestDTO> getExam() throws Exception{
  // return testDAO.getExam();
  // }
  public TestBUS(TestDAO testDAO) {
    this.testDAO = testDAO;
  }

  public void create(TestDTO test, Map<Integer, int[]> topicStructures) throws SQLException {
    Connection conn = null;
    try {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false); // Start transaction

      TestDAO testDAO = new TestDAO(conn);
      QuestionDAO questionDAO = new QuestionDAO(conn);

      // Validate question availability for each topic
      for (Map.Entry<Integer, int[]> entry : topicStructures.entrySet()) {
        int topicId = entry.getKey();
        int[] structure = entry.getValue(); // [easy, medium, diff]

        Map<String, Integer> availableQuestions = questionDAO.getQuestionCountByTopicAndLevel(topicId);
        System.out.println(availableQuestions);

        if (availableQuestions.get("easy") < structure[0]) {
          throw new SQLException("Không đủ câu hỏi dễ cho topic " + topicId +
              ". Yêu cầu: " + structure[0] + ", Có sẵn: " + availableQuestions.get("easy"));
        }
        if (availableQuestions.get("medium") < structure[1]) {
          throw new SQLException("Không đủ câu hỏi trung bình cho topic " + topicId +
              ". Yêu cầu: " + structure[1] + ", Có sẵn: " + availableQuestions.get("medium"));
        }
        if (availableQuestions.get("difficult") < structure[2]) {
          throw new SQLException("Không đủ câu hỏi khó cho topic " + topicId +
              ". Yêu cầu: " + structure[2] + ", Có sẵn: " + availableQuestions.get("difficult"));
        }
      }

      // If validation passes, create test and structure
      testDAO.createTest(test);

      for (Map.Entry<Integer, int[]> entry : topicStructures.entrySet()) {
        int topicId = entry.getKey();
        int[] structure = entry.getValue();
        testDAO.createTestStructure(test.getTestCode(), topicId,
            structure[0], structure[1], structure[2]);
      }

      conn.commit(); // Commit transaction
    } catch (SQLException e) {
      if (conn != null) {
        try {
          conn.rollback(); // Rollback on error
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
      throw e;
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public int delete(TestDTO test) throws SQLException {
    return testDAO.deleteTest(test.getTestCode());
  }

  public void update(TestDTO test, Map<Integer, int[]> topicStructures) throws SQLException {
    Connection conn = null;
    try {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);

      TestDAO testDAO = new TestDAO(conn);
      QuestionDAO questionDAO = new QuestionDAO(conn);

      // Validate question availability
      for (Map.Entry<Integer, int[]> entry : topicStructures.entrySet()) {
        if (entry.getKey() == null) {
          System.err.println("Null value for topic " + entry.getKey());
          continue;
        }
        int topicId = entry.getKey();
        int[] structure = entry.getValue();

        Map<String, Integer> availableQuestions = questionDAO.getQuestionCountByTopicAndLevel(topicId);
        if (availableQuestions.get("easy") < structure[0]) {
          throw new SQLException("Không đủ câu hỏi dễ cho topic " + topicId + ". Yêu cầu: " + structure[0]);
        }
        if (availableQuestions.get("medium") < structure[1]) {
          throw new SQLException("Không đủ câu hỏi trung bình cho topic " + topicId + ". Yêu cầu: " + structure[1]);
        }
        if (availableQuestions.get("difficult") < structure[2]) {
          throw new SQLException("Không đủ câu hỏi khó cho topic " + topicId + ". Yêu cầu: " + structure[2]);
        }
      }

      // Update test and structure
      testDAO.updateTest(test);
      testDAO.deleteTestStructure(test.getTestCode());

      for (Map.Entry<Integer, int[]> entry : topicStructures.entrySet()) {
        testDAO.createTestStructure(test.getTestCode(), entry.getKey(),
            entry.getValue()[0], entry.getValue()[1], entry.getValue()[2]);
      }

      conn.commit();
    } catch (SQLException e) {
      if (conn != null) {
        conn.rollback();
      }
      throw e;
    } finally {
      if (conn != null) {
        conn.close();
      }
    }
  }

  // Utility method to generate unique test code
  public String generateTestCode() {
    // Simple implementation - you might want to make this more sophisticated
    return "TEST" + System.currentTimeMillis() % 1000000;
  }

  public ArrayList<TestDTO> getAll() {
    return testDAO.getAllTest();
  }

  public TestDTO getTestByTestCode(String testCode) {
    return testDAO.getTestByCode(testCode);
  }

  public ArrayList<TestDTO> search(String keyword, int status) {
    keyword = keyword.trim().toLowerCase();
    if (status == -1) {
      return testDAO.search(keyword);
    } else {
      return testDAO.search(keyword, status);
    }
  }

  public boolean hasAExam(String testCode) {
    try {
      ExamDAO examDAO = new ExamDAO();
      return examDAO.hasAExam(testCode);
    } catch (SQLException e) {
      e.printStackTrace();
      return true;
    }
  }
}
