package com.example.qlthitracnghiem.GUI.User;

import javax.swing.JOptionPane;

import com.example.qlthitracnghiem.BUS.UserBUS;

public class CreateUserDialog extends UserDialog {
  private UserPanel parentPanel;

  public CreateUserDialog(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    lblHeader.setText("Tạo tài khoản");
  }

  public CreateUserDialog(java.awt.Frame parent, boolean modal, UserPanel parentPanel) {
    super(parent, modal, parentPanel);
    this.parentPanel = parentPanel;
    lblHeader.setText("Tạo tài khoản");
  }

  @Override
  protected void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {
    // create user
    try {
      String username = txtUsername.getText();
      String password = new String(txtPassword.getPassword());
      String email = txtEmail.getText();
      String fullname = txtFullname.getText();
      boolean isAdmin = ckIsAdmin.isSelected();
      UserBUS.getInstance().create(username, password, email, fullname, isAdmin ? 1 : 0);
      JOptionPane.showMessageDialog(this, "Tạo tài khoản thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
      if (this.parentPanel != null) {
        this.parentPanel.loadDataTable();
      }
      this.dispose();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "Có lỗi xảy ra\n" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    }
  }
}
