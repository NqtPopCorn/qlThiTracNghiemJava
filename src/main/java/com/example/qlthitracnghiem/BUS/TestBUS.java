/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.TestDAO;
import java.util.ArrayList;

import com.example.qlthitracnghiem.DTO.TestDTO;
import java.sql.SQLException;

public class TestBUS {
  public static final int ACTION_SUCCESS = 1;
  public static final int ACTION_ERROR = -9999;
  private static TestBUS instance;

  private TestDAO testDAO;

  public TestBUS() {
    testDAO = new TestDAO();
  }

  public static TestBUS getInstance() {
    if (instance == null)
      instance = new TestBUS();
    return instance;
  }

  public ArrayList<TestDTO> getAll() throws Exception {
    return testDAO.getAll();
  }

  // public ArrayList<TestDTO> getExam() throws Exception{
  // return testDAO.getExam();
  // }
  public TestBUS(TestDAO testDAO) {
    this.testDAO = testDAO;
  }

  public ArrayList<TestDTO> search(String keyword, int status) {
    try {
      return testDAO.search(keyword, status);
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  public TestDTO getTestByTestCode(String tsCode) {
    try {
      return testDAO.getTestByTestCode(tsCode);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public int update(TestDTO test) throws Exception {
    return testDAO.update(test);

  }

  public int updateTestTopics(int testID, Integer[] topics) throws Exception {
    return testDAO.updateTestTopics(testID, topics);
  }

  public int create(TestDTO test, Integer[] topics) throws Exception {
    return testDAO.create(test, topics);
  }
}
