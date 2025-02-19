
package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.QuestionsDAO;
import com.example.qlthitracnghiem.DTO.QuestionsDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionsBUS {
    
    private final QuestionsDAO questionsDAO;
    
      public QuestionsBUS() {
        questionsDAO = new QuestionsDAO();
    }
      
      //phương thức update
      public boolean update(QuestionsDTO question){
           try {
            return questionsDAO.update(question); // Gọi phương thức getAll() từ QuestionDAO
        } catch (SQLException e) {
            return false; // Hoặc bạn có thể xử lý lỗi theo cách khác
        }
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
    
    //Phương thức lấy danh sách câu hỏi theo topic
        public ArrayList<QuestionsDTO> getQuestionsByTopicID(int topicID) {
            try{
            return questionsDAO.getQuestionsByTopicID(topicID);
            } catch (SQLException e){
            return null;
            }
        }
        
    //phương thức lấy câu hỏi theo nội dung
         public QuestionsDTO getQuestionsByContent(String content) {
            try{
            return questionsDAO.getQuestionByContent(content);
            } catch (SQLException e){
            return null;
            }
}
         
         public int create(QuestionsDTO question) {
            try{
            return questionsDAO.create(question);
            } catch (SQLException e){
            return -1;
            }
              }
        
         public List<QuestionsDTO> find(String content, String key) {        
    try {
        return questionsDAO.find(content, key);
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi để dễ debug
        return new ArrayList<>(); // Trả về danh sách rỗng nếu có lỗi
    }
}
         
}
