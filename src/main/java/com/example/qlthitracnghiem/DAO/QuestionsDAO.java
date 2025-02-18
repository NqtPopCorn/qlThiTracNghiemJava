
package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.QuestionsDTO;
import com.example.qlthitracnghiem.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuestionsDAO {
    
public ArrayList<QuestionsDTO> getAll() throws SQLException {
    // Get the database connection
    Connection connection = DBConnection.getConnection();

    // SQL query to fetch all questions
    String sql = "SELECT * FROM questions";

    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        ArrayList<QuestionsDTO> questions = new ArrayList<>();

        while (rs.next()) {
            questions.add(
                new QuestionsDTO(
                    rs.getInt("qID"),          
                    rs.getString("qContent"),    
                    rs.getString("qPictures"),  
                    rs.getInt("qTopicID"),       
                    rs.getInt("qLevel"),      
                    rs.getInt("qStatus")         
                )
            );
        }

        return questions;

    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }
}

// Hàm thêm câu hỏi mới
    public boolean create(QuestionsDTO question) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO questions (qContent, qPictures, qTopicID, qLevel, qStatus) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, question.getqContent());
            ps.setString(2, question.getqPicture());
            ps.setInt(3, question.getqTopicID());
            ps.setInt(4, question.getqLevel());
            ps.setInt(5, question.getqStatus());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        question.setqID(generatedKeys.getInt(1)); // Lấy ID vừa tạo
                    }
                }
            }
            return affectedRows > 0;
        }
    }

    
    //Hàm cập nhật câu hỏi
    public boolean update(QuestionsDTO question) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE questions SET qContent=?, qPictures=?, qTopicID=?, qLevel=?, qStatus=? WHERE qID=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, question.getqContent());
            ps.setString(2, question.getqPicture());
            ps.setInt(3, question.getqTopicID());
            ps.setInt(4, question.getqLevel());
            ps.setInt(5, question.getqStatus());
            ps.setInt(6, question.getqID());

            return ps.executeUpdate() > 0;
        }
    }
    
        //Hàm xóa câu hỏi
    public boolean delete(int qID) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "DELETE FROM questions WHERE qID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, qID);
            return ps.executeUpdate() > 0;
        }
    }
    
    public QuestionsDTO getQuestionByID(int qID) throws SQLException {
    Connection connection = DBConnection.getConnection();
    String sql = "SELECT * FROM questions WHERE qID = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, qID);  // Truyền giá trị ID vào câu lệnh SQL

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {  // Nếu tìm thấy dữ liệu
                return new QuestionsDTO(
                    rs.getInt("qID"),
                    rs.getString("qContent"),
                    rs.getString("qPictures"),
                    rs.getInt("qTopicID"),
                    rs.getInt("qLevel"),
                    rs.getInt("qStatus")
                );
            }
        }
    }
    return null;  // Trả về null nếu không tìm thấy câu hỏi
}

}
