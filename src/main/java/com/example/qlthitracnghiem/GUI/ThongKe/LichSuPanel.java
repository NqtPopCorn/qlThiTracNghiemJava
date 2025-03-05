/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.ThongKe;

import com.example.qlthitracnghiem.BUS.ExamBUS;
import com.example.qlthitracnghiem.BUS.TestBUS;
import com.example.qlthitracnghiem.BUS.ThongKeBUS;
import com.example.qlthitracnghiem.DTO.ExamDTO;
import com.example.qlthitracnghiem.DTO.ResultDTO;
import com.example.qlthitracnghiem.DTO.TestDTO;
import com.example.qlthitracnghiem.GUI.DeThi.TestPanel2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import org.json.JSONArray;

/**
 *
 * @author Asus
 */
public class LichSuPanel extends JPanel {
    private JPanel examListPanel;
    private String userID;
    private String searchText = "";

    private List<ExamDTO> listExamDto = new ArrayList<>();
    private List<ResultDTO> listResultDto = new ArrayList<>();

    public LichSuPanel(String userID, String searchText) {
        this.userID = userID;
        this.searchText = searchText;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        // Tạo panel chứa danh sách bài thi
        examListPanel = new JPanel();
        examListPanel.setLayout(new BoxLayout(examListPanel, BoxLayout.Y_AXIS));
        examListPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(examListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);

        try {
            loadDataForAll();
            loadExamList();
        } catch (SQLException ex) {
            Logger.getLogger(LichSuPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadDataForAll() throws SQLException {
        if (!listResultDto.isEmpty())
            listResultDto.clear();
        if (!listExamDto.isEmpty())
            listExamDto.clear();
        listResultDto = ThongKeBUS.getInstance().getResultDtoByUserID(userID);
        for (ResultDTO result : listResultDto) {
            if (searchString(result.getExCode(), searchText)) {
                ExamDTO exam = new ExamBUS().getExamByExCode(result.getExCode());
                listExamDto.add(exam);
            }
        }
    }

    public boolean searchString(String text, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return true;
        }
        text = text.toLowerCase();
        keyword = keyword.toLowerCase();

        return text.contains(keyword);
    }

    public void loadExamList() throws SQLException {
        examListPanel.removeAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (ExamDTO exam : listExamDto) {
            TestDTO test = TestBUS.getInstance().getTestByTestCode(exam.getTestCode());
            JPanel examPanel;
            examPanel = createExamPanel(
                    test.getTestCode(),
                    exam.getExCode(),
                    test.getTestTitle(),
                    test.getTestDate().toLocalDate().format(formatter),
                    ThongKeBUS.getInstance().searchResultDtoByExamCode(listResultDto, exam.getExCode()).getRsMark(),
                    String.valueOf(test.getTestTime()));
            examListPanel.add(examPanel);
            examListPanel.add(Box.createVerticalStrut(10)); // Thêm khoảng cách giữa các panel
        }
        examListPanel.revalidate();
        examListPanel.repaint();
    }

    private JPanel createExamPanel(String testCode, String exCode, String title, String date, BigDecimal score,
            String testTime) {
        // Tạo panel chứa thông tin
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0)); // Tạo khoảng cách giữa các phần tử
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Giới hạn kích thước panel để thu nhỏ lại
        panel.setMaximumSize(new Dimension(1000, 150));
        panel.setMinimumSize(new Dimension(400, 130));

        // Panel chứa các thông tin
        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        infoPanel.setBackground(Color.WHITE);

        JLabel subjectLabel = new JLabel("Test title: " + title);
        JLabel exCodeLabel = new JLabel("Exam Code: " + exCode);
        JLabel dateLabel = new JLabel("Diễn ra từ ngày: " + date);
        JLabel scoreLabel = new JLabel("Điểm: " + String.valueOf(score));

        // Định dạng font chữ cho đẹp hơn
        Font font = new Font("Arial", Font.PLAIN, 14);
        subjectLabel.setFont(font);
        exCodeLabel.setFont(font);
        dateLabel.setFont(font);
        scoreLabel.setFont(font);

        infoPanel.add(subjectLabel);
        infoPanel.add(exCodeLabel);
        infoPanel.add(dateLabel);
        infoPanel.add(scoreLabel);

        // Tạo panel chứa nút button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton viewButton = new JButton("Xem chi tiết");
        viewButton.setIcon(new ImageIcon(LichSuPanel.class.getResource("/icons/eye.png")));
        viewButton.setBackground(new Color(60, 140, 200)); // Màu xanh nhạt
        viewButton.setForeground(Color.WHITE); // Màu chữ trắng
        viewButton.setFocusPainted(false);
        viewButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        buttonPanel.add(viewButton);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JPanel detailPanel = new JPanel(new BorderLayout());
                    detailPanel.setBackground(new Color(255, 255, 255));

                    // Panel chứa thông tin bài thi và câu hỏi
                    JPanel contentPanel = new JPanel();
                    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
                    contentPanel.setBackground(new Color(255, 255, 255));
                    contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // top, left, bottom, right

                    JLabel jtfTestTitle = new JLabel("Bài thi môn: " + title);
                    jtfTestTitle.setFont(new Font("Serif", Font.BOLD, 18));
                    contentPanel.add(jtfTestTitle);

                    JLabel jtfTestTime = new JLabel("Thời gian: " + testTime + " phút");
                    jtfTestTime.setFont(new Font("Serif", Font.BOLD, 18));
                    contentPanel.add(jtfTestTime);

                    // Hiển thị mã đề
                    JLabel exCodeLabel = new JLabel("Mã đề: " + exCode);
                    exCodeLabel.setFont(new Font("Serif", Font.BOLD, 16));
                    contentPanel.add(exCodeLabel);
                    contentPanel.add(Box.createVerticalStrut(20)); // Khoảng cách giữa phần thời gian và mã đề đầu tiên

                    // Lấy danh sách câu hỏi và câu trả lời cho exCode hiện tại
                    List<Integer> exQuesIDs = new ExamBUS().getExQuesIDsByExCode(exCode);

                    int questionNumber = 1;
                    for (int qID : exQuesIDs) {
                        // Lấy nội dung câu hỏi và hình ảnh
                        Map<String, String> questionData = new ExamBUS().getQuestionContent(qID);
                        String qContent = questionData.get("qContent");
                        String qPictures = questionData.get("qPictures");

                        // Tạo panel cho câu hỏi
                        JPanel questionPanel = new JPanel();
                        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
                        questionPanel.setBackground(new Color(255, 255, 255));

                        // Hiển thị nội dung câu hỏi
                        JLabel qContentLabel = new JLabel("Câu " + questionNumber + ": " + qContent);
                        qContentLabel.setFont(new Font("Serif", Font.BOLD, 12));
                        questionPanel.add(qContentLabel);

                        // Hiển thị hình ảnh câu hỏi (nếu có)
                        if (qPictures != null && !qPictures.isEmpty()) {
                            JLabel qPictureLabel = new JLabel("Hình ảnh: " + qPictures);
                            qPictureLabel.setFont(new Font("Serif", Font.PLAIN, 12));
                            questionPanel.add(qPictureLabel);
                        }

                        // Lấy danh sách câu trả lời và hình ảnh
                        List<Map<String, String>> awContents = new ExamBUS().getAnswerContent(qID);

                        // Tạo ButtonGroup cho câu hỏi hiện tại
                        ButtonGroup buttonGroup = new ButtonGroup();

                        for (Map<String, String> answerData : awContents) {
                            String awContent = answerData.get("awContent");
                            String awPictures = answerData.get("awPictures");
                            String awID = answerData.get("awID");

                            // Tạo JRadioButton cho mỗi câu trả lời
                            JRadioButton radioButton = new JRadioButton(awContent);
                            radioButton.setFont(new Font("Serif", Font.PLAIN, 12));
                            radioButton.setBackground(new Color(255, 255, 255));
                            // Vô hiệu hóa để không cho phép chọn lại
                            radioButton.setEnabled(false);

                            // Hiển thị hình ảnh câu trả lời (nếu có)
                            if (awPictures != null && !awPictures.isEmpty()) {
                                radioButton.setText(awContent + " (Hình ảnh: " + awPictures + ")");
                            }

                            // Chon cau tra loi cua thanh vien
                            for (ResultDTO result : listResultDto) {
                                if (result.getExCode().equals(exCode)) {
                                    for (String answer : new JSONArray(result.getRsAnswers()).toList().stream()
                                            .map(Object::toString).toList()) {
                                        if (answer.equals(awID)) {
                                            radioButton.setSelected(true);
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }

                            buttonGroup.add(radioButton);
                            questionPanel.add(radioButton);
                        }

                        contentPanel.add(questionPanel);
                        contentPanel.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các câu hỏi
                        questionNumber++;
                    }

                    contentPanel.add(Box.createVerticalStrut(20)); // Khoảng cách giữa các mã đề

                    detailPanel.add(contentPanel, BorderLayout.CENTER);

                    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                    bottomPanel.setBackground(new Color(255, 255, 255));

                    JButton btnBack = new JButton("Back");
                    btnBack.setBackground(new Color(28, 58, 118));
                    btnBack.setForeground(Color.WHITE);
                    btnBack.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            examListPanel.removeAll();
                            try {
                                loadExamList();
                            } catch (Exception ex) {
                                Logger.getLogger(TestPanel2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            examListPanel.revalidate();
                            examListPanel.repaint();
                        }
                    });

                    bottomPanel.add(btnBack);
                    detailPanel.add(bottomPanel, BorderLayout.SOUTH);

                    JScrollPane scrollPane = new JScrollPane(detailPanel);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                    // Cập nhật mainPanel với JScrollPane
                    examListPanel.removeAll();
                    examListPanel.add(scrollPane);
                    examListPanel.revalidate();
                    examListPanel.repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LichSuPanel.this, "Lỗi khi lấy dữ liệu!");
                }
            }
        });
        // Thêm các panel vào panel chính
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    public JPanel getTestListPanel() {
        return examListPanel;
    }
}
