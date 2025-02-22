package com.example.qlthitracnghiem.GUI.CauHoi;

import com.example.qlthitracnghiem.BUS.AnswersBUS;
import com.example.qlthitracnghiem.BUS.QuestionsBUS;
import com.example.qlthitracnghiem.BUS.TopicsBUS;
import com.example.qlthitracnghiem.DTO.AnswersDTO;
import com.example.qlthitracnghiem.DTO.QuestionsDTO;
import com.example.qlthitracnghiem.DTO.TopicsDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import java.awt.FileDialog;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.awt.Insets;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; // Hỗ trợ định dạng .xlsx (Excel mới)
import java.io.FileOutputStream; // Ghi dữ liệu ra file
import java.io.IOException; // Xử lý lỗi nhập/xuất file
import java.nio.file.Files; // Sao chép file
import java.nio.file.Paths; // Làm việc với đường dẫn file
import java.nio.file.StandardCopyOption; // Tùy chọn sao chép file
import javax.swing.*; // Thư viện giao diện (JFileChooser, JOptionPane)
import java.io.File; // Làm việc với file trong Java



public class NativeFileChooser_import {
    private String selectedFilePath; // Lưu đường dẫn file
    public TopicsBUS topicBUS = new TopicsBUS();
    public AnswersBUS answersBUS = new AnswersBUS();
    public QuestionsBUS questionsBUS = new QuestionsBUS();
    //các biến để lưu trữ dữ liệu được import ra
    private  List<QuestionsDTO> questions = null;
    private  List<List<AnswersDTO>> answers = null;

