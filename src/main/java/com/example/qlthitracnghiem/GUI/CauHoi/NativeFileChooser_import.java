package com.example.qlthitracnghiem.GUI.CauHoi;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import java.awt.FileDialog;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.awt.Insets;
import java.awt.Font;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class NativeFileChooser_import {
    private String selectedFilePath; // Lưu đường dẫn file
    private String selectedTopic; // Lưu chủ đề đã chọn

       public NativeFileChooser_import() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); // Áp dụng giao diện hiện đại
        } catch (Exception ignored) {}

        createUI(); // Tạo giao diện
    }

    private void createUI() {
        JFrame frame = new JFrame("Chọn Chủ Đề & File");
        frame.setSize(450, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // ✅ Sửa lỗi màu nền

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Chọn chủ đề:");
        label.setFont(new Font("Arial", Font.BOLD, 14));

        String[] topics = {"Toán", "Văn", "Anh", "Lý", "Hóa", "Sinh"}; // Danh sách chủ đề
        JComboBox<String> topicComboBox = new JComboBox<>(topics);
        topicComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        topicComboBox.setBackground(Color.WHITE);

        JButton openFileButton = new JButton(" Chọn File Excel");
        openFileButton.setFont(new Font("Arial", Font.BOLD, 14));
        openFileButton.setBackground(new Color(30, 144, 255)); // Màu xanh dương nổi bật
        openFileButton.setForeground(Color.WHITE);

        JLabel selectedFileLabel = new JLabel(" File: Chưa chọn");
        selectedFileLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        selectedFileLabel.setForeground(new Color(105, 105, 105));

        // Sự kiện chọn file
        openFileButton.addActionListener((ActionEvent e) -> {
            selectedTopic = (String) topicComboBox.getSelectedItem(); // Lấy chủ đề đã chọn
            openFileChooser(selectedFileLabel);
        });

        // Thêm các thành phần vào frame
        gbc.gridx = 0; gbc.gridy = 0;
        frame.add(label, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        frame.add(topicComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        frame.add(openFileButton, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        frame.add(selectedFileLabel, gbc);

        frame.setLocationRelativeTo(null); // Hiển thị giữa màn hình
        frame.setVisible(true);
    }

    public void openFileChooser(JLabel fileLabel) {
        Frame frame = new Frame();
        FileDialog fileDialog = new FileDialog(frame, "Chọn một file", FileDialog.LOAD);
        fileDialog.setVisible(true);

        String directory = fileDialog.getDirectory();
        String filename = fileDialog.getFile();

        if (directory != null && filename != null) {
            String filePath = directory + filename;

            if (filename.toLowerCase().endsWith(".xlsx") || filename.toLowerCase().endsWith(".xls")) {
                selectedFilePath = filePath;
                fileLabel.setText(" File: " + filename); // Hiển thị tên file đã chọn
//                JOptionPane.showMessageDialog(null, " Chủ đề: " + selectedTopic + "\n File: " + filename, "Thông tin chọn", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "❌ Chỉ được chọn file Excel (.xlsx, .xls)", "Lỗi", JOptionPane.ERROR_MESSAGE);
                selectedFilePath = null;
            }
        } else {
            System.out.println("Không có file nào được chọn.");
            selectedFilePath = null;
        }

        frame.dispose();
    }
    // Getter để lấy đường dẫn file đã chọn
    public String getSelectedFilePath() {
        return selectedFilePath;
    }
    



 public static boolean checkFileFormat(File file) {
        if (file == null || !file.exists()) {
            System.out.println(" File không tồn tại hoặc null.");
            return false;
        }

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                System.out.println(" File không có sheet nào.");
                return false;
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                System.out.println(" Không tìm thấy hàng tiêu đề.");
                return false;
            }

            // Danh sách tiêu đề cột đúng
            List<String> requiredHeaders = Arrays.asList(
                    "Question Text", "Option 1", "Option 2", "Option 3", "Option 4", "Option 5", "Correct Answer", "Image Link", "Level"
            );

            // Kiểm tra tiêu đề cột
            for (int i = 0; i < requiredHeaders.size(); i++) {
                Cell cell = headerRow.getCell(i);
                if (cell == null || !cell.getStringCellValue().trim().equalsIgnoreCase(requiredHeaders.get(i))) {
                    System.out.println(" Cột " + (i + 1) + " không đúng format. Cần: " + requiredHeaders.get(i));
                    return false;
                }
            }

            // Kiểm tra từng câu hỏi
            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rowCount; i++) { // Bỏ qua tiêu đề
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell questionCell = row.getCell(0);
                if (questionCell == null || questionCell.getStringCellValue().trim().isEmpty()) {
                    continue; // Nếu không có nội dung câu hỏi thì bỏ qua
                }

                // Kiểm tra có ít nhất 2 đáp án hợp lệ
                int validAnswers = 0;
                for (int j = 1; j <= 5; j++) { // Cột Option 1 -> Option 5
                    Cell optionCell = row.getCell(j);
                    if (optionCell != null && !optionCell.getStringCellValue().trim().isEmpty()) {
                        validAnswers++;
                    }
                }

                if (validAnswers < 2) {
                    System.out.println(" Lỗi: Câu hỏi ở dòng " + (i + 1) + " không có đủ 2 đáp án.");
                    return false;
                }

                // Kiểm tra cột "Level" phải có giá trị và chỉ được là 1, 2 hoặc 3
                Cell levelCell = row.getCell(8); // Cột "Level"
                if (levelCell == null || levelCell.getCellType() != CellType.NUMERIC) {
                    System.out.println(" Lỗi: Cột 'Level' ở dòng " + (i + 1) + " không hợp lệ.");
                    return false;
                }

                int level = (int) levelCell.getNumericCellValue();
                if (level != 1 && level != 3 && level != 2) {
                    System.out.println("Lỗi: 'Level' ở dòng " + (i + 1) + " phải là 1, 2 hoặc 3.");
                    return false;
                }
            }

            System.out.println(" File đúng format.");
            return true;

        } catch (IOException e) {
            System.out.println(" Lỗi khi đọc file: " + e.getMessage());
            return false;
        }
 }
}


