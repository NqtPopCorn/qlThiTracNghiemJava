package com.example.qlthitracnghiem.BUS;

import java.util.ArrayList;

import com.example.qlthitracnghiem.DAO.UserDAO;
import com.example.qlthitracnghiem.DTO.UserDTO;
import com.example.qlthitracnghiem.utils.PasswordUtil;

public class UserBUS {
  public static final int ACTION_SUCCESS = 1;
  public static final int ACTION_ERROR = -9999;

  private UserDAO userDAO;

  private static UserBUS instance;

  public static UserBUS getInstance() {
    if (instance == null) {
      instance = new UserBUS();
    }
    return instance;
  }

  private UserBUS() {
    userDAO = new UserDAO();
  }

  public UserDTO login(String username, String password) throws Exception {
    UserDTO user = userDAO.getByUserEmail(username);
    if (user == null) {
      throw new Exception("User not found");
    }
    if (!PasswordUtil.checkPassword(password, user.getUserPassword())) {
      throw new Exception("Password is incorrect");
    }
    return user;
  }

  public int register(String username, String password, String email) throws Exception {
    UserDTO user = new UserDTO();
    user.setUserName(username);
    user.setUserEmail(email);
    String hashedPassword = PasswordUtil.hashPassword(password);
    user.setUserPassword(hashedPassword);
    return userDAO.create(user);
  }

  public boolean checkExist(String email) throws Exception {
    return userDAO.getByUserEmail(email) != null;
  }

  public int update(String username, String password) throws Exception {
    UserDTO user = new UserDTO();
    user.setUserName(username);
    user.setUserPassword(password);
    return userDAO.update(user);
  }

  public int delete(int id) throws Exception {
    return userDAO.delete(id);
  }

  public UserDTO read(int id) throws Exception {
    return userDAO.read(id);
  }

  public ArrayList<UserDTO> getAll() throws Exception {
    return userDAO.getAll();
  }

}
