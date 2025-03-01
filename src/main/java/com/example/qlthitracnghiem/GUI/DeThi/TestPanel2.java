
package com.example.qlthitracnghiem.GUI.DeThi;

import com.example.qlthitracnghiem.BUS.ExamBUS;
import com.example.qlthitracnghiem.BUS.TestBUS;
import com.example.qlthitracnghiem.DTO.TestDTO;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TestPanel2 extends javax.swing.JPanel {

    private TestBUS testBUS = new TestBUS();
    public TestPanel2()  {
        
        initComponents();
        ArrayList<TestDTO> testList;
        try {
            testList = testBUS.getAll();
             updateTestPanel(testList);
        } catch (Exception ex) {
            Logger.getLogger(TestPanel2.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jcbSearch = new javax.swing.JComboBox<>();
        searchField = new javax.swing.JTextField();
        btnCreateTest = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jpnTest = new javax.swing.JPanel();
        testPanelRow1 = new com.example.qlthitracnghiem.GUI.DeThi.TestPanelRow();
        testPanelRow7 = new com.example.qlthitracnghiem.GUI.DeThi.TestPanelRow();
        testPanelRow6 = new com.example.qlthitracnghiem.GUI.DeThi.TestPanelRow();
        testPanelRow2 = new com.example.qlthitracnghiem.GUI.DeThi.TestPanelRow();
        testPanelRow5 = new com.example.qlthitracnghiem.GUI.DeThi.TestPanelRow();
        testPanelRow4 = new com.example.qlthitracnghiem.GUI.DeThi.TestPanelRow();
        testPanelRow3 = new com.example.qlthitracnghiem.GUI.DeThi.TestPanelRow();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jtxSearch = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jlbTitle = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jpnExam = new javax.swing.JPanel();
        examPanelRow14 = new com.example.qlthitracnghiem.GUI.DeThi.ExamPanelRow();
        examPanelRow12 = new com.example.qlthitracnghiem.GUI.DeThi.ExamPanelRow();
        examPanelRow13 = new com.example.qlthitracnghiem.GUI.DeThi.ExamPanelRow();
        examPanelRow8 = new com.example.qlthitracnghiem.GUI.DeThi.ExamPanelRow();
        examPanelRow9 = new com.example.qlthitracnghiem.GUI.DeThi.ExamPanelRow();
        examPanelRow11 = new com.example.qlthitracnghiem.GUI.DeThi.ExamPanelRow();
        examPanelRow10 = new com.example.qlthitracnghiem.GUI.DeThi.ExamPanelRow();
        jpnChiTietExam = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jlbExamCode = new javax.swing.JLabel();
        btnXuatDocx = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jpnViewChiTiet = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jcbSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang diễn ra", "Đã kết thúc" }));
        jcbSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSearchActionPerformed(evt);
            }
        });

        searchField.setText("Tìm kiếm");
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFieldFocusLost(evt);
            }
        });
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        btnCreateTest.setBackground(new java.awt.Color(28, 58, 118));
        btnCreateTest.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateTest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btnCreateTest.setText("Tạo test");
        btnCreateTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateTestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreateTest)
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreateTest))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jpnTest.setLayout(new javax.swing.BoxLayout(jpnTest, javax.swing.BoxLayout.Y_AXIS));

        testPanelRow1.setName(""); // NOI18N
        testPanelRow1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                testPanelRow1MouseClicked(evt);
            }
        });
        jpnTest.add(testPanelRow1);
        jpnTest.add(testPanelRow7);
        jpnTest.add(testPanelRow6);
        jpnTest.add(testPanelRow2);
        jpnTest.add(testPanelRow5);
        jpnTest.add(testPanelRow4);
        jpnTest.add(testPanelRow3);

        jScrollPane2.setViewportView(jpnTest);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1, "card1");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jtxSearch.setText("Tìm kiếm");
        jtxSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxSearchFocusLost(evt);
            }
        });
        jtxSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxSearchActionPerformed(evt);
            }
        });
        jtxSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxSearchKeyReleased(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(28, 58, 118));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        jButton4.setText("tao de thi");

        jButton5.setBackground(new java.awt.Color(28, 58, 118));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jlbTitle.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        jlbTitle.setText("Kiem tra giua ki");

        jButton6.setBackground(new java.awt.Color(28, 58, 118));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("refresh");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jtxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(249, 249, 249)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(352, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jlbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jpnExam.setBackground(new java.awt.Color(255, 255, 255));
        jpnExam.setLayout(new javax.swing.BoxLayout(jpnExam, javax.swing.BoxLayout.Y_AXIS));

        examPanelRow14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                examPanelRow14MouseClicked(evt);
            }
        });
        jpnExam.add(examPanelRow14);
        jpnExam.add(examPanelRow12);
        jpnExam.add(examPanelRow13);
        jpnExam.add(examPanelRow8);
        jpnExam.add(examPanelRow9);
        jpnExam.add(examPanelRow11);
        jpnExam.add(examPanelRow10);

        jScrollPane3.setViewportView(jpnExam);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel2, "card2");

        jpnChiTietExam.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jButton9.setBackground(new java.awt.Color(28, 58, 118));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Back");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jlbExamCode.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        jlbExamCode.setText("Exam A");

        btnXuatDocx.setBackground(new java.awt.Color(28, 58, 118));
        btnXuatDocx.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatDocx.setText("Xuat Docx");
        btnXuatDocx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatDocxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbExamCode, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXuatDocx)
                .addGap(14, 14, 14))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jlbExamCode, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatDocx))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jScrollPane4.setPreferredSize(new java.awt.Dimension(1066, 570));

        jpnViewChiTiet.setBackground(new java.awt.Color(255, 255, 255));
        jpnViewChiTiet.setPreferredSize(new java.awt.Dimension(1066, 570));
        jpnViewChiTiet.setLayout(new javax.swing.BoxLayout(jpnViewChiTiet, javax.swing.BoxLayout.Y_AXIS));

        jLabel3.setText("Test");
        jpnViewChiTiet.add(jLabel3);

        jScrollPane4.setViewportView(jpnViewChiTiet);

        javax.swing.GroupLayout jpnChiTietExamLayout = new javax.swing.GroupLayout(jpnChiTietExam);
        jpnChiTietExam.setLayout(jpnChiTietExamLayout);
        jpnChiTietExamLayout.setHorizontalGroup(
            jpnChiTietExamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnChiTietExamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnChiTietExamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnChiTietExamLayout.setVerticalGroup(
            jpnChiTietExamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnChiTietExamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jpnChiTietExam, "card3");
    }// </editor-fold>//GEN-END:initComponents
    public void setJlbTitle(String title) {
        jlbTitle.setText(title);
    }
    private void jcbSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSearchActionPerformed
        Search();
    }//GEN-LAST:event_jcbSearchActionPerformed

    private void btnCreateTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateTestActionPerformed
        CreateTestDialog createTestDialog=new CreateTestDialog(null,true);
        createTestDialog.setVisible(true);
    }//GEN-LAST:event_btnCreateTestActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
       Search();
        
    }//GEN-LAST:event_searchFieldActionPerformed
    
    private void searchFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusGained

                if (searchField.getText().equals("Tìm kiếm")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
    }//GEN-LAST:event_searchFieldFocusGained

    private void searchFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusLost
        if (searchField.getText().trim().isEmpty()) {
                    searchField.setText("Tìm kiếm");
                    searchField.setForeground(Color.GRAY);
                }
    }//GEN-LAST:event_searchFieldFocusLost

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        Search();
    }//GEN-LAST:event_searchFieldKeyReleased

    private void jtxSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxSearchActionPerformed

    private void jtxSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxSearchKeyReleased
        ExamBUS examBUS=new ExamBUS();
        
        try {
            examBUS.searchExCode(jtxSearch.getText());
        } catch (Exception ex) {
            Logger.getLogger(TestPanel2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jtxSearchKeyReleased

    private void jtxSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxSearchFocusGained
        if (jtxSearch.getText().trim().isEmpty()) {
                    jtxSearch.setText("Tìm kiếm");
                    jtxSearch.setForeground(Color.GRAY);
                }
    }//GEN-LAST:event_jtxSearchFocusGained

    private void jtxSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxSearchFocusLost
        if (jtxSearch.getText().trim().isEmpty()) {
                    jtxSearch.setText("Tìm kiếm");
                   jtxSearch.setForeground(Color.GRAY);
                }
    }//GEN-LAST:event_jtxSearchFocusLost

    private void btnXuatDocxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatDocxActionPerformed
