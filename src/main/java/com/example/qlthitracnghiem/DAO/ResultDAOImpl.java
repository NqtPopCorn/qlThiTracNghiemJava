/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.ExamDTO;
import com.example.qlthitracnghiem.DTO.ResultDTO;
import com.example.qlthitracnghiem.utils.ConvertUtil;
import com.example.qlthitracnghiem.utils.DBConnection;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

/**
 *
 * @author Asus
 */
public class ResultDAOImpl {

    public List<ResultDTO> getAll() {
        Connection connection = DBConnection.getConnection();
        List<ResultDTO> results = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM result";
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String answers = rs.getString("rs_anwsers");
                String[] rsAnswerArray = (answers != null)
                        ? ConvertUtil.convertJSONArrayToArrayString(new JSONArray(answers))
                        : new String[0];
                results.add(
                        new ResultDTO(
                                rs.getInt("rs_num"),
                                rs.getInt("userID"),
                                rs.getString("exCode"),
                                rs.getString("rs_anwsers"),
                                rs.getBigDecimal("rs_mark"),
                                rs.getTimestamp("rs_date")));
            }
            return results;
        } catch (SQLException ex) {
            Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return List.of();
        } finally {
            if (connection != null) {
                try {
                    if (rs != null)
                        rs.close();
                    if (pst != null)
                        pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int getSoLuongThiSinhByExamCode(String examCode) {
        Connection connection = DBConnection.getConnection();
        int count = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) FROM result WHERE exCode = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, examCode);
            rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            if (connection != null) {
                try {
                    if (rs != null)
                        rs.close();
                    if (pst != null)
                        pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int getMaxScoreByExamCode(String examCode) {
        Connection connection = DBConnection.getConnection();
        int maxScore = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT MAX(rs_mark) FROM result WHERE exCode = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, examCode);
            rs = pst.executeQuery();
            if (rs.next()) {
                maxScore = rs.getInt(1);
            }
            return maxScore;
        } catch (SQLException ex) {
            Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            if (connection != null) {
                try {
                    if (rs != null)
                        rs.close();
                    if (pst != null)
                        pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int getMinScoreByExamCode(String examCode) {
        Connection connection = DBConnection.getConnection();
        int minScore = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT MIN(rs_mark) FROM result WHERE exCode = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, examCode);
            rs = pst.executeQuery();
            if (rs.next()) {
                minScore = rs.getInt(1);
            }
            return minScore;
        } catch (SQLException ex) {
            Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            if (connection != null) {
                try {
                    if (rs != null)
                        rs.close();
                    if (pst != null)
                        pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int getSoLuongThiSinhDatHoacKhongDatByExamCode(String examCode, String type) {
        Connection connection = DBConnection.getConnection();
        int count = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String sql = "";
            if ("DAT".equals(type))
                sql = "SELECT COUNT(*) FROM result WHERE exCode = ? AND rs_mark >= 5";
            else
                sql = "SELECT COUNT(*) FROM result WHERE exCode = ? AND rs_mark < 5";
            pst = connection.prepareStatement(sql);
            pst.setString(1, examCode);
            rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            if (connection != null) {
                try {
                    if (rs != null)
                        rs.close();
                    if (pst != null)
                        pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int getSoLuongThiSinhXuatSacByExamCode(String examCode) {
        Connection connection = DBConnection.getConnection();
        int count = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) FROM result WHERE exCode = ? AND rs_mark >= 9";
            pst = connection.prepareStatement(sql);
            pst.setString(1, examCode);
            rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            if (connection != null) {
                try {
                    if (rs != null)
                        rs.close();
                    if (pst != null)
                        pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public List<ResultDTO> getResultListByUserID(String userID) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ResultDTO> results = new ArrayList<>();
        try {
            String sql = "SELECT * FROM result WHERE userID = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, userID);
            rs = pst.executeQuery();
            while (rs.next()) {
                String answers = rs.getString("rs_anwsers");
                results.add(
                        new ResultDTO(
                                rs.getInt("rs_num"),
                                rs.getInt("userID"),
                                rs.getString("exCode"),
                                rs.getString("rs_anwsers"),
                                rs.getBigDecimal("rs_mark"),
                                rs.getTimestamp("rs_date")));
            }
            return results;
        } catch (SQLException ex) {
            Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return List.of();
        } finally {
            if (connection != null) {
                try {
                    if (rs != null)
                        rs.close();
                    if (pst != null)
                        pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
