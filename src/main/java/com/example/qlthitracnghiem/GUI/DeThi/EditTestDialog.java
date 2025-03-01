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
import java.awt.Frame;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class EditTestDialog extends javax.swing.JDialog {
    private TestDTO test;
    private Map<Integer, DefaultMutableTreeNode> topicMap = new HashMap<>();

    public EditTestDialog() {

    }

    public EditTestDialog(Frame parent, boolean modal, TestDTO test) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        addAutoCalculateHardQuestionsListener();
        loadTopics();

        if (test != null) {
            this.test = test;
            setSelectedTopicNode();
            jtfTitle.setText(test.getTestTitle());
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

    private void loadTopics() {
        TopicsBUS tpBUS = new TopicsBUS();
        ArrayList<TopicsDTO> topics = tpBUS.getAll();
        // Tạo node gốc
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tất cả");
        // Sử dụng HashMap để lưu node theo ID
        topicMap = new HashMap<>();
        topicMap.put(0, root); // Gốc có tpParent = 0
        // Lặp qua danh sách các chủ đề
        for (TopicsDTO topic : topics) {
            if (topic.getTpStatus() == 1) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(topic.getTpTitle());
                topicMap.put(topic.getTpID(), node);

                // Thêm vào node cha dựa vào tpParent
                DefaultMutableTreeNode parent = topicMap.get(topic.getTpParent());
                if (parent != null) {
                    parent.add(node);
                } else {
                    // Nếu không tìm thấy parent, có thể là lỗi dữ liệu hoặc tpParent là 0
                    root.add(node); // Thêm vào root nếu không có parent
                }
            }
        }
        // Gán dữ liệu vào JTree
        tpTree.setModel(new DefaultTreeModel(root));
    }

    private void setSelectedTopicNode() {
        ArrayList<TopicsDTO> topics = new TopicsBUS().getTestTopic(test.getTestID());
        for (TopicsDTO topic : topics) {
            DefaultMutableTreeNode node = topicMap.get(topic.getTpID());
            if (node != null) {
                tpTree.addSelectionPath(new javax.swing.tree.TreePath(node.getPath()));
            }
        }

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

        jScrollPane1 = new javax.swing.JScrollPane();
        tpTree = new javax.swing.JTree();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        lblTgThi = new javax.swing.JTextField();
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
        lblSLCauHoi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sửa ma trận đề");
        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(61, 100));
        jScrollPane1.setViewportView(tpTree);

        jTextField14.setEditable(false);
        jTextField14.setText("Tên bài thi:");
        jTextField14.setFocusable(false);

        jTextField15.setEditable(false);
        jTextField15.setText("Chọn topic:");
        jTextField15.setFocusable(false);

        lblTgThi.setEditable(false);
        lblTgThi.setText("Thời gian thi:");
        lblTgThi.setFocusable(false);

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
        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });

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

        lblSLCauHoi.setText("Số lượng câu hỏi:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 87,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(190, 190, 190))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField15,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField14,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(jtfTitle, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                272, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane1,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(61, 61, 61))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblSLCauHoi,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 125,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(jsQuestionNum,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 272,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jTextField18,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(jtfEasyNum,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 267,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jTextField20,
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jTextField19,
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jTextField21,
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                106,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jTextField1,
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                106,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(30, 30, 30)
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jtfHardNum,
                                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        267,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jtfMediumNum,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        267,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jcbLimit,
                                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        267,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(jDateChooser1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                267,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblTgThi,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 94,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(jsTime,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 292,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(63, 63, 63)))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtfTitle, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223,
                                                Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jsTime, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(lblTgThi, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jsQuestionNum, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(lblSLCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, 49,
                                                        Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jtfEasyNum, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jtfMediumNum, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtfHardNum, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jcbLimit, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField19ActionPerformed

    private void jsQuestionNumStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_jsQuestionNumStateChanged
        lblSLCauHoi.setText("Số lượng câu hỏi: " + jsQuestionNum.getValue());
    }// GEN-LAST:event_jsQuestionNumStateChanged

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField21ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField21ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField18ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField17ActionPerformed

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
            test.setTestCode(this.test.getTestCode());
            test.setTestTitle(title);
            test.setTestTime(timeLimit);
            test.setNum_easy(easyQuestions);
            test.setNum_medium(mediumQuestions);
            test.setNum_diff(hardQuestions);
            test.setTestLimit(Integer.parseInt(attemptLimit));
            test.setTestDate(localDateTime);
            testBUS.updateTestTopics(this.test.getTestID(), topics.toArray(new Integer[0]));
            testBUS.update(test);
            JOptionPane.showMessageDialog(this, "Sửa bài thi thành công!", "Thành công",
                    JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Hành động thất bại, có lỗi xảy ra!\n" + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public ArrayList<Integer> getIDsOfSelectedTopics() {
        ArrayList<Integer> topicIDs = new ArrayList<>();
        for (int i = 0; i < tpTree.getSelectionCount(); i++) {
            TopicsBUS tpBUS = new TopicsBUS();
            topicIDs.add(tpBUS.getTopicIdByName(tpTree.getSelectionPaths()[i].getLastPathComponent().toString()));
        }
        return topicIDs;
    }

    private void jtfHardNumActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jtfHardNumActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jtfHardNumActionPerformed

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
            new EditTestDialog(new JFrame(), true, null).setVisible(true);
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
    private javax.swing.JLabel lblSLCauHoi;
    private javax.swing.JTextField lblTgThi;
    private javax.swing.JTree tpTree;
    // End of variables declaration//GEN-END:variables
}
