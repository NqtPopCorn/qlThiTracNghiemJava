/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.DAO.impl;

import com.example.qlthitracnghiem.DAO.ResultDAOImpl;
import com.example.qlthitracnghiem.BUS.AnswersBUS;
import com.example.qlthitracnghiem.BUS.ExamBUS;
import com.example.qlthitracnghiem.BUS.QuestionsBUS;
import com.example.qlthitracnghiem.BUS.TestBUS;
import com.example.qlthitracnghiem.BUS.TopicsBUS;
import com.example.qlthitracnghiem.BUS.UserBUS;
import com.example.qlthitracnghiem.DAO.ThongKeDAO;
import com.example.qlthitracnghiem.DTO.AnswersDTO;
import com.example.qlthitracnghiem.DTO.ExamDTO;
import com.example.qlthitracnghiem.DTO.QuestionsDTO;
import com.example.qlthitracnghiem.DTO.ResultDTO;
import com.example.qlthitracnghiem.DTO.TestDTO;
import com.example.qlthitracnghiem.DTO.TopicsDTO;
import com.example.qlthitracnghiem.DTO.UserDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class ThongKeDAOImpl implements ThongKeDAO {

    private ResultDAOImpl resultDao = new ResultDAOImpl();

    @Override
    public List<UserDTO> getUserList() {
        try {
            List<UserDTO> users = UserBUS.getInstance().getAll();
            return users;
        } catch (Exception ex) {
            Logger.getLogger(ThongKeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return List.of();
        }
    }

    @Override
    public List<ExamDTO> getExamList() {
        try {
            List<ExamDTO> examList = new ExamBUS().getAll();
            return examList;
        } catch (Exception ex) {
            Logger.getLogger(ThongKeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return List.of();
        }
    }

    @Override
    public List<TopicsDTO> getTopicList() {
        try {
            List<TopicsDTO> topicList = TopicsBUS.getInstance().getAll();
            return topicList;
        } catch (Exception ex) {
            Logger.getLogger(ThongKeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return List.of();
        }
    }

    @Override
    public List<AnswersDTO> getAnswerList() {
        try {
            List<AnswersDTO> answersDto = AnswersBUS.getInstance().getAll();
            return answersDto;
        } catch (Exception ex) {
            Logger.getLogger(ThongKeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return List.of();
        }
    }

    @Override
    public List<ResultDTO> getResultList() {
        try {
            List<ResultDTO> resultDtos = resultDao.getAll();
            return resultDtos;
        } catch (Exception ex) {
            Logger.getLogger(ThongKeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return List.of();
        }
    }

    @Override
    public List<TestDTO> getTestList() {
        try {
            List<TestDTO> testDtos = TestBUS.getInstance().getAll();
            return testDtos;
        } catch (Exception ex) {
            Logger.getLogger(ThongKeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return List.of();
        }
    }

    @Override
    public int getSoLuongThiSinhLamBaiByExamCode(String examCode) {
        return resultDao.getSoLuongThiSinhByExamCode(examCode);
    }

    @Override
    public int getMaxScoreByExamCode(String examCode) {
        return resultDao.getMaxScoreByExamCode(examCode);
    }

    @Override
    public int getMinScoreByExamCode(String examCode) {
        return resultDao.getMinScoreByExamCode(examCode);
    }

    @Override
    public int getSoLuongThiSinhDatHoacKhongDatByExamCode(String examCode, String type) {
        return resultDao.getSoLuongThiSinhDatHoacKhongDatByExamCode(examCode, type);
    }

    @Override
    public int getSoThiSinhXuatSacByExamCode(String examCode) {
        return resultDao.getSoLuongThiSinhXuatSacByExamCode(examCode);
    }

    @Override
    public List<QuestionsDTO> getQuestionList() {
        try {
            List<QuestionsDTO> questionDto = QuestionsBUS.getInstance().getAll();
            return questionDto;
        } catch (Exception ex) {
            Logger.getLogger(ThongKeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return List.of();
        }
    }

    @Override
    public List<ResultDTO> getResultDtoByUserID(String userID) {
        try {
            List<ResultDTO> resultDto = resultDao.getResultListByUserID(userID);
            return resultDto;
        } catch (Exception ex) {
            Logger.getLogger(ThongKeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return List.of();
        }
    }

    @Override
    public ExamDTO getExamDtoByExamCode(String examCode) {
        try {
            return new ExamBUS().getExamByExCode(examCode);
        } catch (Exception ex) {
            Logger.getLogger(ThongKeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<AnswersDTO> getAnswerDtoByQuestionID(String questionID) {
        try {
            return AnswersBUS.getInstance().getAnswerByQuestionID(questionID);
        } catch (Exception ex) {
            Logger.getLogger(ThongKeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return List.of();
        }
    }

    @Override
    public QuestionsDTO getQuestionDtoByQuestionID(String questionID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
