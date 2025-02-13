package com.example.qlthitracnghiem;

import com.example.qlthitracnghiem.GUI.Auth.AuthFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import com.example.qlthitracnghiem.GUI.DashboardFrame;
import com.example.qlthitracnghiem.GUI.Component.RoundedButton;

import java.awt.Dimension;
import java.awt.GridBagLayout;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AuthFrame().setVisible(true);
        });
    }

    // het dung
    private void demoRoundedButton() {
        JFrame demo = new JFrame();
        demo.setTitle("Rounded Toggle Button");
        demo.setSize(300, 200);
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        demo.setLocationRelativeTo(null);

        // Tạo nút toggle với góc bo tròn
        RoundedButton toggleButton = new RoundedButton("1");
        // toggleButton.setPreferredSize(new Dimension(80, 80));
        toggleButton.setState(RoundedButton.NORMAL);
        toggleButton.addActionListener(e -> {
            if (toggleButton.getState() == RoundedButton.NORMAL) {
                toggleButton.setState(RoundedButton.DONE);
            } else if (toggleButton.getState() == RoundedButton.DONE) {
                toggleButton.setState(RoundedButton.NOT_DONE);
            } else {
                toggleButton.setState(RoundedButton.NORMAL);
            }
        });

        // Panel để căn giữa nút
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Căn giữa nút trong cửa sổ
        panel.add(toggleButton);

        demo.add(panel);
        demo.setVisible(true);
    }
}
