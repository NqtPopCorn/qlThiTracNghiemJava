/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.User;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.example.qlthitracnghiem.BUS.UserBUS;
import com.example.qlthitracnghiem.DTO.UserDTO;

/**
 *
 * @author truon
 */
public class UserPanel extends javax.swing.JPanel {
    private JFrame parent;
    private DefaultTableModel tbModel;
    private UserBUS userBUS = UserBUS.getInstance();

    /**
     * Creates new form UserPanel
     */
    public UserPanel(JFrame frame) {
        initComponents();
        this.parent = frame;
        this.tbModel = (DefaultTableModel) tbUsers.getModel();
        loadDataTable();
    }

    private void loadDataTable(List<UserDTO> users) {
        tbModel.setRowCount(0);
        for (UserDTO user : users) {
            tbModel.addRow(
                    new Object[] { user.getUserID(), user.getUserName(),
                            user.getUserEmail(), user.getUserFullName() });
        }
    }

    public void loadDataTable() {
        try {
            List<UserDTO> users = userBUS.getAll();
            loadDataTable(users);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra\n" + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        tfKeyword = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsers = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(1040, 700));
        setPreferredSize(new java.awt.Dimension(1040, 700));

        tfKeyword.setToolTipText("Nhập từ khóa");
        tfKeyword.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập từ khóa"));

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh_30.png"))); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnCreate.setBackground(new java.awt.Color(51, 153, 255));
        btnCreate.setForeground(new java.awt.Color(51, 51, 51));
        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btnCreate.setText("Create");
        btnCreate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnCreate.setBorderPainted(false);
        btnCreate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCreate.setMaximumSize(new java.awt.Dimension(72, 60));
        btnCreate.setMinimumSize(new java.awt.Dimension(72, 40));
        btnCreate.setPreferredSize(new java.awt.Dimension(72, 51));
        btnCreate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(255, 204, 0));
        btnUpdate.setForeground(new java.awt.Color(51, 51, 51));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wrench.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUpdate.setMaximumSize(new java.awt.Dimension(72, 60));
        btnUpdate.setMinimumSize(new java.awt.Dimension(72, 40));
        btnUpdate.setPreferredSize(new java.awt.Dimension(72, 51));
        btnUpdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 153, 153));
        btnDelete.setForeground(new java.awt.Color(51, 51, 51));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/multiply.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setMaximumSize(new java.awt.Dimension(72, 60));
        btnDelete.setPreferredSize(new java.awt.Dimension(72, 57));
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnImport.setBackground(new java.awt.Color(102, 255, 102));
        btnImport.setForeground(new java.awt.Color(51, 51, 51));
        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/excel_30.png"))); // NOI18N
        btnImport.setText("Import");
        btnImport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 81,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 81,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 81,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107,
                                        Short.MAX_VALUE)
                                .addComponent(tfKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 283,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearch)
                                .addGap(18, 18, 18)
                                .addComponent(btnRefresh)
                                .addGap(17, 17, 17)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(41, 41, 41)
                                                        .addComponent(btnRefresh))
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                jPanel1Layout.createSequentialGroup()
                                                                        .addContainerGap(
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(btnUpdate,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        67, Short.MAX_VALUE)
                                                                                .addComponent(btnCreate,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        Short.MAX_VALUE)))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(btnDelete,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                67,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(btnImport,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                67,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(39, 39, 39)
                                                        .addComponent(tfKeyword, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(11, 11, 11)));

        tbUsers.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "UserID", "Username", "Email", "Fullname"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbUsers);
        tbUsers.setColumnModel(generateTableColumnModel());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                                .addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnImportActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                userBUS.importExcel(fileChooser.getSelectedFile());
                loadDataTable();
                JOptionPane.showMessageDialog(this, "Import thành công", "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra\n" + e.getMessage(), "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }// GEN-LAST:event_btnImportActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSearchActionPerformed
        String keyword = tfKeyword.getText();
        try {
            List<UserDTO> users = userBUS.search(keyword);
            loadDataTable(users);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra\n" + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_btnSearchActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tbUsers.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn một dòng để xóa", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        String userId = tbUsers.getValueAt(selectedRow, 0).toString();
        try {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa tài khoản này?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                userBUS.delete(Integer.parseInt(userId));
                JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công", "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);
                loadDataTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra\n" + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }// GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = tbUsers.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn một dòng để cập nhật", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        String userId = tbUsers.getValueAt(selectedRow, 0).toString();
        try {
            new UpdateUserDialog(this, userId).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra\n" + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }// GEN-LAST:event_btnUpdateActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCreateActionPerformed
        new CreateUserDialog(this.parent, true, this).setVisible(true);
    }// GEN-LAST:event_btnCreateActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRefreshActionPerformed
        this.tfKeyword.setText("");
        this.loadDataTable();
    }// GEN-LAST:event_btnRefreshActionPerformed

    public JFrame getParentFrame() {
        return this.parent;
    }

    private TableColumnModel generateTableColumnModel() {
        TableColumnModel columnModel = tbUsers.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30); // UserID
        columnModel.getColumn(1).setPreferredWidth(100); // Username
        columnModel.getColumn(2).setPreferredWidth(200); // Email
        columnModel.getColumn(3).setPreferredWidth(100); // FULLNAME
        return columnModel;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbUsers;
    private javax.swing.JTextField tfKeyword;
    // End of variables declaration//GEN-END:variables
}
