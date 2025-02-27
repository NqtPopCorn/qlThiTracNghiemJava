/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.DAO;

import com.example.qlthitracnghiem.DTO.ExamDTO;
import com.example.qlthitracnghiem.DTO.TestDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.example.qlthitracnghiem.utils.DBConnection;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

// chưa sửa lại mấy phương thức create,update,delete 
// cần gì thì thêm vào nha
public class ExamDAO {
    private int currentIndex = 0;

    public int create(TestDTO exam, int soDe) throws SQLException {
        Connection connection = DBConnection.getConnection();
        try {
            // tạo testCode mới
            String newTestCode = generateNewTestCode(connection);

            String insertTestSQL = "INSERT INTO test (testCode, testTitle, testTime, tpID, num_easy, num_medium, num_diff, testLimit, testDate, testStatus) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement psTest = connection.prepareStatement(insertTestSQL)) {
                psTest.setString(1, newTestCode);
                psTest.setString(2, exam.getTestTitle());
                psTest.setInt(3, exam.getTestTime());
                psTest.setInt(4, exam.getTpID());
                psTest.setInt(5, exam.getNum_easy());
                psTest.setInt(6, exam.getNum_medium());
                psTest.setInt(7, exam.getNum_diff());
                psTest.setInt(8, exam.getTestLimit());
                psTest.setTimestamp(9, Timestamp.valueOf(exam.getTestDate()));
                psTest.setInt(10, 1);
                psTest.executeUpdate();
            }
            generateExams(connection, newTestCode, exam, soDe);

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Không đủ câu hỏi để tạo đề thi. Vui lòng thêm câu hỏi.");
        }
    }

    private void generateExams(Connection connection, String testCode, TestDTO exam, int soDe) throws SQLException {
        String insertExamSQL = "INSERT INTO exams (testCode, exOrder, exCode, ex_quesIDs) VALUES (?, ?, ?, ?)";

        // Reset currentIndex về 0 trước khi bắt đầu tạo đề thi
        currentIndex = 0;

        // Lấy một bộ câu hỏi duy nhất
        JSONArray baseExQuesIDs = generateExQuesIDs(connection, exam.getTpID(), exam.getNum_easy(),
                exam.getNum_medium(), exam.getNum_diff());
        System.err.println("baseExquesIDs" + baseExQuesIDs);
        try (PreparedStatement psExam = connection.prepareStatement(insertExamSQL)) {
            for (int i = 1; i <= soDe; i++) {
                String exOrder = generateRandomExOrder();
                String exCode = testCode + exOrder;
                System.err.println("ExCode: " + exCode);

                // Tạo một bản sao của baseExQuesIDs và xáo trộn thứ tự
                JSONArray shuffledExQuesIDs = new JSONArray(baseExQuesIDs.toString()); // Tạo bản sao
                shuffleJSONArray(shuffledExQuesIDs); // Xáo trộn thứ tự
                System.err.println("shuffledExQuesIDs: " + shuffledExQuesIDs);

                psExam.setString(1, testCode);
                psExam.setString(2, exOrder);
                psExam.setString(3, exCode);
                psExam.setString(4, shuffledExQuesIDs.toString());
                psExam.addBatch();
                currentIndex++;
            }
            psExam.executeBatch();
        }
    }

    private void shuffleJSONArray(JSONArray jsonArray) { // đổi thành list => xáo trộn => tạo 1 mảng json mới => xóa
                                                         // mảng cũ đi thay vô đó là mảng json vừa mới đc xáo trộn
        // Chuyển JSONArray thành danh sách
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.get(i));
        }

        // Xáo trộn danh sách
        Collections.shuffle(list);

        // Tạo một JSONArray mới từ danh sách đã xáo trộn
        JSONArray shuffledArray = new JSONArray(list);

        // Xóa toàn bộ phần tử trong JSONArray cũ và thêm lại từ JSONArray mới
        jsonArray.clear();
        for (int i = 0; i < shuffledArray.length(); i++) {
            jsonArray.put(shuffledArray.get(i));
        }
    }

    // Phương thức tạo testCode mới
    private String generateNewTestCode(Connection connection) throws SQLException {
        String sql = "SELECT testCode FROM test ORDER BY testCode DESC LIMIT 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String lastTestCode = rs.getString("testCode");
                int lastNumber = Integer.parseInt(lastTestCode.replace("TST", ""));
                return "TST" + String.format("%03d", lastNumber + 1);
            } else {
                return "TST001";
            }
        }
    }

    // tạo exOrder bắt đầu từ A (A, B, C, ..., J)
    private String generateRandomExOrder() {
        List<String> letters = new ArrayList<>(
                List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N")); /// hơi dư mà thôi kệ đi
        String exOrder = letters.get(currentIndex);
        // currentIndex = (currentIndex + 1) % letters.size(); // Đảm bảo quay lại đầu
        // danh sách nếu vượt quá kích thước -----------dòng này k cần vì giới hạn số đè
        // tạo là 10 rồi---------

        return exOrder;
    }

    // Tạo câu hỏi dựa vô số câu dễ, khó, tb
    private JSONArray generateExQuesIDs(Connection connection, int tpID, int numEasy, int numMedium, int numDiff)
            throws SQLException {
        JSONArray exQuesIDs = new JSONArray();

        // Lấy câu hỏi dễ
        List<Integer> easyQuestions = getQuestionIDsByLevel(connection, tpID, 1, numEasy);
        if (easyQuestions.size() < numEasy) {
            throw new SQLException("Không đủ câu hỏi dễ cho chủ đề này. Vui lòng thêm câu hỏi.");
        }
        exQuesIDs.putAll(easyQuestions);

        // Lấy câu hỏi trung bình
        List<Integer> mediumQuestions = getQuestionIDsByLevel(connection, tpID, 2, numMedium);
        if (mediumQuestions.size() < numMedium) {
            throw new SQLException("Không đủ câu hỏi trung bình cho chủ đề này. Vui lòng thêm câu hỏi.");
        }
        exQuesIDs.putAll(mediumQuestions);

        // Lấy câu hỏi khó
        List<Integer> diffQuestions = getQuestionIDsByLevel(connection, tpID, 3, numDiff);
        if (diffQuestions.size() < numDiff) {
            throw new SQLException("Không đủ câu hỏi khó cho chủ đề này. Vui lòng thêm câu hỏi.");
        }
        exQuesIDs.putAll(diffQuestions);

        return exQuesIDs;
    }

    // lấy danh sách câu hỏi theo mức độ dễ/tb/khó
    private List<Integer> getQuestionIDsByLevel(Connection connection, int tpID, int level, int limit)
            throws SQLException {
        List<Integer> questionIDs = new ArrayList<>();
        String sql = "SELECT qID FROM questions WHERE qTopicID = ? AND qLevel = ? ORDER BY RAND() LIMIT ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, tpID); // Lọc theo tpID
            ps.setInt(2, level); // Lọc theo mức độ
            ps.setInt(3, limit); // Giới hạn số lượng câu hỏi
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                questionIDs.add(rs.getInt("qID"));
            }
        }
        return questionIDs;
    }

    public int update(TestDTO testDTO, int soDe) throws SQLException {
        Connection connection = DBConnection.getConnection();
        System.err.println("TestCode khi sửa" + testDTO.getTestCode());

        // List<String> currentExamCodes =
        // getExamCodesByTestCode(testDTO.getTestCode());
        // for (String exCode : currentExamCodes) {
        // if (isExCodeExistInResult(exCode)) {
        // throw new SQLException("Không thể cập nhật vì exCode " + exCode + " đã tồn
        // tại trong bảng result.");
        // }
        // }
        deleteExamsByTestCode(testDTO.getTestCode());

        String sql = "UPDATE test SET num_easy= ?, num_medium = ?, num_diff = ? WHERE testCode = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, testDTO.getNum_easy());
            ps.setInt(2, testDTO.getNum_medium());
            ps.setInt(3, testDTO.getNum_diff());
            ps.setString(4, testDTO.getTestCode());
            ps.executeUpdate();

            generateExams(connection, testDTO.getTestCode(), testDTO, soDe);

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
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
            String deleteExamsSQL = "DELETE FROM exams WHERE testCode = ?";
            try (PreparedStatement psExams = connection.prepareStatement(deleteExamsSQL)) {
                psExams.setString(1, testCode);
                psExams.executeUpdate();
            }

            // Xóa bảng test
            String deleteTestSQL = "DELETE FROM test WHERE testCode = ?";
            try (PreparedStatement psTest = connection.prepareStatement(deleteTestSQL)) {
                psTest.setString(1, testCode);
                psTest.executeUpdate();
            }

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    // đm quả lấy data nhìn rườm ra luộm thuộm vcl
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
        String sql = "SELECT aw.awContent, aw.awPictures FROM answers aw JOIN questions q ON aw.qID=q.qID WHERE q.qID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, qID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, String> answerData = new HashMap<>();
                answerData.put("awContent", rs.getString("awContent"));
                answerData.put("awPictures", rs.getString("awPictures")); // Lấy hình ảnh của câu trả lời
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
            if (connection != null) {
                connection.close();
            }
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
}