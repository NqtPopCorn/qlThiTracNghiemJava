/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.qlthitracnghiem.GUI;

import javax.swing.*;
import javax.swing.tree.TreePath;

import com.example.qlthitracnghiem.DTO.UserDTO;
import com.example.qlthitracnghiem.GUI.Auth.AuthFrame;
import com.example.qlthitracnghiem.GUI.CauHoi.QuestionPanel;
import com.example.qlthitracnghiem.GUI.Exam.ChooseExamJPanel;
import com.example.qlthitracnghiem.GUI.Exam.DoExamJPanel;
import com.example.qlthitracnghiem.GUI.Info.InfoPanel;
import com.example.qlthitracnghiem.GUI.ThongKe.MainThongKePanel;
import com.example.qlthitracnghiem.GUI.User.UserPanel;
import com.example.qlthitracnghiem.GUI.CauHoi.QuestionPanel;
import com.example.qlthitracnghiem.GUI.DeThi.TestPanel2;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author truon
 */
public class DashboardFrame extends javax.swing.JFrame {

    private JPanel cauHoiPanel = new JPanel();
    private JPanel userPanel = new UserPanel(this);
    private JPanel thongKePanel = new MainThongKePanel();
    private DoExamJPanel doExamJPanel = new DoExamJPanel(this);
    private ChooseExamJPanel chooseExamJPanel = new ChooseExamJPanel(doExamJPanel);
    private QuestionPanel questionPanel = new QuestionPanel();
    private TestPanel2 testPanel = new TestPanel2();
    private InfoPanel infoPanel = new InfoPanel();
    private UserDTO user;

    // public DashboardFrame(UserDTO user, ButtonGroup buttonGroup, CardLayout
    // cardLayout, JPanel currentPanel,
    // JPanel examplePanel1, JLabel jLabel2, JLabel jLabel7, JPanel jPanel4, JLabel
    // lblUsername,
    // JToggleButton navBtnCauHoi, JToggleButton navBtnDangXuat, JToggleButton
    // navBtnDeThi, JToggleButton navBtnThi,
    // JToggleButton navBtnThongKe, JToggleButton navBtnUser, JPanel
    // navigationPanel, JPanel sidePanel)
    // throws HeadlessException {
    // this.user = user;
    // this.buttonGroup = buttonGroup;
    // this.cardLayout = cardLayout;
    // this.currentPanel = currentPanel;
    // this.examplePanel1 = examplePanel1;
    // this.jLabel2 = jLabel2;
    // this.jLabel7 = jLabel7;
    // this.jPanel4 = jPanel4;
    // this.lblUsername = lblUsername;
    // this.navBtnCauHoi = navBtnCauHoi;
    // this.navBtnDangXuat = navBtnDangXuat;
    // this.navBtnDeThi = navBtnDeThi;
    // this.navBtnThi = navBtnThi;
    // this.navBtnThongKe = navBtnThongKe;
    // this.navBtnUser = navBtnUser;
    // this.navigationPanel = navigationPanel;
    // this.sidePanel = sidePanel;
    // }
    /**
     * Creates new form DashboardFrame
     */
    public DashboardFrame() {
        initComponents();
        setLocationRelativeTo(null);
        currentPanel.removeAll();
        cauHoiPanel.setBackground(Color.GREEN);
        userPanel.setBackground(Color.BLUE);
        thongKePanel.setBackground(Color.white);

        cardLayout = new CardLayout();
        currentPanel.setLayout(cardLayout);
        // add panel moi tai dayday
        currentPanel.add(chooseExamJPanel, "chooseExamPanel");
        currentPanel.add(doExamJPanel, "doExamJPanel");
        currentPanel.add(cauHoiPanel, navBtnCauHoi.getActionCommand());
        currentPanel.add(userPanel, navBtnUser.getActionCommand());
        currentPanel.add(thongKePanel, navBtnThongKe.getActionCommand());
        currentPanel.add(questionPanel, navBtnCauHoi.getActionCommand());
        currentPanel.add(testPanel, navBtnDeThi.getActionCommand());
        currentPanel.add(infoPanel, "InfoPanel");

        buttonGroup.add(navBtnDeThi);
        buttonGroup.add(navBtnCauHoi);
        buttonGroup.add(navBtnUser);
        buttonGroup.add(navBtnThongKe);
        buttonGroup.add(navBtnDangXuat);
        buttonGroup.add(navBtnThi);
        buttonGroup.add(navBtnUserInfo);

        navBtnDeThi.addActionListener(e -> switchPanel(navBtnDeThi.getActionCommand()));
        navBtnThi.addActionListener(e -> switchPanel("chooseExamPanel"));
        navBtnCauHoi.addActionListener(e -> switchPanel(navBtnCauHoi.getActionCommand()));
        navBtnUser.addActionListener(e -> switchPanel(navBtnUser.getActionCommand()));
        navBtnThongKe.addActionListener(e -> switchPanel(navBtnThongKe.getActionCommand()));
        navBtnUserInfo.addActionListener(e -> switchPanel("InfoPanel"));
        navBtnDangXuat.addActionListener(e -> handleLogout());
        navBtnDeThi.setSelected(true);

        cardLayout.show(currentPanel, navBtnDeThi.getActionCommand());
        updateNavigateButton();
    }

