
package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.QuestionsDTO;
import com.example.qlthitracnghiem.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
