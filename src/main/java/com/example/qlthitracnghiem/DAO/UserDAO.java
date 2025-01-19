package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.qlthitracnghiem.interfaces.CrudInterface;
import com.example.qlthitracnghiem.utils.DBConnection;

public class UserDAO implements CrudInterface<UserDTO> {

  public UserDTO getByUserEmail(String email) throws SQLException {
    Connection connection = DBConnection.getConnection();
    String sql = "SELECT * FROM users WHERE userEmail = ?";
    try {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, email);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return new UserDTO(rs.getInt("userID"), rs.getString("userName"), rs.getString("userPassword"),
            rs.getString("userEmail"), rs.getString("userFullName"), rs.getInt("isAdmin"));
      }
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }

  @Override
  public int create(UserDTO user) throws SQLException {
    Connection connection = DBConnection.getConnection();
    String sql = "INSERT INTO users (userName, userPassword, userFullName, userEmail, isAdmin) VALUES (?, ?, ?, ?, ?)";
    try {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, user.getUserName());
      ps.setString(2, user.getUserPassword());
      ps.setString(3, user.getUserFullName());
      ps.setString(4, user.getUserEmail());
      ps.setInt(5, user.isAdmin());
      return ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }

  @Override
  public int update(UserDTO user) throws SQLException {
    Connection connection = DBConnection.getConnection();
    String sql = "UPDATE users SET userPassword = ?, userFullName = ?, userEmail = ?, isAdmin = ? WHERE userID = ?";
    try {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, user.getUserPassword());
      ps.setString(2, user.getUserFullName());
      ps.setString(3, user.getUserEmail());
      ps.setInt(4, user.isAdmin());
      ps.setInt(5, user.getUserID());
      return ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }

  @Override
  public int delete(int id) throws SQLException {
    Connection connection = DBConnection.getConnection();
    String sql = "DELETE FROM users WHERE id = ?";
    try {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setInt(1, id);
      return ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }

  @Override
  public UserDTO read(int id) throws SQLException {
    Connection connection = DBConnection.getConnection();
    String sql = "SELECT * FROM users WHERE id = ?";
    try {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return new UserDTO(rs.getInt("userID"), rs.getString("userName"), rs.getString("userPassword"),
            rs.getString("userEmail"), rs.getString("userFullName"), rs.getInt("isAdmin"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
    return null;
  }

  public ArrayList<UserDTO> getAll() throws SQLException {
    Connection connection = DBConnection.getConnection();
    String sql = "SELECT * FROM users";
    try {
      PreparedStatement ps = connection.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      ArrayList<UserDTO> users = new ArrayList<>();
      while (rs.next()) {
        users.add(
            new UserDTO(rs.getInt("userID"), rs.getString("userName"), rs.getString("userPassword"),
                rs.getString("userEmail"), rs.getString("userFullName"), rs.getInt("isAdmin")));
      }
      return users;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }
}
