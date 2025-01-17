package com.example.qlthitracnghiem.GUI.Component;

import javax.swing.JLabel;

public class DashboardButton extends JLabel {
  public DashboardButton(String text, int width, int height) {
    super();
    this.setFont(new java.awt.Font("Segoe UI", 1, 14));
    this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    this.setText(text);
    this.setMaximumSize(new java.awt.Dimension(width, height));
    this.setPreferredSize(new java.awt.Dimension(width, height));
  }
}
