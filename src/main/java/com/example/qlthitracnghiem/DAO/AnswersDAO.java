
package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.AnswersDTO;
import com.example.qlthitracnghiem.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AnswersDAO {
 
    public AnswersDTO getAnswerByID(int awID) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM answers WHERE awID = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, awID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AnswersDTO(
                    rs.getInt("awID"),
                    rs.getInt("qID"),
                    rs.getString("awContent"),
                    rs.getString("awPictures"),
                    rs.getInt("isRight"),
                    rs.getInt("awStatus")
                );
            }
        }
        return null;  // Trả về null nếu không tìm thấy
    }


    public ArrayList<AnswersDTO> getAll() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM answers";
        ArrayList<AnswersDTO> answersList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                answersList.add(new AnswersDTO(
                    rs.getInt("awID"),
                    rs.getInt("qID"),
                    rs.getString("awContent"),
                    rs.getString("awPictures"),
                    rs.getInt("isRight"),
                    rs.getInt("awStatus")
                ));
            }
        }
        return answersList;
    }


    public boolean delete(int awID) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "DELETE FROM answers WHERE awID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, awID);
            return ps.executeUpdate() > 0; // Trả về true nếu xóa thành công
        }
    }


    public boolean create(AnswersDTO answer) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO answers (qID, awContent, awPictures, isRight, awStatus) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, answer.getQID());
            ps.setString(2, answer.getAwContent());
            ps.setString(3, answer.getAwPictures());
            ps.setInt(4, answer.getIsRight());
            ps.setInt(5, answer.getAwStatus());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        }
    }


public boolean update(AnswersDTO answer) throws SQLException {
    Connection connection = DBConnection.getConnection();
    String sql = "UPDATE answers SET qID=?, awContent=?, awPictures=?, isRight=?, awStatus=? WHERE awID=?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, answer.getQID());
        ps.setString(2, answer.getAwContent());
        ps.setString(3, answer.getAwPictures());
        ps.setInt(4, answer.getIsRight());
        ps.setInt(5, answer.getAwStatus());
        ps.setInt(6, answer.getAwID());

        return ps.executeUpdate() > 0;
    }
}

// Hàm lấy danh sách đáp án theo ID câu hỏi
    public ArrayList<AnswersDTO> getAnswersByQuestionID(int qID) throws SQLException{
    Connection connection = DBConnection.getConnection();
    String sql = "SELECT * FROM answers WHERE qID = ?";
    ArrayList<AnswersDTO> answersList = new ArrayList<>();

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, qID); 

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                answersList.add(new AnswersDTO(
                    rs.getInt("awID"),
                    rs.getInt("qID"),
                    rs.getString("awContent"),
                    rs.getString("awPictures"),
                    rs.getInt("isRight"),
                    rs.getInt("awStatus")
                ));
            }
        }
    }
    return answersList;
}

    public boolean deleteByQuestionID(int qID) throws SQLException {
    Connection connection = DBConnection.getConnection();
    String sql = "DELETE FROM answers WHERE qID = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, qID);
        return ps.executeUpdate() > 0; // Trả về true nếu có ít nhất một bản ghi bị xóa
    }
}

}

