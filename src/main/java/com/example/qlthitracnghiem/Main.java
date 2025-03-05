package com.example.qlthitracnghiem;

import com.example.qlthitracnghiem.GUI.Auth.AuthFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AuthFrame af = new AuthFrame();
            af.setVisible(true);
            System.out.println(af.getSize());
        });
    }
}