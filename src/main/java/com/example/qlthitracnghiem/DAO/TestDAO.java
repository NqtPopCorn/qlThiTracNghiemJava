// TestDAO.java
package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.TestDTO;
import com.example.qlthitracnghiem.utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestDAO {
    private Connection connection;

    public TestDAO() {
        connection = DBConnection.getConnection();
    }

    public TestDAO(Connection connection) {
        this.connection = connection;
    }

    public void createTest(TestDTO test) throws SQLException {
        String sql = "INSERT INTO test (testCode, testTitle, testTime, testLimit, testDate, testStatus) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, test.getTestCode());
            ps.setString(2, test.getTestTitle());
            ps.setInt(3, test.getTestTime());
            ps.setInt(4, test.getTestLimit());
            ps.setString(5, test.getTestDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
            ps.setInt(6, 1); // default status = 1 (active)
            ps.executeUpdate();
        }
    }

    public void createTestStructure(String testCode, int tpID, int numEasy, int numMedium, int numDiff)
            throws SQLException {
        String sql = "INSERT INTO test_structure (testCode, tpID, num_easy, num_medium, num_diff) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, testCode);
            ps.setInt(2, tpID);
            ps.setInt(3, numEasy);
            ps.setInt(4, numMedium);
            ps.setInt(5, numDiff);
            ps.executeUpdate();
        }
    }

    public ArrayList<TestDTO> getAllTest() {
        ArrayList<TestDTO> tests = new ArrayList<>();
        String sql = "SELECT * FROM test";

        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                TestDTO test = new TestDTO();
                test.setTestID(rs.getInt("testID"));
                test.setTestCode(rs.getString("testCode"));
                test.setTestTitle(rs.getString("testTitle"));
                test.setTestTime(rs.getInt("testTime"));
                test.setTestLimit(rs.getInt("testLimit"));
                // test.setTestDate(LocalDateTime.parse(rs.getString("testDate"),
                // DateTimeFormatter.ISO_LOCAL_DATE));
                test.setTestDate(rs.getDate("testDate").toLocalDate().atStartOfDay());
                test.setTestStatus(rs.getInt("testStatus"));
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tests;
    }

    public TestDTO getTestByCode(String testCode) {
        String sql = "SELECT * FROM test WHERE testCode = ?";
        TestDTO test = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, testCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    test = new TestDTO();
                    test.setTestID(rs.getInt("testID"));
                    test.setTestCode(rs.getString("testCode"));
                    test.setTestTitle(rs.getString("testTitle"));
                    test.setTestTime(rs.getInt("testTime"));
                    test.setTestLimit(rs.getInt("testLimit"));
                    // Parse date string and convert to LocalDateTime
                    String dateStr = rs.getString("testDate");
                    LocalDate localDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
                    // Add default time (midnight) to convert to LocalDateTime
                    test.setTestDate(localDate.atStartOfDay());
                    test.setTestStatus(rs.getInt("testStatus"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return test;
    }

    public ArrayList<TestDTO> search(String keyword, int status) {
        ArrayList<TestDTO> tests = new ArrayList<>();
        String sql = "SELECT * FROM test WHERE testStatus = ? and (testTitle LIKE ? OR testCode LIKE ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, status);
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TestDTO test = new TestDTO();
                    test.setTestID(rs.getInt("testID"));
                    test.setTestCode(rs.getString("testCode"));
                    test.setTestTitle(rs.getString("testTitle"));
                    test.setTestTime(rs.getInt("testTime"));
                    test.setTestLimit(rs.getInt("testLimit"));
                    test.setTestDate(rs.getDate("testDate").toLocalDate().atStartOfDay());
                    test.setTestStatus(rs.getInt("testStatus"));
                    tests.add(test);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tests;
    }

    public ArrayList<TestDTO> search(String keyword) {
        ArrayList<TestDTO> tests = new ArrayList<>();
        String sql = "SELECT * FROM test WHERE testTitle LIKE ? OR testCode LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TestDTO test = new TestDTO();
                    test.setTestID(rs.getInt("testID"));
                    test.setTestCode(rs.getString("testCode"));
                    test.setTestTitle(rs.getString("testTitle"));
                    test.setTestTime(rs.getInt("testTime"));
                    test.setTestLimit(rs.getInt("testLimit"));
                    test.setTestDate(rs.getDate("testDate").toLocalDate().atStartOfDay());
                    test.setTestStatus(rs.getInt("testStatus"));
                    tests.add(test);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tests;
    }

    public int deleteTest(String testCode) {
        String sql = "DELETE FROM test WHERE testCode = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, testCode);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void updateTest(TestDTO test) throws SQLException {
        String sql = "UPDATE test SET testTitle = ?, testTime = ?, testLimit = ?, testDate = ?, testStatus = ? " +
                "WHERE testCode = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, test.getTestTitle());
            ps.setInt(2, test.getTestTime());
            ps.setInt(3, test.getTestLimit());
            ps.setString(4, test.getTestDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
            ps.setInt(5, test.getTestStatus());
            ps.setString(6, test.getTestCode());
            ps.executeUpdate();
        }
    }

    public void deleteTestStructure(String testCode) throws SQLException {
        String sql = "DELETE FROM test_structure WHERE testCode = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, testCode);
            ps.executeUpdate();
        }
    }

    public Map<Integer, int[]> getTestStructure(String testCode) throws SQLException {
        Map<Integer, int[]> structure = new HashMap<>();
        String sql = "SELECT * FROM test_structure WHERE testCode = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, testCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int[] counts = new int[] {
                        rs.getInt("num_easy"),
                        rs.getInt("num_medium"),
                        rs.getInt("num_diff")
                };
                structure.put(rs.getInt("tpID"), counts);
            }
        }
        System.out.println("get structure: " + testCode + ": " + structure);
        return structure;
    }
}