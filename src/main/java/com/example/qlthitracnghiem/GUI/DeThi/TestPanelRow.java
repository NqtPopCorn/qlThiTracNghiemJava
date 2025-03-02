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
    private TestDTO testDTO=new TestDTO();
     private String testCode;
    public TestPanelRow() {
        initComponents();
        
    }
    public TestPanelRow(TestPanel2 testPanel2, TestDTO test) { 
        this.parentPanel = testPanel2;
        this.testCode = test.getTestCode();
        this.testDTO=test;
        initComponents();
    }
    
    public void setParentPanel(TestPanel2 parentPanel) {
    this.parentPanel = parentPanel;
}
    public String getTestCode() {
    return this.testCode;
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        txtTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnXem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        setMaximumSize(new java.awt.Dimension(10000, 140));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 8, 0, 8, 0, 8, 0, 8, 0};
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

        btnXem.setBackground(new java.awt.Color(220, 230, 205));
        btnXem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eye.png"))); // NOI18N
        btnXem.setText("Xem");
        btnXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 0);
        add(btnXem, gridBagConstraints);

        btnSua.setBackground(new java.awt.Color(248, 220, 209));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wrench.png"))); // NOI18N
        btnSua.setText("Chỉnh sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 5;
        add(btnSua, gridBagConstraints);

        btnXoa.setBackground(new java.awt.Color(175, 205, 235));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/multiply.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 5;
        add(btnXoa, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
        boolean flag = testBUS.isTestCodeExistExam(testCode);
        if (!flag) {
            EditTestDialog editTestDialog = new EditTestDialog(null, true, testBUS.getTestByTestCode(testCode));
            editTestDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(parentPanel, "Đã tạo đề thi, không thể chỉnh sửa cấu trúc đề", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (SQLException ex) {
        Logger.getLogger(TestPanelRow.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Xóa test sẽ xóa luôn bài kiểm tra. Bạn có chắc chắn muốn xóa không?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

    // Nếu người dùng chọn "Yes" (đồng ý xóa)
    if (confirm == JOptionPane.YES_OPTION) {
        System.err.println("TestCode khi xóa " + testCode);
        try {
            TestBUS testBUS = new TestBUS();
            TestDTO testDTO=testBUS.getTestByTestCode(testCode);
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
            JOptionPane.showMessageDialog(parentPanel, "Đã xảy ra lỗi khi xóa đề thi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Người dùng chọn "No" hoặc đóng hộp thoại
        System.out.println("Người dùng đã hủy bỏ việc xóa.");
    }


    }//GEN-LAST:event_btnXoaActionPerformed
    
    
    private void btnXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemActionPerformed
        if (parentPanel != null) {
      parentPanel.showExamPanel(testDTO);
    }
    }//GEN-LAST:event_btnXemActionPerformed
    public void setTestInfo(TestDTO test) {
    txtTitle.setText(test.getTestTitle());
    jLabel1.setText("Thời gian: " + test.getTestTime() + " phút");
     jLabel2.setText("Trạng thái: " + (test.getTestStatus() == 1 ? "Đang mở" : "Đã kết thúc"));
     
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnXem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel txtTitle;
    // End of variables declaration//GEN-END:variables
}
