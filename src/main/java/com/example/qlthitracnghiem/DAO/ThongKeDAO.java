/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.AnswersDTO;
import com.example.qlthitracnghiem.DTO.ExamDTO;
import com.example.qlthitracnghiem.DTO.QuestionsDTO;
import com.example.qlthitracnghiem.DTO.ResultDTO;
import com.example.qlthitracnghiem.DTO.TestDTO;
import com.example.qlthitracnghiem.DTO.TopicsDTO;
import com.example.qlthitracnghiem.DTO.UserDTO;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface ThongKeDAO {
    
    public List<UserDTO> getUserList();
    public List<ExamDTO> getExamList();
    public List<TopicsDTO> getTopicList();
    public List<AnswersDTO> getAnswerList();
    public List<ResultDTO> getResultList();
    public List<TestDTO> getTestList();
    public int getSoLuongThiSinhLamBaiByExamCode(String examCode);
    public int getMaxScoreByExamCode(String examCode);
    public int getMinScoreByExamCode(String examCode);
    public int getSoLuongThiSinhDatHoacKhongDatByExamCode(String examCode, String type);
    public int getSoThiSinhXuatSacByExamCode(String examCode);
    public List<QuestionsDTO> getQuestionList();
    public List<ResultDTO> getResultDtoByUserID(String userID);
    public ExamDTO getExamDtoByExamCode(String examCode);
    public List<AnswersDTO> getAnswerDtoByQuestionID(String questionID);
    public QuestionsDTO getQuestionDtoByQuestionID(String questionID);
}
