
package com.example.qlthitracnghiem.GUI.CauHoi;

import com.example.qlthitracnghiem.BUS.QuestionsBUS;
import com.example.qlthitracnghiem.BUS.TopicsBUS;
import com.example.qlthitracnghiem.DTO.QuestionsDTO;
import com.example.qlthitracnghiem.DTO.TopicsDTO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author truon
 */
public class QuestionPanel extends javax.swing.JPanel {

    public TopicsBUS topicsBUS = new TopicsBUS();
    public QuestionsBUS questionBUS = new QuestionsBUS();

    public QuestionPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        xoabtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTree2 = new javax.swing.JTree();
        btn_loc = new javax.swing.JButton();
        btnThemChuDe = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        xoabtn_question = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_question = new javax.swing.JTable();
        find_txt = new javax.swing.JTextField();
        find_cbx = new javax.swing.JComboBox<>();
        themCH_btn = new javax.swing.JButton();
        import_btn = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1040, 700));
        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setDividerSize(3);
        jSplitPane1.setEnabled(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        xoabtn.setText("Xóa");
        xoabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoabtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Chủ đề");

        jTree2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jScrollPane3.setViewportView(jTree2);
        loadTopics();

        btn_loc.setText("Lọc");
        btn_loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_locActionPerformed(evt);
            }
        });

        btnThemChuDe.setText("Thêm");
        btnThemChuDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChuDeActionPerformed(evt);
            }
        });

        Reset.setText("Tải lại");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });
        // Thêm style cho nút
        Reset.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14)); // Chữ đậm, cỡ 14
        Reset.setBackground(new java.awt.Color(255, 102, 102)); // Màu nền đỏ nhạt
        Reset.setForeground(Color.WHITE); // Chữ trắng
        Reset.setFocusPainted(false); // Bỏ viền khi nhấn
        Reset.setBorder(BorderFactory.createEtchedBorder()); // Viền nổi nhẹ
        Reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Đổi con trỏ chuột khi di vào

        // Thêm hiệu ứng hover
        Reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Reset.setBackground(new java.awt.Color(255, 51, 51)); // Đỏ đậm hơn khi hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                Reset.setBackground(new java.awt.Color(255, 102, 102)); // Quay lại màu cũ
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane3)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                .createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(btnThemChuDe,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 100,
                                                                Short.MAX_VALUE)
                                                        .addComponent(btn_loc, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(xoabtn, javax.swing.GroupLayout.DEFAULT_SIZE, 100,
                                                                Short.MAX_VALUE)
                                                        .addComponent(Reset, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(32, 32, 32))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(107, 107, 107)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(xoabtn)
                                        .addComponent(btnThemChuDe))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_loc)
                                        .addComponent(Reset))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                                .addContainerGap()));

        // Thay đổi màu nền và màu chữ cho nút bình thường
        xoabtn.setBackground(new java.awt.Color(255, 69, 0)); // Màu nền đỏ cam (RGB: 255, 69, 0)
        xoabtn.setForeground(new java.awt.Color(255, 255, 255)); // Màu chữ trắng

        // Thay đổi font chữ
        xoabtn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

        // Tạo hiệu ứng khi chuột di chuyển qua nút (hover)
        xoabtn.setRolloverEnabled(true); // Kích hoạt hiệu ứng khi di chuột qua nút

        // Khi hover, thay đổi màu nền của nút
        xoabtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xoabtn.setBackground(new java.awt.Color(255, 99, 71)); // Màu nền đỏ sáng khi hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                xoabtn.setBackground(new java.awt.Color(255, 69, 0)); // Trở lại màu đỏ cam khi không hover
            }
        });
        // Thay đổi màu nền và màu chữ cho nút bình thường
        btn_loc.setBackground(new java.awt.Color(128, 128, 128)); // Màu nền xanh dương
        btn_loc.setForeground(new java.awt.Color(255, 255, 255)); // Màu chữ trắng

        // Thay đổi font chữ
        btn_loc.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

        // Tạo hiệu ứng khi chuột di chuyển qua nút (hover)
        btn_loc.setRolloverEnabled(true); // Kích hoạt hiệu ứng khi di chuột qua nút

        // Thay đổi màu nền khi hover
        btn_loc.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_loc.setBackground(new java.awt.Color(255, 165, 0)); // Màu nền cam khi hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_loc.setBackground(new java.awt.Color(128, 128, 128)); // Trở lại màu xanh dương khi không hover
            }
        });
        // Thay đổi màu nền và màu chữ cho nút bình thường
        btnThemChuDe.setBackground(new java.awt.Color(0, 255, 255)); // Màu nền xanh dương
        btnThemChuDe.setForeground(new java.awt.Color(255, 255, 255)); // Màu chữ trắng

        // Thay đổi font chữ
        btnThemChuDe.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

        // Tạo hiệu ứng khi chuột di chuyển qua nút (hover)
        btnThemChuDe.setRolloverEnabled(true); // Kích hoạt hiệu ứng khi di chuột qua nút

        // Thay đổi màu nền khi hover
        btnThemChuDe.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThemChuDe.setBackground(new java.awt.Color(255, 165, 0)); // Màu nền cam khi hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThemChuDe.setBackground(new java.awt.Color(0, 255, 255)); // Trở lại màu xanh dương khi không hover
            }
        });

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Tên chủ đề");

        xoabtn_question.setText("Xóa");
        xoabtn_question.setToolTipText("Xóa khỏi chủ đề hoặc xóa hẳn");
        xoabtn_question.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoabtn_questionActionPerformed(evt);
            }
        });

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        display_questions(-1);

        table_question.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) { // Kiểm tra nếu là double-click
                    int selectedRow = table_question.getSelectedRow(); // Lấy hàng được chọn
                    if (selectedRow != -1) { // Kiểm tra có hàng nào được chọn không
                        String name = table_question.getValueAt(selectedRow, 2).toString();

                        QuestionDetailDialog dialog = new QuestionDetailDialog(null, true, name);
                        dialog.setLocationRelativeTo(null); // Place the dialog in the center of the screen
                        dialog.setVisible(true);
                    }
                }
            }
        });
        jScrollPane2.setViewportView(table_question);

        find_txt.setText("Tìm kiếm");
        find_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                find_txtActionPerformed(evt);
            }
        });

        find_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nội dung", "Mức độ", "Id" }));

        themCH_btn.setText("Thêm");
        themCH_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themCH_btnActionPerformed(evt);
            }
        });

        import_btn.setText("Nhập file");
        import_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NativeFileChooser_import FileChooser = new NativeFileChooser_import();
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout
                                                .createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane2,
                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 722,
                                                                Short.MAX_VALUE)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(themCH_btn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(xoabtn_question)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(find_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 191,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(find_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, 95,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(import_btn)))
                                .addContainerGap()));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(find_txt, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(find_cbx, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(xoabtn_question)
                                        .addComponent(themCH_btn)
                                        .addComponent(import_btn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                                .addContainerGap()));

        // Thay đổi màu nền và màu chữ cho nút bình thường
        xoabtn_question.setBackground(new java.awt.Color(255, 69, 0)); // Màu nền đỏ cam (RGB: 255, 69, 0)
        xoabtn_question.setForeground(new java.awt.Color(255, 255, 255)); // Màu chữ trắng

        // Thay đổi font chữ
        xoabtn_question.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

        // Tạo hiệu ứng khi chuột di chuyển qua nút (hover)
        xoabtn_question.setRolloverEnabled(true); // Kích hoạt hiệu ứng khi di chuột qua nút

        // Khi hover, thay đổi màu nền của nút
        xoabtn_question.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xoabtn_question.setBackground(new java.awt.Color(255, 99, 71)); // Màu nền đỏ sáng khi hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                xoabtn_question.setBackground(new java.awt.Color(255, 69, 0)); // Trở lại màu đỏ cam khi không hover
            }
        });
        // Thay đổi màu nền và màu chữ cho nút bình thường
        themCH_btn.setBackground(new java.awt.Color(0, 123, 255)); // Màu nền xanh dương
        themCH_btn.setForeground(new java.awt.Color(255, 255, 255)); // Màu chữ trắng

        // Thay đổi font chữ
        themCH_btn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

        // Tạo hiệu ứng khi chuột di chuyển qua nút (hover)
        themCH_btn.setRolloverEnabled(true); // Kích hoạt hiệu ứng khi di chuột qua nút

        // Thay đổi màu nền khi hover
        themCH_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                themCH_btn.setBackground(new java.awt.Color(255, 165, 0)); // Màu nền cam khi hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                themCH_btn.setBackground(new java.awt.Color(0, 123, 255)); // Trở lại màu xanh dương khi không hover
            }
        });
        // Thay đổi màu nền và màu chữ cho nút bình thường
        import_btn.setBackground(new java.awt.Color(123, 123, 255)); // Màu nền xanh dư
        import_btn.setForeground(new java.awt.Color(255, 255, 255)); // Màu chữ trắng

        // Thay đổi font chữ
        import_btn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

        // Tạo hiệu ứng khi chuột di chuyển qua nút (hover)
        import_btn.setRolloverEnabled(true); // Kích hoạt hiệu ứng khi di chuột qua nút

        // Thay đổi màu nền khi hover
        import_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                import_btn.setBackground(new java.awt.Color(255, 165, 0)); // Màu nền cam khi hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                import_btn.setBackground(new java.awt.Color(123, 123, 255)); // Trở lại màu xanh dương khi không hover
            }
        });

        jSplitPane1.setRightComponent(jPanel2);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_locActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_locActionPerformed
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree2.getLastSelectedPathComponent();
        if (selectedNode == null) {
            JOptionPane.showMessageDialog(null, "Hãy chọn một chủ đề", "Nhắc nhở", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String nodeContent = selectedNode.toString(); // Lấy nội dung của node

            if (nodeContent.equals("Tất cả")) {
                display_questions(-1);
            } else {
                int topicID = topicsBUS.getTopicIdByName(nodeContent);
                display_questions(topicID);
            }
        }

    }// GEN-LAST:event_btn_locActionPerformed

    private void btnThemChuDeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThemChuDeActionPerformed
        ThemChuDeDialog dialog = new ThemChuDeDialog(null, true);
        dialog.setLocationRelativeTo(null); // Place the dialog in the center of the screen
        dialog.setVisible(true);
    }// GEN-LAST:event_btnThemChuDeActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ResetActionPerformed
        jTree2.setModel(null);
        loadTopics();
        display_questions(-1);
    }// GEN-LAST:event_ResetActionPerformed

    private void themCH_btnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_themCH_btnActionPerformed
        ThemCauHoiDialog dialog = new ThemCauHoiDialog(null, true);
        dialog.setLocationRelativeTo(null); // Place the dialog in the center of the screen
        dialog.setVisible(true); // TODO add your handling code here:
    }// GEN-LAST:event_themCH_btnActionPerformed

    private void find_txtActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_find_txtActionPerformed

        String token = (String) find_cbx.getSelectedItem();
        String content = find_txt.getText();
        String key = "";
        switch (token) {
            case "Id" -> key = "id";
            case "Nội dung" -> key = "content";
            case "Mức độ" -> key = "level";
        }

        if (content.isEmpty()) {
            display_questions(-1);
        } else {
            List<QuestionsDTO> questions = questionBUS.find(content, key);
            display(questions);
        }
    }// GEN-LAST:event_find_txtActionPerformed

    private void xoabtn_questionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_xoabtn_questionActionPerformed
        ArrayList<ArrayList<String>> selectedRows = getCheckedRows();

        if (selectedRows.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn câu hỏi nào!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc chắn muốn xóa những câu hỏi đã chọn?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                for (ArrayList<String> row : selectedRows) {
                    String content = row.get(1); // Lấy nội dung câu hỏi

                    // Tìm câu hỏi theo nội dung
                    QuestionsDTO question_old = questionBUS.getQuestionsByContent(content);

                    if (question_old != null) { // Kiểm tra tránh null
                        // Cập nhật trạng thái thành 0
                        QuestionsDTO question_delete = new QuestionsDTO(
                                question_old.getqID(),
                                question_old.getqContent(),
                                question_old.getqPicture(),
                                question_old.getqTopicID(),
                                question_old.getqLevel(),
                                0 // Đánh dấu đã xóa
                        );

                        // Thực hiện update trong database
                        questionBUS.update(question_delete);
                    }
                }

                // Cập nhật lại danh sách câu hỏi trên giao diện
                display_questions(-1);
            }
        }
    }// GEN-LAST:event_xoabtn_questionActionPerformed

    private void xoabtnActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree2.getLastSelectedPathComponent();
        if (selectedNode != null) {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc chắn muốn xóa chủ đề này(toàn bộ câu hỏi liên quan đến chủ đề sẽ bị xóa)?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                String nodeContent = selectedNode.toString(); // Lấy nội dung của node
                int topicID = topicsBUS.getTopicIdByName(nodeContent);

                ArrayList<QuestionsDTO> question_list = questionBUS.getQuestionsByTopicID(topicID); // lấy toàn bộ câu
                                                                                                    // hỏi của topic đó

                for (QuestionsDTO question : question_list) {
                    QuestionsDTO question_update;
                    question_update = new QuestionsDTO(
                            question.getqID(),
                            question.getqContent(), question.getqPicture(),
                            question.getqTopicID(),
                            question.getqLevel(), 0);// cập nhật trạng thái là 0

                    questionBUS.update(question_update);
                }

                TopicsDTO topic = topicsBUS.getTopicByID(topicID);
                TopicsDTO topic_update = new TopicsDTO(topic.getTpID(), topic.getTpTitle(), topic.getTpParent(), 0);
                topicsBUS.update(topic_update);
                loadTopics();
                display_questions(-1);
                JOptionPane.showMessageDialog(null, "Bạn đã xóa thành công", "Xác nhận",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn một chủ đề", "Nhắc nhở", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    // hàm hiển thị theo topic
    public void display_questions(int qTopicID) {
        // Lấy model hiện tại của bảng (nếu có)
        DefaultTableModel tableModel = (DefaultTableModel) table_question.getModel();

        tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "", "Ảnh", "Nội dung", "Mức độ", "Chủ đề"
                }) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return (columnIndex == 0) ? Boolean.class : String.class; // Cột đầu là checkbox
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Chỉ chỉnh sửa checkbox
            }
        };

        // Xóa tất cả các dòng hiện có để tránh dữ liệu bị lặp
        tableModel.setRowCount(0);

        if (qTopicID == -1) {
            // Lấy danh sách câu hỏi từ database
            ArrayList<QuestionsDTO> questions = questionBUS.getAll();

            for (QuestionsDTO question : questions) {
                if (question.getqStatus() == 1) { // Chỉ lấy các câu hỏi có trạng thái hợp lệ
                    Object[] row = new Object[5];

                    row[0] = false; // Checkbox mặc định là chưa chọn
                    row[1] = question.getqPicture(); // Ảnh (hiện tại đang là đường dẫn)
                    row[2] = question.getqContent(); // Nội dung câu hỏi

                    // Xác định mức độ
                    switch (question.getqLevel()) {
                        case 1:
                            row[3] = "easy";
                            break;
                        case 2:
                            row[3] = "medium";
                            break;
                        default:
                            row[3] = "difficult";
                            break;
                    }

                    // Lấy tên chủ đề từ topicsBUS
                    String topicName = topicsBUS.getTopicByID(question.getqTopicID()).getTpTitle();
                    row[4] = topicName;

                    // Thêm hàng vào bảng
                    tableModel.addRow(row);
                }
            }
        } else {
            ArrayList<QuestionsDTO> QuestionList = questionBUS.getQuestionsByTopicID(qTopicID);
            for (QuestionsDTO question : QuestionList) {
                if (question.getqStatus() == 1) { // Chỉ lấy các câu hỏi có trạng thái hợp lệ
                    Object[] row = new Object[5];

                    row[0] = false; // Checkbox mặc định là chưa chọn
                    row[1] = question.getqPicture(); // Ảnh (hiện tại đang là đường dẫn)
                    row[2] = question.getqContent(); // Nội dung câu hỏi

                    // Xác định mức độ
                    switch (question.getqLevel()) {
                        case 1:
                            row[3] = "easy";
                            break;
                        case 2:
                            row[3] = "medium";
                            break;
                        default:
                            row[3] = "difficult";
                            break;
                    }

                    // Lấy tên chủ đề từ topicsBUS
                    String topicName = topicsBUS.getTopicByID(question.getqTopicID()).getTpTitle();
                    row[4] = topicName;

                    // Thêm hàng vào bảng
                    tableModel.addRow(row);
                }
            }
        }

        // Gán lại model (nếu cần)
        table_question.setModel(tableModel);

        // 1️⃣ Căn giữa tiêu đề cột
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table_question.getTableHeader()
                .getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // 2️⃣ Căn giữa nội dung của cột cuối cùng ("Mức độ")
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Xác định cột cuối cùng (cột "Mức độ")
        int lastColumnIndex = table_question.getColumnCount() - 1;
        table_question.getColumnModel().getColumn(lastColumnIndex).setCellRenderer(centerRenderer);

        // Căn giữa nội dung của cột "Mức độ"
        table_question.getColumnModel().getColumn(lastColumnIndex - 1).setCellRenderer(centerRenderer);

        // Thu hẹp độ rộng của cột "Chủ đề"
        table_question.getColumnModel().getColumn(lastColumnIndex).setPreferredWidth(120); // Độ rộng mong muốn
        table_question.getColumnModel().getColumn(lastColumnIndex).setMaxWidth(200); // Độ rộng tối đa
        table_question.getColumnModel().getColumn(lastColumnIndex).setMinWidth(60); // Độ rộng tối thiểu

        // Thu hẹp độ rộng của cột "Mức độ"
        table_question.getColumnModel().getColumn(lastColumnIndex - 1).setPreferredWidth(80); // Độ rộng mong muốn
        table_question.getColumnModel().getColumn(lastColumnIndex - 1).setMaxWidth(100); // Độ rộng tối đa
        table_question.getColumnModel().getColumn(lastColumnIndex - 1).setMinWidth(60); // Độ rộng tối thiểu

        // 5️⃣ Thu hẹp độ rộng của cột "Ảnh"
        table_question.getColumnModel().getColumn(1).setPreferredWidth(150); // Độ rộng mong muốn cho cột "Ảnh"
        table_question.getColumnModel().getColumn(1).setMaxWidth(300); // Độ rộng tối đa cho cột "Ảnh"
        table_question.getColumnModel().getColumn(1).setMinWidth(50); // Độ rộng tối thiểu cho cột "Ảnh"

        // 4️⃣ Thu hẹp độ rộng của cột checkbox
        table_question.getColumnModel().getColumn(0).setPreferredWidth(40); // Độ rộng mong muốn cho cột checkbox
        table_question.getColumnModel().getColumn(0).setMaxWidth(50); // Độ rộng tối đa cho cột checkbox
        table_question.getColumnModel().getColumn(0).setMinWidth(30);
    }

    // hàm hiển thị theo danh sách có sẵn
    public void display(List<QuestionsDTO> list) {
        // Lấy model hiện tại của bảng (nếu có)
        DefaultTableModel tableModel = (DefaultTableModel) table_question.getModel();

        tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "", "Ảnh", "Nội dung", "Mức độ", "Chủ đề"
                }) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return (columnIndex == 0) ? Boolean.class : String.class; // Cột đầu là checkbox
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Chỉ chỉnh sửa checkbox
            }
        };

        // Xóa tất cả các dòng hiện có để tránh dữ liệu bị lặp
        tableModel.setRowCount(0);

        for (QuestionsDTO question : list) {
            if (question.getqStatus() == 1) { // Chỉ lấy các câu hỏi có trạng thái hợp lệ
                Object[] row = new Object[5];

                row[0] = false; // Checkbox mặc định là chưa chọn
                row[1] = question.getqPicture(); // Ảnh (hiện tại đang là đường dẫn)
                row[2] = question.getqContent(); // Nội dung câu hỏi

                // Xác định mức độ
                switch (question.getqLevel()) {
                    case 1:
                        row[3] = "easy";
                        break;
                    case 2:
                        row[3] = "medium";
                        break;
                    default:
                        row[3] = "difficult";
                        break;
                }

                // Lấy tên chủ đề từ topicsBUS
                String topicName = topicsBUS.getTopicByID(question.getqTopicID()).getTpTitle();
                row[4] = topicName;

                // Thêm hàng vào bảng
                tableModel.addRow(row);
            }
        }
        // Gán lại model (nếu cần)
        table_question.setModel(tableModel);

        // 1️⃣ Căn giữa tiêu đề cột
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table_question.getTableHeader()
                .getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // 2️⃣ Căn giữa nội dung của cột cuối cùng ("Mức độ")
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Xác định cột cuối cùng (cột "Mức độ")
        int lastColumnIndex = table_question.getColumnCount() - 1;
        table_question.getColumnModel().getColumn(lastColumnIndex).setCellRenderer(centerRenderer);

        // Căn giữa nội dung của cột "Mức độ"
        table_question.getColumnModel().getColumn(lastColumnIndex - 1).setCellRenderer(centerRenderer);

        // Thu hẹp độ rộng của cột "Chủ đề"
        table_question.getColumnModel().getColumn(lastColumnIndex).setPreferredWidth(120); // Độ rộng mong muốn
        table_question.getColumnModel().getColumn(lastColumnIndex).setMaxWidth(200); // Độ rộng tối đa
        table_question.getColumnModel().getColumn(lastColumnIndex).setMinWidth(60); // Độ rộng tối thiểu

        // Thu hẹp độ rộng của cột "Mức độ"
        table_question.getColumnModel().getColumn(lastColumnIndex - 1).setPreferredWidth(80); // Độ rộng mong muốn
        table_question.getColumnModel().getColumn(lastColumnIndex - 1).setMaxWidth(100); // Độ rộng tối đa
        table_question.getColumnModel().getColumn(lastColumnIndex - 1).setMinWidth(60); // Độ rộng tối thiểu

        // 5️⃣ Thu hẹp độ rộng của cột "Ảnh"
        table_question.getColumnModel().getColumn(1).setPreferredWidth(150); // Độ rộng mong muốn cho cột "Ảnh"
        table_question.getColumnModel().getColumn(1).setMaxWidth(300); // Độ rộng tối đa cho cột "Ảnh"
        table_question.getColumnModel().getColumn(1).setMinWidth(50); // Độ rộng tối thiểu cho cột "Ảnh"

        // 4️⃣ Thu hẹp độ rộng của cột checkbox
        table_question.getColumnModel().getColumn(0).setPreferredWidth(40); // Độ rộng mong muốn cho cột checkbox
        table_question.getColumnModel().getColumn(0).setMaxWidth(50); // Độ rộng tối đa cho cột checkbox
        table_question.getColumnModel().getColumn(0).setMinWidth(30);
    }

    public void loadTopics() {
        ArrayList<TopicsDTO> topics = topicsBUS.getAll();
        jTree2.setModel(null); // Xóa toàn bộ cây
        // Tạo node gốc
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tất cả");
        // Sử dụng HashMap để lưu node theo ID
        Map<Integer, DefaultMutableTreeNode> nodeMap = new HashMap<>();
        nodeMap.put(0, root); // Gốc có tpParent = 0
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
                    // Nếu không tìm thấy parent, có thể là lỗi dữ liệu hoặc tpParent là 0
                    root.add(node); // Thêm vào root nếu không có parent
                }
            }
        }
        // Gán dữ liệu vào JTree
        jTree2.setModel(new DefaultTreeModel(root));
    }

    // lấy dữ liệu của những hàng được tick
    public ArrayList<ArrayList<String>> getCheckedRows() {
        DefaultTableModel tableModel = (DefaultTableModel) table_question.getModel();
        ArrayList<ArrayList<String>> selectedRows = new ArrayList<>(); // Lưu danh sách hàng được chọn

        // Duyệt qua tất cả các hàng trong bảng
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            Boolean isChecked = (Boolean) tableModel.getValueAt(row, 0); // Lấy trạng thái checkbox (cột 0)
            if (isChecked != null && isChecked) { // Nếu checkbox được tick
                ArrayList<String> rowData = new ArrayList<>(); // Tạo danh sách con cho từng hàng

                // Thêm dữ liệu của hàng vào danh sách
                rowData.add((String) tableModel.getValueAt(row, 1)); // Ảnh
                rowData.add((String) tableModel.getValueAt(row, 2)); // Nội dung câu hỏi
                rowData.add((String) tableModel.getValueAt(row, 3)); // Mức độ
                rowData.add((String) tableModel.getValueAt(row, 4)); // Chủ đề

                selectedRows.add(rowData); // Thêm hàng vào danh sách chính
            }
        }

        return selectedRows; // Trả về danh sách các hàng đã chọn
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Reset;
    private javax.swing.JButton btnThemChuDe;
    private javax.swing.JButton btn_loc;
    private javax.swing.JComboBox<String> find_cbx;
    private javax.swing.JTextField find_txt;
    private javax.swing.JButton import_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTree jTree2;
    public javax.swing.JTable table_question;
    private javax.swing.JButton themCH_btn;
    private javax.swing.JButton xoabtn;
    private javax.swing.JButton xoabtn_question;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new QuestionPanel());
        frame.setSize(1040, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
