/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.ResultDTO;
import com.example.qlthitracnghiem.interfaces.CrudInterface;

/**
 *
 * @author light
 */
import com.example.qlthitracnghiem.DTO.ResultDTO;
import com.example.qlthitracnghiem.utils.DBConnection;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {

    public Connection connection = DBConnection.getConnection();

    public boolean addResult(ResultDTO result) throws SQLException {
        String sql = "INSERT INTO result (rs_num, userID, exCode, rs_anwsers, rs_mark, rs_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, result.getRsNum());
            statement.setInt(2, result.getUserID());
            statement.setString(3, result.getExCode());
            statement.setString(4, result.getRsAnswers());
            statement.setBigDecimal(5, result.getRsMark());
            statement.setTimestamp(6, result.getRsDate());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultDTO getResult(int rsNum, int userID, String exCode) throws SQLException {
        String sql = "SELECT * FROM result WHERE rs_num = ? AND userID = ? AND exCode = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, rsNum);
            statement.setInt(2, userID);
            statement.setString(3, exCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ResultDTO result = new ResultDTO();
                    result.setRsNum(resultSet.getInt("rs_num"));
                    result.setUserID(resultSet.getInt("userID"));
                    result.setExCode(resultSet.getString("exCode"));
                    result.setRsAnswers(resultSet.getString("rs_anwsers"));
                    result.setRsMark(resultSet.getBigDecimal("rs_mark"));
                    result.setRsDate(resultSet.getTimestamp("rs_date"));
                    return result;
                }
                return null;
            }
        }
    }

    public List<ResultDTO> getAllResults() throws SQLException {
        String sql = "SELECT * FROM result";
        List<ResultDTO> results = new ArrayList<>();
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                ResultDTO result = new ResultDTO();
                result.setRsNum(resultSet.getInt("rs_num"));
                result.setUserID(resultSet.getInt("userID"));
                result.setExCode(resultSet.getString("exCode"));
                result.setRsAnswers(resultSet.getString("rs_anwsers"));
                result.setRsMark(resultSet.getBigDecimal("rs_mark"));
                result.setRsDate(resultSet.getTimestamp("rs_date"));
                results.add(result);
            }
            return results;
        }
    }

    public boolean updateResult(ResultDTO result) throws SQLException {
        String sql = "UPDATE result SET rs_anwsers = ?, rs_mark = ?, rs_date = ? WHERE rs_num = ? AND userID = ? AND exCode = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, result.getRsAnswers());
            statement.setBigDecimal(2, result.getRsMark());
            statement.setTimestamp(3, result.getRsDate());
            statement.setInt(4, result.getRsNum());
            statement.setInt(5, result.getUserID());
            statement.setString(6, result.getExCode());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteResult(int rsNum, int userID, String exCode) throws SQLException {
        String sql = "DELETE FROM result WHERE rs_num = ? AND userID = ? AND exCode = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, rsNum);
            statement.setInt(2, userID);
            statement.setString(3, exCode);
            return statement.executeUpdate() > 0;
        }
    }

    public int getHighestRsNum(int userID, String exCode) throws SQLException {
        String sql = "SELECT MAX(rs_num) FROM result WHERE userID = ? AND exCode = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            statement.setString(2, exCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1); // Get the MAX(rs_num)
                }
                return 0; // Return 0 if no results are found
            }
        }
    }
}