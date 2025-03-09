/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.ExamDTO;
import com.example.qlthitracnghiem.DTO.TestDTO;
import com.example.qlthitracnghiem.utils.ConvertUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.example.qlthitracnghiem.utils.DBConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;

public class ExamDAO {
    private static final int MAX_EXAMS = 10;
    private ArrayList<Character> exOrderList = new ArrayList<>(
            List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'));

    public int generateExams(TestDTO test, int soLuong) throws Exception {
        if (soLuong <= 0 || soLuong > MAX_EXAMS) {
            throw new IllegalArgumentException("Số lượng đề thi phải từ 1 đến " + MAX_EXAMS);
        }

        int currentCount = getNumExam(test.getTestCode());
        if (currentCount >= MAX_EXAMS) {
            throw new SQLException("Đã đạt tối đa " + MAX_EXAMS + " đề thi cho testCode: " + test.getTestCode());
        }

        int remainingCapacity = MAX_EXAMS - currentCount;
        int examsToGenerate = Math.min(soLuong, remainingCapacity);

        for (int i = 0; i < examsToGenerate; i++) {
            generateExam(test);
        }
        return examsToGenerate;
    }

    public int generateExam(TestDTO test) throws Exception {
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            int currentCount = getNumExam(test.getTestCode());
            if (currentCount >= MAX_EXAMS) {
                throw new SQLException("Đã đạt tối đa " + MAX_EXAMS + " đề thi cho testCode: " + test.getTestCode());
            }

            String exOrder = String.valueOf(exOrderList.get(currentCount));
            String exCode = test.getTestCode() + exOrder;

            // Get question IDs from test structure
            TestDAO testDAO = new TestDAO(connection);
            Map<Integer, int[]> topicStructures = testDAO.getTestStructure(test.getTestCode());

            JSONArray exQuesIDs = generateExQuesIDsFromStructure(connection, topicStructures);

            String insertExam = "INSERT INTO exams (testCode, exOrder, exCode, ex_quesIDs) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(insertExam)) {
                ps.setString(1, test.getTestCode());
                ps.setString(2, exOrder);
                ps.setString(3, exCode);
                ps.setString(4, exQuesIDs.toString());
                ps.executeUpdate();
            }

            connection.commit();
            return 1;
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private JSONArray generateExQuesIDsFromStructure(Connection connection, Map<Integer, int[]> topicStructures)
            throws SQLException {
        JSONArray exQuesIDs = new JSONArray();

        for (Map.Entry<Integer, int[]> entry : topicStructures.entrySet()) {
            int topicID = entry.getKey();
            int[] counts = entry.getValue(); // [easy, medium, difficult]

            // Get questions for each difficulty level
            List<Integer> easyQuestions = getQuestionIDsByLevel(connection, topicID, "easy", counts[0]);
            if (easyQuestions.size() < counts[0]) {
                throw new SQLException("Không đủ câu hỏi dễ cho topic " + topicID + ". Yêu cầu: " + counts[0]);
            }
            exQuesIDs.putAll(easyQuestions);

            List<Integer> mediumQuestions = getQuestionIDsByLevel(connection, topicID, "medium", counts[1]);
            if (mediumQuestions.size() < counts[1]) {
                throw new SQLException("Không đủ câu hỏi trung bình cho topic " + topicID + ". Yêu cầu: " + counts[1]);
            }
            exQuesIDs.putAll(mediumQuestions);

            List<Integer> difficultQuestions = getQuestionIDsByLevel(connection, topicID, "difficult", counts[2]);
            if (difficultQuestions.size() < counts[2]) {
                throw new SQLException("Không đủ câu hỏi khó cho topic " + topicID + ". Yêu cầu: " + counts[2]);
            }
            exQuesIDs.putAll(difficultQuestions);
        }

        return exQuesIDs;
    }

    private List<Integer> getQuestionIDsByLevel(Connection connection, int topicID, String level, int limit)
            throws SQLException {
        ArrayList<String> levels = new ArrayList<>(List.of("easy", "medium", "difficult"));
        if (!levels.contains(level)) {
            throw new IllegalArgumentException("level phải là 'easy', 'medium' hoặc 'difficult'");
        }
        List<Integer> questionIDs = new ArrayList<>();
        List<Integer> allTopicIDs = getAllTopicIDs(connection, topicID);

        if (!allTopicIDs.isEmpty()) {
            String questionSQL = "SELECT qID FROM questions WHERE qTopicID IN (" +
                    allTopicIDs.stream().map(String::valueOf).collect(Collectors.joining(",")) +
                    ") AND qLevel = ? AND qStatus = 1 ORDER BY RAND() LIMIT ?";
            try (PreparedStatement ps = connection.prepareStatement(questionSQL)) {
                ps.setInt(1, levels.indexOf(level) + 1);
                ps.setInt(2, limit);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    questionIDs.add(rs.getInt("qID"));
                }
            }
        }
        return questionIDs;
    }

