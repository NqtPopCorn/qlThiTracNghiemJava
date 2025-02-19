
package com.example.qlthitracnghiem.GUI.DeThi;

import com.example.qlthitracnghiem.BUS.ExamBUS;
import com.example.qlthitracnghiem.BUS.TestBUS;
import com.example.qlthitracnghiem.DTO.TestDTO;
import org.json.JSONArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestPanel extends JPanel {
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JComboBox<String> comboBox;
    private JTextField searchField;
    private TestBUS testBUS = new TestBUS();
    private ExamBUS examBUS = new ExamBUS();

    public TestPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Panel chứa danh sách bài kiểm tra
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        try {
            updateTestPanel(testBUS.getExam());
        } catch (Exception ex) {
            Logger.getLogger(TestPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Panel chứa ô tìm kiếm và nút tạo đề thi
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        comboBox = new JComboBox<>(new String[] { "Tất cả", "Đang mở", "Đã kết thúc" });
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search();
            }
        });

        searchField = new JTextField("Tìm kiếm đề thi", 15);
        searchField.setForeground(Color.GRAY);
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Tìm kiếm đề thi")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().trim().isEmpty()) {
                    searchField.setText("Tìm kiếm đề thi");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                Search();
            }
        });

        JButton createButton = new JButton("Tạo đề thi");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateTestDialog createTestFrame = new CreateTestDialog(null, true);
                createTestFrame.setVisible(true);
            }
        });
        createButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png")));
        createButton.setForeground(Color.WHITE);
        createButton.setBackground(new Color(28, 58, 118));

        topPanel.add(comboBox);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(searchField);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(createButton);

        add(topPanel, BorderLayout.NORTH);
    }

    private void Search() {
        String keyword = searchField.getText().trim();
        if (keyword.equals("Tìm kiếm đề thi")) {
            keyword = "";
        }
        String statusFilter = (String) comboBox.getSelectedItem();
        int status = -1; // Mặc định là tất cả

        if ("Đang mở".equals(statusFilter)) {
            status = 1;
        } else if ("Đã kết thúc".equals(statusFilter)) {
            status = 0;
        }

        ArrayList<TestDTO> results;
        try {
            results = testBUS.search(keyword, status);
            updateTestPanel(results);
        } catch (Exception ex) {
            Logger.getLogger(TestPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateTestPanel(ArrayList<TestDTO> tests) {
        mainPanel.removeAll();

        if (tests == null || tests.isEmpty()) {
            JLabel emptyLabel = new JLabel("Không tìm thấy kết quả nào.");
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(emptyLabel);
        } else {
            for (TestDTO test : tests) {
                mainPanel.add(createTestPanel(
                        test.getTestID(),
                        test.getTestTitle(),
                        test.getTestDate() != null ? test.getTestDate().toString() : "Không có ngày",
                        test.getTestStatus() == 1 ? "Đang mở" : "Đã kết thúc",
                        test.getTestCode())); // Thêm testCode vào đây
            }
        }

        // Cập nhật lại giao diện
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createTestPanel(int testID, String title, String date, String status, String testCode) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        JLabel testLabel = new JLabel("Kiểm tra lần " + testID);
        JLabel subjectLabel = new JLabel("Test title: " + title);
        JLabel dateLabel = new JLabel("Diễn ra từ ngày: " + date);
        JLabel statusLabel = new JLabel("Tình trạng: " + status);
        testLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        subjectLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Panel chứa các nút bấm
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonPanel.setBackground(new Color(255, 255, 255));
        JButton viewButton = new JButton("Xem chi tiết");
        viewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eye.png")));
        viewButton.setBackground(new Color(220, 230, 205));

        // Xử lý sự kiện khi nhấn nút "Xem chi tiết"
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Lấy danh sách ex_quesIDs từ bảng exams dựa trên testID
                    List<Integer> exQuesIDs = examBUS.getExQuesIDs(testCode);
                    System.err.println("exquesid" + exQuesIDs);
                    // Tạo một JPanel mới để hiển thị thông tin chi tiết
                    JPanel detailPanel = new JPanel();
                    detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));

                    // Duyệt qua từng qID trong ex_quesIDs
                    for (int qID : exQuesIDs) {
                        // Lấy qContent từ bảng questions
                        String qContent = examBUS.getQuestionContent(qID);

                        // Lấy awContent từ bảng answers
                        List<String> awContents = examBUS.getAnswerContent(qID);

                        // Tạo một JPanel để hiển thị câu hỏi và câu trả lời
                        JPanel questionPanel = new JPanel();
                        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
                        questionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                        // Thêm qContent vào JPanel
                        JLabel qContentLabel = new JLabel("Câu hỏi: " + qContent);
                        questionPanel.add(qContentLabel);

                        // Thêm awContent vào JPanel
                        for (String awContent : awContents) {
                            JLabel awContentLabel = new JLabel("Trả lời: " + awContent);
                            questionPanel.add(awContentLabel);
                        }

                        // Thêm questionPanel vào detailPanel
                        detailPanel.add(questionPanel);
                    }

                    // Xóa toàn bộ nội dung cũ của mainPanel và thêm detailPanel vào
                    mainPanel.removeAll();
                    mainPanel.add(detailPanel);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TestPanel.this, "Lỗi khi lấy dữ liệu!");
                }
            }
        });

        JButton editButton = new JButton("Chỉnh sửa");
        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wrench.png")));
        editButton.setBackground(new Color(175, 205, 235));

        JButton deleteButton = new JButton("Xóa đề");
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/multiply.png")));
        deleteButton.setBackground(new Color(248, 220, 209));
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(testLabel);
        panel.add(subjectLabel);
        panel.add(dateLabel);
        panel.add(statusLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(buttonPanel);

        return panel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new TestPanel());
        frame.setSize(1040, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}