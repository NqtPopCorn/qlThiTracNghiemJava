package com.example.qlthitracnghiem;

import com.example.qlthitracnghiem.GUI.Auth.AuthFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AuthFrame().setVisible(true);
        });
    }
}