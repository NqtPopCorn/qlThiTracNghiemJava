/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.Exam;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author truon
 */
public class DoExamJPanel extends javax.swing.JPanel {

    /**
     * Creates new form DoExamJPanel
     */
    private String userId;
    private String testCode;
    private String testOrder;
    private ArrayList<String> questList;

    public DoExamJPanel() {
        initComponents();
        setUserId("1");
        setTestCode("TST001");
        setTestOrder("A");
        initComponent();

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestOrder() {
        return testOrder;
    }

    public void setTestOrder(String testOrder) {
        this.testOrder = testOrder;
    }

    public void setTimer() {
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        headerPanel = new javax.swing.JPanel();
        pnlBaiThiInfo = new javax.swing.JPanel();
        tsNameLabel = new javax.swing.JLabel();
        tsSubjectLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        timePN = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        roundedButton1 = new com.example.qlthitracnghiem.GUI.Component.RoundedButton();
        roundedButton5 = new com.example.qlthitracnghiem.GUI.Component.RoundedButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlCenter = new javax.swing.JPanel();
        question1 = new com.example.qlthitracnghiem.GUI.CauHoi.Question_old();

        setPreferredSize(new java.awt.Dimension(1206, 760));
        setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(new java.awt.Color(51, 255, 51));
        headerPanel.setPreferredSize(new java.awt.Dimension(1040, 140));
        headerPanel.setLayout(new java.awt.GridLayout(1, 0));

        pnlBaiThiInfo.setLayout(new java.awt.GridLayout(2, 0));

        tsNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tsNameLabel.setText("Ten bai thi: Kiem tra giua ky");
        pnlBaiThiInfo.add(tsNameLabel);

        tsSubjectLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tsSubjectLabel.setText("Chu de: Toan cao cap");
        pnlBaiThiInfo.add(tsSubjectLabel);

        headerPanel.add(pnlBaiThiInfo);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        timePN.setBackground(new java.awt.Color(204, 255, 255));
        timePN.setForeground(new java.awt.Color(0, 0, 0));
        timePN.setPreferredSize(new java.awt.Dimension(80, 80));
        timePN.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(timePN, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(timePN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        jButton1.setText("jButton1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);

        headerPanel.add(jPanel1);

        add(headerPanel, java.awt.BorderLayout.PAGE_START);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(204, 565));

        jPanel3.setBackground(new java.awt.Color(102, 255, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 563));
        jPanel3.setRequestFocusEnabled(false);

        roundedButton1.setForeground(new java.awt.Color(0, 0, 0));
        roundedButton1.setText("1");
        roundedButton1.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel3.add(roundedButton1);

        roundedButton5.setText("2");
        roundedButton5.setPreferredSize(new java.awt.Dimension(40, 40));
        roundedButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(roundedButton5);

        jScrollPane2.setViewportView(jPanel3);

        add(jScrollPane2, java.awt.BorderLayout.LINE_END);

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(804, 100));

        pnlCenter.setPreferredSize(new java.awt.Dimension(800, 5000));
        pnlCenter.setLayout(new javax.swing.BoxLayout(pnlCenter, javax.swing.BoxLayout.Y_AXIS));
        pnlCenter.add(question1);

        jScrollPane1.setViewportView(pnlCenter);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void roundedButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_roundedButton5ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_roundedButton5ActionPerformed

    private void btnNopBaiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNopBaiActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnNopBaiActionPerformed

    public void initComponent() {
//        timePN.add(new CountdownTimer(100), BorderLayout.CENTER);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlBaiThiInfo;
    private javax.swing.JPanel pnlCenter;
    private com.example.qlthitracnghiem.GUI.CauHoi.Question_old question1;
    private com.example.qlthitracnghiem.GUI.Component.RoundedButton roundedButton1;
    private com.example.qlthitracnghiem.GUI.Component.RoundedButton roundedButton5;
    private javax.swing.JPanel timePN;
    private javax.swing.JLabel tsNameLabel;
    private javax.swing.JLabel tsSubjectLabel;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.add(new DoExamJPanel());
        frame.setVisible(true);
    }
}
