//
//package com.example.qlthitracnghiem.GUI.DeThi;
//
//import com.example.qlthitracnghiem.BUS.ExamBUS;
//import com.example.qlthitracnghiem.BUS.TestBUS;
//import com.example.qlthitracnghiem.DAO.ExamDAO;
//import com.example.qlthitracnghiem.DTO.TestDTO;
//import com.example.qlthitracnghiem.utils.ImageUtil;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class TestPanel extends JPanel {
//    private JPanel mainPanel;
//    private JScrollPane scrollPane;
//    private JComboBox<String> comboBox;
//    private JTextField searchField;
//    private TestBUS testBUS = new TestBUS();
//    private ExamBUS examBUS = new ExamBUS();
//
//    public TestPanel() {
//        initComponents();
//    }
//
//    private void initComponents() {
//        setLayout(new BorderLayout());
//
//        // Panel chứa danh sách bài kiểm tra
//        mainPanel = new JPanel();
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        mainPanel.setBackground(new Color(255, 255, 255));
//        try {
//            updateTestPanel(testBUS.getAll());
//        } catch (Exception ex) {
//            Logger.getLogger(TestPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        scrollPane = new JScrollPane(mainPanel); // k hiện thanh scroll do kích thước chiều y nhỏ quá đó đm nóa
//        add(scrollPane, BorderLayout.CENTER);
//
//        // Panel chứa ô tìm kiếm và nút tạo đề thi
//        JPanel topPanel = new JPanel();
//        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
//
//        comboBox = new JComboBox<>(new String[] { "Tất cả", "Đang mở", "Đã kết thúc" });
//        comboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Search();
//            }
//        });
//
////        searchField = new JTextField("Tìm kiếm đề thi", 15);
////        searchField.setForeground(Color.GRAY);
////        searchField.addFocusListener(new FocusListener() {
////            @Override
////            public void focusGained(FocusEvent e) {
////                if (searchField.getText().equals("Tìm kiếm đề thi")) {
////                    searchField.setText("");
////                    searchField.setForeground(Color.BLACK);
////                }
////            }
////
////            @Override
////            public void focusLost(FocusEvent e) {
////                if (searchField.getText().trim().isEmpty()) {
////                    searchField.setText("Tìm kiếm đề thi");
////                    searchField.setForeground(Color.GRAY);
////                }
////            }
////        });
////        searchField.addKeyListener(new KeyAdapter() {
////            @Override
////            public void keyReleased(KeyEvent e) {
////                Search();
////            }
////        });
//
//        JButton createButton = new JButton("Tạo đề thi");
//        createButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                CreateTestDialog createTestDialog = new CreateTestDialog(null, true);
//                createTestDialog.setVisible(true);
//                try {
//                    updateTestPanel(testBUS.getAll());
//                } catch (Exception ex) {
//                    Logger.getLogger(TestPanel.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            }
//        });
//        createButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png")));
//        createButton.setForeground(Color.WHITE);
//        createButton.setBackground(new Color(28, 58, 118));
//
//        topPanel.add(comboBox);
//        topPanel.add(Box.createHorizontalStrut(10));
//        topPanel.add(searchField);
//        topPanel.add(Box.createHorizontalGlue());
//        topPanel.add(createButton);
//
//        add(topPanel, BorderLayout.NORTH);
//    }
//
//    private void Search() {
//        String keyword = searchField.getText().trim();
//        if (keyword.equals("Tìm kiếm đề thi")) {
//            keyword = "";
//        }
//        String statusFilter = (String) comboBox.getSelectedItem();
//        int status = -1; // Mặc định là tất cả
//
//        if ("Đang mở".equals(statusFilter)) {
//            status = 1;
//        } else if ("Đã kết thúc".equals(statusFilter)) {
//            status = 0;
//        }
//
//        ArrayList<TestDTO> results;
//        try {
//            results = testBUS.search(keyword, status);
//            updateTestPanel(results);
//        } catch (Exception ex) {
//            Logger.getLogger(TestPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void updateTestPanel(ArrayList<TestDTO> tests) {
//        mainPanel.removeAll();
//
//        if (tests == null || tests.isEmpty()) {
//            JLabel emptyLabel = new JLabel("Không tìm thấy kết quả nào.");
//            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//            mainPanel.add(emptyLabel);
//        } else {
//            for (TestDTO test : tests) {
//                mainPanel.add(createTestPanel(
//                        test.getTestTime(),
//                        test.getTestTitle(),
//                        test.getTestDate() != null ? test.getTestDate().toString() : "Không có ngày",
//                        test.getTestStatus() == 1 ? "Đang mở" : "Đã kết thúc",
//                        test.getTestCode(),
//                        test));
//            }
//        }
//
//        mainPanel.revalidate();
//        mainPanel.repaint();
//    }
//
//    private JPanel createTestPanel(int testTime, String title, String date, String status, String testCode,
//            TestDTO test) {
//        JPanel panel = new JPanel();
//        panel.setBackground(new Color(255, 255, 255));
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(Color.BLACK),
//                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
//        JLabel subjectLabel = new JLabel("Test title: " + title);
//        JLabel dateLabel = new JLabel("Diễn ra từ ngày: " + date);
//        JLabel statusLabel = new JLabel("Tình trạng: " + status);
//        subjectLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
//        buttonPanel.setBackground(new Color(255, 255, 255));
//        JButton viewButton = new JButton("Xem chi tiết");
//        viewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eye.png")));
//        viewButton.setBackground(new Color(220, 230, 205));
//
//        viewButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    // Lấy danh sách exCode từ bảng exams dựa trên testCode
//                    List<String> examCodes = examBUS.getExamCode(testCode);
//                    System.err.println("examCode: " + examCodes);
//
//                    // Tạo detailPanel
//                    JPanel detailPanel = new JPanel(new BorderLayout());
//                    detailPanel.setBackground(new Color(255, 255, 255));
//
//                    // Tạo buttonPanel chứa các nút
//                    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//                    buttonPanel.setBackground(Color.WHITE);
//
//                    // Tạo nút Export
//                    JButton exportButton = new JButton("Export to DOCX");
//                    exportButton.setBackground(new Color(28, 58, 118));
//                    exportButton.setForeground(Color.WHITE);
//                    exportButton.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            String filePath = ExportDOCX.chooseFileToSave();
//                            System.err.println("filePath: " + filePath);
//                            if (filePath != null) {
//                                try {
//                                    ExportDOCX.exportToDocx(test, examCodes, filePath);
//                                    JOptionPane.showMessageDialog(TestPanel.this, "Xuất file thành công!", "Thành công",
//                                            JOptionPane.INFORMATION_MESSAGE);
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    JOptionPane.showMessageDialog(TestPanel.this, "Lỗi khi xuất file!", "Lỗi",
//                                            JOptionPane.ERROR_MESSAGE);
//                                }
//                            } else {
//                                JOptionPane.showMessageDialog(TestPanel.this, "Không có file được chọn!", "Thông báo",
//                                        JOptionPane.WARNING_MESSAGE);
//                            }
//                        }
//                    });
//
//                    buttonPanel.add(exportButton);
//
//                    detailPanel.add(buttonPanel, BorderLayout.NORTH);
//
//                    JPanel contentPanel = new JPanel();
//                    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
//                    contentPanel.setBackground(new Color(255, 255, 255));
//                    contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // top, left, bottom, right
//
//                    JLabel jtfTestTitle = new JLabel("Bài thi môn: " + title);
//                    jtfTestTitle.setFont(new Font("Serif", Font.BOLD, 18));
//                    contentPanel.add(jtfTestTitle);
//
//                    JLabel jtfTestTime = new JLabel("Thời gian: " + testTime + " phút");
//                    jtfTestTime.setFont(new Font("Serif", Font.BOLD, 18));
//                    contentPanel.add(jtfTestTime);
//
//                    for (String exCode : examCodes) {
//                        // Hiển thị mã đề
//                        JLabel exCodeLabel = new JLabel("Mã đề: " + exCode);
//                        exCodeLabel.setFont(new Font("Serif", Font.BOLD, 16));
//                        contentPanel.add(exCodeLabel);
//                        contentPanel.add(Box.createVerticalStrut(20)); // Khoảng cách giữa phần thời gian và mã đề đầu
//                                                                       // tiên
//
//                        List<Integer> exQuesIDs = examBUS.getExQuesIDsByExCode(exCode);
//                        System.err.println("ex_quesIDs: " + exQuesIDs);
//
//                        int questionNumber = 1;
//                        for (int qID : exQuesIDs) {
//                            Map<String, String> questionData = examBUS.getQuestionContent(qID);
//                            String qContent = questionData.get("qContent");
//                            String qPictures = questionData.get("qPictures");
//
//                            JPanel questionPanel = new JPanel();
//                            questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
//                            questionPanel.setBackground(new Color(255, 255, 255));
//
//                            JLabel qContentLabel = new JLabel("Câu " + questionNumber + ": " + qContent);
//                            qContentLabel.setFont(new Font("Serif", Font.BOLD, 12));
//                            questionPanel.add(qContentLabel);
//                            if (qPictures != null && !qPictures.isEmpty()) {
//                                JLabel qPictureLabel = new JLabel("");
//                                ImageUtil.setIcon(qPictureLabel, qPictures, 100, 100);
//                                qPictureLabel.setFont(new Font("Serif", Font.PLAIN, 12));
//                                questionPanel.add(qPictureLabel);
//                            }
//
//                            List<Map<String, String>> awContents = examBUS.getAnswerContent(qID);
//
//                            ButtonGroup buttonGroup = new ButtonGroup();
//                            for (Map<String, String> answerData : awContents) {
//                                String awContent = answerData.get("awContent");
//                                String awPictures = answerData.get("awPictures");
//
//                                JPanel answerPanel = new JPanel();
//                                answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));
//                                answerPanel.setBackground(new Color(255, 255, 255));
//
//                                if (awPictures != null && !awPictures.isEmpty()) {
//                                    JLabel answerImageLabel = new JLabel();
//                                    ImageUtil.setIcon(answerImageLabel, awPictures, 100, 100); // Điều chỉnh kích thước
//                                                                                               // ảnh nếu cần
//                                    answerPanel.add(answerImageLabel);
//                                }
//
//                                JRadioButton radioButton = new JRadioButton(awContent);
//                                radioButton.setFont(new Font("Serif", Font.PLAIN, 12));
//                                radioButton.setBackground(new Color(255, 255, 255));
//                                buttonGroup.add(radioButton);
//                                answerPanel.add(radioButton);
//                                questionPanel.add(answerPanel);
//                            }
//
//                            contentPanel.add(questionPanel);
//                            contentPanel.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các câu hỏi
//                            questionNumber++;
//                        }
//
//                        contentPanel.add(Box.createVerticalStrut(20)); // Khoảng cách giữa các mã đề
//                    }
//
//                    detailPanel.add(contentPanel, BorderLayout.CENTER);
//
//                    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//                    bottomPanel.setBackground(new Color(255, 255, 255));
//
//                    JButton btnBack = new JButton("Back");
//                    btnBack.setBackground(new Color(28, 58, 118));
//                    btnBack.setForeground(Color.WHITE);
//                    btnBack.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            mainPanel.removeAll();
//                            try {
//                                updateTestPanel(testBUS.getAll());
//                            } catch (Exception ex) {
//                                Logger.getLogger(TestPanel.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                            mainPanel.revalidate();
//                            mainPanel.repaint();
//                        }
//                    });
//
//                    bottomPanel.add(btnBack);
//                    detailPanel.add(bottomPanel, BorderLayout.SOUTH);
//                    JScrollPane scrollPane = new JScrollPane(detailPanel);
//                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//
//                    mainPanel.removeAll();
//                    mainPanel.add(scrollPane);
//                    mainPanel.revalidate();
//                    mainPanel.repaint();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(TestPanel.this, "Lỗi khi lấy dữ liệu!");
//                }
//            }
//        });
//        JButton editButton = new JButton("Chỉnh sửa");
//        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wrench.png")));
//        editButton.setBackground(new Color(175, 205, 235));
//        editButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<String> examCodes;
//                try {
//                    examCodes = examBUS.getExamCode(testCode);
//                    boolean canEdit = true;
//                    for (String exCode : examCodes) {
//                        if (examBUS.isExCodeExistInResult(exCode)) {
//                            JOptionPane.showMessageDialog(
//                                    TestPanel.this,
//                                    "Đã có học sinh làm bài kiểm tra, không thể chỉnh sửa!",
//                                    "Thông báo",
//                                    JOptionPane.WARNING_MESSAGE);
//                            canEdit = false;
//                            break;
//                        }
//                    }
//
//                    if (canEdit) {
//                        EditTestDialog editTestDialog = new EditTestDialog(null, true, test);
//                        editTestDialog.setVisible(true);
//                        try {
//                            updateTestPanel(testBUS.getAll());
//                        } catch (Exception ex) {
//                            Logger.getLogger(TestPanel.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(
//                            TestPanel.this,
//                            "Lỗi khi kiểm tra mã đề thi!",
//                            "Lỗi",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
//        JButton deleteButton = new JButton("Xóa đề");
//        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/multiply.png")));
//        deleteButton.setBackground(new Color(248, 220, 209));
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                deleteButtonActionPerformed(testCode);
//            }
//        });
//
//        buttonPanel.add(viewButton);
//        buttonPanel.add(editButton);
//        buttonPanel.add(deleteButton);
//        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
//        panel.add(subjectLabel);
//        panel.add(dateLabel);
//        panel.add(statusLabel);
//        panel.add(Box.createVerticalStrut(5));
//        panel.add(buttonPanel);
//
//        return panel;
//    }
//
//    private void deleteButtonActionPerformed(String testCode) {
//        System.err.println("TestCode khi xóa " + testCode);
//        try {
//            ExamDAO examDAO = new ExamDAO();
//            int result = examDAO.delete(testCode);
//
//            if (result == 1) {
//                JOptionPane.showMessageDialog(this, "Xóa đề thi thành công!", "Thành công",
//                        JOptionPane.INFORMATION_MESSAGE);
//                // Cập nhật lại danh sách bài thi sau khi xóa
//                updateTestPanel(testBUS.getAll());
//            } else {
//                JOptionPane.showMessageDialog(
//                        TestPanel.this,
//                        "Đã có học sinh làm bài kiểm tra, không thể xóa!",
//                        "Thông báo",
//                        JOptionPane.WARNING_MESSAGE);
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(
//                    TestPanel.this,
//                    "Đã có học sinh làm bài kiểm tra, không thể xóa!",
//                    "Thông báo",
//                    JOptionPane.WARNING_MESSAGE);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa đề thi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.add(new TestPanel());
//        frame.setSize(1040, 700);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
//}