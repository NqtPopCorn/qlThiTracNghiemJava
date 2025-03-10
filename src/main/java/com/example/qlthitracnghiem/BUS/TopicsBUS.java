package com.example.qlthitracnghiem.BUS;

import com.example.qlthitracnghiem.DAO.TopicsDAO;
import com.example.qlthitracnghiem.DTO.TopicsDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class TopicsBUS {
    private TopicsDAO topicsDAO;
    private static TopicsBUS instance;

    // Constructor
    public TopicsBUS() {
        topicsDAO = new TopicsDAO();
    }

    // create instance
    public static TopicsBUS getInstance() {
        if (instance == null)
            instance = new TopicsBUS();
        return instance;
    }

    // Hàm kiểm tra trạng thái của chủ đề dựa vào tpID
    public boolean checkStatus(int tpID) {
        try {
            return topicsDAO.checkStatus(tpID);
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Mặc định trả về false nếu có lỗi
        }
    }

    // Phương thức để lấy tất cả câu hỏi
    public ArrayList<TopicsDTO> getAll() {
        return topicsDAO.getAll(); // Gọi phương thức getAll() từ QuestionDAO
    }

    public boolean isTitleExist(String tpTitle) {
        try {
            return topicsDAO.isTitleExist(tpTitle);
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    public int getTopicIdByName(String tpTitle) {
        try {
            TopicsDAO dao = new TopicsDAO();
            return dao.getIdByName(tpTitle);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean create(TopicsDTO topic) {
        try {
            return topicsDAO.create(topic);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public TopicsDTO getTopicByID(int tpID) {
        try {
            return topicsDAO.getTopicByID(tpID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(TopicsDTO topic) {
        try {
            return topicsDAO.update(topic);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<TopicsDTO> getAllSortedByName() {
        try {
            return topicsDAO.getAllSortedByName();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Trả về danh sách rỗng nếu có lỗi
        }
    }

    public ArrayList<TopicsDTO> getTestTopic(int testID) {
        try {
            return topicsDAO.getTestTopic(testID);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Trả về danh sách rỗng nếu có lỗi
        }
    }

    public String getTopicNameById(int tpID) {
        try {
            return topicsDAO.getTopicNameById(tpID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