    public DashboardFrame(UserDTO user) {
        this();
        this.user = user;
        if (user != null) {
            navBtnUserInfo.setText(user.getUserName());
            infoPanel = new InfoPanel(user);
            currentPanel.add(infoPanel, "InfoPanel");
            doExamJPanel.setUserID(user.getUserID());
            chooseExamJPanel.setUserId(user.getUserID());
        }
    }

    public void disableAllNavButtons() {
        // Get all components from the panel
        Component[] components = navigationPanel.getComponents();
        components = Arrays.copyOf(components, components.length + 1);
        components[components.length - 1] = navBtnUserInfo;
        for (Component component : components) {
            if (component instanceof JToggleButton) {
                JToggleButton button = (JToggleButton) component;
                button.setEnabled(false); // Disable the button
            }
        }
    }

    public void enableAllNavButtons() {
        Component[] components = navigationPanel.getComponents();
        components = Arrays.copyOf(components, components.length + 1);
        components[components.length - 1] = navBtnUserInfo;
        for (Component component : components) {
            if (component instanceof JToggleButton) {
                JToggleButton button = (JToggleButton) component;
                button.setEnabled(true); // Disable the button
            }
        }
    }

    public void switchPanel(String panelName) {
        updateNavigateButton();
        cardLayout.show(currentPanel, panelName);
    }

    private void handleLogout() {
        this.dispose();
        new AuthFrame().setVisible(true);
    }

    private void updateNavigateButton() {
        ArrayList<Component> components = new ArrayList<>(Arrays.asList(navigationPanel.getComponents()));
        components.add(navBtnUserInfo);

        for (Component component : components) {
            if (component instanceof JToggleButton) {
                JToggleButton button = (JToggleButton) component;
                if (button.isSelected()) {
                    button.setBackground(Color.BLUE);
                    button.setForeground(Color.WHITE);
                } else {
                    button.setBackground(Color.WHITE);
                    button.setForeground(Color.BLACK);
                }
            }
        }
    }

    public void setUser(UserDTO user) {
        this.user = user;
        if (user != null) {
            navBtnUserInfo.setText(user.getUserName());
            infoPanel = new InfoPanel(user);
            currentPanel.add(infoPanel, "InfoPanel");
            doExamJPanel.setUserID(user.getUserID());
        }
    }

