
package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.QuestionsDAO;
import com.example.qlthitracnghiem.DTO.QuestionsDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionsBUS {

    private final QuestionsDAO questionsDAO;

    public QuestionsBUS() {
        questionsDAO = new QuestionsDAO();
    }

    // phương thức update
    public boolean update(QuestionsDTO question) {
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

    // Phương thức lấy danh sách câu hỏi theo topic
    public ArrayList<QuestionsDTO> getQuestionsByTopicID(int topicID) {
        try {
            return questionsDAO.getQuestionsByTopicID(topicID);
        } catch (SQLException e) {
            return null;
        }
    }

    // phương thức lấy câu hỏi theo nội dung
    public QuestionsDTO getQuestionsByContent(String content) {
        try {
            return questionsDAO.getQuestionByContent(content);
        } catch (SQLException e) {
            return null;
        }
    }

}
