/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.ThongKe;

import com.example.qlthitracnghiem.BUS.ExamBUS;
import com.example.qlthitracnghiem.BUS.ThongKeBUS;
import com.example.qlthitracnghiem.DTO.ExamDTO;
import com.example.qlthitracnghiem.DTO.TestDTO;
import com.example.qlthitracnghiem.DTO.TopicsDTO;
import com.example.qlthitracnghiem.DTO.UserDTO;
import java.awt.CardLayout;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

/**
 *
 * @author Asus
 */
public class MainThongKePanel extends javax.swing.JPanel {

    public MainThongKePanel() {
        initComponents();
        initCartForThongKe();
        initDataForAllComboBox();
        initActions();
        invisibleComponentsNotUse();
    }
    
    //-------------------------------CODE--------------------------------------
    private List<UserDTO> userList;
    private List<TestDTO> testList;
    private List<String> examCodeList;
    
    DefaultComboBoxModel<String> modelUserTong = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> modelUserSoSanh = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> modelTestTong = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> modelTestBaiThi = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> modelTestSoSanh = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> modelExamBaiThi = new DefaultComboBoxModel<>();
    
    private String testCodeTKBaiThi = ""; // dùng để search examCode
    private String examCodeTKBaiThi = "";
    private String userId = ""; // dùng cho LichSuPanel.java
    
    private JPanel lichSuPanel;
    
    public void initCartForThongKe() {
        thongKeTongCardPanel.setBackground(Color.white);
        thongKeBaiThiCardPanel.setBackground(Color.white);
        thongKeSoSanhCardPanel.setBackground(Color.white);
        thongKeLichSuCardPanel.setBackground(Color.white);
        hienThiThongKePanel.setLayout(cardLayout);
        hienThiThongKePanel.add(thongKeTongCardPanel, thongKeTongLB.getText());
        hienThiThongKePanel.add(thongKeBaiThiCardPanel, thongKeBaiThiLB.getText());
        hienThiThongKePanel.add(thongKeSoSanhCardPanel, thongKeSoSanhLB.getText());
        hienThiThongKePanel.add(thongKeLichSuCardPanel, thongKeLichSuLB.getText());
    }
    
    // Tắt các component không xài
    public void invisibleComponentsNotUse() {
        thongKeSoSanhPanel.setVisible(false);
    }
    
    // Tạo model chung cho các combobox
    public void initDeFaultComboBoxModel(DefaultComboBoxModel<String> model, List<?> list) {
       // Xóa toàn bộ dữ liệu cũ trong ComboBox
       model.removeAllElements();

       // Nếu danh sách rỗng, thoát luôn (không cần duyệt)
       if (list == null || list.isEmpty()) {
           return;
       }
    
        for (Object obj : list) {
            if (obj instanceof TestDTO test) { // Java 16+ hỗ trợ pattern matching
                if (test.getTestStatus() == 1) {
                    model.addElement(test.getTestCode() + "-" + test.getTestTitle());
                }
            } else if (obj instanceof UserDTO user) {
                if (user.isAdmin() != 1) {
                    model.addElement(user.getUserID() + "-" + user.getUserFullName());
                }
            }
        }
    }

    // Load dữ liệu cho các combobox
    public void initDataForAllComboBox() {
        // users
        userList = ThongKeBUS.getInstance().getUserListBus();
        initDeFaultComboBoxModel(modelUserTong, userList);     
        initDeFaultComboBoxModel(modelUserSoSanh, userList);     
        searchUserThongKeCB.setModel(modelUserTong);
        searchUserSoSanhKetQuaCB.setModel(modelUserSoSanh);
 
        // tests
        testList = ThongKeBUS.getInstance().getTestListBus();
        initDeFaultComboBoxModel(modelTestTong, testList); 
        initDeFaultComboBoxModel(modelTestBaiThi, testList);     
        initDeFaultComboBoxModel(modelTestSoSanh, testList);
        searchTestTKTongCB.setModel(modelTestTong);
        testSearchBaiThiComboBox.setModel(modelTestBaiThi);
        searchTestSoSanhKetQuaCB.setModel(modelTestSoSanh);
        
        // exams following by TestCode
        examSearchBaiThiComboBox.setModel(modelExamBaiThi);
    }
    
