package com.example.qlthitracnghiem.BUS;

import java.util.ArrayList;

import com.example.qlthitracnghiem.DAO.UserDAO;
import com.example.qlthitracnghiem.DTO.UserDTO;
import com.example.qlthitracnghiem.utils.PasswordUtil;

public class UserBUS {
  public static final int ACTION_SUCCESS = 1;
  public static final int LOGIN_WRONG_USERNAME = 0;
  public static final int LOGIN_WRONG_PASSWORD = -1;
  public static final int ACTION_ERROR = -9999;

  private UserDAO userDAO;

  public UserBUS() {
    userDAO = UserDAO.getInstance();
  }

  public int login(String username, String password) {
    try {
      UserDTO user = userDAO.getByUsername(username);
      if (user == null) {
        return LOGIN_WRONG_USERNAME;
      }
      if (PasswordUtil.checkPassword(password, user.getUserPassword())) {
        return ACTION_SUCCESS;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return LOGIN_WRONG_PASSWORD;
  }

  public int register(String username, String password, String email) {
    UserDTO user = new UserDTO();
    user.setUserName(username);
    user.setUserEmail(email);
    String hashedPassword = PasswordUtil.hashPassword(password);
    user.setUserPassword(hashedPassword);
    return userDAO.create(user);
  }

  public boolean checkExist(String username) {
    return userDAO.getByUsername(username) != null;
  }

  public int update(String username, String password) {
    UserDTO user = new UserDTO();
    user.setUserName(username);
    user.setUserPassword(password);
    return userDAO.update(user);
  }

  public int delete(int id) {
    return userDAO.delete(id);
  }

  public UserDTO read(int id) {
    return userDAO.read(id);
  }

  public ArrayList<UserDTO> getAll() {
    return userDAO.getAll();
  }

}
