/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.ExamDTOq;
import com.example.qlthitracnghiem.DTO.UserDTO;
import com.example.qlthitracnghiem.utils.DBConnection;
import java.util.Arrays;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author light
 */
public class Quang {

    static public ArrayList getArrayQuestions(String testCode,String exOrder) throws SQLException {

        Connection connection = DBConnection.getConnection();
        //get random order of the exam A,B,C
        ArrayList<String> exOrders = getArrayOfExamOrderByExamId();
        Random random = new Random();
        int randomIndex = random.nextInt(exOrders.size());
        String randomOrder = exOrders.get(randomIndex);
        System.out.println(randomOrder);

        String sql = "SELECT * FROM exams WHERE testCode = ? AND exOrder = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, testCode);
            ps.setString(2, randomOrder);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String idQuestString = rs.getString("ex_quesIDs");
                System.out.println(idQuestString);

                List<Integer> preparedList = Arrays.stream(idQuestString.replaceAll("\\[|\\]", "").split(", "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                ArrayList<Integer> questionArray = new ArrayList<>(preparedList); //convert List to ArrayList

                System.out.println("ArrayList 2: " + questionArray);
                System.out.println("2: " + questionArray.get(2));

                return questionArray;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    static public ArrayList getArrayOfExamOrderByExamId() throws SQLException {
        Connection connection = DBConnection.getConnection();

        String sql = "SELECT DISTINCT exOrder FROM exams WHERE testCode = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "TST003");
            ResultSet rs = ps.executeQuery();
            ArrayList<String> examOrders = new ArrayList<>();
            while (rs.next()) {
                examOrders.add(rs.getString("exOrder"));
            }
            return examOrders;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public static String getRandomItem(ArrayList<String> list) {
        if (list == null || list.isEmpty()) {
            return null; // Or throw an exception
        }
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
    
    public static ArrayList getExams() throws SQLException{
        Connection connection = DBConnection.getConnection();

        String sql = "SELECT DISTINCT testCode FROM exams ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
         
            ResultSet rs = ps.executeQuery();
            ArrayList<String> examList = new ArrayList<>();
            while (rs.next()) {
                examList.add(rs.getString("testCode"));
            }
            return examList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public static ArrayList getTestsByTestCode(String testCode)throws SQLException{
        Connection connection = DBConnection.getConnection();

        String sql = "SELECT * FROM test WHERE testCode = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "TST003");
            ResultSet rs = ps.executeQuery();
            ArrayList<String> examOrders = new ArrayList<>();
            while (rs.next()) {
                examOrders.add(rs.getString("exOrder"));
            }
            return examOrders;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        
    }
    
    public static void main(String[] args) {
        try {
//            getArrayQuestions("TST003");

        } catch (Exception e) {
        }
    }
}
