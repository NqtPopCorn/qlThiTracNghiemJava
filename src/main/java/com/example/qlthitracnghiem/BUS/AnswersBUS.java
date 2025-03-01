package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.AnswersDAO;
import com.example.qlthitracnghiem.DTO.AnswersDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswersBUS {

    private static AnswersBUS instance;

    public static AnswersBUS getInstance() {
        if (instance == null)
            instance = new AnswersBUS();
        return instance;
    }

    public ArrayList<AnswersDTO> getAll() throws SQLException {
        return AnswersDAO.getInstance().getAll();
    }

    public List<AnswersDTO> getAnswerByQuestionID(String questionID) throws SQLException {
        return AnswersDAO.getInstance().getAnswerByQuestionID(questionID);
    }

    public AnswersDAO answersDAO = new AnswersDAO();

    public boolean create(AnswersDTO answer) {
        try {
            return answersDAO.create(answer);
        } catch (SQLException e) {
            return false;
        }
    }

    public ArrayList<AnswersDTO> getAnswersByQuestionID(int qID) {
        try {
            return answersDAO.getAnswersByQuestionID(qID);
        } catch (SQLException e) {
            return null;
        }
    }
    public AnswersDTO getAnswersDTOByAnswerID(int awId) {
        try {
            return answersDAO.getAnswerByID(awId);
        } catch (SQLException e) {
            return null;
        }
    }
    public boolean deleteByQuestionID(int qID) {
        try {
            return answersDAO.deleteByQuestionID(qID);
        } catch (SQLException e) {
            return false;
        }
    }
}
