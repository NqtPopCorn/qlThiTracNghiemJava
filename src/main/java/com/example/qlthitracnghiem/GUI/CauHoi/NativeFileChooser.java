package com.example.qlthitracnghiem.GUI.CauHoi;

import java.awt.FileDialog;
import java.awt.Frame;
import javax.swing.JOptionPane;

public class NativeFileChooser {
    private String selectedFilePath; // Biến lưu đường dẫn file

    
    public NativeFileChooser(){};
    
    
    public void openFileChooser() {
        Frame frame = new Frame(); // Tạo frame ẩn
        FileDialog fileDialog = new FileDialog(frame, "Chọn một file", FileDialog.LOAD);

        fileDialog.setVisible(true); // Hiển thị hộp thoại
         fileDialog.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
        // Lấy file đã chọn
        String directory = fileDialog.getDirectory();
        String filename = fileDialog.getFile();

        if (filename != null) {
            // Chỉ chấp nhận file PNG hoặc JPG
            if (filename.toLowerCase().endsWith(".png") || filename.toLowerCase().endsWith(".jpg") || filename.toLowerCase().endsWith(".jpeg")) {
                selectedFilePath =  filename;
                System.out.println("File đã chọn: " + selectedFilePath);
            } else {
                JOptionPane.showMessageDialog(null,"Chỉ được chọn file PNG hoặc file JPG", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
                selectedFilePath = null; // Đặt lại nếu chọn sai định dạng
            }
        } else {
            System.out.println("Không có file nào được chọn.");
            selectedFilePath = null;
        }

        frame.dispose(); // Đóng frame sau khi chọn file
    }

    // Getter để lấy tên file đã chọn
    public String getSelectedFilePath() {
        return selectedFilePath;
    }
}
