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

  private TestDAO testDAO;

  public TestBUS() {
    testDAO = new TestDAO();
  }

  public ArrayList<TestDTO> getAll() throws Exception {
    return testDAO.getAll();
  }

  public ArrayList<TestDTO> search(String keyword, int status) {
    try {
      return testDAO.search(keyword, status);
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }
}
