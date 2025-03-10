package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.ExamDAO;
import com.example.qlthitracnghiem.DTO.ExamDTO;
import com.example.qlthitracnghiem.DTO.TestDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExamBUS {
    public static final int ACTION_SUCCESS = 1;
    public static final int ACTION_ERROR = -9999;

    private ExamDAO ExamDAO;

    public ExamBUS() {
        ExamDAO = new ExamDAO();
    }

    public ExamBUS(ExamDAO ExamDAO) {
        this.ExamDAO = ExamDAO;
    }

    public ArrayList<String> searchExCode(String keyword) throws Exception {
        return ExamDAO.findExCode(keyword);
    }

    public List<Map<String, String>> getAnswerContent(int qID) throws Exception {
        return ExamDAO.getAnswerContent(qID);
    }

    public Map<String, String> getQuestionContent(int qID) throws Exception {
        return ExamDAO.getQuestionContent(qID);
    }

    public List<Integer> getExQuesIDs(String testCode) throws Exception {
        return ExamDAO.getExQuesIDs(testCode);
    }

    public ExamDTO getExamByExCode(String exCode) throws SQLException {
        return ExamDAO.getExamByExCode(exCode);
    }

    public List<String> getExamCode(String testcode) throws SQLException {
        return ExamDAO.getExamCodesByTestCode(testcode);
    }

    public List<String> getExamCodesByTestCode(String testcode) throws SQLException {
        return ExamDAO.getExamCodesByTestCode(testcode);
    }

    public ArrayList<String> getAllExCode() throws SQLException {
        return ExamDAO.getAllExCode();
    }

    public List<Integer> getExQuesIDsByExCode(String exCode) throws SQLException, Exception {
        return ExamDAO.getExQuesIDsByExCode(exCode);
    }

    // public int update(TestDTO testDTO, int soDe) throws SQLException {
    // return ExamDAO.update(testDTO, soDe);
    // }

    public boolean isExCodeExistInResult(String exCode) throws SQLException {
        return ExamDAO.isExCodeExistInResult(exCode);
    }

    public int delete(String testCode) throws SQLException {
        return ExamDAO.delete(testCode);
    }

    public ArrayList<ExamDTO> getAll() throws SQLException {
        return ExamDAO.getAll();
    }

    public int generate(TestDTO testDTO, int soDe) throws Exception {
        return ExamDAO.generateExams(testDTO, soDe);
    }

    public ArrayList<String> search(String keyword, String testCode) throws Exception {
        return ExamDAO.search(keyword, testCode);
    }

    public String getTestCodeByExamCode(String examCode) throws SQLException {
        return ExamDAO.getTestCodeByExamCode(examCode);
    }
}
