
package com.example.qlthitracnghiem.GUI.DeThi;

import com.example.qlthitracnghiem.BUS.TestBUS;
import com.example.qlthitracnghiem.DTO.TestDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestPanel extends JPanel {
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JComboBox<String> comboBox;
    private JTextField searchField;
    TestBUS testBUS = new TestBUS();

    public TestPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Panel chứa danh sách bài kiểm tra
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        try {
            updateTestPanel(testBUS.getAll());
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
        //tạo placeholder
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Khi người dùng nhấp vào ô tìm kiếm
                if (searchField.getText().equals("Tìm kiếm đề thi")) {
                    searchField.setText(""); 
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Khi người dùng rời khỏi ô tìm kiếm
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
        createButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png")));
        createButton.setForeground(Color.WHITE);
        createButton.setBackground(new Color(28, 58, 118));

        topPanel.add(comboBox);
        topPanel.add(Box.createHorizontalStrut(10)); 
        topPanel.add(searchField);
        topPanel.add(Box.createHorizontalGlue()); // Đẩy nút "Tạo đề thi" sang phải
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
                        test.getTestStatus() == 1 ? "Đang mở" : "Đã kết thúc"));
            }
        }

        // Cập nhật lại giao diện
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createTestPanel(int testID, String title, String date, String status) {
        JPanel panel = new JPanel();
        panel.setBackground(new java.awt.Color(255, 255, 255));
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
        buttonPanel.setBackground(new java.awt.Color(255, 255, 255));
        JButton viewButton = new JButton("Xem chi tiết");
        viewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eye.png")));
        viewButton.setBackground(new java.awt.Color(220, 230, 205));

        JButton editButton = new JButton("Chỉnh sửa");
        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wrench.png")));
        editButton.setBackground(new java.awt.Color(175, 205, 235));

        JButton deleteButton = new JButton("Xóa đề");
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/multiply.png")));
        deleteButton.setBackground(new java.awt.Color(248, 220, 209));
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