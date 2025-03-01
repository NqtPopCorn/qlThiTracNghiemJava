/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// LogBUS.java
package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.LogDAO;
import com.example.qlthitracnghiem.DTO.LogDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LogBUS {

    private LogDAO logDAO;

    public LogBUS() {
        this.logDAO = new LogDAO();
    }

    public int addLog(LogDTO log) {
        try {
            return logDAO.addLog(log); // return id of the row was inserted
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; //  return error index
        }
    }

    public LogDTO getLog(int logID) {
        try {
            return logDAO.getLog(logID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<LogDTO> getAllLogs() {
        try {
            return logDAO.getAllLogs();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateLog(LogDTO log) {
        try {
            return logDAO.updateLog(log);
        } catch (SQLException e) {
            e.printStackTrace();
            
            return false;
        }
    }

    public boolean deleteLog(int logID) {
        try {
            return logDAO.deleteLog(logID);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}