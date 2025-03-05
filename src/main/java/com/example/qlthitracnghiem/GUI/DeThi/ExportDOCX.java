package com.example.qlthitracnghiem.GUI.DeThi;

import com.example.qlthitracnghiem.BUS.ExamBUS;
import com.example.qlthitracnghiem.DTO.TestDTO;
import java.io.ByteArrayInputStream;
import org.apache.poi.xwpf.usermodel.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;

public class ExportDOCX {

    public static String chooseFileToSave() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export DOCX");
        fileChooser.setAcceptAllFileFilterUsed(false); // Tắt lựa chọn "All files"

        // Chỉ cho phép chọn file .docx
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Word Documents (*.docx)", "docx");
        fileChooser.addChoosableFileFilter(filter);

        // Hiển thị hộp thoại Save
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Đảm bảo file có đuôi .docx
            if (!filePath.toLowerCase().endsWith(".docx")) {
                filePath += ".docx";
            }

            return filePath;
        }

        return null;
    }

    // Phương thức để xuất dữ liệu ra file .docx
    public static void exportToDocx(TestDTO test, String examCodes, String filePath)
            throws IOException, Exception {
        XWPFDocument document = new XWPFDocument();
        ExamBUS examBUS = new ExamBUS();
        // Tạo tiêu đề cho mỗi mã đề
        XWPFParagraph titleParagraph = document.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setBold(true);
        titleRun.setFontSize(14);
        titleRun.setText("Tên bài thi: " + test.getTestTitle());
        titleRun.addBreak();

        titleRun.setText("Thời gian: " + test.getTestTime() + " phút");
        titleRun.addBreak();

        titleRun.setText("Mã Đề: " + examCodes);
        titleRun.addBreak();

        List<Integer> exQuesIDs = examBUS.getExQuesIDsByExCode(examCodes);
        int questionNumber = 1;

        for (int qID : exQuesIDs) {
            // Lấy nội dung câu hỏi và hình ảnh
            Map<String, String> questionData = examBUS.getQuestionContent(qID);
            String qContent = questionData.get("qContent");
            String qPictures = questionData.get("qPictures");

            // Tạo đoạn văn cho câu hỏi
            XWPFParagraph questionParagraph = document.createParagraph();
            XWPFRun questionRun = questionParagraph.createRun();
            questionRun.setText("Câu " + questionNumber + ": " + qContent);
            questionRun.addBreak();

            if (qPictures != null && !qPictures.isEmpty()) {
                addImageToDocument(questionRun, qPictures);
            }

            // Lấy danh sách câu trả lời
            List<Map<String, String>> awContents = examBUS.getAnswerContent(qID);
            for (Map<String, String> answerData : awContents) {
                String awContent = answerData.get("awContent");
                String awPictures = answerData.get("awPictures");

                XWPFParagraph answerParagraph = document.createParagraph();
                XWPFRun answerRun = answerParagraph.createRun();
                answerRun.setText(" - " + awContent);
                answerRun.addBreak();

                if (awPictures != null && !awPictures.isEmpty()) {
                    addImageToDocument(answerRun, awPictures);
                }
            }

            questionNumber++;
        }

        // Thêm dòng phân cách giữa các mã đề
        XWPFParagraph separatorParagraph = document.createParagraph();
        XWPFRun separatorRun = separatorParagraph.createRun();
        separatorRun.setText(
                "------------------------------------------------------------------------------------------------");
        separatorRun.addBreak();

        // Ghi file
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            document.write(out);
        }

        document.close();
    }

    public static void addImageToDocument(XWPFRun run, String imagePath) throws IOException, InvalidFormatException {
        InputStream imageStream = null;

        // đường dẫn tương đối
        imageStream = ExportDOCX.class.getClassLoader()
                .getResourceAsStream(imagePath.startsWith("/") ? imagePath.substring(1) : imagePath); // má vcl lấy mãi
                                                                                                      // k đc cái path
                                                                                                      // hóa ra do cái
                                                                                                      // dấu /pictures
                                                                                                      // bên db, cmn
        // System.err.println("imageStream" + imageStream);
        if (imageStream != null) {
            // Chuyển đổi hình ảnh thành mảng byte
            byte[] imageBytes = IOUtils.toByteArray(imageStream);

            // Chuyển đổi mảng byte thành InputStream
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);

            // Chèn hình ảnh vào tài liệu
            run.addPicture(byteArrayInputStream, XWPFDocument.PICTURE_TYPE_PNG, imagePath, Units.toEMU(200),
                    Units.toEMU(200));
        } else {
            throw new IOException("Không thể tìm thấy hình ảnh: " + imagePath);
        }
    }

    public static InputStream getResourceAsStream(String relativePath) {
        return ExportDOCX.class.getClassLoader().getResourceAsStream(relativePath);
    }

    public static String getAbsolutePath(String relativePath) {
        // Chuyển đổi đường dẫn tương đối thành đường dẫn tuyệt đối
        return Paths.get(relativePath).toAbsolutePath().toString();
    }
}
