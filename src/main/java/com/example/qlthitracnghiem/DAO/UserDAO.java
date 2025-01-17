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
  private static UserDAO instance;

  private UserDAO() {
  }

  public static UserDAO getInstance() {
    if (instance == null) {
      instance = new UserDAO();
    }
    return instance;
  }

  public UserDTO getByUsername(String username) {
    Connection connection = DBConnection.getConnection();
    String sql = "SELECT * FROM users WHERE username = ?";
    try {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, username);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return new UserDTO(rs.getInt("userID"), rs.getString("userName"), rs.getString("userPassword"),
            rs.getString("userEmail"), rs.getString("userFullName"), rs.getInt("isAdmin"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public int create(UserDTO user) {
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
    }
    return 0;
  }

  @Override
  public int update(UserDTO user) {
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
    }
    return 0;
  }

  @Override
  public int delete(int id) {
    Connection connection = DBConnection.getConnection();
    String sql = "DELETE FROM users WHERE id = ?";
    try {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setInt(1, id);
      return ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public UserDTO read(int id) {
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
    }
    return null;
  }

  public ArrayList<UserDTO> getAll() {
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
    }
    return null;
  }
}
