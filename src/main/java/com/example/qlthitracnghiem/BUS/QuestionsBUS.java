package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.QuestionsDAO;
import com.example.qlthitracnghiem.DTO.QuestionsDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionsBUS {
    
    private static QuestionsBUS instance;

    public static QuestionsBUS getInstance() {
        if(instance == null)
            instance = new QuestionsBUS();
        return instance;
    }

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

    //lay 1 doi tuong cau hoi
    public QuestionsDTO getQuestionDTOById(Integer quesId) {
        try {

            return questionsDAO.getQuestionByID(quesId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Hoặc bạn có thể xử lý lỗi theo cách khác
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

    public int createInt(QuestionsDTO question) {
        try {
            return questionsDAO.createInt(question);
        } catch (SQLException e) {
            return -1;
        }
    }

    //phương thức tìm kiếm câu hỏi theo key
    public List<QuestionsDTO> find(String content, String key) {
        try {
            return questionsDAO.find(content, key);
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi để dễ debug
            return new ArrayList<>(); // Trả về danh sách rỗng nếu có lỗi
        }
    }

    //phương thức kiểm tra câu hỏi có tồn tại ko
    public boolean isQuestionExists(String content) throws SQLException {
        try {
            return questionsDAO.isQuestionExists(content);
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi để dễ debug
            return false;
        }
    }

}
