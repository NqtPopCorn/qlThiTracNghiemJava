/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author light
 */
package com.example.qlthitracnghiem.GUI.Exam;

/**
 *
 * @author light
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountdownTimer extends JPanel {

    private JLabel timeLabel;
    private int secondsRemaining;
    private Timer timer;

    public CountdownTimer(int durationSeconds) {
        
        setLayout(new BorderLayout());

        secondsRemaining = durationSeconds;

        timeLabel = new JLabel(formatTime(secondsRemaining));
        timeLabel.setFont(new Font("Arial", Font.BOLD, 48)); // Make it big
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(timeLabel, BorderLayout.CENTER);

        timer = new Timer(1000, new ActionListener() { // Timer fires every 1000ms (1 second)
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsRemaining--;
                if (secondsRemaining < 0) {
                    timer.stop();
                    timeLabel.setText("Time's up!");
                    // Perform your action when the timer finishes
                    JOptionPane.showMessageDialog(CountdownTimer.this, "Countdown Finished!");
                } else {
                    timeLabel.setText(formatTime(secondsRemaining));
                }
            }
        });

    }
    public void startTimer() {
        timer.start();
    }
    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds); // Format as MM:SS
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CountdownTimer(60)); // 60 seconds countdown
    }

}