
package com.example.qlthitracnghiem.GUI.CauHoi;

import com.example.qlthitracnghiem.BUS.QuestionsBUS;
import com.example.qlthitracnghiem.BUS.TopicsBUS;
import com.example.qlthitracnghiem.DTO.TopicsDTO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;

/**
 *
 * @author truon
 */
public class ThemCauHoiDialog extends javax.swing.JDialog {


    public TopicsBUS topicsBUS = new TopicsBUS();
    public QuestionsBUS questionBUS = new QuestionsBUS();
    
    public ThemCauHoiDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         this.setSize(484, 777); // Đặt kích thước hợp lý
    this.setLocationRelativeTo(null); // Hiển thị giữa màn hình

        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        tp_cbx = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        level_cbx = new javax.swing.JComboBox<>();
        anh_field = new javax.swing.JTextField();
        file_btn = new javax.swing.JButton();
        panel_ans = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        anh_field2 = new javax.swing.JTextField();
        file_btn1 = new javax.swing.JButton();
        panel_ans1 = new javax.swing.JPanel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        anh_field3 = new javax.swing.JTextField();
        file_btn2 = new javax.swing.JButton();
        panel_ans2 = new javax.swing.JPanel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        anh_field4 = new javax.swing.JTextField();
        file_btn3 = new javax.swing.JButton();
        panel_ans3 = new javax.swing.JPanel();
        jCheckBox4 = new javax.swing.JCheckBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        anh_field5 = new javax.swing.JTextField();
        file_btn4 = new javax.swing.JButton();
        panel_ans4 = new javax.swing.JPanel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        anh_field6 = new javax.swing.JTextField();
        file_btn5 = new javax.swing.JButton();
        file_btn6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel2.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Đáp án");
        jLabel5.setMaximumSize(new java.awt.Dimension(160, 32));
        jLabel5.setPreferredSize(new java.awt.Dimension(160, 32));
        jPanel2.add(jLabel5);
        jLabel5.setBounds(100, 190, 160, 32);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Thêm câu hỏi");
        jLabel6.setMaximumSize(new java.awt.Dimension(160, 32));
        jLabel6.setPreferredSize(new java.awt.Dimension(160, 32));
        jPanel2.add(jLabel6);
        jLabel6.setBounds(120, 10, 160, 32);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 90, 260, 60);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel3.setText("Nội dung câu hỏi (*)");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(30, 60, 170, 20);

        tp_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ArrayList<TopicsDTO> sortedTopics = topicsBUS.getAllSortedByName();
        // Xóa dữ liệu cũ và thêm danh sách mới vào JComboBox
        tp_cbx.removeAllItems(); // Xóa các item mặc định

        for (TopicsDTO topic : sortedTopics) {
            tp_cbx.addItem(topic.getTpTitle()); // Thêm từng chủ đề vào combo box
        }
        jPanel2.add(tp_cbx);
        tp_cbx.setBounds(290, 80, 170, 22);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel1.setText("Chủ đề câu hỏi");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(290, 60, 100, 16);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel2.setText("Mức độ");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(290, 110, 110, 16);

        level_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "easy", "medium", "difficult"}));
        jPanel2.add(level_cbx);
        level_cbx.setBounds(290, 130, 110, 22);

        anh_field.setText("Ảnh câu hỏi ");
        anh_field.setEditable(false);
        anh_field.setOpaque(false); // Loại bỏ màu nền
        anh_field.setBorder(null); // Xóa viền
        jPanel2.add(anh_field);
        anh_field.setBounds(290, 160, 250, 22);

        file_btn.setText("Chọn file");
        jPanel2.add(file_btn);
        file_btn.setBounds(170, 160, 110, 23);
        file_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NativeFileChooser FileChooser = new NativeFileChooser();
                FileChooser.openFileChooser();
                anh_field.setText(FileChooser.getSelectedFilePath());
            }
        });
        // Tùy chỉnh button
        file_btn.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ đậm
        file_btn.setForeground(Color.WHITE); // Chữ màu trắng
        file_btn.setBackground(new Color(30, 144, 255)); // Màu xanh dương
        file_btn.setFocusPainted(false); // Bỏ viền focus
        file_btn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Viền trong suốt

        // Hiệu ứng hover: đổi màu khi di chuột vào
        file_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                file_btn.setBackground(new Color(0, 102, 204)); // Màu xanh đậm hơn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                file_btn.setBackground(new Color(30, 144, 255)); // Quay lại màu gốc
            }
        });

        panel_ans.setLayout(null);

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        panel_ans.add(jCheckBox1);
        jCheckBox1.setBounds(5, 43, 19, 19);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        panel_ans.add(jScrollPane2);
        jScrollPane2.setBounds(29, 5, 220, 86);

        anh_field2.setText("Ảnh đáp án");
        anh_field2.setEditable(false);
        anh_field2.setOpaque(false); // Loại bỏ màu nền
        anh_field2.setBorder(null); // Xóa viền
        anh_field2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anh_field2ActionPerformed(evt);
            }
        });
        panel_ans.add(anh_field2);
        anh_field2.setBounds(260, 60, 130, 22);

        file_btn1.setText("Chọn file");
        panel_ans.add(file_btn1);
        file_btn1.setBounds(260, 30, 100, 23);
        file_btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NativeFileChooser FileChooser = new NativeFileChooser();
                FileChooser.openFileChooser();
                anh_field2.setText(FileChooser.getSelectedFilePath());
            }
        });
        // Tùy chỉnh button
        file_btn1.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ đậm
        file_btn1.setForeground(Color.WHITE); // Chữ màu trắng
        file_btn1.setBackground(new Color(30, 144, 255)); // Màu xanh dương
        file_btn1.setFocusPainted(false); // Bỏ viền focus
        file_btn1.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Viền trong suốt

        // Hiệu ứng hover: đổi màu khi di chuột vào
        file_btn1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                file_btn1.setBackground(new Color(0, 102, 204)); // Màu xanh đậm hơn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                file_btn1.setBackground(new Color(30, 144, 255)); // Quay lại màu gốc
            }
        });

        jPanel2.add(panel_ans);
        panel_ans.setBounds(10, 230, 520, 100);

        panel_ans1.setLayout(null);

        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        panel_ans1.add(jCheckBox2);
        jCheckBox2.setBounds(5, 43, 19, 19);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        panel_ans1.add(jScrollPane3);
        jScrollPane3.setBounds(29, 5, 220, 86);

        anh_field3.setText("Ảnh đáp án");
        anh_field3.setEditable(false);
        anh_field3.setOpaque(false); // Loại bỏ màu nền
        anh_field3.setBorder(null); // Xóa viền
        anh_field3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anh_field3ActionPerformed(evt);
            }
        });
        panel_ans1.add(anh_field3);
        anh_field3.setBounds(260, 60, 130, 22);

        file_btn2.setText("Chọn file");
        panel_ans1.add(file_btn2);
        file_btn2.setBounds(260, 30, 100, 23);
        file_btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NativeFileChooser FileChooser = new NativeFileChooser();
                FileChooser.openFileChooser();
                anh_field3.setText(FileChooser.getSelectedFilePath());
            }
        });
        // Tùy chỉnh button
        file_btn2.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ đậm
        file_btn2.setForeground(Color.WHITE); // Chữ màu trắng
        file_btn2.setBackground(new Color(30, 144, 255)); // Màu xanh dương
        file_btn2.setFocusPainted(false); // Bỏ viền focus
        file_btn2.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Viền trong suốt

        // Hiệu ứng hover: đổi màu khi di chuột vào
        file_btn2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                file_btn2.setBackground(new Color(0, 102, 204)); // Màu xanh đậm hơn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                file_btn2.setBackground(new Color(30, 144, 255)); // Quay lại màu gốc
            }
        });

        jPanel2.add(panel_ans1);
        panel_ans1.setBounds(10, 430, 390, 100);

        panel_ans2.setLayout(null);

        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        panel_ans2.add(jCheckBox3);
        jCheckBox3.setBounds(5, 43, 19, 19);

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        panel_ans2.add(jScrollPane4);
        jScrollPane4.setBounds(29, 5, 220, 86);

        anh_field4.setText("Ảnh đáp án");
        anh_field4.setEditable(false);
        anh_field4.setOpaque(false); // Loại bỏ màu nền
        anh_field4.setBorder(null); // Xóa viền
        anh_field4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anh_field4ActionPerformed(evt);
            }
        });
        panel_ans2.add(anh_field4);
        anh_field4.setBounds(260, 60, 130, 22);

        file_btn3.setText("Chọn file");
        panel_ans2.add(file_btn3);
        file_btn3.setBounds(260, 30, 100, 23);
        file_btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NativeFileChooser FileChooser = new NativeFileChooser();
                FileChooser.openFileChooser();
                anh_field4.setText(FileChooser.getSelectedFilePath());
            }
        });
        // Tùy chỉnh button
        file_btn3.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ đậm
        file_btn3.setForeground(Color.WHITE); // Chữ màu trắng
        file_btn3.setBackground(new Color(30, 144, 255)); // Màu xanh dương
        file_btn3.setFocusPainted(false); // Bỏ viền focus
        file_btn3.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Viền trong suốt

        // Hiệu ứng hover: đổi màu khi di chuột vào
        file_btn3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                file_btn3.setBackground(new Color(0, 102, 204)); // Màu xanh đậm hơn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                file_btn3.setBackground(new Color(30, 144, 255)); // Quay lại màu gốc
            }
        });

        jPanel2.add(panel_ans2);
        panel_ans2.setBounds(10, 530, 390, 100);

        panel_ans3.setLayout(null);

        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });
        panel_ans3.add(jCheckBox4);
        jCheckBox4.setBounds(5, 43, 19, 19);

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane5.setViewportView(jTextArea5);

        panel_ans3.add(jScrollPane5);
        jScrollPane5.setBounds(29, 5, 220, 86);

        anh_field5.setText("Ảnh đáp án");
        anh_field5.setEditable(false);
        anh_field5.setOpaque(false); // Loại bỏ màu nền
        anh_field5.setBorder(null); // Xóa viền
        anh_field5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anh_field5ActionPerformed(evt);
            }
        });
        panel_ans3.add(anh_field5);
        anh_field5.setBounds(260, 60, 130, 22);

        file_btn4.setText("Chọn file");
        panel_ans3.add(file_btn4);
        file_btn4.setBounds(260, 30, 100, 23);
        file_btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NativeFileChooser FileChooser = new NativeFileChooser();
                FileChooser.openFileChooser();
                anh_field5.setText(FileChooser.getSelectedFilePath());
            }
        });
        // Tùy chỉnh button
        file_btn4.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ đậm
        file_btn4.setForeground(Color.WHITE); // Chữ màu trắng
        file_btn4.setBackground(new Color(30, 144, 255)); // Màu xanh dương
        file_btn4.setFocusPainted(false); // Bỏ viền focus
        file_btn4.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Viền trong suốt

        // Hiệu ứng hover: đổi màu khi di chuột vào
        file_btn4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                file_btn4.setBackground(new Color(0, 102, 204)); // Màu xanh đậm hơn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                file_btn4.setBackground(new Color(30, 144, 255)); // Quay lại màu gốc
            }
        });

        jPanel2.add(panel_ans3);
        panel_ans3.setBounds(10, 630, 390, 100);

        panel_ans4.setLayout(null);

        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });
        panel_ans4.add(jCheckBox5);
        jCheckBox5.setBounds(5, 43, 19, 19);

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jScrollPane6.setViewportView(jTextArea6);

        panel_ans4.add(jScrollPane6);
        jScrollPane6.setBounds(29, 5, 220, 86);

        anh_field6.setText("Ảnh đáp án");
        anh_field6.setEditable(false);
        anh_field6.setOpaque(false); // Loại bỏ màu nền
        anh_field6.setBorder(null); // Xóa viền
        anh_field6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anh_field6ActionPerformed(evt);
            }
        });
        panel_ans4.add(anh_field6);
        anh_field6.setBounds(260, 60, 130, 22);

        file_btn5.setText("Chọn file");
        panel_ans4.add(file_btn5);
        file_btn5.setBounds(260, 30, 100, 23);
        file_btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NativeFileChooser FileChooser = new NativeFileChooser();
                FileChooser.openFileChooser();
                anh_field6.setText(FileChooser.getSelectedFilePath());
            }
        });
        // Tùy chỉnh button
        file_btn5.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ đậm
        file_btn5.setForeground(Color.WHITE); // Chữ màu trắng
        file_btn5.setBackground(new Color(30, 144, 255)); // Màu xanh dương
        file_btn5.setFocusPainted(false); // Bỏ viền focus
        file_btn5.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Viền trong suốt

        // Hiệu ứng hover: đổi màu khi di chuột vào
        file_btn5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                file_btn5.setBackground(new Color(0, 102, 204)); // Màu xanh đậm hơn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                file_btn5.setBackground(new Color(30, 144, 255)); // Quay lại màu gốc
            }
        });

        jPanel2.add(panel_ans4);
        panel_ans4.setBounds(10, 330, 390, 100);

        file_btn6.setText("Xác nhận");
        jPanel2.add(file_btn6);
        file_btn6.setBounds(10, 160, 110, 23);
        file_btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // Tùy chỉnh button
        file_btn6.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ đậm
        file_btn6.setForeground(Color.WHITE); // Chữ màu trắng
        file_btn6.setBackground(new Color(0,255,0)); // Màu xanh dương
        file_btn6.setFocusPainted(false); // Bỏ viền focus
        file_btn6.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Viền trong suốt

        // Hiệu ứng hover: đổi màu khi di chuột vào
        file_btn6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                file_btn.setBackground(new Color(0, 102, 204)); // Màu xanh đậm hơn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                file_btn6.setBackground(new Color(0, 255, 0)); // Quay lại màu gốc
            }
        });

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 540, 730);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void anh_field2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anh_field2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anh_field2ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void anh_field3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anh_field3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anh_field3ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void anh_field4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anh_field4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anh_field4ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void anh_field5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anh_field5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anh_field5ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void anh_field6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anh_field6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anh_field6ActionPerformed

    
      
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThemCauHoiDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemCauHoiDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemCauHoiDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemCauHoiDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            ThemCauHoiDialog dialog = new ThemCauHoiDialog(new javax.swing.JFrame(), true);
            dialog.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
            dialog.setVisible(true); // Hiển thị dialog
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                    
                });

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anh_field;
    private javax.swing.JTextField anh_field2;
    private javax.swing.JTextField anh_field3;
    private javax.swing.JTextField anh_field4;
    private javax.swing.JTextField anh_field5;
    private javax.swing.JTextField anh_field6;
    private javax.swing.JButton file_btn;
    private javax.swing.JButton file_btn1;
    private javax.swing.JButton file_btn2;
    private javax.swing.JButton file_btn3;
    private javax.swing.JButton file_btn4;
    private javax.swing.JButton file_btn5;
    private javax.swing.JButton file_btn6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JComboBox<String> level_cbx;
    private javax.swing.JPanel panel_ans;
    private javax.swing.JPanel panel_ans1;
    private javax.swing.JPanel panel_ans2;
    private javax.swing.JPanel panel_ans3;
    private javax.swing.JPanel panel_ans4;
    private javax.swing.JComboBox<String> tp_cbx;
    // End of variables declaration//GEN-END:variables
}
