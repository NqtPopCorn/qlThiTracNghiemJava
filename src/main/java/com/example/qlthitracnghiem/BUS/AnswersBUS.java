/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.AnswersDAO;
import com.example.qlthitracnghiem.DTO.AnswersDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class AnswersBUS {
    
    private static AnswersBUS instance;
    
    public static AnswersBUS getInstance() {
        if(instance == null)
            instance = new AnswersBUS();
        return instance;
    }
    
    public ArrayList<AnswersDTO> getAll() throws SQLException {
        return AnswersDAO.getInstance().getAll();
    }

    public List<AnswersDTO> getAnswerByQuestionID(String questionID) throws SQLException {
       return AnswersDAO.getInstance().getAnswerByQuestionID(questionID);
    }
}
