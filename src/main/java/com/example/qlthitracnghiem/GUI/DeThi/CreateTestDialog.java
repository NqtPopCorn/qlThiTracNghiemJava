/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.DeThi;

import com.example.qlthitracnghiem.BUS.ExamBUS;
import com.example.qlthitracnghiem.BUS.TestBUS;
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
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class CreateTestDialog extends javax.swing.JDialog {
    private TopicsBUS topicsBUS = new TopicsBUS();

    public CreateTestDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        addAutoCalculateHardQuestionsListener();
        loadTopics();
    }

    public CreateTestDialog() {

    }

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

        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        lblSLCauHoi = new javax.swing.JTextField();
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
        tpTree = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tạo ma trận đề");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(0, 0));
        setPreferredSize(new java.awt.Dimension(505, 619));

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

        lblSLCauHoi.setEditable(false);
        lblSLCauHoi.setText("Số lượng câu hỏi:");
        lblSLCauHoi.setFocusable(false);

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

        jtfEasyNum.setText("0");

        jtfTitle.setToolTipText("");

        jtfMediumNum.setText("0");

        jtfHardNum.setText("0");
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
        jsQuestionNum.setMinimum(3);
        jsQuestionNum.setMinorTickSpacing(1);
        jsQuestionNum.setPaintLabels(true);
        jsQuestionNum.setPaintTicks(true);
        jsQuestionNum.setSnapToTicks(true);
        jsQuestionNum.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsQuestionNumStateChanged(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setText("Ngày làm bài:");
        jTextField1.setFocusable(false);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(61, 100));

        tpTree.setToolTipText("hold Ctrl to multi select");
        jScrollPane1.setViewportView(tpTree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtfHardNum)
                                    .addComponent(jtfMediumNum)
                                    .addComponent(jtfEasyNum, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(36, 36, 36))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jsTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtfTitle, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jcbLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSLCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsQuestionNum, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(btnCreate)))
                .addContainerGap())
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jsTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jsQuestionNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSLCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfEasyNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfMediumNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfHardNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jsQuestionNumStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_jsQuestionNumStateChanged
        lblSLCauHoi.setText("Số lượng câu hỏi: " + jsQuestionNum.getValue());
    }// GEN-LAST:event_jsQuestionNumStateChanged

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField17ActionPerformed

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
        String title = jtfTitle.getText().trim();
        ArrayList<Integer> topics = getIDsOfSelectedTopics();
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
        try {
            TestBUS testBUS = new TestBUS();
            TestDTO test = new TestDTO();
            test.setTestCode(test.getTestCode());
            test.setTestTitle(title);
            test.setTestTime(timeLimit);
            test.setNum_easy(easyQuestions);
            test.setNum_medium(mediumQuestions);
            test.setNum_diff(hardQuestions);
            test.setTestLimit(Integer.parseInt(attemptLimit));
            test.setTestDate(localDateTime);
            testBUS.create(test, topics.toArray(new Integer[0]));
            JOptionPane.showMessageDialog(this, "Thêm cấu trúc thành côngcông thành công!", "Thành công",
                    JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Hành động thất bại, có lỗi xảy ra!\n" + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_btnCreateActionPerformed

    public ArrayList<Integer> getIDsOfSelectedTopics() {
        ArrayList<Integer> topicIDs = new ArrayList<>();
        for (int i = 0; i < tpTree.getSelectionCount(); i++) {
            TopicsBUS tpBUS = new TopicsBUS();
            topicIDs.add(tpBUS.getTopicIdByName(tpTree.getSelectionPaths()[i].getLastPathComponent().toString()));
        }
        return topicIDs;
    }

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField22ActionPerformed

    private void jtfHardNumActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jtfHardNumActionPerformed

    }// GEN-LAST:event_jtfHardNumActionPerformed
     // hàm để load topic

    public void loadTopics() {
        ArrayList<TopicsDTO> topics = topicsBUS.getAll();
        tpTree.setModel(null);
        // Tạo node gốc
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tất cả");
        // Sử dụng HashMap để lưu node theo ID
        Map<Integer, DefaultMutableTreeNode> nodeMap = new HashMap<>();
        nodeMap.put(0, root);
        // Lặp qua danh sách các chủ đề
        for (TopicsDTO topic : topics) {
            if (topic.getTpStatus() == 1) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(topic.getTpTitle());
                nodeMap.put(topic.getTpID(), node);

                // Thêm vào node cha dựa vào tpParent
                DefaultMutableTreeNode parent = nodeMap.get(topic.getTpParent());
                if (parent != null) {
                    parent.add(node);
                } else {

                    root.add(node); // Thêm vào root nếu không có parent
                }
            }
        }
        // Gán dữ liệu vào JTree
        tpTree.setModel(new DefaultTreeModel(root));
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
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JComboBox<String> jcbLimit;
    private javax.swing.JSlider jsQuestionNum;
    private javax.swing.JSlider jsTime;
    private javax.swing.JTextField jtfEasyNum;
    private javax.swing.JTextField jtfHardNum;
    private javax.swing.JTextField jtfMediumNum;
    private javax.swing.JTextField jtfTitle;
    private javax.swing.JTextField lblSLCauHoi;
    private javax.swing.JTree tpTree;
    // End of variables declaration//GEN-END:variables
}
