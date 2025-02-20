/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.CauHoi;

import com.example.qlthitracnghiem.BUS.TopicsBUS;
import com.example.qlthitracnghiem.DTO.TopicsDTO;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author truon
 */
public class ThemChuDeDialog extends javax.swing.JDialog {

    /**
     * Creates new form ThemChuDeDialog
     * @param parent
     */
    public ThemChuDeDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         this.setSize(400, 280); // Đặt kích thước hợp lý
    this.setLocationRelativeTo(null); // Hiển thị giữa màn hình

        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        title_txt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cdParent_cbx = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel2.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Thêm chủ đề");
        jLabel5.setMaximumSize(new java.awt.Dimension(160, 32));
        jLabel5.setPreferredSize(new java.awt.Dimension(160, 32));
        jPanel2.add(jLabel5);
        jLabel5.setBounds(120, 10, 160, 32);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tên chủ đề");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(30, 60, 120, 20);

        title_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                title_txtActionPerformed(evt);
            }
        });
        jPanel2.add(title_txt);
        title_txt.setBounds(30, 90, 330, 30);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel2.setText("Chủ đề bao hàm");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(240, 130, 110, 20);

        TopicsBUS topicsBUS = new TopicsBUS();
        ArrayList<TopicsDTO> topics = topicsBUS.getAll();

        // Đưa danh sách chủ đề vào JComboBox
        cdParent_cbx.removeAllItems();
        cdParent_cbx.addItem("Không có");
        for (TopicsDTO topic : topics) {
            if(topicsBUS.checkStatus(topic.getTpStatus())){
                cdParent_cbx.addItem(topic.getTpTitle()); // Chỉ hiển thị tên chủ đề
            }
        }
        cdParent_cbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdParent_cbxActionPerformed(evt);
            }
        });
        jPanel2.add(cdParent_cbx);
        cdParent_cbx.setBounds(72, 130, 150, 22);

        jButton1.setText("Xác nhận");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(150, 170, 100, 30);
        jButton1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14)); // Font chữ lớn hơn, in đậm
        jButton1.setBackground(new java.awt.Color(51, 153, 255)); // Màu nền xanh dương nhạt
        jButton1.setForeground(Color.WHITE); // Chữ màu trắng
        jButton1.setFocusPainted(false); // Bỏ viền khi nhấn
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Đổi con trỏ chuột khi di vào

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 400, 270);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void title_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_title_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_title_txtActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     String add_name = title_txt.getText().trim(); // Trim để tránh nhập khoảng trắng vô nghĩa
String name_parent = (cdParent_cbx.getSelectedItem() != null) ? cdParent_cbx.getSelectedItem().toString() : ""; // Kiểm tra null

TopicsBUS topicsBUS = new TopicsBUS();
int id_parent = (name_parent.isEmpty()) ? -1 : topicsBUS.getTopicIdByName(name_parent);

int id_topic_old = topicsBUS.getTopicIdByName(add_name);
TopicsDTO token = topicsBUS.getTopicByID(id_topic_old);

if (add_name.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên chủ đề cần thêm", "Thiếu thông tin!!", JOptionPane.INFORMATION_MESSAGE);
} 
else if (token != null && token.getTpStatus() == 1) {
    JOptionPane.showMessageDialog(null, "Chủ đề này đã tồn tại", "Thông tin sai lệch!!", JOptionPane.INFORMATION_MESSAGE);
} 
else if (token != null && token.getTpStatus() == 0) { // Khi chủ đề có nhưng bị vô hiệu hóa
    TopicsDTO token1 = new TopicsDTO(token.getTpID(), token.getTpTitle(), token.getTpParent(), 1);
    boolean updated = topicsBUS.update(token1);
    
    if (updated) {
        JOptionPane.showMessageDialog(null, "Chủ đề đã được kích hoạt lại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, "Không thể cập nhật chủ đề", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
} 
else {
    TopicsDTO topic = new TopicsDTO();
    topic.setTpTitle(add_name);
    topic.setTpStatus(1);
    topic.setTpParent((id_parent == -1) ? 0 : id_parent);

    // Hiển thị hộp thoại cảnh báo
    int confirm = JOptionPane.showConfirmDialog(
        null,
        "Bạn có chắc chắn muốn thêm chủ đề này?",
        "Xác nhận",
        JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

    if (confirm == JOptionPane.YES_OPTION) {
        // Nếu chọn "YES", tiến hành thêm
        boolean created = topicsBUS.create(topic);
        
        if (created) {
            JOptionPane.showMessageDialog(null, "Bạn đã thêm thành công", "Xác nhận", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Không thể thêm chủ đề", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    }//GEN-LAST:event_jButton1ActionPerformed

    private void cdParent_cbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdParent_cbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cdParent_cbxActionPerformed

    
      
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
            java.util.logging.Logger.getLogger(ThemChuDeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemChuDeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemChuDeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemChuDeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            ThemChuDeDialog dialog = new ThemChuDeDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cdParent_cbx;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField title_txt;
    // End of variables declaration//GEN-END:variables
}