    private List<Integer> getAllTopicIDs(Connection connection, int topicID) throws SQLException {
        List<Integer> topicIDs = new ArrayList<>();
        String sql = "WITH RECURSIVE subTopics AS (" +
                "    SELECT tpID FROM topics WHERE tpID = ?" +
                "    UNION ALL" +
                "    SELECT t.tpID FROM topics t" +
                "    INNER JOIN subTopics s ON t.tpParent = s.tpID" +
                ") SELECT tpID FROM subTopics";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, topicID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                topicIDs.add(rs.getInt("tpID"));
            }
        }
        return topicIDs;
    }

    private int getNumExam(String testCode) throws SQLException {
        String sql = "SELECT COUNT(*) as count FROM exams WHERE testCode = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, testCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }
            return 0;
        }
    }

    public boolean hasAExam(String testCode) throws SQLException {
        return getNumExam(testCode) > 0;
    }

    public int delete(String testCode) throws SQLException {
        Connection connection = DBConnection.getConnection();
        try {
            List<String> examCodes = getExamCodesByTestCode(testCode);

            for (String exCode : examCodes) {
                if (isExCodeExistInResult(exCode)) {
                    throw new SQLException("Đã có học sinh làm bài kiểm tra, không thể xóa.");
                }
            }
            // Xóa bảng exam
            String deleteExamsSQL = "DELETE FROM exams WHERE exCode = ?";
            try (PreparedStatement psExams = connection.prepareStatement(deleteExamsSQL)) {
                psExams.setString(1, testCode);
                psExams.executeUpdate();
            }
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
        }
    }

    public Map<String, String> getQuestionContent(int qID) throws Exception { // lấy nội dung câu hỏi và hình ảnh
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT q.qContent, q.qPictures FROM questions q JOIN exams e ON JSON_CONTAINS(e.ex_quesIDs, CAST(q.qID AS CHAR)) WHERE q.qID =? GROUP BY qID";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, qID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Map<String, String> questionData = new HashMap<>();
                questionData.put("qContent", rs.getString("qContent"));
                questionData.put("qPictures", rs.getString("qPictures")); // Lấy hình ảnh của câu hỏi
                return questionData;
            }
        }
        return null;
    }

    public List<Map<String, String>> getAnswerContent(int qID) throws Exception { // lấy nội dung câu trả lời và hình
                                                                                  // ảnh
        Connection connection = DBConnection.getConnection();
        List<Map<String, String>> awContents = new ArrayList<>();
        String sql = "SELECT aw.awContent, aw.awPictures, aw.awID FROM answers aw JOIN questions q ON aw.qID=q.qID WHERE q.qID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, qID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, String> answerData = new HashMap<>();
                answerData.put("awContent", rs.getString("awContent"));
                answerData.put("awPictures", rs.getString("awPictures")); // Lấy hình ảnh của câu trả lời
                answerData.put("awID", String.valueOf(rs.getInt("awID")));
                awContents.add(answerData);
            }
        }
        return awContents;
    }

    public List<Integer> getExQuesIDs(String testCode) throws Exception { // hàm này dùng để lấy exQuesIDs từ testCode
                                                                          // ------------2 hàm khác nhau nha---------
        Connection connection = DBConnection.getConnection();
        List<Integer> exQuesIDs = new ArrayList<>();
        String sql = "SELECT ex_quesIDs FROM exams WHERE testCode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, testCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {

                String jsonArray = rs.getString("ex_quesIDs");
                JSONArray json = new JSONArray(jsonArray);
                for (int i = 0; i < json.length(); i++) {
                    exQuesIDs.add(json.getInt(i));
                }
            }
        }
        return exQuesIDs;
    }

    public List<Integer> getExQuesIDsByExCode(String exCode) throws SQLException { // hàm này dùng để lấy exQuesIDs từ
                                                                                   // exCode
        Connection connection = DBConnection.getConnection();
        List<Integer> exQuesIDs = new ArrayList<>();
        String sql = "SELECT ex_quesIDs FROM exams WHERE exCode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, exCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String jsonArray = rs.getString("ex_quesIDs");
                JSONArray json = new JSONArray(jsonArray);
                for (int i = 0; i < json.length(); i++) {
                    exQuesIDs.add(json.getInt(i));
                }
            }
        } finally {
        }
        return exQuesIDs;
    }

    public static ArrayList findExCode(String keyword) throws SQLException {
        Connection connection = DBConnection.getConnection();

        String sql = "SELECT * FROM exams where exCode Like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            ArrayList<String> exCodeList = new ArrayList<>();
            while (rs.next()) {
                exCodeList.add(rs.getString("exCode"));
            }
            return exCodeList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ArrayList search(String keyword, String testCode) throws SQLException {
        Connection connection = DBConnection.getConnection();

        String sql = "SELECT * FROM exams where exCode Like ? and testCode=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, testCode);
            ResultSet rs = ps.executeQuery();
            ArrayList<String> exCodeList = new ArrayList<>();
            while (rs.next()) {
                exCodeList.add(rs.getString("exCode"));
            }
            return exCodeList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<String> getExamCodesByTestCode(String testCode) throws SQLException { // lấy excode để hiển thị mã đề
        List<String> examCodes = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT exCode FROM exams WHERE testCode = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, testCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                examCodes.add(rs.getString("exCode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return examCodes;
    }

    public ArrayList<ExamDTO> getAll() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM exams";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<ExamDTO> exams = new ArrayList<>();
            while (rs.next()) {
                exams.add(
                        new ExamDTO(
                                rs.getString("testCode"),
                                rs.getString("exOrder"),
                                rs.getString("exCode"),
                                ConvertUtil.convertJSONArrayToArrayString(new JSONArray(rs.getString("ex_quesIDs")))));
            }
            return exams;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
        }
    }

    public ExamDTO getExamDtoByExamCode(String examCode) throws SQLException {
        ExamDTO examDto = new ExamDTO();
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM exams WHERE exCode = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, examCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String questions = rs.getString("ex_quesIDs");
                String[] quesIDs = (questions != null)
                        ? ConvertUtil.convertJSONArrayToArrayString(new JSONArray(questions))
                        : new String[0];
                examDto.setTestCode(rs.getString("testCode"));
                examDto.setExOrder(rs.getString("exOrder"));
                examDto.setExCode(rs.getString("exCode"));
                examDto.setExCode(rs.getString("exCode"));
                examDto.setEx_quesIDs(quesIDs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
        }
        return examDto;
    }

    public boolean isExCodeExistInResult(String exCode) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT COUNT(*) FROM result WHERE exCode = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, exCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }

        return false;
    }

    public void deleteExamsByTestCode(String testCode) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "DELETE FROM exams WHERE testCode = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, testCode);
            ps.executeUpdate();
        }
    }

    public ExamDTO getExamByExCode(String exCode) throws SQLException {
        Connection connection = DBConnection.getConnection();

        String sql = "SELECT * FROM exams WHERE exCode = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, exCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JSONArray quesIDs = new JSONArray(rs.getString("ex_quesIDs"));
                List<Integer> exQuesIDs = new ArrayList<Integer>();
                JSONArray json = new JSONArray(rs.getString("ex_quesIDs"));
                for (int i = 0; i < json.length(); i++) {
                    exQuesIDs.add(json.getInt(i));
                }
                return new ExamDTO(
                        rs.getString("testCode"),
                        rs.getString("exOrder"),
                        rs.getString("exCode"),
                        exQuesIDs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    public ArrayList<String> getAllExCode() throws SQLException {
        Connection connection = DBConnection.getConnection();

        String sql = "SELECT DISTINCT exCode FROM exams ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<String> exCodeList = new ArrayList<>();
            while (rs.next()) {
                exCodeList.add(rs.getString("exCode"));
            }
            return exCodeList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String getTestCodeByExamCode(String examCode) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT testCode FROM exams WHERE exCode = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, examCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("testCode");
            }
        }
        return null;
    }
}