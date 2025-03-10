
package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.TopicsDTO;
import com.example.qlthitracnghiem.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TopicsDAO {
    public TopicsDTO getTopicByID(int tpID) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM topics WHERE tpID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, tpID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new TopicsDTO(
                        rs.getInt("tpID"),
                        rs.getString("tpTitle"),
                        rs.getInt("tpParent"),
                        rs.getInt("tpStatus"));
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public ArrayList<TopicsDTO> getAll() {
        String sql = "SELECT * FROM topics";
        ArrayList<TopicsDTO> topicsList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            if (connection == null) {
                throw new SQLException("Không thể kết nối đến database");
            }

            while (rs.next()) {
                topicsList.add(new TopicsDTO(
                        rs.getInt("tpID"),
                        rs.getString("tpTitle"),
                        rs.getInt("tpParent"),
                        rs.getInt("tpStatus")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TopicsDAO.class.getName()).log(Level.SEVERE, "Lỗi truy vấn topics", ex);
        }
        return topicsList;
    }

    public boolean delete(int tpID) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "DELETE FROM topics WHERE tpID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, tpID);
            return ps.executeUpdate() > 0; // Trả về true nếu xóa thành công
        }
    }

    public boolean create(TopicsDTO topic) throws SQLException {
        System.out.println("parent: " + topic.getTpParent());
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO topics (tpTitle, tpParent, tpStatus) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, topic.getTpTitle());

            // Nếu tpParent là null, đặt NULL vào cột thay vì số nguyên
            if (topic.getTpParent() == -1) {
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setInt(2, topic.getTpParent());
            }

            ps.setInt(3, topic.getTpStatus());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean update(TopicsDTO topic) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE topics SET tpTitle=?, tpParent=?, tpStatus=? WHERE tpID=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, topic.getTpTitle());
            if (topic.getTpParent() == null) {
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setInt(2, topic.getTpParent());
            }
            ps.setInt(2, topic.getTpParent());
            ps.setInt(3, topic.getTpStatus());
            ps.setInt(4, topic.getTpID());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean checkStatus(int tpID) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT tpStatus FROM topics WHERE tpID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, tpID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("tpStatus") == 1; // Trả về true nếu tpStatus = 1 (hoạt động)
            }
        }
        return false; // Trả về false nếu không tìm thấy hoặc tpStatus = 0 (không hoạt động)
    }

    public boolean isTitleExist(String tpTitle) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT COUNT(*) FROM topics WHERE tpTitle = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tpTitle);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu COUNT > 0 thì chủ đề đã tồn tại
            }
        }
        return false;
    }

    public int getIdByName(String tpTitle) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT tpID FROM topics WHERE tpTitle = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tpTitle);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("tpID"); // Trả về ID nếu tìm thấy
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }

    // sắp xếp theo tên
    public ArrayList<TopicsDTO> getAllSortedByName() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM topics ORDER BY tpTitle ASC"; // Sắp xếp theo bảng chữ cái
        ArrayList<TopicsDTO> topicsList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                topicsList.add(new TopicsDTO(
                        rs.getInt("tpID"),
                        rs.getString("tpTitle"),
                        rs.getInt("tpParent"),
                        rs.getInt("tpStatus")));
            }
        }
        return topicsList;
    }

    public ArrayList<TopicsDTO> getTestTopic(int testID) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM topics WHERE tpID IN (SELECT topicID FROM test_topic WHERE testID = ?)";
        ArrayList<TopicsDTO> topicsList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, testID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                topicsList.add(new TopicsDTO(
                        rs.getInt("tpID"),
                        rs.getString("tpTitle"),
                        rs.getInt("tpParent"),
                        rs.getInt("tpStatus")));
            }
        }
        return topicsList;
    }

    public String getTopicNameById(int topicId) {
        // Implement this to return topic name from database
        // Here's a sample implementation:
        String sql = "SELECT tpTitle FROM topics WHERE tpID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, topicId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("tpTitle");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
