/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.Auth;

import javax.swing.JOptionPane;
import com.example.qlthitracnghiem.BUS.UserBUS;

/**
 *
 * @author truon
 */
public class RegisterPanel extends javax.swing.JPanel {

    private AuthPanel authPanel;
    private UserBUS userBUS;

    public RegisterPanel(AuthPanel authPanel) {
        initComponents();
        this.authPanel = authPanel;
        userBUS = new UserBUS();
    }

    private boolean validateForm() {
        String password = new String(txtPassword.getPassword());
        String password1 = new String(txtPassword1.getPassword());
        if (txtUserEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống");
            return false;
        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống");
            return false;
        } else if (!password.equals(password1)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không trùng khớp");
            return false;
        } else if (userBUS.checkExist(txtUserName.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDangNhap = new javax.swing.JButton();
        btnResgister = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtUserEmail = new javax.swing.JTextField();
        txtPassword1 = new javax.swing.JPasswordField();
        txtUserName = new javax.swing.JTextField();

        btnDangNhap.setText("Về đăng nhập");
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        btnResgister.setText("Xác nhận");
        btnResgister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResgisterActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Đăng kí");

        txtPassword.setBorder(javax.swing.BorderFactory.createTitledBorder("Mật khẩu"));

        txtUserEmail.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));
        txtUserEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserEmailActionPerformed(evt);
            }
        });

        txtPassword1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập lại mật khẩu"));

        txtUserName.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên đăng nhập"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtUserEmail)
                                        .addComponent(txtPassword)
                                        .addComponent(txtPassword1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtUserName))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(157, Short.MAX_VALUE)
                                .addComponent(btnDangNhap)
                                .addGap(155, 155, 155))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(166, 166, 166)
                                                .addComponent(btnResgister))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(172, 172, 172)
                                                .addComponent(jLabel3)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(11, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnResgister)
                                .addGap(12, 12, 12)
                                .addComponent(btnDangNhap)
                                .addGap(18, 18, 18)));
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserEmailActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtUserEmailActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtUserEmailActionPerformed

    private void btnResgisterActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnResgisterActionPerformed
        String username = txtUserName.getText();
        String password = new String(txtPassword.getPassword());
        String email = txtUserEmail.getText();
        if (!validateForm()) {
            return;
        }
        int result = userBUS.register(username, password, email);
        if (result == UserBUS.ACTION_SUCCESS) {
            JOptionPane.showMessageDialog(this, "Đăng kí thành công");
            authPanel.showLoginPanel();
        } else {
            JOptionPane.showMessageDialog(this, "Đăng kí thất bại");
            txtUserEmail.setText("");
            txtPassword.setText("");
            txtPassword1.setText("");
        }
    }// GEN-LAST:event_btnResgisterActionPerformed

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDangNhapActionPerformed
        authPanel.showLoginPanel();
    }// GEN-LAST:event_btnDangNhapActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JButton btnResgister;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPassword1;
    private javax.swing.JTextField txtUserEmail;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
