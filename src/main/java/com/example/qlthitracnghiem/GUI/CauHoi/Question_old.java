/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.CauHoi;

import javax.swing.Icon;

import com.example.qlthitracnghiem.utils.ImageUtil;

/**
 *
 * @author truon
 */
public class Question_old extends javax.swing.JPanel {

    /**
     * Creates new form QuestionPanel
     */
    public Question_old() {
        initComponents();
        ImageUtil.setIcon(jLabel3, "/icons/ic_user_30.png", 100, 80);
        questionOption1.setButtonText("A");
        questionOption2.setButtonText("B");
        questionOption3.setButtonText("C");
        questionOption4.setButtonText("D");
    }

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        questionOption1 = new com.example.qlthitracnghiem.GUI.CauHoi.QuestionOption_old();
        questionOption2 = new com.example.qlthitracnghiem.GUI.CauHoi.QuestionOption_old();
        questionOption6 = new com.example.qlthitracnghiem.GUI.CauHoi.QuestionOption_old();
        questionOption5 = new com.example.qlthitracnghiem.GUI.CauHoi.QuestionOption_old();
        questionOption3 = new com.example.qlthitracnghiem.GUI.CauHoi.QuestionOption_old();
        questionOption4 = new com.example.qlthitracnghiem.GUI.CauHoi.QuestionOption_old();

        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 1000));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ic_user_30.png"))); // NOI18N
        jLabel3.setText("<html><p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum</p></html>");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel3);

        add(jPanel1);
        add(questionOption1);
        add(questionOption2);
        add(questionOption6);
        add(questionOption5);
        add(questionOption3);
        add(questionOption4);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        javax.swing.JFrame frame = new javax.swing.JFrame();
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new java.awt.FlowLayout());

        Question_old questionPanel = new Question_old();
        frame.add(questionPanel);
        System.out.println(questionPanel.getPreferredSize());
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private com.example.qlthitracnghiem.GUI.CauHoi.QuestionOption_old questionOption1;
    private com.example.qlthitracnghiem.GUI.CauHoi.QuestionOption_old questionOption2;
    private com.example.qlthitracnghiem.GUI.CauHoi.QuestionOption_old questionOption3;
    private com.example.qlthitracnghiem.GUI.CauHoi.QuestionOption_old questionOption4;
    private com.example.qlthitracnghiem.GUI.CauHoi.QuestionOption_old questionOption5;
    private com.example.qlthitracnghiem.GUI.CauHoi.QuestionOption_old questionOption6;
    // End of variables declaration//GEN-END:variables
}