       public NativeFileChooser_import() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); // Áp dụng giao diện hiện đại
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ignored) {}

        createUI(); // Tạo giao diện
    }

    private void createUI() {
 JFrame frame = new JFrame("Chọn Chủ Đề & File");
        frame.setSize(500, 250);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(240, 248, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label chọn chủ đề
        JLabel label = new JLabel("Chọn chủ đề:");
        label.setFont(new Font("Arial", Font.BOLD, 14));

        // Danh sách chủ đề

        JComboBox<String> topicComboBox = new JComboBox<>();
        for(TopicsDTO topic : topicBUS.getAll()){
            topicComboBox.addItem(topic.getTpTitle());
        }
        
        topicComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        topicComboBox.setBackground(Color.WHITE);

        // Panel chứa 2 nút (Chọn file + Tải file mẫu)
        JPanel fileButtonPanel = new JPanel(new GridBagLayout());
        fileButtonPanel.setBackground(new Color(240, 248, 255)); // Đồng bộ màu nền

        // Nút chọn file Excel
        JButton openFileButton = new JButton("Chọn File Excel");
        openFileButton.setFont(new Font("Arial", Font.BOLD, 14));
        openFileButton.setBackground(new Color(30, 144, 255)); // Màu xanh dương
        openFileButton.setForeground(Color.WHITE);

        // Nút tải file mẫu
        JButton downloadTemplateButton = new JButton("Tải File Mẫu");
        downloadTemplateButton.setFont(new Font("Arial", Font.BOLD, 14));
        downloadTemplateButton.setBackground(new Color(34, 139, 34)); // Màu xanh lá
        downloadTemplateButton.setForeground(Color.WHITE);
        
        downloadTemplateButton.addActionListener(e -> {
    try {
        // Tạo file mẫu
        String filePath = "Template.xlsx";
        createExcelTemplate(filePath);

        // Mở hộp thoại lưu file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file mẫu");
        fileChooser.setSelectedFile(new File(filePath));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File saveFile = fileChooser.getSelectedFile();
            Files.copy(Paths.get(filePath), saveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            JOptionPane.showMessageDialog(null, "File mẫu đã được lưu thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải file mẫu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
});

        

        // Label hiển thị file đã chọn
        JLabel selectedFileLabel = new JLabel("File: Chưa chọn");
        selectedFileLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        selectedFileLabel.setForeground(new Color(105, 105, 105));

        // Nút xác nhận
        JButton confirmButton = new JButton("Xác Nhận");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setBackground(new Color(255, 165, 0)); // Màu cam
        confirmButton.setForeground(Color.WHITE);

                // Sự kiện chọn file
        openFileButton.addActionListener((ActionEvent e) -> {
            try {
                openFileChooser(selectedFileLabel);
            } catch (IOException ex) {
                Logger.getLogger(NativeFileChooser_import.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Sự kiện xác nhận
confirmButton.addActionListener(e -> {
    // Kiểm tra xem đã chọn file chưa
    if (questions == null || answers == null || questions.isEmpty() || answers.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Hãy chọn 1 file", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            null,
            "Bạn có chắc chắn muốn import file này?",
            "Xác nhận",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

    if (confirm == JOptionPane.YES_OPTION) {
        int topicId = topicBUS.getTopicIdByName((String) topicComboBox.getSelectedItem());
        List<String> duplicateQuestions = new ArrayList<>();
        
        for (int i = 0; i < questions.size(); i++) {
            QuestionsDTO question = questions.get(i);
            String qContent = question.getqContent();
            String qPic = question.getqPicture();
            int level = question.getqLevel();

            QuestionsDTO token = new QuestionsDTO();
            token.setqContent(qContent);
            token.setqLevel(level);
            token.setqPicture(qPic);
            token.setqStatus(1);
            token.setqTopicID(topicId);

            try {
                if (questionsBUS.isQuestionExists(qContent)) {
                    duplicateQuestions.add(qContent); // Lưu câu hỏi trùng vào danh sách
                    continue;
                }
                else{
                              }
   
                int newQuestionID = questionsBUS.createInt(token); // Thêm question mới

                if (i < answers.size()) { // Kiểm tra tránh lỗi IndexOutOfBoundsException
                    List<AnswersDTO> ansQuestion = answers.get(i);

                    for (AnswersDTO ans : ansQuestion) {
                        AnswersDTO tokenAns = new AnswersDTO();
                        tokenAns.setQID(newQuestionID);
                        tokenAns.setAwContent(ans.getAwContent());
                        tokenAns.setAwPictures(ans.getAwPictures());
                        tokenAns.setIsRight(ans.getIsRight());
                        tokenAns.setAwStatus(1);
                        answersBUS.create(tokenAns); // Thêm đáp án
                    }
                }
  
            } catch (SQLException ex) {
                Logger.getLogger(NativeFileChooser_import.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (!duplicateQuestions.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                    "Một số câu hỏi đã bị trùng và không được thêm:\n" + String.join("\n", duplicateQuestions),
                    "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Đã import file thành công", "Xác nhận", JOptionPane.INFORMATION_MESSAGE);
        }
    }
});

        // Bố cục 2 nút trong cùng 1 hàng
        GridBagConstraints gbcFileBtn = new GridBagConstraints();
        gbcFileBtn.insets = new Insets(5, 5, 5, 5);
        gbcFileBtn.fill = GridBagConstraints.HORIZONTAL;

        gbcFileBtn.gridx = 0;
        gbcFileBtn.gridy = 0;
        fileButtonPanel.add(openFileButton, gbcFileBtn);

        gbcFileBtn.gridx = 1;
        fileButtonPanel.add(downloadTemplateButton, gbcFileBtn);

        // Thêm các thành phần vào frame
        gbc.gridx = 0; gbc.gridy = 0;
        frame.add(label, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        frame.add(topicComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        frame.add(fileButtonPanel, gbc); // Thêm panel chứa 2 nút

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        frame.add(selectedFileLabel, gbc);

        gbc.gridy = 3;
        frame.add(confirmButton, gbc);

        frame.setLocationRelativeTo(null); // Hiển thị giữa màn hình
        frame.setVisible(true);
        
    }

    public void openFileChooser(JLabel fileLabel) throws FileNotFoundException, IOException {
        Frame frame = new Frame();
        FileDialog fileDialog = new FileDialog(frame, "Chọn một file", FileDialog.LOAD);
        fileDialog.setVisible(true);

        String directory = fileDialog.getDirectory();
        String filename = fileDialog.getFile();

        if (directory != null && filename != null) {
            String filePath = directory + filename;
            File file = new File(filePath);
            if (!filename.toLowerCase().endsWith(".xlsx") && !filename.toLowerCase().endsWith(".xls")) {
             JOptionPane.showMessageDialog(null, "❌ Chỉ được chọn file Excel (.xlsx, .xls)", "Lỗi", JOptionPane.ERROR_MESSAGE);
                selectedFilePath = null;
            } else if(!checkFileFormat(file)) { return; }
          
            
else {
    selectedFilePath = filePath;
    fileLabel.setText(" File: " + filename); // Hiển thị tên file đã chọn

    try (FileInputStream fis = new FileInputStream(file);
         Workbook workbook = new XSSFWorkbook(fis)) {
        
        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();

        List<QuestionsDTO> questions_token = new ArrayList<>();
        List<List<AnswersDTO>> answers_token = new ArrayList<>();

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            // Lấy nội dung câu hỏi
            Cell cell_question = row.getCell(0);
            if (cell_question == null || cell_question.getStringCellValue().trim().isEmpty()) {
                continue; // Bỏ qua nếu không có nội dung câu hỏi
            }
            String question_name = cell_question.getStringCellValue();

            // Lấy link ảnh (có thể là rỗng)
            Cell cell_Qpic = row.getCell(7);
            String question_picture = (cell_Qpic != null) ? cell_Qpic.getStringCellValue() : "";

            // Lấy level (có thể là số hoặc chữ)
            Cell cell_level = row.getCell(8);
            int level_question = 1; // Mặc định là 1
            if (cell_level != null) {
                if (cell_level.getCellType() == CellType.STRING) {
                    level_question = Integer.parseInt(cell_level.getStringCellValue().trim());
                } else if (cell_level.getCellType() == CellType.NUMERIC) {
                    level_question = (int) cell_level.getNumericCellValue();
                }
            }

            // Thêm câu hỏi vào danh sách
            QuestionsDTO question = new QuestionsDTO(1, question_name, question_picture, 1, level_question, 1);
            questions_token.add(question);

            // Xử lý danh sách đáp án
            List<AnswersDTO> list_token = new ArrayList<>();
            for (int j = 1; j <= 5; j++) { // Lưu từng đáp án
                Cell cell_contentAns = row.getCell(j);
                Cell cell_ImgAns = row.getCell(j + 8);
                Cell cell_Isright = row.getCell(6);

                String ans = (cell_contentAns != null) ? cell_contentAns.getStringCellValue() : "";
                String img_ans = (cell_ImgAns != null) ? cell_ImgAns.getStringCellValue() : "";

                int isRight = -1; // Mặc định là -1 (không có đáp án đúng)
                if (cell_Isright != null) {
                    if (cell_Isright.getCellType() == CellType.STRING) {
                        isRight = Integer.parseInt(cell_Isright.getStringCellValue().trim());
                    } else if (cell_Isright.getCellType() == CellType.NUMERIC) {
                        isRight = (int) cell_Isright.getNumericCellValue();
                    }
                }

                if (ans.isEmpty() && img_ans.isEmpty()) {
                    continue; // Bỏ qua nếu đáp án rỗng
                }

                int token = (j == isRight) ? 1 : 0;
                list_token.add(new AnswersDTO(1, 1, ans, img_ans, token, 1));
            }

            answers_token.add(list_token);
        }

        this.questions = questions_token;
        this.answers = answers_token;
    } catch (IOException | NumberFormatException e) {
        e.printStackTrace();
    }
}

        }
    }


    
    // Getter để lấy đường dẫn file đã chọn
    public String getSelectedFilePath() {
        return selectedFilePath;
    }
    

   public static boolean checkFileFormat(File file) {
    if (file == null || !file.exists()) {
        JOptionPane.showMessageDialog(null, "File không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    try (FileInputStream fis = new FileInputStream(file);
         Workbook workbook = new XSSFWorkbook(fis)) {

        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            JOptionPane.showMessageDialog(null, "File không có sheet nào", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy tiêu đề", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Danh sách tiêu đề cột theo file mới
        List<String> requiredHeaders = Arrays.asList(
            "Question Text", "Option 1", "Option 2", "Option 3", "Option 4", "Option 5",
            "Correct Answer", "Image Link", "Level", 
            "Image answer 1", "Image answer 2", "Image answer 3", "Image answer 4", "Image answer 5"
        );

        // Kiểm tra tiêu đề cột
        for (int i = 0; i < requiredHeaders.size(); i++) {
            Cell cell = headerRow.getCell(i);
            if (cell == null || !cell.getStringCellValue().trim().equalsIgnoreCase(requiredHeaders.get(i))) {
                JOptionPane.showMessageDialog(null, "Cột " + (i + 1) + " không đúng format. Cần: " + requiredHeaders.get(i),
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
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

            // Kiểm tra có ít nhất 2 đáp án hợp lệ (chữ hoặc ảnh)
            int validAnswers = 0;
            for (int j = 1; j <= 5; j++) { // Cột Option 1 -> Option 5
                Cell optionCell = row.getCell(j);
                Cell imageCell = row.getCell(j + 9); // Cột Image answer 1 -> 5

                boolean hasText = optionCell != null && !optionCell.getStringCellValue().trim().isEmpty();
                boolean hasImage = imageCell != null && !imageCell.getStringCellValue().trim().isEmpty();

                if (hasText || hasImage) {
                    validAnswers++;
                }
            }

            if (validAnswers < 2) {
                JOptionPane.showMessageDialog(null, "Lỗi: Câu hỏi ở dòng " + (i + 1) + " không có đủ 2 đáp án hợp lệ.",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Kiểm tra cột "Correct Answer"
            Cell correctAnsCell = row.getCell(6); // Cột "Correct Answer"
            if (correctAnsCell == null || correctAnsCell.getCellType() != CellType.NUMERIC) {
                JOptionPane.showMessageDialog(null, "Lỗi: 'Correct Answer' ở dòng " + (i + 1) + " không hợp lệ.",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            int correctAns = (int) correctAnsCell.getNumericCellValue();
            if (correctAns < 1 || correctAns > 5) {
                JOptionPane.showMessageDialog(null, "Lỗi: 'Correct Answer' ở dòng " + (i + 1) + " phải là số từ 1 đến 5.",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Lấy ô chứa đáp án đúng (chữ) và ô chứa ảnh của đáp án đúng
            Cell correctOptionCell = row.getCell(correctAns); // Option 1-5 (chữ)
            Cell correctImageCell = row.getCell(correctAns + 9); // Image answer 1-5

            boolean correctHasText = correctOptionCell != null && !correctOptionCell.getStringCellValue().trim().isEmpty();
            boolean correctHasImage = correctImageCell != null && !correctImageCell.getStringCellValue().trim().isEmpty();

            // Nếu đáp án đúng trỏ vào ô không có cả chữ lẫn ảnh -> Báo lỗi
            if (!correctHasText && !correctHasImage) {
                JOptionPane.showMessageDialog(null, "Lỗi: 'Correct Answer' ở dòng " + (i + 1) + " trỏ vào ô không có đáp án.",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Kiểm tra cột "Level"
            Cell levelCell = row.getCell(8); // Cột "Level"
            if (levelCell == null || levelCell.getCellType() != CellType.NUMERIC) {
                JOptionPane.showMessageDialog(null, "Lỗi: Cột 'Level' ở dòng " + (i + 1) + " không hợp lệ.",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            int level = (int) levelCell.getNumericCellValue();
            if (level < 1 || level > 3) {
                JOptionPane.showMessageDialog(null, "Lỗi: 'Level' ở dòng " + (i + 1) + " phải là 1, 2 hoặc 3.",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        JOptionPane.showMessageDialog(null, "File đúng format!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        return true;

    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Lỗi khi đọc file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
   
   public void createExcelTemplate(String filePath) {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Template");

    // Tạo hàng đầu tiên (tiêu đề cột)
    String[] headers = {"Question Text", "Option 1", "Option 2", "Option 3", "Option 4", "Option 5",
                        "Correct Answer", "Image Link", "Level", "Image answer 1", "Image answer 2",
                        "Image answer 3", "Image answer 4", "Image answer 5"};

    Row headerRow = sheet.createRow(0);
    for (int i = 0; i < headers.length; i++) {
        Cell cell = headerRow.createCell(i);
        cell.setCellValue(headers[i]);

        // Định dạng in đậm cho tiêu đề
        CellStyle style = workbook.createCellStyle();
        cell.setCellStyle(style);
    }

    // Ghi file
    try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
        workbook.write(fileOut);
        workbook.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}

 


