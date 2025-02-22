/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.Info;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.example.qlthitracnghiem.BUS.UserBUS;
import com.example.qlthitracnghiem.DTO.UserDTO;

/**
 *
 * @author truon
 */
public class InfoPanel extends javax.swing.JPanel {
    private UserBUS userBUS = UserBUS.getInstance();
    private UserDTO user;

    // test only
    public InfoPanel() {
        initComponents();
    }

    // main
    public InfoPanel(UserDTO user) {
        initComponents();
        if (user != null) {
            this.user = user;
            System.out.println("userID: " + user.getUserID());
            txtUserName.setText(user.getUserName());
            txtEmail.setText(user.getUserEmail());
            txtFullName.setText(user.getUserFullName());
            loadExamResult();
        }
    }

    private void loadExamResult() {
        try {
            pnlResultList.removeAll();
            ArrayList<ArrayList<String>> list = userBUS.getLichSuLamBai(user.getUserID());
            if (list.size() == 0) {
                JLabel label = new JLabel("Không có kết quả nào");
                label.setFont(new Font("Arial", Font.ITALIC, 18));
                pnlResultList.add(label);
            } else {
                for (ArrayList<String> row : list) {
                    ExamResultPanel panel = new ExamResultPanel(row.stream().toArray(String[]::new));
                    pnlResultList.add(panel);
                }
            }
            pnlResultList.revalidate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại sau\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlInfo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        btnUpdateInfo = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        pnlPassword = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnSavePassword = new javax.swing.JButton();
        txtNewPassword = new javax.swing.JPasswordField();
        txtConfirmPassword = new javax.swing.JPasswordField();
        txtCurrentPassword = new javax.swing.JPasswordField();
        pnlExamResult = new javax.swing.JPanel();
        tfSearchExam = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlResultList = new javax.swing.JPanel();
        examResultPanel1 = new com.example.qlthitracnghiem.GUI.Info.ExamResultPanel();
        examResultPanel2 = new com.example.qlthitracnghiem.GUI.Info.ExamResultPanel();
        examResultPanel3 = new com.example.qlthitracnghiem.GUI.Info.ExamResultPanel();
        btnSearchExam = new javax.swing.JButton();
        btnRefreshResult = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(680, 615));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] { 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 };
        jPanel1Layout.rowHeights = new int[] { 0, 16, 0, 16, 0, 16, 0, 16, 0, 16, 0, 16, 0, 16, 0 };
        jPanel1.setLayout(jPanel1Layout);

