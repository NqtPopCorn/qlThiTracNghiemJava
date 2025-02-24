
package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.ExamDAO;
import com.example.qlthitracnghiem.DAO.TestDAO;
import com.example.qlthitracnghiem.DTO.ExamDTO;
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
  public boolean createExam(TestDTO exam,int examNum) {
        try {
          
            

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
    public int update(TestDTO testDTO, int soDe) throws SQLException{
        return ExamDAO.update(testDTO,soDe);
    }
    public boolean isExCodeExistInResult(String exCode) throws SQLException{
        return ExamDAO.isExCodeExistInResult(exCode);
    }
    public int delete(String testCode) throws SQLException {
        return ExamDAO.delete(testCode);
    }
}
