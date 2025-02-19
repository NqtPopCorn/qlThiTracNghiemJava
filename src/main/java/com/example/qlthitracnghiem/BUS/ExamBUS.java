
package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.ExamDAO;
import com.example.qlthitracnghiem.DTO.TestDTO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

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

  public List<Map<String, String>> getAnswerContent(int qID) throws Exception {
    return ExamDAO.getAnswerContent(qID);
  }

  public Map<String, String> getQuestionContent(int qID) throws Exception {
    return ExamDAO.getQuestionContent(qID);
  }

  public List<Integer> getExQuesIDs(String testCode) throws Exception {
    return ExamDAO.getExQuesIDs(testCode);
  }
  public boolean createExam(String title, int topic, int timeLimit, int totalQuestions,
                             int easyQuestions, int mediumQuestions, int hardQuestions,
                             String attemptLimit, int examNum,LocalDateTime formattedDate) {
        try {
          
            TestDTO exam = new TestDTO();
            exam.setTestTitle(title);
            exam.setTestTime(timeLimit);
            exam.setTpID(topic); 
            exam.setNum_easy(easyQuestions);
            exam.setNum_medium(mediumQuestions);
            exam.setNum_diff(hardQuestions);
            exam.setTestLimit(Integer.parseInt(attemptLimit)); 
            exam.setTestDate(formattedDate); 

            int result = ExamDAO.create(exam, examNum);

            // Trả về true nếu tạo thành công
            return result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public List<String> getExamCode(String testcode) throws SQLException{
        return ExamDAO.getExamCodesByTestCode(testcode);
    }
    public List<Integer> getExQuesIDsByExCode(String exCode) throws SQLException, Exception {
        return ExamDAO.getExQuesIDsByExCode(exCode);
    }
}
