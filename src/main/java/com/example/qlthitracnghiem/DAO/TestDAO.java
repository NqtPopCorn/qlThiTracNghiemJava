/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.TestDTO;
import com.example.qlthitracnghiem.DTO.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.qlthitracnghiem.interfaces.CrudInterface;
import com.example.qlthitracnghiem.utils.DBConnection;
import java.time.LocalDateTime;

// chưa sửa lại mấy phương thức create,update,delete 
public class TestDAO implements CrudInterface<UserDTO> {

  public ArrayList<TestDTO> search(String keyword, int status) throws SQLException {
    ArrayList<TestDTO> tests = new ArrayList<>();
    Connection connection = DBConnection.getConnection();

    // SQL có điều kiện tìm kiếm theo từ khóa và trạng thái
    String sql = "SELECT * FROM test WHERE (testTitle LIKE ?)";
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
}