    public UserDTO getUser() {
        return user;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        cardLayout = new java.awt.CardLayout();
        sidePanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        navBtnUserInfo = new javax.swing.JToggleButton();
        navigationPanel = new javax.swing.JPanel();
        navBtnDeThi = new javax.swing.JToggleButton();
        navBtnThi = new javax.swing.JToggleButton();
        navBtnCauHoi = new javax.swing.JToggleButton();
        navBtnUser = new javax.swing.JToggleButton();
        navBtnThongKe = new javax.swing.JToggleButton();
        navBtnDangXuat = new javax.swing.JToggleButton();
        currentPanel = new javax.swing.JPanel();
        examplePanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sidePanel.setBackground(new java.awt.Color(255, 102, 255));
        sidePanel.setPreferredSize(new java.awt.Dimension(160, 700));
        sidePanel.setLayout(new java.awt.BorderLayout());

        jPanel4.setMaximumSize(new java.awt.Dimension(160, 180));
        jPanel4.setPreferredSize(new java.awt.Dimension(160, 180));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SGU");

        navBtnUserInfo.setBackground(new java.awt.Color(60, 63, 65));
        navBtnUserInfo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        navBtnUserInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ic_user_30.png"))); // NOI18N
        navBtnUserInfo.setText("Your User Name");
        navBtnUserInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        navBtnUserInfo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(navBtnUserInfo, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 148,
                                                Short.MAX_VALUE))
                                .addContainerGap()));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(navBtnUserInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 125,
                                        Short.MAX_VALUE)
                                .addContainerGap()));

        sidePanel.add(jPanel4, java.awt.BorderLayout.NORTH);

        navigationPanel.setPreferredSize(new java.awt.Dimension(160, 600));
        navigationPanel.setLayout(new java.awt.GridLayout(6, 1));

        navBtnDeThi.setText("Đề Thi");
        navBtnDeThi.setActionCommand("DeThi");
        navigationPanel.add(navBtnDeThi);

        navBtnThi.setText("Thi");
        navBtnThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navBtnThiActionPerformed(evt);
            }
        });
        navigationPanel.add(navBtnThi);

        navBtnCauHoi.setText("Câu hỏi");
        navBtnCauHoi.setActionCommand("CauHoi");
        navigationPanel.add(navBtnCauHoi);

        navBtnUser.setText("Người dùng");
        navBtnUser.setActionCommand("NguoiDung");
        navigationPanel.add(navBtnUser);

        navBtnThongKe.setText("Thống Kê");
        navBtnThongKe.setActionCommand("ThongKe");
        navigationPanel.add(navBtnThongKe);

        navBtnDangXuat.setText("Đăng xuất");
        navBtnDangXuat.setActionCommand("DangXuat");
        navigationPanel.add(navBtnDangXuat);

        sidePanel.add(navigationPanel, java.awt.BorderLayout.WEST);

        getContentPane().add(sidePanel, java.awt.BorderLayout.WEST);

        currentPanel.setBackground(new java.awt.Color(255, 153, 153));
        currentPanel.setLayout(new java.awt.CardLayout());

        jLabel7.setText(" Example Panel");

        javax.swing.GroupLayout examplePanel1Layout = new javax.swing.GroupLayout(examplePanel1);
        examplePanel1.setLayout(examplePanel1Layout);
        examplePanel1Layout.setHorizontalGroup(
                examplePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(examplePanel1Layout.createSequentialGroup()
                                .addGap(324, 324, 324)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(622, Short.MAX_VALUE)));
        examplePanel1Layout.setVerticalGroup(
                examplePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(examplePanel1Layout.createSequentialGroup()
                                .addContainerGap(373, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(243, 243, 243)));

        currentPanel.add(examplePanel1, "card3");

        getContentPane().add(currentPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void navBtnThiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_navBtnThiActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_navBtnThiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private java.awt.CardLayout cardLayout;
    private javax.swing.JPanel currentPanel;
    private javax.swing.JPanel examplePanel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JToggleButton navBtnCauHoi;
    private javax.swing.JToggleButton navBtnDangXuat;
    private javax.swing.JToggleButton navBtnDeThi;
    private javax.swing.JToggleButton navBtnThi;
    private javax.swing.JToggleButton navBtnThongKe;
    private javax.swing.JToggleButton navBtnUser;
    private javax.swing.JToggleButton navBtnUserInfo;
    private javax.swing.JPanel navigationPanel;
    private javax.swing.JPanel sidePanel;
    // End of variables declaration//GEN-END:variables
}
