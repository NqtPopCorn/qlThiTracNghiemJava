package com.example.qlthitracnghiem.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestFrame extends JFrame {
  private JPanel navigationPanel;
  private JPanel indexPanel;
  private ButtonGroup buttonGroup;
  private CardLayout cardLayout;

  public TestFrame() {
    setTitle("BorderLayout with Panels and Icons");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Khởi tạo navigationPanel (bên trái)
    navigationPanel = new JPanel();
    navigationPanel.setLayout(new GridLayout(5, 1, 10, 10));
    navigationPanel.setBackground(Color.LIGHT_GRAY);

    // Khởi tạo indexPanel (bên phải)
    indexPanel = new JPanel();
    cardLayout = new CardLayout();
    indexPanel.setLayout(cardLayout);

    // Tạo các button điều hướng với icon
    buttonGroup = new ButtonGroup();
    String[] panels = { "Home", "Settings", "Profile", "Help", "About" };

    for (int i = 0; i < panels.length; i++) {
      createNavigationButton(panels[i], "/icons/ic_user_30.png");
    }

    // Thêm các panel con vào indexPanel
    indexPanel.add(createContentPanel("Welcome to Home Panel"), "Home");
    indexPanel.add(createContentPanel("This is Settings Panel"), "Settings");
    indexPanel.add(createContentPanel("Your Profile Information"), "Profile");
    indexPanel.add(createContentPanel("Help and Support"), "Help");
    indexPanel.add(createContentPanel("About the Application"), "About");

    // Thêm navigationPanel và indexPanel vào frame
    add(navigationPanel, BorderLayout.WEST);
    add(indexPanel, BorderLayout.CENTER);

    // Hiển thị panel đầu tiên
    cardLayout.show(indexPanel, "Home");
  }

  // Tạo nút điều hướng
  private void createNavigationButton(String panelName, String iconPath) {
    JToggleButton button = new JToggleButton(panelName);
    button.setIcon(new ImageIcon(getClass().getResource(iconPath))); // Thêm icon
    button.setHorizontalTextPosition(SwingConstants.RIGHT); // Đặt chữ bên phải của icon
    button.setVerticalTextPosition(SwingConstants.CENTER); // Căn giữa chữ và icon
    button.setIconTextGap(10); // Khoảng cách giữa icon và chữ

    buttonGroup.add(button);
    navigationPanel.add(button);

    // Thêm sự kiện chuyển đổi panel
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cardLayout.show(indexPanel, panelName);
        updateButtonStyles();
      }
    });

    // Đặt nút đầu tiên được chọn mặc định
    if (buttonGroup.getSelection() == null) {
      button.setSelected(true);
    }
  }

  // Tạo nội dung cho các panel
  private JPanel createContentPanel(String text) {
    JPanel panel = new JPanel();
    panel.setBackground(new Color(200, 220, 240)); // Màu nền nhẹ
    JLabel label = new JLabel(text, SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 18));
    panel.add(label);
    return panel;
  }

  // Cập nhật hiệu ứng nút (highlight nút đang chọn)
  private void updateButtonStyles() {
    for (Component comp : navigationPanel.getComponents()) {
      if (comp instanceof JToggleButton) {
        JToggleButton button = (JToggleButton) comp;
        if (button.isSelected()) {
          button.setBackground(Color.BLUE);
          button.setForeground(Color.WHITE);
        } else {
          button.setBackground(Color.LIGHT_GRAY);
          button.setForeground(Color.BLACK);
        }
      }
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      TestFrame frame = new TestFrame();
      frame.setVisible(true);
    });
  }
}
