/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.Exam;

import javax.swing.JFrame;

/**
 *
 * @author truon
 */
public class DoExamJPanel extends javax.swing.JPanel {

    /**
     * Creates new form DoExamJPanel
     */
    public DoExamJPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlBaiThiInfo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnNopBai = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        roundedButton1 = new com.example.qlthitracnghiem.GUI.Component.RoundedButton();
        roundedButton5 = new com.example.qlthitracnghiem.GUI.Component.RoundedButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlCenter = new javax.swing.JPanel();
        question1 = new com.example.qlthitracnghiem.GUI.CauHoi.Question();
        question2 = new com.example.qlthitracnghiem.GUI.CauHoi.Question();
        question3 = new com.example.qlthitracnghiem.GUI.CauHoi.Question();

        setPreferredSize(new java.awt.Dimension(1206, 760));
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(51, 255, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel1.setText("19:59");

        pnlBaiThiInfo.setLayout(new java.awt.GridLayout(2, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Ten bai thi: Kiem tra giua ky");
        pnlBaiThiInfo.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Chu de: Toan cao cap");
        pnlBaiThiInfo.add(jLabel3);

        btnNopBai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNopBai.setText("Nộp bài");
        btnNopBai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNopBaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBaiThiInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 467, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(62, 62, 62)
                .addComponent(btnNopBai, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBaiThiInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNopBai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

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
        pnlCenter.add(question2);
        pnlCenter.add(question3);

        jScrollPane1.setViewportView(pnlCenter);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void roundedButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_roundedButton5ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_roundedButton5ActionPerformed

    private void btnNopBaiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNopBaiActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnNopBaiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNopBai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlBaiThiInfo;
    private javax.swing.JPanel pnlCenter;
    private com.example.qlthitracnghiem.GUI.CauHoi.Question question1;
    private com.example.qlthitracnghiem.GUI.CauHoi.Question question2;
    private com.example.qlthitracnghiem.GUI.CauHoi.Question question3;
    private com.example.qlthitracnghiem.GUI.Component.RoundedButton roundedButton1;
    private com.example.qlthitracnghiem.GUI.Component.RoundedButton roundedButton5;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.add(new DoExamJPanel());
        frame.setVisible(true);
    }
}
