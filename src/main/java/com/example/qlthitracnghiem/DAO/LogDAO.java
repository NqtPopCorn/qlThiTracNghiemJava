/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.LogDTO;
import java.sql.SQLException;

import com.example.qlthitracnghiem.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class LogDAO {

    public Connection connection = DBConnection.getConnection();

    public LogDAO() {
      
    }

    public boolean addLog(LogDTO log) throws SQLException {
        String sql = "INSERT INTO logs (logID, logContent, logUserID, logExCode, logDate) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, log.getLogID());
            statement.setString(2, log.getLogContent());
            statement.setInt(3, log.getLogUserID());
            statement.setString(4, log.getLogExCode());
            statement.setTimestamp(5, log.getLogDate());
            return statement.executeUpdate() > 0;
        }
    }

    public LogDTO getLog(int logID) throws SQLException {
        String sql = "SELECT * FROM logs WHERE logID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, logID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    LogDTO log = new LogDTO();
                    log.setLogID(resultSet.getInt("logID"));
                    log.setLogContent(resultSet.getString("logContent"));
                    log.setLogUserID(resultSet.getInt("logUserID"));
                    log.setLogExCode(resultSet.getString("logExCode"));
                    log.setLogDate(resultSet.getTimestamp("logDate"));
                    return log;
                }
                return null;
            }
        }
    }

    public List<LogDTO> getAllLogs() throws SQLException {
        String sql = "SELECT * FROM logs";
        List<LogDTO> logs = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                LogDTO log = new LogDTO();
                log.setLogID(resultSet.getInt("logID"));
                log.setLogContent(resultSet.getString("logContent"));
                log.setLogUserID(resultSet.getInt("logUserID"));
                log.setLogExCode(resultSet.getString("logExCode"));
                log.setLogDate(resultSet.getTimestamp("logDate"));
                logs.add(log);
            }
            return logs;
        }
    }

    public boolean updateLog(LogDTO log) throws SQLException {
        String sql = "UPDATE logs SET logContent = ?, logUserID = ?, logExCode = ?, logDate = ? WHERE logID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, log.getLogContent());
            statement.setInt(2, log.getLogUserID());
            statement.setString(3, log.getLogExCode());
            statement.setTimestamp(4, log.getLogDate());
            statement.setInt(5, log.getLogID());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteLog(int logID) throws SQLException {
        String sql = "DELETE FROM logs WHERE logID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, logID);
            return statement.executeUpdate() > 0;
        }
    }
}
