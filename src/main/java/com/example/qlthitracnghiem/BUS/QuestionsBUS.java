
package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.QuestionsDAO;
import com.example.qlthitracnghiem.DTO.QuestionsDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionsBUS {
    
    private QuestionsDAO questionsDAO;
    
      public QuestionsBUS() {
        questionsDAO = new QuestionsDAO();
    }
      
      // Phương thức để lấy tất cả câu hỏi
    public ArrayList<QuestionsDTO> getAll() {
        try {
            return questionsDAO.getAll(); // Gọi phương thức getAll() từ QuestionDAO
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Hoặc bạn có thể xử lý lỗi theo cách khác
        }
    }
}
