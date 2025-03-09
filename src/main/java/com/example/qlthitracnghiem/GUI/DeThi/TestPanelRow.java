/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.DeThi;

import com.example.qlthitracnghiem.BUS.TestBUS;
import com.example.qlthitracnghiem.DTO.TestDTO;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author truon
 */
public class TestPanelRow extends javax.swing.JPanel {
    private TestBUS testBUS = new TestBUS();
    private TestPanel2 parentPanel;
    private TestDTO testDTO = new TestDTO();
    private String testCode;

    public TestPanelRow() {
        initComponents();

    }

    public TestPanelRow(TestPanel2 testPanel2, TestDTO test) {
        this.parentPanel = testPanel2;
        this.testCode = test.getTestCode();
        this.testDTO = test;
        initComponents();
    }

    public void setParentPanel(TestPanel2 parentPanel) {
        this.parentPanel = parentPanel;
    }

    public String getTestCode() {
        return this.testCode;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        txtTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnXemExam = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnXemChiTiet = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        setMaximumSize(new java.awt.Dimension(10000, 140));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0};
        layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0};
        layout.columnWeights = new double[] {1.0};
        setLayout(layout);

        txtTitle.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        txtTitle.setText("Kiểm tra giữ kì");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(txtTitle, gridBagConstraints);

        jLabel1.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jLabel1, gridBagConstraints);

        jLabel2.setText("jLabel2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jLabel2, gridBagConstraints);

        btnXemExam.setBackground(new java.awt.Color(220, 230, 205));
        btnXemExam.setForeground(new java.awt.Color(51, 51, 51));
        btnXemExam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eye.png"))); // NOI18N
        btnXemExam.setText("Xem các đề thi");
        btnXemExam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemExamActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 3;
        add(btnXemExam, gridBagConstraints);

        btnSua.setBackground(new java.awt.Color(255, 204, 51));
        btnSua.setForeground(new java.awt.Color(51, 51, 51));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wrench.png"))); // NOI18N
        btnSua.setText("Chỉnh sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 3;
        add(btnSua, gridBagConstraints);

        btnXoa.setBackground(new java.awt.Color(255, 153, 153));
        btnXoa.setForeground(new java.awt.Color(51, 51, 51));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/multiply.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setPreferredSize(new java.awt.Dimension(100, 31));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 3;
        add(btnXoa, gridBagConstraints);

        btnXemChiTiet.setBackground(new java.awt.Color(102, 102, 255));
        btnXemChiTiet.setForeground(new java.awt.Color(51, 51, 51));
        btnXemChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eye.png"))); // NOI18N
        btnXemChiTiet.setText("Xem chi tiết");
        btnXemChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 0);
        add(btnXemChiTiet, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXemChiTietActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXemChiTietActionPerformed
        try {
            UpdateTestDialog updateTestDialog = new UpdateTestDialog(null, true, testDTO.getTestCode());
            updateTestDialog.setTitle("Xem chi tiết đề thi");
            updateTestDialog.disableAll();
            updateTestDialog.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(TestPanelRow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// GEN-LAST:event_btnXemChiTietActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSuaActionPerformed
        try {
            boolean flag = testBUS.hasAExam(testCode);
            if (!flag) {
                UpdateTestDialog updateTestDialog = new UpdateTestDialog(null, true, testDTO.getTestCode());
                updateTestDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(parentPanel, "Đã tạo đề thi, không thể chỉnh sửa cấu trúc đề",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(TestPanelRow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }// GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXoaActionPerformed

        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Xóa test sẽ xóa luôn bài kiểm tra. Bạn có chắc chắn muốn xóa không?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        // Nếu người dùng chọn "Yes" (đồng ý xóa)
        if (confirm == JOptionPane.YES_OPTION) {
            System.err.println("TestCode khi xóa " + testCode);
            try {
                TestBUS testBUS = new TestBUS();
                TestDTO testDTO = testBUS.getTestByTestCode(testCode);
                int result = testBUS.delete(testDTO);

                if (result == 1) {

                    JOptionPane.showMessageDialog(parentPanel, "Xóa đề thi thành công!", "Thành công",
                            JOptionPane.INFORMATION_MESSAGE);
                    if (parentPanel != null) {
                        ArrayList<TestDTO> updatedTestList = testBUS.getAll();
                        parentPanel.updateTestPanel(updatedTestList);
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            parentPanel,
                            "Đã có học sinh làm bài kiểm tra, không thể xóa!",
                            "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        parentPanel,
                        "Đã có học sinh làm bài kiểm tra, không thể xóa!",
                        "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parentPanel, "Đã xảy ra lỗi khi xóa đề thi!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Người dùng chọn "No" hoặc đóng hộp thoại
            System.out.println("Người dùng đã hủy bỏ việc xóa.");
        }

    }// GEN-LAST:event_btnXoaActionPerformed

    private void btnXemExamActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXemActionPerformed
        if (parentPanel != null) {
            parentPanel.showExamPanel(testDTO);
        }
    }// GEN-LAST:event_btnXemActionPerformed

    public void setTestInfo(TestDTO test) {
        txtTitle.setText(test.getTestTitle());
        jLabel1.setText("Thời gian: " + test.getTestTime() + " phút");
        jLabel2.setText("Trạng thái: " + (test.getTestStatus() == 1 ? "Đang mở" : "Đã kết thúc"));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnXemChiTiet;
    private javax.swing.JButton btnXemExam;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel txtTitle;
    // End of variables declaration//GEN-END:variables
}
