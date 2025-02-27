/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.ThongKeDAO;
import com.example.qlthitracnghiem.DAO.impl.ThongKeDAOImpl;
import com.example.qlthitracnghiem.DTO.AnswersDTO;
import com.example.qlthitracnghiem.DTO.ExamDTO;
import com.example.qlthitracnghiem.DTO.QuestionsDTO;
import com.example.qlthitracnghiem.DTO.ResultDTO;
import com.example.qlthitracnghiem.DTO.TestDTO;
import com.example.qlthitracnghiem.DTO.TopicsDTO;
import com.example.qlthitracnghiem.DTO.UserDTO;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class ThongKeBUS {

    private static ThongKeBUS instance;

    private ThongKeDAO thongKeDao = new ThongKeDAOImpl();

    private List<UserDTO> userDto;
    private List<TopicsDTO> topicDto;
    private List<ExamDTO> examDto;
    private List<ResultDTO> resultDto;
    private List<TestDTO> testDto;
    private List<AnswersDTO> answerDto;
    private List<QuestionsDTO> questionDto;

    public ThongKeBUS() {
        try {
            loadDataForAllList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ThongKeBUS getInstance() {
        if (instance == null)
            instance = new ThongKeBUS();
        return instance;
    }

    public void loadDataForAllList() throws Exception {
        userDto = thongKeDao.getUserList();
        topicDto = thongKeDao.getTopicList();
        examDto = thongKeDao.getExamList();
        resultDto = thongKeDao.getResultList();
    }

    public List<TestDTO> getTestListBus() {
        if (testDto == null)
            testDto = thongKeDao.getTestList();
        return testDto;
    }

    public List<UserDTO> getUserListBus() {
        if (userDto == null)
            userDto = thongKeDao.getUserList();
        return userDto;
    }

    public List<TopicsDTO> getTopicListBus() {
        if (topicDto == null)
            topicDto = thongKeDao.getTopicList();
        return topicDto;
    }

    public List<ExamDTO> getExamListBus() {
        if (examDto == null)
            examDto = thongKeDao.getExamList();
        return examDto;
    }

    public List<ResultDTO> getResultListBus() {
        if (resultDto == null)
            resultDto = thongKeDao.getResultList();
        return resultDto;
    }

    public List<QuestionsDTO> getQuestionListBus() {
        if (questionDto == null)
            questionDto = thongKeDao.getQuestionList();
        return questionDto;
    }

    public List<AnswersDTO> getAnswerListBus() {
        if (answerDto == null)
            answerDto = thongKeDao.getAnswerList();
        return answerDto;
    }

    public boolean searchString(String text, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return true;
        }
        text = text.toLowerCase();
        keyword = keyword.toLowerCase();

        return text.contains(keyword);
    }

    public List<UserDTO> searchUserByUserIDAndUserFullName(String keyword) {
        List<UserDTO> resultFilter = new ArrayList<UserDTO>();
        for (UserDTO user : userDto) {
            if (searchString(String.valueOf(user.getUserID()), keyword)
                    || searchString(user.getUserFullName(), keyword)) {
                resultFilter.add(user);
            }
        }
        return resultFilter;
    }

    public List<TestDTO> searchTestByTestCodeAndTestTitle(String keyword) {
        List<TestDTO> resultFilter = new ArrayList<TestDTO>();
        for (TestDTO test : testDto) {
            if (searchString(String.valueOf(test.getTestCode()), keyword)
                    || searchString(test.getTestTitle(), keyword)) {
                System.out.println("Tim thay");
                resultFilter.add(test);
            }
        }
        return resultFilter;
    }

    public List<String> getListExamByTestCode(String testCode) throws SQLException {
        List<String> result = new ArrayList<>();
        if (!testCode.isEmpty()) {
            result = new ExamBUS().getExamCode(testCode);
        }
        return result;
    }

    public int getSoLuongThiSinhLamBaiByTestCode(String testCode) throws SQLException {
        List<String> examCodes = new ArrayList<>();
        examCodes = this.getListExamByTestCode(testCode);
        if (examCodes.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (String examCode : examCodes) {
            sum += thongKeDao.getSoLuongThiSinhLamBaiByExamCode(examCode);
        }
        return sum;
    }

    public int getMaxScoreByTestCode(String testCode) throws SQLException {
        List<String> examCodes = new ArrayList<>();
        examCodes = this.getListExamByTestCode(testCode);
        if (examCodes.isEmpty()) {
            return 0;
        }
        int max = 0;
        for (String examCode : examCodes) {
            max = Math.max(max, thongKeDao.getMaxScoreByExamCode(examCode));
        }
        return max;
    }

    public int getMinScoreByTestCode(String testCode) throws SQLException {
        List<String> examCodes = new ArrayList<>();
        examCodes = this.getListExamByTestCode(testCode);
        if (examCodes.isEmpty()) {
            return 0;
        }
        int min = 0;
        for (String examCode : examCodes) {
            min = Math.min(min, thongKeDao.getMaxScoreByExamCode(examCode));
        }
        return min;
    }

    public int getSoLuongThiSinhDatHoacKhongDatByTestCode(String testCode, String type) throws SQLException {
        List<String> examCodes = new ArrayList<>();
        examCodes = this.getListExamByTestCode(testCode);
        if (examCodes.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (String examCode : examCodes) {
            count += thongKeDao.getSoLuongThiSinhDatHoacKhongDatByExamCode(examCode, type);
        }
        return count;
    }

    public int getSoThiSinhXuatSacByTestCode(String testCode) throws SQLException {
        List<String> examCodes = new ArrayList<>();
        examCodes = this.getListExamByTestCode(testCode);
        if (examCodes.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (String examCode : examCodes) {
            count += thongKeDao.getSoThiSinhXuatSacByExamCode(examCode);
        }
        return count;
    }

    public int getSoLuongThiSinhDatHoacKhongDatByExamCode(String examCode, String type) throws SQLException {
        return thongKeDao.getSoLuongThiSinhDatHoacKhongDatByExamCode(examCode, type);
    }

    public int getMaxScoreByExamCode(String examCode) throws SQLException {
        return thongKeDao.getMaxScoreByExamCode(examCode);
    }

    public int getMinScoreByExamCode(String examCode) throws SQLException {
        return thongKeDao.getMaxScoreByExamCode(examCode);
    }

    public int getSoThiSinhXuatSacByExamCode(String examCode) throws SQLException {
        return thongKeDao.getSoThiSinhXuatSacByExamCode(examCode);
    }

    public List<String> getExamListByTestCodeAndExamCode(String testCode, String examCode) {
        List<String> result = new ArrayList<>();
        for (ExamDTO exam : examDto) {
            if (searchString(exam.getTestCode(), testCode) && searchString(exam.getExCode(), examCode)) {
                result.add(exam.getExCode());
            }
        }
        return result;
    }

    public List<ResultDTO> getResultDtoByUserID(String userID) {
        return thongKeDao.getResultDtoByUserID(userID);
    }

    public ExamDTO getExamDtoByExamCode(String examCode) {
        return thongKeDao.getExamDtoByExamCode(examCode);
    }

    public List<AnswersDTO> getAnswerDtoByQuestionID(String questionID) {
        return thongKeDao.getAnswerDtoByQuestionID(questionID);
    }

    public QuestionsDTO getQuestionDtoByQuestionID(String questionID) {
        return thongKeDao.getQuestionDtoByQuestionID(questionID);
    }

    public ResultDTO searchResultDtoByExamCode(List<ResultDTO> resultList, String examCode) {
        for (ResultDTO result : resultList) {
            if (result.getExCode().equals(examCode))
                return result;
        }
        return null;
    }
}
