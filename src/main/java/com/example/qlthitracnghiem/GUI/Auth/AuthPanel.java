/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.Auth;

import javax.swing.*;

import com.example.qlthitracnghiem.DTO.UserDTO;
import com.example.qlthitracnghiem.GUI.DashboardFrame;

import java.awt.*;

public class AuthPanel extends JPanel {
    private CardLayout cardLayout;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private JFrame parentFrame;

    public AuthPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        // Khởi tạo các panel con
        loginPanel = new LoginPanel(this); // Truyền tham chiếu AuthPanel để điều khiển chuyển đổi
        registerPanel = new RegisterPanel(this);

        // Thêm các panel con vào CardLayout
        add(loginPanel, "Login");
        add(registerPanel, "Register");

        // Mặc định hiển thị màn hình login
        cardLayout.show(this, "Login");
    }

    // Phương thức chuyển đổi giữa các panel
    public void showLoginPanel() {
        cardLayout.show(this, "Login");
    }

    public void showRegisterPanel() {
        cardLayout.show(this, "Register");
    }

    public void navigateToDashboard(UserDTO user) {
        parentFrame.dispose();
        new DashboardFrame(user).setVisible(true);
    }

}
