/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.DeThi;

import com.example.qlthitracnghiem.BUS.ExamBUS;
import com.example.qlthitracnghiem.BUS.TopicsBUS;
import com.example.qlthitracnghiem.DTO.TestDTO;
import com.example.qlthitracnghiem.DTO.TopicsDTO;
import java.awt.Frame;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EditTestDialog extends javax.swing.JDialog {
        private HashMap<String, Integer> topicMap = new HashMap<>();
        private String testCode;
        public EditTestDialog(){
            
        }
        public EditTestDialog(Frame parent, boolean modal, TestDTO test) {
                super(parent, modal);
                initComponents();
                addAutoCalculateHardQuestionsListener();

                if (test != null) {
                        testCode = test.getTestCode();

                        jtfTitle.setText(test.getTestTitle());
                        loadTopics(test.getTpID());
                        jsTime.setValue(test.getTestTime());
                        jsQuestionNum.setValue(test.getNum_easy() + test.getNum_medium() + test.getNum_diff());
                        jtfEasyNum.setText(String.valueOf(test.getNum_easy()));
                        jtfMediumNum.setText(String.valueOf(test.getNum_medium()));
                        jtfHardNum.setText(String.valueOf(test.getNum_diff()));
                        jcbLimit.setSelectedItem(String.valueOf(test.getTestLimit()));
                        LocalDate localDate = test.getTestDate().toLocalDate();

                        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); // đổi ngày
                                                                                                           // từ
                                                                                                           // localdate
                                                                                                           // sang date

                        jDateChooser1.setDate(date);
                        System.err.println("TestTopic" + test.getTestID());
                }
        }

        private void loadTopics(int tpID) {
//                TopicsBUS tpBUS = new TopicsBUS();
//                TopicsDTO topics = tpBUS.getTopicByID(tpID);
//                jcbTopic.removeAllItems(); // Xóa dữ liệu cũ
//                jcbTopic.addItem(topics.getTpTitle());
//                topicMap.put(topics.getTpTitle(), topics.getTpID());
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        //
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jtfEasyNum = new javax.swing.JTextField();
        jtfMediumNum = new javax.swing.JTextField();
        jtfHardNum = new javax.swing.JTextField();
        jcbLimit = new javax.swing.JComboBox<>();
        btnEdit = new javax.swing.JButton();
        jsTime = new javax.swing.JSlider();
        jsQuestionNum = new javax.swing.JSlider();
        jTextField1 = new javax.swing.JTextField();
        jtfTitle = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sửa ma trận đề");
        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(61, 100));
        jScrollPane1.setViewportView(jTree1);

        jTextField14.setEditable(false);
        jTextField14.setText("Tên bài thi:");
        jTextField14.setFocusable(false);

        jTextField15.setEditable(false);
        jTextField15.setText("Chọn topic:");
        jTextField15.setFocusable(false);

        jTextField16.setEditable(false);
        jTextField16.setText("Thời gian thi:");
        jTextField16.setFocusable(false);
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jTextField17.setEditable(false);
        jTextField17.setText("Số lượng câu hỏi:");
        jTextField17.setFocusable(false);
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        jTextField18.setEditable(false);
        jTextField18.setText("Số câu dễ:");
        jTextField18.setFocusable(false);
        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });

        jTextField19.setEditable(false);
        jTextField19.setText("Số câu trung bình:");
        jTextField19.setFocusable(false);

        jTextField20.setEditable(false);
        jTextField20.setText("Số câu khó: ");
        jTextField20.setFocusable(false);

        jTextField21.setEditable(false);
        jTextField21.setText("Số lượt làm bài:");
        jTextField21.setFocusable(false);
        jTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });

        jtfEasyNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfEasyNumActionPerformed(evt);
            }
        });

        jtfHardNum.setEditable(false);
        jtfHardNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfHardNumActionPerformed(evt);
            }
        });

        jcbLimit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        jcbLimit.setEnabled(false);
        jcbLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbLimitActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(28, 58, 118));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jsTime.setMajorTickSpacing(15);
        jsTime.setMaximum(90);
        jsTime.setMinimum(15);
        jsTime.setMinorTickSpacing(15);
        jsTime.setPaintLabels(true);
        jsTime.setPaintTicks(true);
        jsTime.setSnapToTicks(true);
        jsTime.setEnabled(false);

        jsQuestionNum.setMajorTickSpacing(10);
        jsQuestionNum.setMaximum(50);
        jsQuestionNum.setMinimum(10);
        jsQuestionNum.setPaintLabels(true);
        jsQuestionNum.setPaintTicks(true);
        jsQuestionNum.setSnapToTicks(true);

        jTextField1.setEditable(false);
        jTextField1.setText("Ngày làm bài:");
        jTextField1.setFocusable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jtfTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfTitleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                .addGap(38, 38, 38)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(92, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtfHardNum, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jcbLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtfEasyNum, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jtfMediumNum, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jsTime, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jsQuestionNum, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jsTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jsQuestionNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfEasyNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfMediumNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfHardNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField21ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

        private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField16ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextField16ActionPerformed

        private void jtfTitleActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jtfTitleActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jtfTitleActionPerformed

        private void jcbLimitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jcbLimitActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jcbLimitActionPerformed

        private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextField1ActionPerformed

        private void jtfEasyNumActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jtfEasyNumActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jtfEasyNumActionPerformed

        private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEditActionPerformed
//                System.err.println("total q" + jsQuestionNum.getValue());
//                String title = jtfTitle.getText().trim();
////                String topic = (String) jcbTopic.getSelectedItem();
//                int topicID = topicMap.getOrDefault(topic, -1);
//                int timeLimit = jsTime.getValue();
//                int totalQuestions = jsQuestionNum.getValue();
//                int easyQuestions = Integer.parseInt(jtfEasyNum.getText().trim());
//                int mediumQuestions = Integer.parseInt(jtfMediumNum.getText().trim());
//                int hardQuestions = totalQuestions - easyQuestions - mediumQuestions;
//                System.err.println("total hard" + hardQuestions);
//                Date selectedDate = jDateChooser1.getDate();
//
//                LocalDateTime localDateTime = selectedDate.toInstant()
//                                .atZone(ZoneId.systemDefault())
//                                .toLocalDateTime();
//                String attemptLimit = (String) jcbLimit.getSelectedItem();
////                int examNum = jsExamNum.getValue();
//                System.err.println("Exam_Num" + examNum);
//                try {
//
//                        ExamBUS examBUS = new ExamBUS();
//                        TestDTO exam = new TestDTO();
//                        exam.setTestCode(testCode);
//                        exam.setTestTitle(title);
//                        exam.setTpID(topicID);
//                        exam.setTestTime(timeLimit);
//
//                        exam.setNum_easy(easyQuestions);
//                        exam.setNum_medium(mediumQuestions);
//                        exam.setNum_diff(hardQuestions);
//                        exam.setTestLimit(Integer.parseInt(attemptLimit));
//                        exam.setTestDate(localDateTime);
//                        int isEdit = examBUS.update(exam, examNum);
//
//                        if (isEdit == 1) {
//                                JOptionPane.showMessageDialog(this, "Sửa đề thi thành công!", "Thành công",
//                                                JOptionPane.INFORMATION_MESSAGE);
//                                dispose();
//                        } else {
//                                JOptionPane.showMessageDialog(this, "Sửa đề thi thất bại!", "Lỗi",
//                                                JOptionPane.ERROR_MESSAGE);
//                        }
//                } catch (SQLException ex) {
//                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//                } catch (NumberFormatException ex) {
//                        JOptionPane.showMessageDialog(this, "Vui lòng nhập lại số câu hỏi dễ và trung bình!", "Lỗi",
//                                        JOptionPane.ERROR_MESSAGE);
//                } catch (Exception ex) {
//                        ex.printStackTrace();
//                        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi sửa đề thi!", "Lỗi",
//                                        JOptionPane.ERROR_MESSAGE);
//                }
        }

        private void jtfHardNumActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jtfHardNumActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jtfHardNumActionPerformed

        private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField22ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextField22ActionPerformed

        private void jcbTopicActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jcbTopicActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jcbTopicActionPerformed

        private void addAutoCalculateHardQuestionsListener() {
                DocumentListener listener = new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                                calculateHardQuestions();
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                                calculateHardQuestions();
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                                calculateHardQuestions();
                        }
                };

                jtfEasyNum.getDocument().addDocumentListener(listener);
                jtfMediumNum.getDocument().addDocumentListener(listener);
        }

        private void calculateHardQuestions() {
                try {
                        int totalQuestions = jsQuestionNum.getValue();
                        int easyQuestions = Integer.parseInt(jtfEasyNum.getText().trim());
                        int mediumQuestions = Integer.parseInt(jtfMediumNum.getText().trim());
                        int hardQuestions = totalQuestions - easyQuestions - mediumQuestions;

                        jtfHardNum.setText(String.valueOf(hardQuestions));
                } catch (NumberFormatException ex) {

                }
        }

        public static void main(String args[]) {

                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(EditTestDialog.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(EditTestDialog.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(EditTestDialog.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(EditTestDialog.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                }
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTree jTree1;
    private javax.swing.JComboBox<String> jcbLimit;
    private javax.swing.JSlider jsQuestionNum;
    private javax.swing.JSlider jsTime;
    private javax.swing.JTextField jtfEasyNum;
    private javax.swing.JTextField jtfHardNum;
    private javax.swing.JTextField jtfMediumNum;
    private javax.swing.JTextField jtfTitle;
    // End of variables declaration//GEN-END:variables
}
