package com.example.qlthitracnghiem.DAO;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.example.qlthitracnghiem.utils.DBConnection;

public class UserInfoDAO {

  // test
  public int updateUserInfo(String userID, String email, String fullName) {
    String sql = "UPDATE users SET userEmail = ?, userFullName = ? WHERE userID = ?";
    try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
      ps.setString(1, email);
      ps.setString(2, fullName);
      ps.setString(3, userID);
      return ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  // test
  public int updatePassword(String userID, String password) {
    String sql = "UPDATE users SET userPassword = ? WHERE userID = ?";
    try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
      ps.setString(1, password);
      ps.setString(2, userID);
      return ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  public ArrayList<ArrayList<String>> getLichSuLamBai(String userID) {
    // rs_num, exCode, title, rs_mark, rs_date, topic
    ArrayList<ArrayList<String>> list = new ArrayList<>();
    String sql = "SELECT r.*, t.testTitle AS title, tp.tpTitle AS topic\r\n" + //
        "FROM result r, exams e, test t, topics tp\r\n" + //
        "WHERE e.testCode = t.testCode AND r.exCode = e.exCode AND t.tpID = tp.tpID AND r.userID = ?";
    try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
      ps.setString(1, userID);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        ArrayList<String> row = new ArrayList<>();
        row.add(rs.getString("rs_num"));
        row.add(rs.getString("exCode"));
        row.add(rs.getString("title"));
        row.add(rs.getString("rs_mark"));
        row.add(rs.getString("rs_date"));
        row.add(rs.getString("topic"));
        list.add(row);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public ArrayList<ArrayList<String>> searchLichSuLamBai(String userID, String keyword) {
    // rs_num, exCode, title, rs_mark, rs_date, topic
    ArrayList<ArrayList<String>> list = new ArrayList<>();
    String sql = "SELECT r.*, t.testTitle AS title, tp.tpTitle AS topic\r\n" + //
        "FROM result r, exams e, test t, topics tp\r\n" + //
        "WHERE e.testCode = t.testCode AND r.exCode = e.exCode AND t.tpID = tp.tpID AND r.userID = ? AND (t.testTitle LIKE ? OR tp.tpTitle LIKE ?)";
    try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
      ps.setString(1, userID);
      ps.setString(2, "%" + keyword + "%");
      ps.setString(3, "%" + keyword + "%");
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        ArrayList<String> row = new ArrayList<>();
        row.add(rs.getString("rs_num"));
        row.add(rs.getString("exCode"));
        row.add(rs.getString("title"));
        row.add(rs.getString("rs_mark"));
        row.add(rs.getString("rs_date"));
        row.add(rs.getString("topic"));
        list.add(row);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public static void main(String[] args) {
    UserInfoDAO dao = new UserInfoDAO();
    JFrame frame = new JFrame();
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JTable table = new JTable();
    ArrayList<ArrayList<String>> list = dao.getLichSuLamBai("2");
    Object[][] data = new Object[list.size()][6];
    for (int i = 0; i < list.size(); i++) {
      for (int j = 0; j < 6; j++) {
        data[i][j] = list.get(i).get(j);
        System.out.println(data[i][j]);
      }
    }
    String[] columns = { "Lần làm bài", "Mã bài thi", "Tiêu đề bài thi", "Điểm số", "Ngày thi", "Chủ đề" };

    DefaultTableModel model = new DefaultTableModel(data, columns);
    table.setModel(model);
    JScrollPane scrollPane = new JScrollPane(table);
    frame.add(scrollPane);
    frame.setVisible(true);

  }
}
