package com.example.qlthitracnghiem.BUS;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

  public int create(String username, String password, String email, String fullname, int isAdmin) throws Exception {
    UserDTO user = new UserDTO();
    user.setUserName(username);
    user.setUserPassword(password);
    user.setUserEmail(email);
    user.setUserFullName(fullname);
    user.setIsAdmin(isAdmin);
    return userDAO.create(user);
  }

  public int create(UserDTO user) throws Exception {
    return userDAO.create(user);
  }

  public boolean checkExist(String email) throws Exception {
    return userDAO.getByUserEmail(email) != null;
  }

  public int update(UserDTO user) throws Exception {
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

  public ArrayList<UserDTO> search(String keyword) throws Exception {
    ArrayList<UserDTO> data = getAll().stream()
        .filter(user -> user.getUserName().contains(keyword) || user.getUserEmail().contains(keyword)
            || String.valueOf(user.getUserID()).contains(keyword)
            || (user.getUserFullName() != null && user.getUserFullName().contains(keyword)))
        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    return data;
  }

  public int updateUserInfo(int userID, String email, String fullName) throws Exception {
    return userDAO.updateUserInfo(userID + "", email, fullName);
  }

  public int updatePassword(int userID, String currentPassword, String newPassword, String confirmPassword)
      throws Exception {
    UserDTO user = userDAO.read(userID);
    if (!PasswordUtil.checkPassword(currentPassword, user.getUserPassword())) {
      System.out.println("Current password: " + currentPassword);
      throw new Exception("Current password is incorrect");
    }
    if (!newPassword.equals(confirmPassword)) {
      throw new Exception("New password and confirm password are not matched");
    }
    return userDAO.updatePassword(userID + "", PasswordUtil.hashPassword(newPassword));
  }

  public ArrayList<ArrayList<String>> getLichSuLamBai(int userID) throws Exception {
    return userDAO.getLichSuLamBai(userID + "");
  }

  public ArrayList<ArrayList<String>> searchLichSuLamBai(int userID, String keyword) throws Exception {
    return userDAO.searchLichSuLamBai(userID + "", keyword);
  }

  public void importExcel(File file) throws Exception {
    FileInputStream fis = new FileInputStream(file);
    Workbook workbook = new XSSFWorkbook(fis);
    org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
    String errors = "";

    for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Bỏ qua hàng tiêu đề
      try {
        Row row = sheet.getRow(i);
        if (row == null)
          continue;

        UserDTO user = new UserDTO();
        user.setUserName(row.getCell(0).getStringCellValue());
        user.setUserFullName(row.getCell(1).getStringCellValue());
        user.setUserEmail(row.getCell(2).getStringCellValue());
        if (checkExist(user.getUserEmail())) {
          errors += "Row " + i + ": Email already exists\n";
          continue;
        }
        String pwd = row.getCell(3).getStringCellValue();
        user.setUserPassword(PasswordUtil.hashPassword(pwd));
        user.setIsAdmin((int) row.getCell(4).getNumericCellValue());

        System.out.println(user.toString());

        this.create(user);
      } catch (Exception e) {
        errors += "Row " + i + ": " + e.getMessage() + "\n";
        e.printStackTrace();
        continue;
      }
    }

    workbook.close();
    fis.close();

    if (!errors.isEmpty()) {
      throw new Exception(errors);
    }
  }

}
