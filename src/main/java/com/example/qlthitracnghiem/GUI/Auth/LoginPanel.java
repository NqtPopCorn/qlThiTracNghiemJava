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
public class LoginPanel extends javax.swing.JPanel {

    private AuthPanel authPanel;
    private UserBUS userBUS;

    public LoginPanel(AuthPanel authPanel) {
        initComponents();
        this.authPanel = authPanel;
        userBUS = UserBUS.getInstance();
    }

    private boolean validateForm() {
        String password = new String(txtPassword.getPassword());
        if (txtUser.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống");
            return false;
        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống");
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnResgister = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();

        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 8, 0, 8, 0, 8, 0};
        layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0};
        setLayout(layout);

        btnResgister.setText("Đăng kí");
        btnResgister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResgisterActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        add(btnResgister, gridBagConstraints);

        btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLogin.setText("Xác nhận");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 10;
        add(btnLogin, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Đăng nhập");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 5, 0);
        add(jLabel3, gridBagConstraints);

        txtUser.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 324;
        gridBagConstraints.ipady = 11;
        add(txtUser, gridBagConstraints);

        txtPassword.setBorder(javax.swing.BorderFactory.createTitledBorder("Mật khẩu"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 324;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        add(txtPassword, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLoginActionPerformed
        if (validateForm() == false) {
            return;
        }
        String password = new String(txtPassword.getPassword());
        String username = txtUser.getText();
        try {
            authPanel.navigateToDashboard(userBUS.login(username, password));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }// GEN-LAST:event_btnLoginActionPerformed

    private void btnResgisterActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnResgisterActionPerformed
        authPanel.showRegisterPanel(); // Chuyển sang RegisterPanel
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnResgister;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
