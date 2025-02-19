
package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.ExamDAO;
import java.util.List;

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

  public List<String> getAnswerContent(int qID) throws Exception {
    return ExamDAO.getAnswerContent(qID);
  }

  public String getQuestionContent(int qID) throws Exception {
    return ExamDAO.getQuestionContent(qID);
  }

  public List<Integer> getExQuesIDs(String testCode) throws Exception {
    return ExamDAO.getExQuesIDs(testCode);
  }

}
