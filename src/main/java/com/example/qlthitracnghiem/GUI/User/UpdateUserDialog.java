package com.example.qlthitracnghiem.GUI.User;

import javax.swing.JOptionPane;

import com.example.qlthitracnghiem.BUS.UserBUS;
import com.example.qlthitracnghiem.DTO.UserDTO;
import com.example.qlthitracnghiem.utils.PasswordUtil;

public class UpdateUserDialog extends UserDialog {
  private UserPanel parentPanel;
  private UserDTO user;

  public UpdateUserDialog(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    lblHeader.setText("Cập nhật tài khoản");
  }

  public UpdateUserDialog(UserPanel parentPanel, String userId) throws Exception {
    super(parentPanel.getParentFrame(), true);
    this.parentPanel = parentPanel;
    UserBUS userBUS = UserBUS.getInstance();
    this.user = userBUS.read(Integer.parseInt(userId));
    if (user == null) {
      throw new Exception("User not found");
    }
    txtUsername.setText(this.user.getUserName());
    txtEmail.setText(this.user.getUserEmail());
    txtFullname.setText(this.user.getUserFullName());
    ckIsAdmin.setSelected(this.user.isAdmin() == 1);
    lblHeader.setText("Cập nhật tài khoản");
  }

  @Override
  protected void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {
    // update user
    try {
      String username = txtUsername.getText();
      String password = new String(txtPassword.getPassword());
      String email = txtEmail.getText();
      String fullname = txtFullname.getText();
      boolean isAdmin = ckIsAdmin.isSelected();
      this.user.setUserName(username);
      this.user.setUserPassword(password.isEmpty() ? this.user.getUserPassword() : PasswordUtil.hashPassword(password));
      this.user.setUserEmail(email);
      this.user.setUserFullName(fullname);
      this.user.setIsAdmin(isAdmin ? 1 : 0);
      UserBUS.getInstance().update(this.user);
      JOptionPane.showMessageDialog(this, "Cập nhật tài khoản thành công", "Thành công",
          JOptionPane.INFORMATION_MESSAGE);
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
