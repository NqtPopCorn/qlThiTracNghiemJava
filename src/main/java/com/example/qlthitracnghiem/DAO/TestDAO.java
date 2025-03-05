/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.BUS.ExamBUS;
import com.example.qlthitracnghiem.BUS.TestBUS;
import com.example.qlthitracnghiem.DTO.TestDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.qlthitracnghiem.utils.DBConnection;
import java.time.LocalDateTime;
import java.util.List;

// chưa sửa lại mấy phương thức create,update,delete 
public class TestDAO {
  /// ý tưởng là hiển thị test dựa trên testcode tham chiếu từ bảng exams, lúc ấn
  /// vô xem chi tiết thì hiện chi tiết đề ra
  public ArrayList<TestDTO> search(String keyword, int status) throws SQLException {
    ArrayList<TestDTO> tests = new ArrayList<>();
    Connection connection = DBConnection.getConnection();
    String sql = "SELECT t.* FROM test t  WHERE t.testTitle LIKE ?";
    if (status != -1) {
      sql += " AND testStatus = ?";
    }

    try {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, "%" + keyword + "%");

      if (status != -1) {
        ps.setInt(2, status);
      }

      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        tests.add(new TestDTO(
            rs.getInt("testID"),
            rs.getString("testCode"),
            rs.getString("testTitle"),
            rs.getInt("testTime"),
            rs.getInt("num_easy"),
            rs.getInt("num_medium"),
            rs.getInt("num_diff"),
            rs.getInt("testLimit"),
            rs.getObject("testDate", LocalDateTime.class) != null
                ? rs.getTimestamp("testDate").toLocalDateTime()
                : null,
            rs.getInt("testStatus")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
    }
    return tests;
  }

  public TestDTO getTestByTestCode(String tsCode) throws SQLException {
    Connection connection = DBConnection.getConnection();
    String sql = "SELECT * FROM test WHERE testCode = ?";
    try {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, tsCode);
      ResultSet rs = ps.executeQuery();
      TestDTO test = new TestDTO();
      if (rs.next()) {
        test = new TestDTO(
            rs.getInt("testID"),
            rs.getString("testCode"),
            rs.getString("testTitle"),
            rs.getInt("testTime"),
            rs.getInt("num_easy"),
            rs.getInt("num_medium"),
            rs.getInt("num_diff"),
            rs.getInt("testLimit"),
            rs.getObject("testDate", LocalDateTime.class),
            rs.getInt("testStatus"));
      }
      return test;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }

  public int create(TestDTO test, Integer[] topics) throws SQLException {
    Connection connection = DBConnection.getConnection();
    connection.setAutoCommit(false); // Bắt đầu transaction
    int rowsInserted = 0;
    String testCode = String.format("TST%03d", getAutoIncrement());
    String createTest = "INSERT INTO test(testCode, testTitle, testTime, num_easy, num_medium, num_diff, testLimit, testDate, testStatus) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String createTestTopic = "INSERT INTO test_topic(testID, topicID) VALUES(?, ?)";
    try (
        PreparedStatement psCreateTest = connection.prepareStatement(createTest,
            PreparedStatement.RETURN_GENERATED_KEYS);
        PreparedStatement psCreateTestTopic = connection.prepareStatement(createTestTopic);) {
      psCreateTest.setString(1, testCode);
      psCreateTest.setString(2, test.getTestTitle());
      psCreateTest.setInt(3, test.getTestTime());
      psCreateTest.setInt(4, test.getNum_easy());
      psCreateTest.setInt(5, test.getNum_medium());
      psCreateTest.setInt(6, test.getNum_diff());
      psCreateTest.setInt(7, test.getTestLimit());
      psCreateTest.setObject(8, test.getTestDate());
      psCreateTest.setInt(9, 1);
      rowsInserted = psCreateTest.executeUpdate();
      ResultSet rs = psCreateTest.getGeneratedKeys();
      if (rs.next()) {
        int testID = rs.getInt(1);
        test.setTestID(testID);
        for (Integer topicID : topics) {
          psCreateTestTopic.setInt(1, testID);
          psCreateTestTopic.setInt(2, topicID);
          psCreateTestTopic.executeUpdate();
        }
      }
      connection.commit();
      return rowsInserted;
    } catch (SQLException e) {
      connection.rollback();
      throw e;
    } finally {
      connection.setAutoCommit(true);
      connection.close();
    }
  }

  public int update(TestDTO test) throws SQLException {
    System.out.println("update test: " + test.toString());
    String sql = "UPDATE test SET testTitle = ?, testTime = ?, num_easy = ?, num_medium = ?, num_diff = ?, testLimit = ?, testDate = ?, testStatus = ? WHERE testCode = ?";
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, test.getTestTitle());
      ps.setInt(2, test.getTestTime());
      ps.setInt(3, test.getNum_easy());
      ps.setInt(4, test.getNum_medium());
      ps.setInt(5, test.getNum_diff());
      ps.setInt(6, test.getTestLimit());
      ps.setObject(7, test.getTestDate());
      ps.setInt(8, test.getTestStatus());
      ps.setString(9, test.getTestCode());
      return ps.executeUpdate();
    }
  }

  public int getAutoIncrement() throws SQLException {
    Connection connection = DBConnection.getConnection();
    String sql = "SELECT MAX(testID) AS AUTO_INCREMENT FROM test";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return rs.getInt("AUTO_INCREMENT") + 1;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
    return -1;
  }

  public int delete(TestDTO testCode) throws SQLException {

    Connection connection = DBConnection.getConnection();
    ExamBUS examBUS = new ExamBUS();
    try {
      List<String> examCodes = examBUS.getExamCodesByTestCode(testCode.getTestCode());

      for (String exCode : examCodes) {
        if (examBUS.isExCodeExistInResult(exCode)) {
          throw new SQLException("Đã có học sinh làm bài kiểm tra, không thể xóa.");
        }
      }
      // Xóa bảng test_topic
      String deleteTopic = "Delete from test_topic where testID=? ";
      try (PreparedStatement psExams = connection.prepareStatement(deleteTopic)) {
        psExams.setInt(1, testCode.getTestID());
        psExams.executeUpdate();
      }
            // Xóa bảng exam
      String deleteExamsSQL = "DELETE FROM exams WHERE testCode = ?";
      try (PreparedStatement psExams = connection.prepareStatement(deleteExamsSQL)) {
        psExams.setString(1, testCode.getTestCode());
        psExams.executeUpdate();
      }
      String deleteTest = "DELETE FROM test where testcode=?";
      try (PreparedStatement psExams = connection.prepareStatement(deleteTest)) {
        psExams.setString(1, testCode.getTestCode());
        psExams.executeUpdate();
      }


      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
    }
  }

  public ArrayList<TestDTO> getAll() throws SQLException {
    Connection connection = DBConnection.getConnection();
    String sql = "SELECT * FROM test";
    try {
      PreparedStatement ps = connection.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      ArrayList<TestDTO> tests = new ArrayList<>();
      while (rs.next()) {
        tests.add(
            new TestDTO(
                rs.getInt("testID"),
                rs.getString("testCode"),
                rs.getString("testTitle"),
                rs.getInt("testTime"),
                rs.getInt("num_easy"),
                rs.getInt("num_medium"),
                rs.getInt("num_diff"),
                rs.getInt("testLimit"),
                rs.getObject("testDate", LocalDateTime.class),
                rs.getInt("testStatus")));
      }
      return tests;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }

  public int updateTestTopics(int testID, Integer[] topics) throws SQLException {
    System.out.print("update test: testID = " + testID + ", topics = ");
    for (Integer topic : topics) {
      System.out.print(topic + ", ");
    }
    System.out.println();
    Connection connection = DBConnection.getConnection();
    connection.setAutoCommit(false); // Bắt đầu transaction
    int rowsInserted = 0;

    String deleteSql = "DELETE FROM test_topic WHERE testID = ?";
    String insertSql = "INSERT INTO test_topic(testID, topicID) VALUES(?, ?)";

    try (PreparedStatement psDelete = connection.prepareStatement(deleteSql);
        PreparedStatement psInsert = connection.prepareStatement(insertSql)) {

      // Xóa các chủ đề cũ
      psDelete.setInt(1, testID);
      psDelete.executeUpdate();

      // Thêm các chủ đề mới
      for (Integer topicID : topics) {
        psInsert.setInt(1, testID);
        psInsert.setInt(2, topicID);
        rowsInserted += psInsert.executeUpdate();
      }

      // Nếu không có lỗi, commit transaction
      connection.commit();
      return rowsInserted;

    } catch (SQLException e) {
      connection.rollback(); // Hoàn tác nếu có lỗi
      throw e;
    } finally {
      connection.setAutoCommit(true); // Bật lại auto-commit
    }
  }

  public TestDTO getTestDtoByTestCode(String testCode) throws SQLException {
    TestDTO testDto = null;
    Connection connection = DBConnection.getConnection();
    String sql = "SELECT * FROM test WHERE testCode = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, testCode);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        testDto = new TestDTO(
            rs.getInt("testID"),
            rs.getString("testCode"),
            rs.getString("testTitle"),
            rs.getInt("testTime"),
            rs.getInt("num_easy"),
            rs.getInt("num_medium"),
            rs.getInt("num_diff"),
            rs.getInt("testLimit"),
            rs.getObject("testDate", LocalDateTime.class),
            rs.getInt("testStatus"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
    }
    return testDto;
  }

  public boolean isTestCodeExistExam(String testCode) throws SQLException {
    Connection connection = DBConnection.getConnection();
    String sql = "SELECT COUNT(*) FROM exams WHERE testCode = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, testCode);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return rs.getInt(1) > 0;
      }
    }
    return false;
  }
}
