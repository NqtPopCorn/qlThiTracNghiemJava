/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.CauHoi;

import com.example.qlthitracnghiem.utils.ImageUtil;
import java.awt.Dimension;
import java.awt.FontMetrics;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author truon
 */
public class QuestionOption extends javax.swing.JPanel {

    /**
     * Creates new form QuestionOption
     */
    public QuestionOption() {
        initComponents();
        ImageUtil.setIcon(this.paragraph1, "/icons/ic_user_30.png", 80, 60);
        // adjustHeight();
    }

    public void setParagraph(String text) {
        this.paragraph1.setText(text);
    }

    public void setButtonText(String text) {
        this.btnChoose.setText(text);
    }

    // public void adjustHeight() {
    // SwingUtilities.invokeLater(() -> {
    // // System.out.println("paragraph1: " + this.paragraph1.getSize());
    // // System.out.println("size: " + this.getPreferredSize());
    // setPreferredSize(new Dimension(1000, 500));
    // revalidate();
    // });
    // }

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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnChoose = new javax.swing.JRadioButton();
        paragraph1 = new com.example.qlthitracnghiem.GUI.Component.Paragraph();

        setPreferredSize(new java.awt.Dimension(800, 160));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));

        btnChoose.setText("A");
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });
        add(btnChoose);

        paragraph1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ic_user_30.png"))); // NOI18N
        paragraph1.setText(
                "<html><p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry. and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry. including versions of Lorem Ipsum.</p></html>");
        paragraph1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        add(paragraph1);
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnChooseActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnChooseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnChoose;
    public com.example.qlthitracnghiem.GUI.Component.Paragraph paragraph1;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.add(new QuestionOption());
        frame.setVisible(true);
    }
}
