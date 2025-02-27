/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.DeThi;

import com.example.qlthitracnghiem.BUS.ExamBUS;
import com.example.qlthitracnghiem.BUS.TopicsBUS;
import com.example.qlthitracnghiem.DTO.TestDTO;
import com.example.qlthitracnghiem.DTO.TopicsDTO;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CreateTestDialog extends javax.swing.JDialog {
        private HashMap<String, Integer> topicMap = new HashMap<>();

        public CreateTestDialog(java.awt.Frame parent, boolean modal) {
                super(parent, modal);
                initComponents();
                addAutoCalculateHardQuestionsListener();
                loadTopics();
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jtfEasyNum = new javax.swing.JTextField();
        jtfTitle = new javax.swing.JTextField();
        jtfMediumNum = new javax.swing.JTextField();
        jtfHardNum = new javax.swing.JTextField();
        jcbLimit = new javax.swing.JComboBox<>();
        btnCreate = new javax.swing.JButton();
        jsTime = new javax.swing.JSlider();
        jsQuestionNum = new javax.swing.JSlider();
        jTextField1 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tạo ma trận đề");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(0, 0));

        jTextField14.setEditable(false);
        jTextField14.setText("Tên bài thi:");
        jTextField14.setFocusable(false);
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        jTextField15.setEditable(false);
        jTextField15.setText("Chọn topic:");
        jTextField15.setFocusable(false);

        jTextField16.setEditable(false);
        jTextField16.setText("Thời gian thi:");
        jTextField16.setFocusable(false);

        jTextField17.setEditable(false);
        jTextField17.setText("Số lượng câu hỏi:");
        jTextField17.setFocusable(false);

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

        jtfTitle.setToolTipText("");

        jtfHardNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfHardNumActionPerformed(evt);
            }
        });

        jcbLimit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));

        btnCreate.setBackground(new java.awt.Color(28, 58, 118));
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("Tạo đề thi");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        jsTime.setMajorTickSpacing(15);
        jsTime.setMaximum(90);
        jsTime.setMinimum(15);
        jsTime.setMinorTickSpacing(15);
        jsTime.setPaintLabels(true);
        jsTime.setPaintTicks(true);
        jsTime.setSnapToTicks(true);

        jsQuestionNum.setMajorTickSpacing(10);
        jsQuestionNum.setMaximum(50);
        jsQuestionNum.setMinimum(10);
        jsQuestionNum.setPaintLabels(true);
        jsQuestionNum.setPaintTicks(true);
        jsQuestionNum.setSnapToTicks(true);

        jTextField1.setEditable(false);
        jTextField1.setText("Ngày làm bài:");
        jTextField1.setFocusable(false);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(61, 100));

        jTree1.setToolTipText("hold Ctrl to multi select");
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jsTime, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jtfTitle, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jcbLimit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jsQuestionNum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jtfEasyNum, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtfHardNum)
                                    .addComponent(jtfMediumNum, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(btnCreate)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jsTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jsQuestionNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfEasyNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfMediumNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfHardNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

        private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCreateActionPerformed
                try {

                        String title = jtfTitle.getText().trim();
                        String topic = (String) jcbTopic.getSelectedItem();
                        int topicID = topicMap.getOrDefault(topic, -1);
                        int timeLimit = jsTime.getValue();
                        int totalQuestions = jsQuestionNum.getValue();
                        int easyQuestions = Integer.parseInt(jtfEasyNum.getText().trim());
                        int mediumQuestions = Integer.parseInt(jtfMediumNum.getText().trim());
                        int hardQuestions = totalQuestions - easyQuestions - mediumQuestions;
                        Date selectedDate = jDateChooser1.getDate();

                        LocalDateTime localDateTime = selectedDate.toInstant()
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDateTime();
                        String attemptLimit = (String) jcbLimit.getSelectedItem();
                        int examNum = jsExamNum.getValue();
                        if (title.isEmpty()) {
                                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên bài thi!", "Lỗi",
                                                JOptionPane.ERROR_MESSAGE);
                                return;
                        }

                        ExamBUS examBUS = new ExamBUS();
                        TestDTO exam = new TestDTO();
                        exam.setTestTitle(title);
                        exam.setTpID(topicID);
                        exam.setTestTime(timeLimit);

                        exam.setNum_easy(easyQuestions);
                        exam.setNum_medium(mediumQuestions);
                        exam.setNum_diff(hardQuestions);
                        exam.setTestLimit(Integer.parseInt(attemptLimit));
                        exam.setTestDate(localDateTime);
                        boolean isCreated = examBUS.createExam(exam, examNum);

                        if (isCreated) {
                                JOptionPane.showMessageDialog(this, "Tạo đề thi thành công!", "Thành công",
                                                JOptionPane.INFORMATION_MESSAGE);
                                dispose();

                        } else {
                                JOptionPane.showMessageDialog(this, "Tạo đề thi thất bại!", "Lỗi",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho số câu hỏi!", "Lỗi",
                                        JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tạo đề thi!", "Lỗi",
                                        JOptionPane.ERROR_MESSAGE);
                }

        }// GEN-LAST:event_btnCreateActionPerformed

        private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField22ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextField22ActionPerformed

        private void jtfHardNumActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jtfHardNumActionPerformed

        }// GEN-LAST:event_jtfHardNumActionPerformed
         // hàm để load topic

        private void loadTopics() {
                TopicsBUS tpBUS = new TopicsBUS();
                ArrayList<TopicsDTO> topics = tpBUS.getAll();

                jcbTopic.removeAllItems(); // Xóa dữ liệu cũ

                for (TopicsDTO topic : topics) {
                        jcbTopic.addItem(topic.getTpTitle());
                        topicMap.put(topic.getTpTitle(), topic.getTpID()); // Lưu ID tương ứng

                }
        }

        private void jcbTopicActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jcbTopicActionPerformed

        }// GEN-LAST:event_jcbTopicActionPerformed

        private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField14ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextField14ActionPerformed

        private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField18ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextField18ActionPerformed

        /**
         * @param args the command line arguments
         */
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
                        java.util.logging.Logger.getLogger(CreateTestDialog.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(CreateTestDialog.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(CreateTestDialog.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(CreateTestDialog.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                }
                // </editor-fold>
                // </editor-fold>

                /* Create and display the dialog */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                CreateTestDialog dialog = new CreateTestDialog(new javax.swing.JFrame(), true);
                                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                                        @Override
                                        public void windowClosing(java.awt.event.WindowEvent e) {
                                                System.exit(0);
                                        }
                                });
                                dialog.setVisible(true);
                        }
                });
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
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