        txtUserName.setEditable(false);
        txtUserName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUserName.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên đăng nhập"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(txtUserName, gridBagConstraints);

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(txtEmail, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thông tin cá nhân");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(jLabel1, gridBagConstraints);

        txtFullName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFullName.setBorder(javax.swing.BorderFactory.createTitledBorder("Họ và tên"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(txtFullName, gridBagConstraints);

        btnUpdateInfo.setText("Sửa");
        btnUpdateInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateInfoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.weightx = 0.2;
        jPanel1.add(btnUpdateInfo, gridBagConstraints);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 0.2;
        jPanel1.add(btnCancel, gridBagConstraints);

        pnlInfo.add(jPanel1);

        jTabbedPane1.addTab("Thông tin cá nhân", pnlInfo);

        jPanel4.setPreferredSize(new java.awt.Dimension(680, 615));
        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWidths = new int[] { 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 };
        jPanel4Layout.rowHeights = new int[] { 0, 16, 0, 16, 0, 16, 0, 16, 0 };
        jPanel4.setLayout(jPanel4Layout);

        jLabel2.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Đổi mật khẩu");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        jPanel4.add(jLabel2, gridBagConstraints);

        btnSavePassword.setText("Xác nhận");
        btnSavePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavePasswordActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.weightx = 0.2;
        jPanel4.add(btnSavePassword, gridBagConstraints);

        txtNewPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNewPassword.setBorder(javax.swing.BorderFactory.createTitledBorder("Mật khẩu mới"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(txtNewPassword, gridBagConstraints);

        txtConfirmPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtConfirmPassword.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhắc lại mật khẩu"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(txtConfirmPassword, gridBagConstraints);

        txtCurrentPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCurrentPassword.setBorder(javax.swing.BorderFactory.createTitledBorder("Mật khẩu hiện tại"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(txtCurrentPassword, gridBagConstraints);

        javax.swing.GroupLayout pnlPasswordLayout = new javax.swing.GroupLayout(pnlPassword);
        pnlPassword.setLayout(pnlPasswordLayout);
        pnlPasswordLayout.setHorizontalGroup(
                pnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1078, Short.MAX_VALUE)
                        .addGroup(pnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlPasswordLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))));
        pnlPasswordLayout.setVerticalGroup(
                pnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 615, Short.MAX_VALUE)
                        .addGroup(pnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlPasswordLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))));

        jTabbedPane1.addTab("Đổi mật khẩu", pnlPassword);

        tfSearchExam.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        pnlResultList.setLayout(new javax.swing.BoxLayout(pnlResultList, javax.swing.BoxLayout.Y_AXIS));
        pnlResultList.add(examResultPanel1);
        pnlResultList.add(examResultPanel2);
        pnlResultList.add(examResultPanel3);

        jScrollPane1.setViewportView(pnlResultList);

        btnSearchExam.setText("Go");
        btnSearchExam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchExamActionPerformed(evt);
            }
        });

        btnRefreshResult.setText("Refresh");
        btnRefreshResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshResultActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlExamResultLayout = new javax.swing.GroupLayout(pnlExamResult);
        pnlExamResult.setLayout(pnlExamResultLayout);
        pnlExamResultLayout.setHorizontalGroup(
                pnlExamResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlExamResultLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlExamResultLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(pnlExamResultLayout.createSequentialGroup()
                                                .addComponent(tfSearchExam, javax.swing.GroupLayout.PREFERRED_SIZE, 390,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnSearchExam)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        476, Short.MAX_VALUE)
                                                .addComponent(btnRefreshResult, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)))
                                .addContainerGap()));
        pnlExamResultLayout.setVerticalGroup(
                pnlExamResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlExamResultLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(pnlExamResultLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tfSearchExam)
                                        .addComponent(btnRefreshResult, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSearchExam, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                                .addContainerGap()));

        jTabbedPane1.addTab("Lịch sử làm bài", pnlExamResult);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshResultActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRefreshResultActionPerformed
        loadExamResult();
    }// GEN-LAST:event_btnRefreshResultActionPerformed

    private void refreshPasswordPanel() {
        txtCurrentPassword.setText("");
        txtNewPassword.setText("");
        txtConfirmPassword.setText("");
    }

    private void btnUpdateInfoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSaveInfoActionPerformed
        try {
            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận sửa thông tin?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.NO_OPTION) {
                return;
            }
            userBUS.updateUserInfo(user.getUserID(), txtEmail.getText(), txtFullName.getText());
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại sau\n" + e.getMessage());
            e.printStackTrace();
        }

    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelActionPerformed
        txtEmail.setText(user.getUserEmail());
        txtFullName.setText(user.getUserFullName());
    }

    private void btnSavePasswordActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSavePasswordActionPerformed
        try {
            userBUS.updatePassword(user.getUserID(), String.valueOf(txtCurrentPassword.getPassword()),
                    String.valueOf(txtNewPassword.getPassword()),
                    String.valueOf(txtConfirmPassword.getPassword()));
            JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu thành công");
            refreshPasswordPanel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại sau\n" + e.getMessage());
            e.printStackTrace();
        }
    }// GEN-LAST:event_btnSavePasswordActionPerformed

    private void btnSearchExamActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSearchExamActionPerformed
        try {
            pnlResultList.removeAll();
            ArrayList<ArrayList<String>> list = userBUS.searchLichSuLamBai(user.getUserID(), tfSearchExam.getText());
            if (list.size() == 0) {
                JLabel label = new JLabel("Không có kết quả nào");
                label.setFont(new Font("Arial", Font.ITALIC, 18));
                pnlResultList.add(label);
                System.out.println("No result");
            } else {
                for (ArrayList<String> row : list) {
                    ExamResultPanel panel = new ExamResultPanel(row.stream().toArray(String[]::new));
                    pnlResultList.add(panel);
                }
                System.out.println("Result found");
            }
            pnlResultList.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại sau\n" + e.getMessage());
            e.printStackTrace();
        }
    }// GEN-LAST:event_btnSearchExamActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRefreshResult;
    private javax.swing.JButton btnSavePassword;
    private javax.swing.JButton btnSearchExam;
    private javax.swing.JButton btnUpdateInfo;
    private com.example.qlthitracnghiem.GUI.Info.ExamResultPanel examResultPanel1;
    private com.example.qlthitracnghiem.GUI.Info.ExamResultPanel examResultPanel2;
    private com.example.qlthitracnghiem.GUI.Info.ExamResultPanel examResultPanel3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pnlExamResult;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlPassword;
    private javax.swing.JPanel pnlResultList;
    private javax.swing.JTextField tfSearchExam;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JPasswordField txtCurrentPassword;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JPasswordField txtNewPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
