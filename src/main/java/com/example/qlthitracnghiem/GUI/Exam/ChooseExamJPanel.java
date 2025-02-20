/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.Exam;

import com.example.qlthitracnghiem.DAO.QaDAO;
import com.example.qlthitracnghiem.DTO.ExamDTOq;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author light
 */
public class ChooseExamJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ChooseExamJPanel
     */
    DefaultTableModel tbModel = new DefaultTableModel();
    private DoExamJPanel doExJPanel;
    public ChooseExamJPanel(DoExamJPanel doExamJPanel) {
        this.doExJPanel = doExamJPanel;
        initComponents();
        customstyle();

        handleChooseTestCodeInTable();
    }
    private void customstyle() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        examJTable.setModel(tbModel);
        examJTable.setRowHeight(50);
        examJTable.setDefaultEditor(Object.class, null);
        tbModel.addColumn("Mã đề thi");
        loadDataTable();

        for (int i = 0; i < examJTable.getColumnCount(); i++) {
            examJTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    private void handleChooseTestCodeInTable() {
        ListSelectionModel selectionModel = examJTable.getSelectionModel();
        ListSelectionListener existingListener = null;

        if (existingListener == null) {
            selectionModel.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = examJTable.getSelectedRow();
                        if (selectedRow != -1) {
                            // Get the table model (make sure tbModel is accessible here)
                            if (tbModel != null) {  //check if tbModel is initialized
                                String selectedTestCode = (String) tbModel.getValueAt(selectedRow, 0);
                                currentTestCodeJLabel.setText(selectedTestCode);
                            } else {
                                JOptionPane.showMessageDialog(examJTable, "Table Model is not initialized");
                            }

                        }
                    }
                }
            });
        }
    }

    private void loadDataTable() {
        try {
            ArrayList<String> testCodeList = QaDAO.getExams();
            loadDataTable(testCodeList);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra\n" + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDataTable(ArrayList<String> testCodeList) {
        tbModel.setRowCount(0);
        for (String tsCode : testCodeList) {
            tbModel.addRow(
                    new Object[]{tsCode});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPanel = new javax.swing.JScrollPane();
        examJTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        titleMaDeThiLabel = new javax.swing.JLabel();
        currentTestCodeJLabel = new javax.swing.JLabel();
        doTestBtn = new javax.swing.JButton();

        examJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đề thi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        examJTable.setColumnSelectionAllowed(true);
        examJTable.getTableHeader().setReorderingAllowed(false);
        jScrollPanel.setViewportView(examJTable);
        examJTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel3.setText("Tìm kiếm bài thi:");

        searchTextField.setToolTipText("nhập mã");
        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        searchBtn.setText("Tìm kiếm");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new java.awt.GridBagLayout());

        titleMaDeThiLabel.setText("Chọn mã đề thi vào vào làm bài:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 9, 0);
        jPanel2.add(titleMaDeThiLabel, gridBagConstraints);

        currentTestCodeJLabel.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        currentTestCodeJLabel.setText("chưa chọn bài thi!");
        currentTestCodeJLabel.setToolTipText("trống");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 7, 0);
        jPanel2.add(currentTestCodeJLabel, gridBagConstraints);

        doTestBtn.setText("Làm bài");
        doTestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doTestBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel2.add(doTestBtn, gridBagConstraints);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchBtn)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        String keyword = searchTextField.getText();
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        String keyword = searchTextField.getText().replace("\\s", "");

        if (keyword == "") {
            loadDataTable();
        } else {
            try {
                ArrayList<String> result = QaDAO.getExams(keyword);
                loadDataTable(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            searchTextField.setText("");
        }

    }//GEN-LAST:event_searchBtnActionPerformed

    private void doTestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doTestBtnActionPerformed
        String testCode = currentTestCodeJLabel.getText();
        boolean isChooseTestCode = testCode.startsWith("TST");
        if (isChooseTestCode) {
            // move to do exam panel
            System.out.println("move to new frame");
            
//            ExamDTOq exDto = 
            
            doExJPanel.setTsCodeLabel(testCode);
            
            CardLayout cardLayout = (CardLayout) getParent().getLayout(); 
            cardLayout.show(getParent(), "doExamJPanel");
             
            setBorder(BorderFactory.createTitledBorder("Panel B"));
        } else {
            JOptionPane.showMessageDialog(doTestBtn, "Chưa chọn bài thi");
        }
    }//GEN-LAST:event_doTestBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currentTestCodeJLabel;
    private javax.swing.JButton doTestBtn;
    private javax.swing.JTable examJTable;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPanel;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JLabel titleMaDeThiLabel;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
//        frame.add(new ChooseExamJPanel());
        frame.setVisible(true);
    }
}
