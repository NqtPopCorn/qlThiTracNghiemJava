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
import java.util.List;
import org.json.JSONArray;

// chưa sửa lại mấy phương thức create,update,delete 
// cần gì thì thêm vào nha
public class ExamDAO implements CrudInterface<UserDTO> {
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

  // đm quả lấy data nhìn rườm ra luộm thuộm vcl
  public String getQuestionContent(int qID) throws Exception {
    Connection connection = DBConnection.getConnection();
    String sql = "SELECT q.qContent FROM questions q JOIN exams e ON JSON_CONTAINS(e.ex_quesIDs, CAST(q.qID AS CHAR)) WHERE q.qID =? GROUP BY qID";

    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, qID);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        return rs.getString("qContent");
      }
    }
    return null;
  }

  public List<String> getAnswerContent(int qID) throws Exception {
    Connection connection = DBConnection.getConnection();
    List<String> awContents = new ArrayList<>();
    String sql = "SELECT aw.awContent FROM answers aw JOIN questions q ON aw.qID=q.qID WHERE q.qID = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, qID);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        awContents.add(rs.getString("awContent"));
      }
    }
    return awContents;
  }

  public List<Integer> getExQuesIDs(String testCode) throws Exception {
    Connection connection = DBConnection.getConnection();
    List<Integer> exQuesIDs = new ArrayList<>();
    String sql = "SELECT ex_quesIDs FROM exams WHERE testCode = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setString(1, testCode);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        // Giả sử ex_quesIDs được lưu dưới dạng JSON array trong cơ sở dữ liệu
        String jsonArray = rs.getString("ex_quesIDs");
        JSONArray json = new JSONArray(jsonArray);
        for (int i = 0; i < json.length(); i++) {
          exQuesIDs.add(json.getInt(i));
        }
      }
    }
    return exQuesIDs;
  }
}