//        String filePath = ExportDOCX.chooseFileToSave();
//                            System.err.println("filePath: " + filePath);
//                            
//                            if (filePath != null) {
//                                try {
//                                    ExportDOCX.exportToDocx(test, examCodes, filePath);
//                                    JOptionPane.showMessageDialog(TestPanel2.this, "Xuất file thành công!", "Thành công",
//                                            JOptionPane.INFORMATION_MESSAGE);
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    JOptionPane.showMessageDialog(TestPanel2.this, "Lỗi khi xuất file!", "Lỗi",
//                                            JOptionPane.ERROR_MESSAGE);
//                                }
//                            } else {
//                                JOptionPane.showMessageDialog(TestPanel2.this, "Không có file được chọn!", "Thông báo",
//                                        JOptionPane.WARNING_MESSAGE);
//                            }
    }//GEN-LAST:event_btnXuatDocxActionPerformed
    private void Search() {
        String keyword = searchField.getText().trim();
        if (keyword.equals("Tìm kiếm")) {
            keyword = "";
        }
        String statusFilter = (String) jcbSearch.getSelectedItem();
        int status = -1; // Mặc định là tất cả

        if ("Đang mở".equals(statusFilter)) {
            status = 1;
        } else if ("Đã kết thúc".equals(statusFilter)) {
            status = 0;
        }

        ArrayList<TestDTO> results;
        try {
            results = testBUS.search(keyword, status);
            updateTestPanel(results);
        } catch (Exception ex) {
            Logger.getLogger(TestPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void updateTestPanel(ArrayList<TestDTO> testList) {
    jpnTest.removeAll();
    for (TestDTO test : testList) {
        
        TestPanelRow testPanelRow = new TestPanelRow(this, test);
        testPanelRow.setTestInfo(test); 
        testPanelRow.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                ((CardLayout) TestPanel2.this.getLayout()).show(TestPanel2.this, "card2");
            }
        });
        jpnTest.add(testPanelRow);
    }
    
    jpnTest.revalidate();
    jpnTest.repaint();
}
    public void showExamPanel(TestDTO testCode) {
    try {
        ExamBUS examBUS = new ExamBUS();
        List<String> examCodes = examBUS.getExamCodesByTestCode(testCode.getTestCode());

        jpnExam.removeAll(); 
        jlbTitle.setText(testCode.getTestTitle());
        if (examCodes.isEmpty()) {
            JLabel lblNoExam = new JLabel("Hiện tại không có đề thi nào");
            lblNoExam.setFont(new Font("Arial", Font.BOLD, 16)); 
            lblNoExam.setHorizontalAlignment(JLabel.CENTER); 
            jpnExam.add(lblNoExam); 
        } else {
            
            for (String examCode : examCodes) {
                jlbExamCode.setText("Mã đề: " + examCode); 
                ExamPanelRow examPanelRow = new ExamPanelRow(examCode, jpnViewChiTiet, this); 
                examPanelRow.setExamInfo(examCode);
                jpnExam.add(examPanelRow); 
            }
        }

        jpnExam.revalidate();
        jpnExam.repaint();

        CardLayout cardLayout = (CardLayout) this.getLayout();
        cardLayout.show(this, "card2"); 
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách đề thi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    private void examPanelRow14MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_examPanelRow14MouseClicked
        ((CardLayout) this.getLayout()).show(this, "card3");
    }// GEN-LAST:event_examPanelRow14MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton9ActionPerformed
        ((CardLayout) this.getLayout()).show(this, "card2");
    }// GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton5ActionPerformed
        ((CardLayout) this.getLayout()).show(this, "card1");
    }// GEN-LAST:event_jButton5ActionPerformed

    private void testPanelRow1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_testPanelRow1MouseClicked
        
        ((CardLayout) this.getLayout()).show(this, "card2");
    }// GEN-LAST:event_testPanelRow1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateTest;
    private javax.swing.JButton btnXuatDocx;
    private com.example.qlthitracnghiem.GUI.DeThi.ExamPanelRow examPanelRow10;
    private com.example.qlthitracnghiem.GUI.DeThi.ExamPanelRow examPanelRow11;
    private com.example.qlthitracnghiem.GUI.DeThi.ExamPanelRow examPanelRow12;
    private com.example.qlthitracnghiem.GUI.DeThi.ExamPanelRow examPanelRow13;
    private com.example.qlthitracnghiem.GUI.DeThi.ExamPanelRow examPanelRow14;
    private com.example.qlthitracnghiem.GUI.DeThi.ExamPanelRow examPanelRow8;
    private com.example.qlthitracnghiem.GUI.DeThi.ExamPanelRow examPanelRow9;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox<String> jcbSearch;
    private javax.swing.JLabel jlbExamCode;
    private javax.swing.JLabel jlbTitle;
    private javax.swing.JPanel jpnChiTietExam;
    private javax.swing.JPanel jpnExam;
    private javax.swing.JPanel jpnTest;
    private javax.swing.JPanel jpnViewChiTiet;
    private javax.swing.JTextField jtxSearch;
    private javax.swing.JTextField searchField;
    private com.example.qlthitracnghiem.GUI.DeThi.TestPanelRow testPanelRow1;
    private com.example.qlthitracnghiem.GUI.DeThi.TestPanelRow testPanelRow2;
    private com.example.qlthitracnghiem.GUI.DeThi.TestPanelRow testPanelRow3;
    private com.example.qlthitracnghiem.GUI.DeThi.TestPanelRow testPanelRow4;
    private com.example.qlthitracnghiem.GUI.DeThi.TestPanelRow testPanelRow5;
    private com.example.qlthitracnghiem.GUI.DeThi.TestPanelRow testPanelRow6;
    private com.example.qlthitracnghiem.GUI.DeThi.TestPanelRow testPanelRow7;
    // End of variables declaration//GEN-END:variables
}
