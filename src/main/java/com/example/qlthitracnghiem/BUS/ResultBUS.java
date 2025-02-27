/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.BUS;

/**
 *
 * @author light
 */

import com.example.qlthitracnghiem.DAO.ResultDAO;
import com.example.qlthitracnghiem.DTO.ResultDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ResultBUS {

    private ResultDAO resultDAO;

    public ResultBUS() {
        this.resultDAO = new ResultDAO();
    }

    public boolean addResult(ResultDTO result) {
        try {
            return resultDAO.addResult(result);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it, throw a custom exception)
            return false;
        }
    }

    public ResultDTO getResult(int rsNum, int userID, String exCode) {
        try {
            return resultDAO.getResult(rsNum, userID, exCode);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int getHighestRsNum(int userID, String exCode){
        try {
            return resultDAO.getHighestRsNum( userID, exCode);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<ResultDTO> getAllResults() {
        try {
            return resultDAO.getAllResults();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateResult(ResultDTO result) {
        try {
            return resultDAO.updateResult(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteResult(int rsNum, int userID, String exCode) {
        try {
            return resultDAO.deleteResult(rsNum, userID, exCode);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}