    // Khởi tạo các action cho combobox
    public void initActions() {
        // Tests
        searchTestTKTongCB.addActionListener(e -> {
            String selectedValue = (String) searchTestTKTongCB.getSelectedItem();
            if (selectedValue != null) {
                String[] parts = selectedValue.split("-");
                String testCode = parts[0].trim(); // Lấy ID
                String testTitle = parts.length > 1 ? parts[1].trim() : "Khong tim thay"; // Lấy tên
                
                // Cap nhat thong ke
                testCodeTKTongLB.setText("TestCode: " + testCode);     
                try {
                    int tongSoLuongThiSinh = ThongKeBUS.getInstance().getSoLuongThiSinhLamBaiByTestCode(testCode);
                    int soLuongThiSinhDat = ThongKeBUS.getInstance().getSoLuongThiSinhDatHoacKhongDatByTestCode(testCode, "DAT");
                    int soLuongThiSinhKhongDat = ThongKeBUS.getInstance().getSoLuongThiSinhDatHoacKhongDatByTestCode(testCode, "KHONG DAT");
                    int soLuongThiSinhXuatSac = ThongKeBUS.getInstance().getSoThiSinhXuatSacByTestCode(testCode);
                    soLuongThiSinhLamBaiThiLB.setText("Số lượng thí sinh làm bài: " + 
                            String.valueOf(tongSoLuongThiSinh));
                    maxScoreTKTongLB.setText(
                            String.valueOf(ThongKeBUS.getInstance().getMaxScoreByTestCode(testCode)));
                    minScoreTKTongLB.setText(
                            String.valueOf(ThongKeBUS.getInstance().getMinScoreByTestCode(testCode)));
                    soLuongThiSinhDatLB.setText("Số lượng thí sinh đạt (>=50%): " + 
                            String.valueOf(soLuongThiSinhDat));
                    soLuongThiSinhKhongDatLB.setText("Số lượng thí sinh không đạt: " + 
                            String.valueOf(soLuongThiSinhKhongDat));
                    soThiSinhXuatSacLB.setText("Số thí sinh xuất sắc (>9): " + 
                            String.valueOf(soLuongThiSinhXuatSac));
                    if(soLuongThiSinhDat >= soLuongThiSinhKhongDat)
                        thongBaoTienDoHocTapTKTongLB.setText("Tiến Độ Học Tập Trung Bình Của Các Thành Viên: ĐẠT");
                    else 
                        thongBaoTienDoHocTapTKTongLB.setText("Tiến Độ Học Tập Trung Bình Của Các Thành Viên: FAIL");
                    // Cap nhat ProgressBar
                    soLuongThiSinhDatPB.setValue((int) (((double) soLuongThiSinhDat / tongSoLuongThiSinh) * 100));
                    soLuongThiSinhDatPB.setStringPainted(true);   

                    soLuongThiSinhKhongDatPB.setValue((int) (((double) soLuongThiSinhKhongDat / tongSoLuongThiSinh) * 100));
                    soLuongThiSinhKhongDatPB.setStringPainted(true);     

                    soLuongThiSinhXuatSacPB.setValue((int) (((double) soLuongThiSinhXuatSac / tongSoLuongThiSinh) * 100));
                    soLuongThiSinhXuatSacPB.setStringPainted(true);    

                } catch (SQLException ex) {
                    Logger.getLogger(MainThongKePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        testSearchBaiThiComboBox.addActionListener(e -> {
            String selectedValue = (String) testSearchBaiThiComboBox.getSelectedItem();
            if (selectedValue != null) {
                String[] parts = selectedValue.split("-");
                String testCode = parts[0].trim(); // Lấy ID
                String testTitle = parts.length > 1 ? parts[1].trim() : "Khong tim thay"; // Lấy tên
                testCodeTKBaiThi = parts[0].trim(); // dùng để search examCode
                // Cap nhat thong ke
                testCodeTKBaiThiLB.setText("TestCode: " + testCode);    
                examCodeTKBaiThiLB.setText("ExamCode:");
                try {
                    examCodeList = ThongKeBUS.getInstance().getListExamByTestCode(testCode);
                    modelExamBaiThi.removeAllElements();
                    for(String value: examCodeList) {
                        modelExamBaiThi.addElement(value);
                    }
                    examSearchBaiThiComboBox.setModel(modelExamBaiThi);
                } catch (SQLException ex) {
                    Logger.getLogger(MainThongKePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // Exams
        examSearchBaiThiComboBox.addActionListener(e -> {
            String selectedValue = (String) examSearchBaiThiComboBox.getSelectedItem();
            if (selectedValue != null) {
                examCodeTKBaiThi = selectedValue.trim();
                // Cap nhat thong ke 
                examCodeTKBaiThiLB.setText("ExamCode:" + examCodeTKBaiThi);
                try {
                    int soLuongThiSinhDat = ThongKeBUS.getInstance().getSoLuongThiSinhDatHoacKhongDatByExamCode(examCodeTKBaiThi, "DAT");
                    int soLuongThiSinhKhongDat = ThongKeBUS.getInstance().getSoLuongThiSinhDatHoacKhongDatByExamCode(examCodeTKBaiThi, "KHONG DAT");
                    int tongSoLuongThiSinh = soLuongThiSinhDat + soLuongThiSinhKhongDat;
                    int soLuongThiSinhXuatSac = ThongKeBUS.getInstance().getSoThiSinhXuatSacByExamCode(examCodeTKBaiThi);
                    highScoreThongKeBaiThiLB.setText(
                            String.valueOf(ThongKeBUS.getInstance().getMaxScoreByExamCode(examCodeTKBaiThi)));
                    lowScoreThongKeBaiThiLB.setText(
                            String.valueOf(ThongKeBUS.getInstance().getMinScoreByExamCode(examCodeTKBaiThi)));
                    soThiSinhDatTKBaiThiLB.setText("Số lượng thí sinh đạt (>=50%): " + 
                            String.valueOf(soLuongThiSinhDat));
                    soThiSinhKhongDatTKBaiThiLB.setText("Số lượng thí sinh không đạt: " + 
                            String.valueOf(soLuongThiSinhKhongDat));
                    soThiSinhXuatSacTKBaiThiLB.setText("Số thí sinh xuất sắc (>9): " + 
                            String.valueOf(soLuongThiSinhXuatSac));
                    if(soLuongThiSinhDat >= soLuongThiSinhKhongDat)
                        targetThongKeBaiThiLB.setText("ĐẠT");
                    else 
                        targetThongKeBaiThiLB.setText("FAIL");
                    // Cap nhat ProgressBar
                    soThiSinhDatTKBaiThiPB.setValue((int) (((double) soLuongThiSinhDat / tongSoLuongThiSinh) * 100));
                    soThiSinhDatTKBaiThiPB.setStringPainted(true);   

                    soThiSinhKhongDatTKBaiThiPB.setValue((int) (((double) soLuongThiSinhKhongDat / tongSoLuongThiSinh) * 100));
                    soThiSinhKhongDatTKBaiThiPB.setStringPainted(true);     

                    soThiSinhXuatSacTKBaiThiPB.setValue((int) (((double) soLuongThiSinhXuatSac / tongSoLuongThiSinh) * 100));
                    soThiSinhXuatSacTKBaiThiPB.setStringPainted(true);    
                } catch (SQLException ex) {
                    Logger.getLogger(MainThongKePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // Users
        searchUserThongKeCB.addActionListener(e -> {
            String selectedValue = (String) searchUserThongKeCB.getSelectedItem();

            if (selectedValue != null) {
                // Tách ID từ chuỗi (nếu chuỗi có dạng "123 - Nguyễn Văn A")
                String[] parts = selectedValue.split("-");
                userId = parts[0].trim(); // Lấy ID
                String userName = parts.length > 1 ? parts[1].trim() : "Khong tim thay"; // Lấy tên
                
                userIdThongKeLB.setText(userId);
                userNameThongKeLB.setText(userName);
                for(UserDTO user: userList) {
                    if(String.valueOf(user.getUserID()).equals(userId)) {
                        fullNameThongKeLB.setText(user.getUserFullName());
                        emailThongKeLB.setText(user.getUserEmail());
                        break;
                    }
                }

                // Load lich su exam cua userID
                this.lichSuPanel = new LichSuPanel(userId, "");
                lichSuBaiThiPN.removeAll();
                lichSuBaiThiPN.add(lichSuPanel);
                lichSuBaiThiPN.revalidate();
                lichSuBaiThiPN.repaint();
            }
        });
    }
    
    //-------------------------------END-CODE--------------------------------------

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        chonThongKePanel = new javax.swing.JPanel();
        tieuDeThongKePN = new javax.swing.JPanel();
        tieuDePN = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        searchUserPN = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        searchUserThongKeTF = new javax.swing.JTextField();
        searchUserThongKeCB = new javax.swing.JComboBox<>();
        thongTinUserThongKePN = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        userIdThongKeLB = new javax.swing.JLabel();
        userNameThongKeLB = new javax.swing.JLabel();
        fullNameThongKeLB = new javax.swing.JLabel();
        emailThongKeLB = new javax.swing.JLabel();
        luaChonThongKe1 = new javax.swing.JPanel();
        thongKeTongPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        thongKeTongLB = new javax.swing.JLabel();
        thongKeBaiThiPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        thongKeBaiThiLB = new javax.swing.JLabel();
        luaChonThongKe2 = new javax.swing.JPanel();
        thongKeLichSuPanel = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        thongKeLichSuLB = new javax.swing.JLabel();
        thongKeSoSanhPanel = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        thongKeSoSanhLB = new javax.swing.JLabel();
        hienThiThongKePanel = new javax.swing.JPanel();
        thongKeBaiThiCardPanel = new javax.swing.JPanel();
        thongKeBaiThiCardLB = new javax.swing.JLabel();
        thongKeBaiThiCardPN = new javax.swing.JPanel();
        progressThongKeBaiThiCardPN = new javax.swing.JPanel();
        scoreThongKeBaiThiPN = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        JLabel10 = new javax.swing.JLabel();
        highScoreThongKeBaiThiLB = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lowScoreThongKeBaiThiLB = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        targetThongKeBaiThiLB = new javax.swing.JLabel();
        progressThongKeBaiThiPN = new javax.swing.JPanel();
        testCodeTKBaiThiLB = new javax.swing.JLabel();
        examCodeTKBaiThiLB = new javax.swing.JLabel();
        soThiSinhDatTKBaiThiLB = new javax.swing.JLabel();
        soThiSinhDatTKBaiThiPB = new javax.swing.JProgressBar();
        soThiSinhKhongDatTKBaiThiLB = new javax.swing.JLabel();
        soThiSinhKhongDatTKBaiThiPB = new javax.swing.JProgressBar();
        soThiSinhXuatSacTKBaiThiLB = new javax.swing.JLabel();
        soThiSinhXuatSacTKBaiThiPB = new javax.swing.JProgressBar();
        searchThongKeBaiThiCardPN = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        testSearchBaiThiComboBox = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        testSearchBaiThiTF = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        examSearchBaiThiComboBox = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        examSearchBaiThiTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        thongKeSoSanhCardPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        searchSoSanhKetQuaPN = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        searchTestSoSanhKetQuaTF = new javax.swing.JTextField();
        searchTestSoSanhKetQuaCB = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        searchExamSoSanhKetQuaTF = new javax.swing.JTextField();
        searchExamSoSanhKetQuaCB = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        searchUserSoSanhKetQuaTF = new javax.swing.JTextField();
        searchUserSoSanhKetQuaCB = new javax.swing.JComboBox<>();
        ketQuaTimKiemSoSanhKetQuaLB = new javax.swing.JLabel();
        ketQuaUser1PN = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        soCauDungUser1PB = new javax.swing.JProgressBar();
        jLabel24 = new javax.swing.JLabel();
        soCauTraLoiSaiUser1PB = new javax.swing.JProgressBar();
        jLabel28 = new javax.swing.JLabel();
        thoiGianHTUser1PB = new javax.swing.JProgressBar();
        jPanel13 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        highScoreUser1LB = new javax.swing.JLabel();
        ketQuaUser2PN = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        soCauDungUser2PB = new javax.swing.JProgressBar();
        jLabel35 = new javax.swing.JLabel();
        soCauTraLoiSaiUser2PB = new javax.swing.JProgressBar();
        jLabel36 = new javax.swing.JLabel();
        thoiGianHTUser2PB = new javax.swing.JProgressBar();
        jPanel20 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        highScoreUser2LB = new javax.swing.JLabel();
        thongKeLichSuCardPanel = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchForExamLichSuTF = new javax.swing.JTextField();
        lichSuBaiThiPN = new javax.swing.JPanel();
        thongKeTongCardPanel = new javax.swing.JPanel();
        tieuDeThongKeTongPN = new javax.swing.JPanel();
        thongKeTongCardLB = new javax.swing.JLabel();
        searchThongKeTongPN = new javax.swing.JPanel();
        searchTopicAndExamTKTongPN = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        searchTestTKTongCB = new javax.swing.JComboBox<>();
        searchTestTKTongTF = new javax.swing.JTextField();
        thongKeTongScorePanel = new javax.swing.JPanel();
        scoreLB = new javax.swing.JLabel();
        highScorePN = new javax.swing.JPanel();
        highScoreLB = new javax.swing.JLabel();
        maxScoreTKTongLB = new javax.swing.JLabel();
        lowScorePN = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        minScoreTKTongLB = new javax.swing.JLabel();
        averageScorePN = new javax.swing.JPanel();
        thongKeTongProgressPanel = new javax.swing.JPanel();
        testCodeTKTongLB = new javax.swing.JLabel();
        soLuongThiSinhLamBaiThiLB = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        soLuongThiSinhDatLB = new javax.swing.JLabel();
        soLuongThiSinhDatPB = new javax.swing.JProgressBar();
        soLuongThiSinhKhongDatLB = new javax.swing.JLabel();
        soLuongThiSinhKhongDatPB = new javax.swing.JProgressBar();
        soThiSinhXuatSacLB = new javax.swing.JLabel();
        soLuongThiSinhXuatSacPB = new javax.swing.JProgressBar();
        thongBaoTienDoHocTapTKTongLB = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1040, 628));
        setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        chonThongKePanel.setBackground(new java.awt.Color(255, 255, 255));
        chonThongKePanel.setLayout(new java.awt.GridLayout(3, 0, 0, 15));

        tieuDeThongKePN.setBackground(new java.awt.Color(255, 255, 255));
        tieuDeThongKePN.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        tieuDeThongKePN.setLayout(new java.awt.BorderLayout(0, 2));

        tieuDePN.setBackground(new java.awt.Color(255, 255, 255));
        tieuDePN.setPreferredSize(new java.awt.Dimension(523, 140));
        tieuDePN.setLayout(new java.awt.GridLayout(2, 0));

        jLabel27.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Thống Kê Tổng Và Cá Nhân Thành Viên");
        jLabel27.setPreferredSize(new java.awt.Dimension(457, 40));
        tieuDePN.add(jLabel27);

        searchUserPN.setBackground(new java.awt.Color(204, 204, 204));
        searchUserPN.setPreferredSize(new java.awt.Dimension(511, 30));
        searchUserPN.setLayout(new java.awt.BorderLayout());

        jLabel22.setBackground(new java.awt.Color(204, 204, 204));
        jLabel22.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Search User:");
        jLabel22.setPreferredSize(new java.awt.Dimension(111, 30));
        searchUserPN.add(jLabel22, java.awt.BorderLayout.PAGE_START);

        searchUserThongKeTF.setBackground(new java.awt.Color(255, 255, 255));
        searchUserThongKeTF.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        searchUserThongKeTF.setForeground(new java.awt.Color(0, 0, 0));
        searchUserThongKeTF.setPreferredSize(new java.awt.Dimension(64, 15));
        searchUserThongKeTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchUserThongKeTFKeyReleased(evt);
            }
        });
        searchUserPN.add(searchUserThongKeTF, java.awt.BorderLayout.CENTER);

        searchUserThongKeCB.setBackground(new java.awt.Color(204, 204, 204));
        searchUserThongKeCB.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        searchUserThongKeCB.setForeground(new java.awt.Color(0, 0, 0));
        searchUserThongKeCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        searchUserThongKeCB.setPreferredSize(new java.awt.Dimension(180, 25));
        searchUserPN.add(searchUserThongKeCB, java.awt.BorderLayout.LINE_END);

        tieuDePN.add(searchUserPN);

        tieuDeThongKePN.add(tieuDePN, java.awt.BorderLayout.CENTER);

        thongTinUserThongKePN.setBackground(new java.awt.Color(0, 0, 0));
        thongTinUserThongKePN.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0)));
        thongTinUserThongKePN.setPreferredSize(new java.awt.Dimension(511, 110));
        thongTinUserThongKePN.setLayout(new java.awt.GridLayout(1, 0, 2, 0));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(new java.awt.GridLayout(4, 0));

        jLabel31.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("UserId:");
        jPanel18.add(jLabel31);

        jLabel32.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("UserName:");
        jPanel18.add(jLabel32);

        jLabel33.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Fullname:");
        jPanel18.add(jLabel33);

        jLabel37.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Email:");
        jPanel18.add(jLabel37);

        thongTinUserThongKePN.add(jPanel18);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(new java.awt.GridLayout(4, 0));

        userIdThongKeLB.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
        userIdThongKeLB.setForeground(new java.awt.Color(0, 0, 0));
        jPanel19.add(userIdThongKeLB);

        userNameThongKeLB.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
        userNameThongKeLB.setForeground(new java.awt.Color(0, 0, 0));
        jPanel19.add(userNameThongKeLB);

        fullNameThongKeLB.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
        fullNameThongKeLB.setForeground(new java.awt.Color(0, 0, 0));
        jPanel19.add(fullNameThongKeLB);

        emailThongKeLB.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
        emailThongKeLB.setForeground(new java.awt.Color(0, 0, 0));
        jPanel19.add(emailThongKeLB);

        thongTinUserThongKePN.add(jPanel19);

        tieuDeThongKePN.add(thongTinUserThongKePN, java.awt.BorderLayout.PAGE_END);

        chonThongKePanel.add(tieuDeThongKePN);

        luaChonThongKe1.setBackground(new java.awt.Color(255, 255, 255));
        luaChonThongKe1.setPreferredSize(new java.awt.Dimension(700, 207));
        luaChonThongKe1.setLayout(new java.awt.GridLayout(1, 0, 20, 5));

        thongKeTongPanel.setBackground(new java.awt.Color(255, 153, 153));
        thongKeTongPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        thongKeTongPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thongKeTongPanelMouseClicked(evt);
            }
        });

        thongKeTongLB.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        thongKeTongLB.setForeground(new java.awt.Color(0, 0, 0));
        thongKeTongLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        thongKeTongLB.setText("Thống Kê Tổng");

        javax.swing.GroupLayout thongKeTongPanelLayout = new javax.swing.GroupLayout(thongKeTongPanel);
        thongKeTongPanel.setLayout(thongKeTongPanelLayout);
        thongKeTongPanelLayout.setHorizontalGroup(
            thongKeTongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongKeTongPanelLayout.createSequentialGroup()
                .addGroup(thongKeTongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(thongKeTongPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(thongKeTongLB, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)))
                .addContainerGap())
        );
        thongKeTongPanelLayout.setVerticalGroup(
            thongKeTongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thongKeTongPanelLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thongKeTongLB, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        luaChonThongKe1.add(thongKeTongPanel);

        thongKeBaiThiPanel.setBackground(new java.awt.Color(153, 255, 153));
        thongKeBaiThiPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        thongKeBaiThiPanel.setForeground(new java.awt.Color(0, 0, 0));
        thongKeBaiThiPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thongKeBaiThiPanelMouseClicked(evt);
            }
        });

        thongKeBaiThiLB.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        thongKeBaiThiLB.setForeground(new java.awt.Color(0, 0, 0));
        thongKeBaiThiLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        thongKeBaiThiLB.setText("Thống Kê Bài Thi");

        javax.swing.GroupLayout thongKeBaiThiPanelLayout = new javax.swing.GroupLayout(thongKeBaiThiPanel);
        thongKeBaiThiPanel.setLayout(thongKeBaiThiPanelLayout);
        thongKeBaiThiPanelLayout.setHorizontalGroup(
            thongKeBaiThiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongKeBaiThiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(thongKeBaiThiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                    .addComponent(thongKeBaiThiLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        thongKeBaiThiPanelLayout.setVerticalGroup(
            thongKeBaiThiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongKeBaiThiPanelLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(thongKeBaiThiLB, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        luaChonThongKe1.add(thongKeBaiThiPanel);

        chonThongKePanel.add(luaChonThongKe1);

        luaChonThongKe2.setBackground(new java.awt.Color(255, 255, 255));
        luaChonThongKe2.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        thongKeLichSuPanel.setBackground(new java.awt.Color(153, 153, 255));
        thongKeLichSuPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        thongKeLichSuPanel.setPreferredSize(new java.awt.Dimension(350, 205));
        thongKeLichSuPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thongKeLichSuPanelMouseClicked(evt);
            }
        });

        thongKeLichSuLB.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        thongKeLichSuLB.setForeground(new java.awt.Color(0, 0, 0));
        thongKeLichSuLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        thongKeLichSuLB.setText("Lịch Sử Bài Thi");

        javax.swing.GroupLayout thongKeLichSuPanelLayout = new javax.swing.GroupLayout(thongKeLichSuPanel);
        thongKeLichSuPanel.setLayout(thongKeLichSuPanelLayout);
        thongKeLichSuPanelLayout.setHorizontalGroup(
            thongKeLichSuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongKeLichSuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(thongKeLichSuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                    .addComponent(thongKeLichSuLB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                .addContainerGap())
        );
        thongKeLichSuPanelLayout.setVerticalGroup(
            thongKeLichSuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongKeLichSuPanelLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thongKeLichSuLB, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        luaChonThongKe2.add(thongKeLichSuPanel);

        thongKeSoSanhPanel.setBackground(new java.awt.Color(153, 255, 255));
        thongKeSoSanhPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        thongKeSoSanhPanel.setPreferredSize(new java.awt.Dimension(350, 205));
        thongKeSoSanhPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thongKeSoSanhPanelMouseClicked(evt);
            }
        });

        thongKeSoSanhLB.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        thongKeSoSanhLB.setForeground(new java.awt.Color(0, 0, 0));
        thongKeSoSanhLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        thongKeSoSanhLB.setText("So Sánh Kết Quả");

        javax.swing.GroupLayout thongKeSoSanhPanelLayout = new javax.swing.GroupLayout(thongKeSoSanhPanel);
        thongKeSoSanhPanel.setLayout(thongKeSoSanhPanelLayout);
        thongKeSoSanhPanelLayout.setHorizontalGroup(
            thongKeSoSanhPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongKeSoSanhPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(thongKeSoSanhPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(thongKeSoSanhLB, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                .addContainerGap())
        );
        thongKeSoSanhPanelLayout.setVerticalGroup(
            thongKeSoSanhPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongKeSoSanhPanelLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thongKeSoSanhLB, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        luaChonThongKe2.add(thongKeSoSanhPanel);

        chonThongKePanel.add(luaChonThongKe2);

        add(chonThongKePanel);

        hienThiThongKePanel.setBackground(new java.awt.Color(255, 255, 153));
        hienThiThongKePanel.setLayout(new java.awt.CardLayout());

        thongKeBaiThiCardPanel.setBackground(new java.awt.Color(255, 255, 255));
        thongKeBaiThiCardPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0), new java.awt.Color(153, 153, 153)));
        thongKeBaiThiCardPanel.setLayout(new java.awt.BorderLayout(5, 10));

        thongKeBaiThiCardLB.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        thongKeBaiThiCardLB.setForeground(new java.awt.Color(0, 0, 0));
        thongKeBaiThiCardLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        thongKeBaiThiCardLB.setText("Thống Kê Bài Thi");
        thongKeBaiThiCardLB.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        thongKeBaiThiCardLB.setPreferredSize(new java.awt.Dimension(200, 40));
        thongKeBaiThiCardPanel.add(thongKeBaiThiCardLB, java.awt.BorderLayout.PAGE_START);

        thongKeBaiThiCardPN.setBackground(new java.awt.Color(255, 255, 255));
        thongKeBaiThiCardPN.setLayout(new java.awt.BorderLayout(0, 10));

        progressThongKeBaiThiCardPN.setBackground(new java.awt.Color(153, 255, 153));
        progressThongKeBaiThiCardPN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        progressThongKeBaiThiCardPN.setPreferredSize(new java.awt.Dimension(509, 150));
        progressThongKeBaiThiCardPN.setLayout(new java.awt.BorderLayout(10, 0));

        scoreThongKeBaiThiPN.setBackground(new java.awt.Color(255, 255, 255));
        scoreThongKeBaiThiPN.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0), new java.awt.Color(153, 153, 153)));
        scoreThongKeBaiThiPN.setPreferredSize(new java.awt.Dimension(180, 465));
        scoreThongKeBaiThiPN.setLayout(new java.awt.GridLayout(4, 0));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Score");
        jPanel6.add(jLabel9);

        scoreThongKeBaiThiPN.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        JLabel10.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        JLabel10.setForeground(new java.awt.Color(51, 153, 0));
        JLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabel10.setText("High Score");
        jPanel7.add(JLabel10, java.awt.BorderLayout.PAGE_END);

        highScoreThongKeBaiThiLB.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        highScoreThongKeBaiThiLB.setForeground(new java.awt.Color(0, 0, 0));
        highScoreThongKeBaiThiLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        highScoreThongKeBaiThiLB.setText("100");
        jPanel7.add(highScoreThongKeBaiThiLB, java.awt.BorderLayout.CENTER);

        scoreThongKeBaiThiPN.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel12.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 153, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Low Score");
        jPanel8.add(jLabel12, java.awt.BorderLayout.PAGE_END);

        lowScoreThongKeBaiThiLB.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lowScoreThongKeBaiThiLB.setForeground(new java.awt.Color(0, 0, 0));
        lowScoreThongKeBaiThiLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lowScoreThongKeBaiThiLB.setText("50");
        jPanel8.add(lowScoreThongKeBaiThiLB, java.awt.BorderLayout.CENTER);

        scoreThongKeBaiThiPN.add(jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 153, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Target");
        jPanel9.add(jLabel14, java.awt.BorderLayout.PAGE_END);

        targetThongKeBaiThiLB.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        targetThongKeBaiThiLB.setForeground(new java.awt.Color(0, 0, 0));
        targetThongKeBaiThiLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        targetThongKeBaiThiLB.setText("Đạt");
        jPanel9.add(targetThongKeBaiThiLB, java.awt.BorderLayout.CENTER);

        scoreThongKeBaiThiPN.add(jPanel9);

        progressThongKeBaiThiCardPN.add(scoreThongKeBaiThiPN, java.awt.BorderLayout.LINE_END);

        progressThongKeBaiThiPN.setBackground(new java.awt.Color(255, 255, 255));
        progressThongKeBaiThiPN.setLayout(new java.awt.GridLayout(8, 0));

        testCodeTKBaiThiLB.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        testCodeTKBaiThiLB.setForeground(new java.awt.Color(0, 0, 0));
        testCodeTKBaiThiLB.setText("TestCode: ");
        progressThongKeBaiThiPN.add(testCodeTKBaiThiLB);

        examCodeTKBaiThiLB.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        examCodeTKBaiThiLB.setForeground(new java.awt.Color(0, 0, 0));
        examCodeTKBaiThiLB.setText("ExamCode:");
        progressThongKeBaiThiPN.add(examCodeTKBaiThiLB);

        soThiSinhDatTKBaiThiLB.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        soThiSinhDatTKBaiThiLB.setForeground(new java.awt.Color(0, 0, 0));
        soThiSinhDatTKBaiThiLB.setText("Số thí sinh đạt (>=50%):");
        progressThongKeBaiThiPN.add(soThiSinhDatTKBaiThiLB);

        soThiSinhDatTKBaiThiPB.setBackground(new java.awt.Color(204, 204, 204));
        soThiSinhDatTKBaiThiPB.setForeground(new java.awt.Color(153, 255, 153));
        progressThongKeBaiThiPN.add(soThiSinhDatTKBaiThiPB);

        soThiSinhKhongDatTKBaiThiLB.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        soThiSinhKhongDatTKBaiThiLB.setForeground(new java.awt.Color(0, 0, 0));
        soThiSinhKhongDatTKBaiThiLB.setText("Số thí sinh không đạt:");
        progressThongKeBaiThiPN.add(soThiSinhKhongDatTKBaiThiLB);

        soThiSinhKhongDatTKBaiThiPB.setBackground(new java.awt.Color(204, 204, 204));
        soThiSinhKhongDatTKBaiThiPB.setForeground(new java.awt.Color(153, 255, 153));
        progressThongKeBaiThiPN.add(soThiSinhKhongDatTKBaiThiPB);

        soThiSinhXuatSacTKBaiThiLB.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        soThiSinhXuatSacTKBaiThiLB.setForeground(new java.awt.Color(0, 0, 0));
        soThiSinhXuatSacTKBaiThiLB.setText("Số thí sinh xuất sắc (>9)");
        progressThongKeBaiThiPN.add(soThiSinhXuatSacTKBaiThiLB);

        soThiSinhXuatSacTKBaiThiPB.setBackground(new java.awt.Color(204, 204, 204));
        soThiSinhXuatSacTKBaiThiPB.setForeground(new java.awt.Color(153, 255, 153));
        progressThongKeBaiThiPN.add(soThiSinhXuatSacTKBaiThiPB);

        progressThongKeBaiThiCardPN.add(progressThongKeBaiThiPN, java.awt.BorderLayout.CENTER);

        thongKeBaiThiCardPN.add(progressThongKeBaiThiCardPN, java.awt.BorderLayout.CENTER);

        searchThongKeBaiThiCardPN.setBackground(new java.awt.Color(153, 255, 153));
        searchThongKeBaiThiCardPN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        searchThongKeBaiThiCardPN.setPreferredSize(new java.awt.Dimension(509, 170));
        searchThongKeBaiThiCardPN.setLayout(new java.awt.GridLayout(4, 2, 5, 5));

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));
        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        jLabel7.setBackground(new java.awt.Color(153, 255, 153));
        jLabel7.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Test:");
        jPanel3.add(jLabel7);

        testSearchBaiThiComboBox.setBackground(new java.awt.Color(255, 255, 255));
        testSearchBaiThiComboBox.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        testSearchBaiThiComboBox.setForeground(new java.awt.Color(0, 0, 0));
        testSearchBaiThiComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(testSearchBaiThiComboBox);

        searchThongKeBaiThiCardPN.add(jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        testSearchBaiThiTF.setBackground(new java.awt.Color(255, 255, 255));
        testSearchBaiThiTF.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        testSearchBaiThiTF.setForeground(new java.awt.Color(0, 0, 0));
        testSearchBaiThiTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                testSearchBaiThiTFKeyReleased(evt);
            }
        });
        jPanel2.add(testSearchBaiThiTF);

        searchThongKeBaiThiCardPN.add(jPanel2);

        jPanel4.setBackground(new java.awt.Color(153, 255, 153));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jLabel8.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Exam:");
        jPanel4.add(jLabel8);

        examSearchBaiThiComboBox.setBackground(new java.awt.Color(255, 255, 255));
        examSearchBaiThiComboBox.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        examSearchBaiThiComboBox.setForeground(new java.awt.Color(0, 0, 0));
        examSearchBaiThiComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(examSearchBaiThiComboBox);

        searchThongKeBaiThiCardPN.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        examSearchBaiThiTF.setBackground(new java.awt.Color(255, 255, 255));
        examSearchBaiThiTF.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        examSearchBaiThiTF.setForeground(new java.awt.Color(0, 0, 0));
        examSearchBaiThiTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                examSearchBaiThiTFKeyReleased(evt);
            }
        });
        jPanel5.add(examSearchBaiThiTF);

        searchThongKeBaiThiCardPN.add(jPanel5);

        thongKeBaiThiCardPN.add(searchThongKeBaiThiCardPN, java.awt.BorderLayout.PAGE_START);

        thongKeBaiThiCardPanel.add(thongKeBaiThiCardPN, java.awt.BorderLayout.CENTER);

        jLabel16.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Lời Nhắc: ");
        jLabel16.setPreferredSize(new java.awt.Dimension(72, 50));
        thongKeBaiThiCardPanel.add(jLabel16, java.awt.BorderLayout.PAGE_END);

        hienThiThongKePanel.add(thongKeBaiThiCardPanel, "card3");

        thongKeSoSanhCardPanel.setBackground(new java.awt.Color(255, 255, 255));
        thongKeSoSanhCardPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0), new java.awt.Color(153, 153, 153)));
        thongKeSoSanhCardPanel.setLayout(new java.awt.BorderLayout(5, 10));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("So Sánh Kết Quả");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        jLabel10.setPreferredSize(new java.awt.Dimension(59, 40));
        thongKeSoSanhCardPanel.add(jLabel10, java.awt.BorderLayout.PAGE_START);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        jPanel10.setLayout(new java.awt.GridLayout(3, 0, 0, 10));

        searchSoSanhKetQuaPN.setBackground(new java.awt.Color(204, 255, 255));
        searchSoSanhKetQuaPN.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        searchSoSanhKetQuaPN.setLayout(new java.awt.BorderLayout(10, 2));

        jLabel17.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Search");
        jLabel17.setPreferredSize(new java.awt.Dimension(59, 30));
        searchSoSanhKetQuaPN.add(jLabel17, java.awt.BorderLayout.PAGE_START);

        jPanel14.setBackground(new java.awt.Color(204, 255, 255));
        jPanel14.setLayout(new java.awt.GridLayout(3, 0));

        jLabel19.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Test:");
        jPanel14.add(jLabel19);

        searchTestSoSanhKetQuaTF.setBackground(new java.awt.Color(255, 255, 255));
        searchTestSoSanhKetQuaTF.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jPanel14.add(searchTestSoSanhKetQuaTF);

        searchTestSoSanhKetQuaCB.setBackground(new java.awt.Color(204, 204, 204));
        searchTestSoSanhKetQuaCB.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        searchTestSoSanhKetQuaCB.setForeground(new java.awt.Color(0, 0, 0));
        searchTestSoSanhKetQuaCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel14.add(searchTestSoSanhKetQuaCB);

        jLabel20.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Exam:");
        jPanel14.add(jLabel20);

        searchExamSoSanhKetQuaTF.setBackground(new java.awt.Color(255, 255, 255));
        searchExamSoSanhKetQuaTF.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jPanel14.add(searchExamSoSanhKetQuaTF);

        searchExamSoSanhKetQuaCB.setBackground(new java.awt.Color(204, 204, 204));
        searchExamSoSanhKetQuaCB.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        searchExamSoSanhKetQuaCB.setForeground(new java.awt.Color(0, 0, 0));
        searchExamSoSanhKetQuaCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel14.add(searchExamSoSanhKetQuaCB);

        jLabel21.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("User:");
        jPanel14.add(jLabel21);

        searchUserSoSanhKetQuaTF.setBackground(new java.awt.Color(255, 255, 255));
        searchUserSoSanhKetQuaTF.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jPanel14.add(searchUserSoSanhKetQuaTF);

        searchUserSoSanhKetQuaCB.setBackground(new java.awt.Color(204, 204, 204));
        searchUserSoSanhKetQuaCB.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        searchUserSoSanhKetQuaCB.setForeground(new java.awt.Color(0, 0, 0));
        searchUserSoSanhKetQuaCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel14.add(searchUserSoSanhKetQuaCB);

        searchSoSanhKetQuaPN.add(jPanel14, java.awt.BorderLayout.CENTER);

        ketQuaTimKiemSoSanhKetQuaLB.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        ketQuaTimKiemSoSanhKetQuaLB.setForeground(new java.awt.Color(0, 0, 0));
        ketQuaTimKiemSoSanhKetQuaLB.setText("Thông báo kết quả tìm kiếm: ");
        ketQuaTimKiemSoSanhKetQuaLB.setPreferredSize(new java.awt.Dimension(59, 30));
        searchSoSanhKetQuaPN.add(ketQuaTimKiemSoSanhKetQuaLB, java.awt.BorderLayout.PAGE_END);

        jPanel10.add(searchSoSanhKetQuaPN);

        ketQuaUser1PN.setBackground(new java.awt.Color(204, 255, 255));
        ketQuaUser1PN.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        ketQuaUser1PN.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel18.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("KẾT QUẢ USER 1:");
        jLabel18.setPreferredSize(new java.awt.Dimension(157, 30));
        ketQuaUser1PN.add(jLabel18, java.awt.BorderLayout.PAGE_START);

        jPanel11.setBackground(new java.awt.Color(204, 255, 255));
        jPanel11.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0)));
        jPanel12.setLayout(new java.awt.GridLayout(6, 0));

        jLabel23.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Số Câu Đúng:");
        jPanel12.add(jLabel23);

        soCauDungUser1PB.setBackground(new java.awt.Color(204, 204, 204));
        soCauDungUser1PB.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        soCauDungUser1PB.setForeground(new java.awt.Color(204, 255, 255));
        jPanel12.add(soCauDungUser1PB);

        jLabel24.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Số Câu Sai:");
        jPanel12.add(jLabel24);

        soCauTraLoiSaiUser1PB.setBackground(new java.awt.Color(204, 204, 204));
        soCauTraLoiSaiUser1PB.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        soCauTraLoiSaiUser1PB.setForeground(new java.awt.Color(204, 255, 255));
        jPanel12.add(soCauTraLoiSaiUser1PB);

        jLabel28.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Thời Gian Hoàn Thành:");
        jPanel12.add(jLabel28);

        thoiGianHTUser1PB.setBackground(new java.awt.Color(204, 204, 204));
        thoiGianHTUser1PB.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        thoiGianHTUser1PB.setForeground(new java.awt.Color(204, 255, 255));
        jPanel12.add(thoiGianHTUser1PB);

        jPanel11.add(jPanel12);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0)));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel29.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 153, 153));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("High Score");
        jLabel29.setPreferredSize(new java.awt.Dimension(95, 40));
        jPanel13.add(jLabel29, java.awt.BorderLayout.PAGE_END);

        highScoreUser1LB.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        highScoreUser1LB.setForeground(new java.awt.Color(0, 0, 0));
        highScoreUser1LB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        highScoreUser1LB.setText("100");
        jPanel13.add(highScoreUser1LB, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel13);

        ketQuaUser1PN.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel10.add(ketQuaUser1PN);

        ketQuaUser2PN.setBackground(new java.awt.Color(204, 255, 255));
        ketQuaUser2PN.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        ketQuaUser2PN.setLayout(new java.awt.BorderLayout());

        jLabel30.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("KẾT QUẢ USER 2:");
        jLabel30.setPreferredSize(new java.awt.Dimension(157, 30));
        ketQuaUser2PN.add(jLabel30, java.awt.BorderLayout.PAGE_START);

        jPanel15.setBackground(new java.awt.Color(204, 255, 255));
        jPanel15.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0)));
        jPanel17.setLayout(new java.awt.GridLayout(6, 0));

        jLabel34.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Số Câu Đúng:");
        jPanel17.add(jLabel34);

        soCauDungUser2PB.setBackground(new java.awt.Color(204, 204, 204));
        soCauDungUser2PB.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        soCauDungUser2PB.setForeground(new java.awt.Color(204, 255, 255));
        jPanel17.add(soCauDungUser2PB);

        jLabel35.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Số Câu Sai:");
        jPanel17.add(jLabel35);

        soCauTraLoiSaiUser2PB.setBackground(new java.awt.Color(204, 204, 204));
        soCauTraLoiSaiUser2PB.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        soCauTraLoiSaiUser2PB.setForeground(new java.awt.Color(204, 255, 255));
        jPanel17.add(soCauTraLoiSaiUser2PB);

        jLabel36.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Thời Gian Hoàn Thành:");
        jPanel17.add(jLabel36);

        thoiGianHTUser2PB.setBackground(new java.awt.Color(204, 204, 204));
        thoiGianHTUser2PB.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        thoiGianHTUser2PB.setForeground(new java.awt.Color(204, 255, 255));
        jPanel17.add(thoiGianHTUser2PB);

        jPanel15.add(jPanel17);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0)));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jLabel38.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 153, 153));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("High Score");
        jLabel38.setPreferredSize(new java.awt.Dimension(95, 40));
        jPanel20.add(jLabel38, java.awt.BorderLayout.PAGE_END);

        highScoreUser2LB.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        highScoreUser2LB.setForeground(new java.awt.Color(0, 0, 0));
        highScoreUser2LB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        highScoreUser2LB.setText("100");
        jPanel20.add(highScoreUser2LB, java.awt.BorderLayout.CENTER);

        jPanel15.add(jPanel20);

        ketQuaUser2PN.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel10.add(ketQuaUser2PN);

        thongKeSoSanhCardPanel.add(jPanel10, java.awt.BorderLayout.CENTER);

        hienThiThongKePanel.add(thongKeSoSanhCardPanel, "card4");

        thongKeLichSuCardPanel.setBackground(new java.awt.Color(255, 255, 255));
        thongKeLichSuCardPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0), new java.awt.Color(204, 204, 204)));
        thongKeLichSuCardPanel.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel39.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Lịch Sử Bài Thi");
        jLabel39.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0)));
        thongKeLichSuCardPanel.add(jLabel39, java.awt.BorderLayout.PAGE_START);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setLayout(new java.awt.BorderLayout());

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(new java.awt.GridLayout(3, 0));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Search For Exam:");
        jPanel21.add(jLabel1);

        searchForExamLichSuTF.setBackground(new java.awt.Color(255, 255, 255));
        searchForExamLichSuTF.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        searchForExamLichSuTF.setForeground(new java.awt.Color(0, 0, 0));
        searchForExamLichSuTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchForExamLichSuTFKeyReleased(evt);
            }
        });
        jPanel21.add(searchForExamLichSuTF);

        jPanel22.add(jPanel21, java.awt.BorderLayout.PAGE_START);

        lichSuBaiThiPN.setBackground(new java.awt.Color(255, 255, 255));
        lichSuBaiThiPN.setLayout(new javax.swing.BoxLayout(lichSuBaiThiPN, javax.swing.BoxLayout.LINE_AXIS));
        jPanel22.add(lichSuBaiThiPN, java.awt.BorderLayout.CENTER);

        thongKeLichSuCardPanel.add(jPanel22, java.awt.BorderLayout.CENTER);

        hienThiThongKePanel.add(thongKeLichSuCardPanel, "card5");

        thongKeTongCardPanel.setBackground(new java.awt.Color(255, 255, 255));
        thongKeTongCardPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102)));
        thongKeTongCardPanel.setLayout(new java.awt.BorderLayout(10, 20));

        tieuDeThongKeTongPN.setBackground(new java.awt.Color(255, 255, 255));
        tieuDeThongKeTongPN.setPreferredSize(new java.awt.Dimension(509, 150));
        tieuDeThongKeTongPN.setLayout(new java.awt.BorderLayout(0, 10));

        thongKeTongCardLB.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        thongKeTongCardLB.setForeground(new java.awt.Color(0, 0, 0));
        thongKeTongCardLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        thongKeTongCardLB.setText("Thống Kê Tổng");
        thongKeTongCardLB.setToolTipText("");
        thongKeTongCardLB.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(153, 153, 153)));
        thongKeTongCardLB.setPreferredSize(new java.awt.Dimension(175, 40));
        tieuDeThongKeTongPN.add(thongKeTongCardLB, java.awt.BorderLayout.PAGE_START);

        searchThongKeTongPN.setBackground(new java.awt.Color(255, 102, 102));
        searchThongKeTongPN.setLayout(new java.awt.BorderLayout(5, 0));

        searchTopicAndExamTKTongPN.setBackground(new java.awt.Color(255, 153, 153));
        searchTopicAndExamTKTongPN.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        searchTopicAndExamTKTongPN.setLayout(new java.awt.GridLayout(4, 0));

        jLabel25.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Test");
        searchTopicAndExamTKTongPN.add(jLabel25);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new java.awt.BorderLayout());

        searchTestTKTongCB.setBackground(new java.awt.Color(204, 204, 204));
        searchTestTKTongCB.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        searchTestTKTongCB.setForeground(new java.awt.Color(0, 0, 0));
        searchTestTKTongCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        searchTestTKTongCB.setPreferredSize(new java.awt.Dimension(250, 28));
        jPanel16.add(searchTestTKTongCB, java.awt.BorderLayout.LINE_END);

        searchTestTKTongTF.setBackground(new java.awt.Color(255, 255, 255));
        searchTestTKTongTF.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        searchTestTKTongTF.setForeground(new java.awt.Color(0, 0, 0));
        searchTestTKTongTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTestTKTongTFKeyReleased(evt);
            }
        });
        jPanel16.add(searchTestTKTongTF, java.awt.BorderLayout.CENTER);

        searchTopicAndExamTKTongPN.add(jPanel16);

        searchThongKeTongPN.add(searchTopicAndExamTKTongPN, java.awt.BorderLayout.CENTER);

        tieuDeThongKeTongPN.add(searchThongKeTongPN, java.awt.BorderLayout.CENTER);

        thongKeTongCardPanel.add(tieuDeThongKeTongPN, java.awt.BorderLayout.PAGE_START);

        thongKeTongScorePanel.setBackground(new java.awt.Color(255, 255, 255));
        thongKeTongScorePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        thongKeTongScorePanel.setPreferredSize(new java.awt.Dimension(200, 631));
        thongKeTongScorePanel.setVerifyInputWhenFocusTarget(false);
        thongKeTongScorePanel.setLayout(new java.awt.GridLayout(4, 0));

        scoreLB.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        scoreLB.setForeground(new java.awt.Color(204, 0, 51));
        scoreLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreLB.setText("SCORE");
        scoreLB.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(153, 153, 153)));
        thongKeTongScorePanel.add(scoreLB);

        highScorePN.setBackground(new java.awt.Color(255, 255, 255));
        highScorePN.setLayout(new java.awt.BorderLayout());

        highScoreLB.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        highScoreLB.setForeground(new java.awt.Color(255, 102, 102));
        highScoreLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        highScoreLB.setText("High Score");
        highScoreLB.setPreferredSize(new java.awt.Dimension(127, 40));
        highScorePN.add(highScoreLB, java.awt.BorderLayout.PAGE_END);

        maxScoreTKTongLB.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        maxScoreTKTongLB.setForeground(new java.awt.Color(0, 0, 0));
        maxScoreTKTongLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        maxScoreTKTongLB.setText("0");
        highScorePN.add(maxScoreTKTongLB, java.awt.BorderLayout.CENTER);

        thongKeTongScorePanel.add(highScorePN);

        lowScorePN.setBackground(new java.awt.Color(255, 255, 255));
        lowScorePN.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Low Score");
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 40));
        lowScorePN.add(jLabel2, java.awt.BorderLayout.PAGE_END);

        minScoreTKTongLB.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        minScoreTKTongLB.setForeground(new java.awt.Color(0, 0, 0));
        minScoreTKTongLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minScoreTKTongLB.setText("0");
        lowScorePN.add(minScoreTKTongLB, java.awt.BorderLayout.CENTER);

        thongKeTongScorePanel.add(lowScorePN);

        averageScorePN.setBackground(new java.awt.Color(255, 255, 255));
        averageScorePN.setLayout(new java.awt.BorderLayout());
        thongKeTongScorePanel.add(averageScorePN);

        thongKeTongCardPanel.add(thongKeTongScorePanel, java.awt.BorderLayout.LINE_END);

        thongKeTongProgressPanel.setBackground(new java.awt.Color(255, 255, 255));
        thongKeTongProgressPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));
        thongKeTongProgressPanel.setLayout(new java.awt.GridLayout(11, 0));

        testCodeTKTongLB.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        testCodeTKTongLB.setForeground(new java.awt.Color(0, 0, 0));
        testCodeTKTongLB.setText("TestCode: ");
        thongKeTongProgressPanel.add(testCodeTKTongLB);

        soLuongThiSinhLamBaiThiLB.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        soLuongThiSinhLamBaiThiLB.setForeground(new java.awt.Color(0, 0, 0));
        soLuongThiSinhLamBaiThiLB.setText("Số lượng thí sinh làm bài thi: ");
        thongKeTongProgressPanel.add(soLuongThiSinhLamBaiThiLB);
        thongKeTongProgressPanel.add(jSeparator5);

        soLuongThiSinhDatLB.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        soLuongThiSinhDatLB.setForeground(new java.awt.Color(0, 0, 0));
        soLuongThiSinhDatLB.setText("Số lượng thí sinh đạt (>=50%)");
        thongKeTongProgressPanel.add(soLuongThiSinhDatLB);

        soLuongThiSinhDatPB.setBackground(new java.awt.Color(204, 204, 204));
        soLuongThiSinhDatPB.setForeground(new java.awt.Color(255, 153, 153));
        thongKeTongProgressPanel.add(soLuongThiSinhDatPB);

        soLuongThiSinhKhongDatLB.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        soLuongThiSinhKhongDatLB.setForeground(new java.awt.Color(0, 0, 0));
        soLuongThiSinhKhongDatLB.setText("Số lượng thí sinh không đạt");
        thongKeTongProgressPanel.add(soLuongThiSinhKhongDatLB);

        soLuongThiSinhKhongDatPB.setBackground(new java.awt.Color(204, 204, 204));
        soLuongThiSinhKhongDatPB.setForeground(new java.awt.Color(255, 153, 153));
        thongKeTongProgressPanel.add(soLuongThiSinhKhongDatPB);

        soThiSinhXuatSacLB.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        soThiSinhXuatSacLB.setForeground(new java.awt.Color(0, 0, 0));
        soThiSinhXuatSacLB.setText("Số thí sinh xuất sắc (>9)");
        thongKeTongProgressPanel.add(soThiSinhXuatSacLB);

        soLuongThiSinhXuatSacPB.setBackground(new java.awt.Color(204, 204, 204));
        soLuongThiSinhXuatSacPB.setForeground(new java.awt.Color(255, 153, 153));
        thongKeTongProgressPanel.add(soLuongThiSinhXuatSacPB);

        thongKeTongCardPanel.add(thongKeTongProgressPanel, java.awt.BorderLayout.CENTER);

        thongBaoTienDoHocTapTKTongLB.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        thongBaoTienDoHocTapTKTongLB.setForeground(new java.awt.Color(0, 0, 0));
        thongBaoTienDoHocTapTKTongLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        thongBaoTienDoHocTapTKTongLB.setText("Tiến Độ Học Tập Trung Bình Của Các Thành Viên: ĐẠT");
        thongBaoTienDoHocTapTKTongLB.setPreferredSize(new java.awt.Dimension(50, 50));
        thongKeTongCardPanel.add(thongBaoTienDoHocTapTKTongLB, java.awt.BorderLayout.PAGE_END);

        hienThiThongKePanel.add(thongKeTongCardPanel, "card2");

        add(hienThiThongKePanel);
    }// </editor-fold>//GEN-END:initComponents

    private void thongKeTongPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongKeTongPanelMouseClicked
        // Mo card tuong ung
        cardLayout.show(hienThiThongKePanel, thongKeTongLB.getText());
    }//GEN-LAST:event_thongKeTongPanelMouseClicked

    private void thongKeBaiThiPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongKeBaiThiPanelMouseClicked
        // Mo card tuong ung
        cardLayout.show(hienThiThongKePanel, thongKeBaiThiLB.getText());
    }//GEN-LAST:event_thongKeBaiThiPanelMouseClicked

    private void thongKeSoSanhPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongKeSoSanhPanelMouseClicked
        cardLayout.show(hienThiThongKePanel, thongKeSoSanhLB.getText());
    }//GEN-LAST:event_thongKeSoSanhPanelMouseClicked

    private void thongKeLichSuPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongKeLichSuPanelMouseClicked
        cardLayout.show(hienThiThongKePanel, thongKeLichSuLB.getText());
    }//GEN-LAST:event_thongKeLichSuPanelMouseClicked

    private void searchTestTKTongTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTestTKTongTFKeyReleased
        String textSearch = searchTestTKTongTF.getText();
        testList = ThongKeBUS.getInstance().searchTestByTestCodeAndTestTitle(textSearch);
        if (testList.isEmpty()) {
            System.out.println("Không tìm thấy kết quả nào.");
        }
        initDeFaultComboBoxModel(modelTestTong, testList);
        searchTestTKTongCB.setModel(modelTestTong);
    }//GEN-LAST:event_searchTestTKTongTFKeyReleased

    private void searchUserThongKeTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchUserThongKeTFKeyReleased
        String textSearch = searchUserThongKeTF.getText();
        userList = ThongKeBUS.getInstance().searchUserByUserIDAndUserFullName(textSearch);
        initDeFaultComboBoxModel(modelUserTong, userList);
        searchUserThongKeCB.setModel(modelUserTong);
    }//GEN-LAST:event_searchUserThongKeTFKeyReleased

    private void testSearchBaiThiTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_testSearchBaiThiTFKeyReleased
        String textSearch = testSearchBaiThiTF.getText();
        testList = ThongKeBUS.getInstance().searchTestByTestCodeAndTestTitle(textSearch);
        initDeFaultComboBoxModel(modelTestBaiThi, testList);
        testSearchBaiThiComboBox.setModel(modelTestBaiThi);
    }//GEN-LAST:event_testSearchBaiThiTFKeyReleased

    private void examSearchBaiThiTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_examSearchBaiThiTFKeyReleased
        String textSearch = examSearchBaiThiTF.getText();
        examCodeList = ThongKeBUS.getInstance().getExamListByTestCodeAndExamCode(testCodeTKBaiThi, textSearch);
        if (examCodeList.isEmpty()) {
            System.out.println("Không tìm thấy kết quả nào.");
        }
        modelExamBaiThi.removeAllElements();
        for(String value: examCodeList) {
            modelExamBaiThi.addElement(value);
        }
        examSearchBaiThiComboBox.setModel(modelExamBaiThi);
    }//GEN-LAST:event_examSearchBaiThiTFKeyReleased

    private void searchForExamLichSuTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchForExamLichSuTFKeyReleased
        String textSearch = searchForExamLichSuTF.getText();
        // Load lich su exam cua userID khi tiem kiem
        this.lichSuPanel = new LichSuPanel(userId, textSearch);
        lichSuBaiThiPN.removeAll();
        lichSuBaiThiPN.add(lichSuPanel);
        lichSuBaiThiPN.revalidate();
        lichSuBaiThiPN.repaint();
    }//GEN-LAST:event_searchForExamLichSuTFKeyReleased

    
    private CardLayout cardLayout = new CardLayout();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabel10;
    private javax.swing.JPanel averageScorePN;
    private javax.swing.JPanel chonThongKePanel;
    private javax.swing.JLabel emailThongKeLB;
    private javax.swing.JLabel examCodeTKBaiThiLB;
    private javax.swing.JComboBox<String> examSearchBaiThiComboBox;
    private javax.swing.JTextField examSearchBaiThiTF;
    private javax.swing.JLabel fullNameThongKeLB;
    private javax.swing.JPanel hienThiThongKePanel;
    private javax.swing.JLabel highScoreLB;
    private javax.swing.JPanel highScorePN;
    private javax.swing.JLabel highScoreThongKeBaiThiLB;
    private javax.swing.JLabel highScoreUser1LB;
    private javax.swing.JLabel highScoreUser2LB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel ketQuaTimKiemSoSanhKetQuaLB;
    private javax.swing.JPanel ketQuaUser1PN;
    private javax.swing.JPanel ketQuaUser2PN;
    private javax.swing.JPanel lichSuBaiThiPN;
    private javax.swing.JPanel lowScorePN;
    private javax.swing.JLabel lowScoreThongKeBaiThiLB;
    private javax.swing.JPanel luaChonThongKe1;
    private javax.swing.JPanel luaChonThongKe2;
    private javax.swing.JLabel maxScoreTKTongLB;
    private javax.swing.JLabel minScoreTKTongLB;
    private javax.swing.JPanel progressThongKeBaiThiCardPN;
    private javax.swing.JPanel progressThongKeBaiThiPN;
    private javax.swing.JLabel scoreLB;
    private javax.swing.JPanel scoreThongKeBaiThiPN;
    private javax.swing.JComboBox<String> searchExamSoSanhKetQuaCB;
    private javax.swing.JTextField searchExamSoSanhKetQuaTF;
    private javax.swing.JTextField searchForExamLichSuTF;
    private javax.swing.JPanel searchSoSanhKetQuaPN;
    private javax.swing.JComboBox<String> searchTestSoSanhKetQuaCB;
    private javax.swing.JTextField searchTestSoSanhKetQuaTF;
    private javax.swing.JComboBox<String> searchTestTKTongCB;
    private javax.swing.JTextField searchTestTKTongTF;
    private javax.swing.JPanel searchThongKeBaiThiCardPN;
    private javax.swing.JPanel searchThongKeTongPN;
    private javax.swing.JPanel searchTopicAndExamTKTongPN;
    private javax.swing.JPanel searchUserPN;
    private javax.swing.JComboBox<String> searchUserSoSanhKetQuaCB;
    private javax.swing.JTextField searchUserSoSanhKetQuaTF;
    private javax.swing.JComboBox<String> searchUserThongKeCB;
    private javax.swing.JTextField searchUserThongKeTF;
    private javax.swing.JProgressBar soCauDungUser1PB;
    private javax.swing.JProgressBar soCauDungUser2PB;
    private javax.swing.JProgressBar soCauTraLoiSaiUser1PB;
    private javax.swing.JProgressBar soCauTraLoiSaiUser2PB;
    private javax.swing.JLabel soLuongThiSinhDatLB;
    private javax.swing.JProgressBar soLuongThiSinhDatPB;
    private javax.swing.JLabel soLuongThiSinhKhongDatLB;
    private javax.swing.JProgressBar soLuongThiSinhKhongDatPB;
    private javax.swing.JLabel soLuongThiSinhLamBaiThiLB;
    private javax.swing.JProgressBar soLuongThiSinhXuatSacPB;
    private javax.swing.JLabel soThiSinhDatTKBaiThiLB;
    private javax.swing.JProgressBar soThiSinhDatTKBaiThiPB;
    private javax.swing.JLabel soThiSinhKhongDatTKBaiThiLB;
    private javax.swing.JProgressBar soThiSinhKhongDatTKBaiThiPB;
    private javax.swing.JLabel soThiSinhXuatSacLB;
    private javax.swing.JLabel soThiSinhXuatSacTKBaiThiLB;
    private javax.swing.JProgressBar soThiSinhXuatSacTKBaiThiPB;
    private javax.swing.JLabel targetThongKeBaiThiLB;
    private javax.swing.JLabel testCodeTKBaiThiLB;
    private javax.swing.JLabel testCodeTKTongLB;
    private javax.swing.JComboBox<String> testSearchBaiThiComboBox;
    private javax.swing.JTextField testSearchBaiThiTF;
    private javax.swing.JProgressBar thoiGianHTUser1PB;
    private javax.swing.JProgressBar thoiGianHTUser2PB;
    private javax.swing.JLabel thongBaoTienDoHocTapTKTongLB;
    private javax.swing.JLabel thongKeBaiThiCardLB;
    private javax.swing.JPanel thongKeBaiThiCardPN;
    private javax.swing.JPanel thongKeBaiThiCardPanel;
    private javax.swing.JLabel thongKeBaiThiLB;
    private javax.swing.JPanel thongKeBaiThiPanel;
    private javax.swing.JPanel thongKeLichSuCardPanel;
    private javax.swing.JLabel thongKeLichSuLB;
    private javax.swing.JPanel thongKeLichSuPanel;
    private javax.swing.JPanel thongKeSoSanhCardPanel;
    private javax.swing.JLabel thongKeSoSanhLB;
    private javax.swing.JPanel thongKeSoSanhPanel;
    private javax.swing.JLabel thongKeTongCardLB;
    private javax.swing.JPanel thongKeTongCardPanel;
    private javax.swing.JLabel thongKeTongLB;
    private javax.swing.JPanel thongKeTongPanel;
    private javax.swing.JPanel thongKeTongProgressPanel;
    private javax.swing.JPanel thongKeTongScorePanel;
    private javax.swing.JPanel thongTinUserThongKePN;
    private javax.swing.JPanel tieuDePN;
    private javax.swing.JPanel tieuDeThongKePN;
    private javax.swing.JPanel tieuDeThongKeTongPN;
    private javax.swing.JLabel userIdThongKeLB;
    private javax.swing.JLabel userNameThongKeLB;
    // End of variables declaration//GEN-END:variables
}
