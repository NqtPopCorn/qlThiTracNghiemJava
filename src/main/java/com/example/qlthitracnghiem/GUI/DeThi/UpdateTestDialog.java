/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.DeThi;

import com.example.qlthitracnghiem.BUS.ExamBUS;
import com.example.qlthitracnghiem.BUS.TestBUS;
import com.example.qlthitracnghiem.BUS.TopicsBUS;
import com.example.qlthitracnghiem.DAO.TestDAO;
import com.example.qlthitracnghiem.DTO.TestDTO;
import com.example.qlthitracnghiem.DTO.TopicsDTO;
import com.example.qlthitracnghiem.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.tree.TreePath;

public class UpdateTestDialog extends javax.swing.JDialog {
    private TopicsBUS topicsBUS = new TopicsBUS();
    private TestBUS testBUS = new TestBUS();
    private DefaultComboBoxModel<String> comboBoxModel;
    private Map<Integer, int[]> topicStructures = new HashMap<>();
    private Map<String, Integer> topicNameToIdMap = new HashMap<>();
    private String testCode = "TEST8918"; // Default test code

    public UpdateTestDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        comboBoxModel = new DefaultComboBoxModel<>();
        cbAttachedTopic.setModel(comboBoxModel);
        setupListeners();
        setLocationRelativeTo(null);
        loadTopics();
        loadTestData();
    }

    public UpdateTestDialog(java.awt.Frame parent, boolean modal, String testCode) {
        super(parent, modal);
        this.testCode = testCode;
        initComponents();
        comboBoxModel = new DefaultComboBoxModel<>();
        cbAttachedTopic.setModel(comboBoxModel);
        setupListeners();
        setLocationRelativeTo(null);
        loadTopics();
        loadTestData();
    }

    private void setupListeners() {
        btnAttachTopic.addActionListener(evt -> btnAttachTopicActionPerformed(evt));
        btnDetachTopic.addActionListener(evt -> btnDetachTopicActionPerformed(evt));
        cbAttachedTopic.addActionListener(evt -> cbAttachedTopicActionPerformed(evt));
        btnConfirm.addActionListener(evt -> btnConfirmActionPerformed(evt));
        // Slider change listeners to update topic structure
        sliderNumEasy.addChangeListener(e -> updateTopicStructure());
        sliderNumMedium.addChangeListener(e -> updateTopicStructure());
        sliderNumDiff.addChangeListener(e -> updateTopicStructure());
        sliderTestTime.addChangeListener(e -> updateTestLabels());
        sliderTestLimit.addChangeListener(e -> updateTestLabels());
    }

    private void updateTestLabels() {
        lblTestTime.setText("Thời gian làm bài: " + sliderTestTime.getValue() + " phút");
        lblTestLimit.setText("Số lần làm bài: " + sliderTestLimit.getValue() + " lần");
    }

    private void loadTestData() {
        try {
            TestDAO testDAO = new TestDAO();
            TestDTO test = testDAO.getTestByCode(testCode);
            if (test != null) {
                txtTestTtitle.setText(test.getTestTitle());
                sliderTestTime.setValue(test.getTestTime());
                sliderTestLimit.setValue(test.getTestLimit());
                dateChooserTestDate.setDate(Date
                        .from(test.getTestDate().atZone(ZoneId.systemDefault()).toInstant()));

                // Load existing structure
                topicStructures = testDAO.getTestStructure(testCode);
                for (Map.Entry<Integer, int[]> entry : topicStructures.entrySet()) {
                    String topicName = topicsBUS.getTopicNameById(entry.getKey());
                    if (topicName != null) {
                        topicNameToIdMap.put(topicName, entry.getKey());
                        comboBoxModel.addElement(topicName);
                    }
                }
                if (comboBoxModel.getSize() > 0) {
                    cbAttachedTopic.setSelectedIndex(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {
        String title = txtTestTtitle.getText().trim();
        int time = sliderTestTime.getValue();
        int limit = sliderTestLimit.getValue();
        Date selectedDate = dateChooserTestDate.getDate();

        // Validation
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tiêu đề không được để trống!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (time <= 0) {
            JOptionPane.showMessageDialog(this, "Thời gian làm bài phải lớn hơn 0!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (limit <= 0) {
            JOptionPane.showMessageDialog(this, "Giới hạn lượt làm phải lớn hơn 0!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày thi!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDateTime testDateTime = selectedDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        if (testDateTime.isBefore(LocalDateTime.now())) {
            JOptionPane.showMessageDialog(this, "Ngày thi phải sau ngày hiện tại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (topicStructures.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một chủ đề!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            TestDTO test = new TestDTO();
            test.setTestCode(testCode);
            test.setTestTitle(title);
            test.setTestTime(time);
            test.setTestLimit(limit);
            test.setTestDate(testDateTime);
            test.setTestStatus(1); // Assuming active status
            for (Map.Entry<Integer, int[]> entry : topicStructures.entrySet()) {
                System.out.println("Topic ID(key): " + entry.getKey());
                String topicName = topicsBUS.getTopicNameById(entry.getKey());
                System.out.println("Topic name: " + topicName);
                int[] structure = entry.getValue();
                for (int i = 0; i < structure.length; i++) {
                    System.out.println("Structure " + i + ": " + structure[i]);
                }
            }
            testBUS.update(test, topicStructures);
            JOptionPane.showMessageDialog(this, "Cập nhật đề thi thành công!", "Thành công",
                    JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void disableAll() {
        txtTestTtitle.setEnabled(false);
        sliderTestTime.setEnabled(false);
        sliderTestLimit.setEnabled(false);
        dateChooserTestDate.setEnabled(false);
        treeTestTopic.setEnabled(false);
        btnAttachTopic.setEnabled(false);
        btnDetachTopic.setEnabled(false);
        sliderNumEasy.setEnabled(false);
        sliderNumMedium.setEnabled(false);
        sliderNumDiff.setEnabled(false);
        btnConfirm.setEnabled(false);
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        lblTestTime = new javax.swing.JLabel();
        lblTestLimit = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTestTtitle = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        treeTestTopic = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        sliderNumEasy = new javax.swing.JSlider();
        sliderNumMedium = new javax.swing.JSlider();
        sliderNumDiff = new javax.swing.JSlider();
        lblNumEasy = new javax.swing.JLabel();
        lblNumMedium = new javax.swing.JLabel();
        lblNumDiff = new javax.swing.JLabel();
        cbAttachedTopic = new javax.swing.JComboBox<>();
        btnAttachTopic = new javax.swing.JButton();
        btnDetachTopic = new javax.swing.JButton();
        sliderTestTime = new javax.swing.JSlider();
        sliderTestLimit = new javax.swing.JSlider();
        dateChooserTestDate = new com.toedter.calendar.JDateChooser();
        btnConfirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Test");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(0, 0));

        jLabel2.setText("testTitle");

        lblTestTime.setText("testTime (phút):");

        lblTestLimit.setText("testLimit (lần):");

        jLabel5.setText("testDate");

        jLabel6.setText("testTopic");

        jScrollPane1.setViewportView(treeTestTopic);

        sliderNumEasy.setMajorTickSpacing(10);
        sliderNumEasy.setMaximum(50);
        sliderNumEasy.setMinorTickSpacing(5);
        sliderNumEasy.setPaintLabels(true);
        sliderNumEasy.setPaintTicks(true);

        sliderNumMedium.setMajorTickSpacing(10);
        sliderNumMedium.setMaximum(50);
        sliderNumMedium.setMinorTickSpacing(5);
        sliderNumMedium.setPaintLabels(true);
        sliderNumMedium.setPaintTicks(true);

        sliderNumDiff.setMajorTickSpacing(10);
        sliderNumDiff.setMaximum(50);
        sliderNumDiff.setMinorTickSpacing(5);
        sliderNumDiff.setPaintLabels(true);
        sliderNumDiff.setPaintTicks(true);

        lblNumEasy.setText("Câu dễ: ");

        lblNumMedium.setText("Câu trung bình:");

        lblNumDiff.setText("Câu khó:");

        cbAttachedTopic.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(lblNumEasy, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblNumMedium,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 125,
                                                                Short.MAX_VALUE)
                                                        .addComponent(lblNumDiff, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(sliderNumMedium,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 188,
                                                                Short.MAX_VALUE)
                                                        .addComponent(sliderNumEasy,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 0,
                                                                Short.MAX_VALUE)
                                                        .addComponent(sliderNumDiff,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 0,
                                                                Short.MAX_VALUE)))
                                        .addComponent(cbAttachedTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 219,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cbAttachedTopic, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sliderNumEasy, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(lblNumEasy, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sliderNumMedium, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(lblNumMedium, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(sliderNumDiff, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblNumDiff, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()));

        btnAttachTopic.setText("Thêm");

        btnDetachTopic.setText("Xóa");

        sliderTestTime.setMajorTickSpacing(15);
        sliderTestTime.setMaximum(180);
        sliderTestTime.setMinimum(10);
        sliderTestTime.setMinorTickSpacing(5);
        sliderTestTime.setPaintLabels(true);
        sliderTestTime.setPaintTicks(true);
        sliderTestTime.setValue(15);

        sliderTestLimit.setMajorTickSpacing(1);
        sliderTestLimit.setMaximum(3);
        sliderTestLimit.setMinimum(1);
        sliderTestLimit.setPaintLabels(true);
        sliderTestLimit.setValue(1);

        btnConfirm.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnConfirm.setText("Xác nhận");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        false)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel2,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        77,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(txtTestTtitle))
                                                                        .addGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                layout.createSequentialGroup()
                                                                                        .addComponent(jLabel6,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                67,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGap(18, 18, 18)
                                                                                        .addComponent(jScrollPane1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                218,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(btnAttachTopic,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(btnDetachTopic,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(85, 85, 85)
                                                                .addComponent(jPanel1,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(lblTestLimit,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblTestTime, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(97, 97, 97)
                                                .addComponent(sliderTestTime, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(20, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                layout.createSequentialGroup()
                                                        .addComponent(btnConfirm)
                                                        .addGap(174, 174, 174))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(dateChooserTestDate,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 216,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addContainerGap())
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                        layout.createSequentialGroup()
                                                                .addComponent(sliderTestLimit,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 255,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(91, 91, 91))))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtTestTtitle, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnAttachTopic)
                                                .addGap(17, 17, 17)
                                                .addComponent(btnDetachTopic)
                                                .addGap(32, 32, 32))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(lblTestTime, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sliderTestTime, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTestLimit, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sliderTestLimit, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dateChooserTestDate, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(btnConfirm)
                                .addGap(20, 20, 20)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAttachTopicActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAttachTopicActionPerformed
        TreePath[] selectedPaths = treeTestTopic.getSelectionPaths();
        if (selectedPaths == null || selectedPaths.length == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một chủ đề từ cây!",
                    "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for (TreePath path : selectedPaths) {
            String topicName = path.getLastPathComponent().toString();
            int topicId = topicsBUS.getTopicIdByName(topicName);

            if (!topicNameToIdMap.containsKey(topicName)) {
                comboBoxModel.addElement(topicName);
                topicNameToIdMap.put(topicName, topicId);
                topicStructures.put(topicId, new int[] { 0, 0, 0 }); // Initialize with zeros
            }
        }

        // Select the last added topic
        if (comboBoxModel.getSize() > 0) {
            cbAttachedTopic.setSelectedIndex(comboBoxModel.getSize() - 1);
        }
    }// GEN-LAST:event_btnAttachTopicActionPerformed

    private void btnDetachTopicActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDetachTopicActionPerformed
        int selectedIndex = cbAttachedTopic.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một chủ đề để xóa!",
                    "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String topicName = (String) comboBoxModel.getElementAt(selectedIndex);
        Integer topicId = topicNameToIdMap.get(topicName);

        // comboBoxModel.removeElementAt(selectedIndex);
        // topicNameToIdMap.remove(topicName);
        cbAttachedTopic.removeItemAt(selectedIndex);
        topicStructures.remove(topicId);

        // Reset sliders if no topics remain
        if (comboBoxModel.getSize() == 0) {
            resetSliders();
        }
    }// GEN-LAST:event_btnDetachTopicActionPerformed

    private void cbAttachedTopicActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbAttachedTopicActionPerformed
        System.out.println("cbAttachedTopicActionPerformed");
        String selectedTopic = (String) cbAttachedTopic.getSelectedItem();
        if (selectedTopic != null) {
            Integer topicId = topicNameToIdMap.get(selectedTopic);
            int[] structure = topicStructures.getOrDefault(topicId, new int[] { 0, 0, 0 });

            // Update sliders with stored values
            sliderNumEasy.setValue(structure[0]);
            sliderNumMedium.setValue(structure[1]);
            sliderNumDiff.setValue(structure[2]);
        }
    }// GEN-LAST:event_cbAttachedTopicActionPerformed

    public void loadTopics() {
        ArrayList<TopicsDTO> topics = topicsBUS.getAll();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tất cả");
        Map<Integer, DefaultMutableTreeNode> nodeMap = new HashMap<>();
        nodeMap.put(0, root);

        for (TopicsDTO topic : topics) {
            if (topic.getTpStatus() == 1) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(topic.getTpTitle());
                nodeMap.put(topic.getTpID(), node);

                DefaultMutableTreeNode parent = nodeMap.get(topic.getTpParent());
                if (parent != null) {
                    parent.add(node);
                } else {
                    root.add(node);
                }
            }
        }
        treeTestTopic.setModel(new DefaultTreeModel(root));
    }

    private void updateTopicStructure() {
        String selectedTopic = (String) cbAttachedTopic.getSelectedItem();
        if (selectedTopic != null) {
            Integer topicId = topicNameToIdMap.get(selectedTopic);
            int[] structure = new int[] {
                    sliderNumEasy.getValue(),
                    sliderNumMedium.getValue(),
                    sliderNumDiff.getValue()
            };
            topicStructures.put(topicId, structure);
        }
        // show to labels
        lblNumEasy.setText("Câu dễ: " + sliderNumEasy.getValue());
        lblNumMedium.setText("Câu trung bình: " + sliderNumMedium.getValue());
        lblNumDiff.setText("Câu khó: " + sliderNumDiff.getValue());
    }

    private void resetSliders() {
        sliderNumEasy.setValue(0);
        sliderNumMedium.setValue(0);
        sliderNumDiff.setValue(0);
    }

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
            java.util.logging.Logger.getLogger(UpdateTestDialog.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateTestDialog.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateTestDialog.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateTestDialog.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateTestDialog dialog = new UpdateTestDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAttachTopic;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDetachTopic;
    private javax.swing.JComboBox<String> cbAttachedTopic;
    private com.toedter.calendar.JDateChooser dateChooserTestDate;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNumDiff;
    private javax.swing.JLabel lblNumEasy;
    private javax.swing.JLabel lblNumMedium;
    private javax.swing.JLabel lblTestLimit;
    private javax.swing.JLabel lblTestTime;
    private javax.swing.JSlider sliderNumDiff;
    private javax.swing.JSlider sliderNumEasy;
    private javax.swing.JSlider sliderNumMedium;
    private javax.swing.JSlider sliderTestLimit;
    private javax.swing.JSlider sliderTestTime;
    private javax.swing.JTree treeTestTopic;
    private javax.swing.JTextField txtTestTtitle;
    // End of variables declaration//GEN-END:variables
}
