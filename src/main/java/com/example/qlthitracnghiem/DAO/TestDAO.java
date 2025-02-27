/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.ExamDTO;
import com.example.qlthitracnghiem.DTO.TestDTO;
import com.example.qlthitracnghiem.DTO.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.qlthitracnghiem.interfaces.CrudInterface;
import com.example.qlthitracnghiem.utils.ConvertUtil;
import com.example.qlthitracnghiem.utils.DBConnection;
import java.time.LocalDateTime;
import org.json.JSONArray;

// chưa sửa lại mấy phương thức create,update,delete 
public class TestDAO implements CrudInterface<UserDTO> {
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
            rs.getInt("tpID"),
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
      if (connection != null) {
        connection.close();
      }
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
            rs.getInt("tpID"),
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

  @Override
  public int create(UserDTO user) throws SQLException {

    return 0;

  }

  @Override
  public int update(UserDTO user) throws SQLException {

    return 0;
  }

  @Override
  public int delete(int id) throws SQLException {

    return 0;
  }

  @Override
  public UserDTO read(int id) throws SQLException {

    return null;
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
                rs.getInt("tpID"),
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
  // public static void main(String[] args) {
  // try {
  // TestDTO a = TestDAO.getTestByTestCode("TST001");
  // System.out.println(a.toString());
  // } catch (Exception e) {
  // }
  //
  //

  // }
  // public ArrayList<TestDTO> getExam() throws SQLException {
  // Connection connection = DBConnection.getConnection();
  // String sql = "SELECT t.*, e.exOrder, e.exCode, e.ex_quesIDs " +
  // "FROM test t " +
  // "JOIN exams e ON t.testCode = e.testCode";
  // try {
  // PreparedStatement ps = connection.prepareStatement(sql);
  // ResultSet rs = ps.executeQuery();
  // ArrayList<TestDTO> tests = new ArrayList<>();
  // while (rs.next()) {
  // // Lấy thông tin từ bảng test
  // int testID = rs.getInt("testID");
  // String testCode = rs.getString("testCode");
  // String testTitle = rs.getString("testTitle");
  // int testTime = rs.getInt("testTime");
  // int tpID = rs.getInt("tpID");
  // int num_easy = rs.getInt("num_easy");
  // int num_medium = rs.getInt("num_medium");
  // int num_diff = rs.getInt("num_diff");
  // int testLimit = rs.getInt("testLimit");
  // LocalDateTime testDate = rs.getObject("testDate", LocalDateTime.class);
  // int testStatus = rs.getInt("testStatus");
  //
  // TestDTO testDTO = new TestDTO(
  // testID, testCode, testTitle, testTime, tpID, num_easy, num_medium, num_diff,
  // testLimit, testDate, testStatus);
  //
  // tests.add(testDTO);
  // }
  // return tests;
  // } catch (SQLException e) {
  // e.printStackTrace();
  // throw e;
  // }
  // }

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
            rs.getInt("tpID"),
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
      if (connection != null) {
        connection.close();
      }
    }
    return testDto;
  }

}
