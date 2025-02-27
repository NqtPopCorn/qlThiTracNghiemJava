/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.Exam;

import com.example.qlthitracnghiem.BUS.ExamBUS;
import com.example.qlthitracnghiem.BUS.ResultBUS;
import com.example.qlthitracnghiem.BUS.TestBUS;
import com.example.qlthitracnghiem.DTO.AnswersDTO;
import com.example.qlthitracnghiem.DTO.ExamDTO;
import com.example.qlthitracnghiem.DTO.ResultDTO;
import com.example.qlthitracnghiem.DTO.TestDTO;
import com.example.qlthitracnghiem.GUI.Component.RoundedButton;
import com.example.qlthitracnghiem.GUI.DashboardFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.json.JSONArray;

/**
 *
 * @author truon
 */
public class DoExamJPanel extends javax.swing.JPanel {

    /**
     * Creates new form DoExamJPanel
     */
    private DashboardFrame dbFrame;
    private CardLayout cardLayout;
    private CountdownTimer countdownPN;

    private ExamBUS exBUS;
    private TestBUS tsBUS;
    private ResultBUS rsBUS = new ResultBUS();

    private ExamDTO exDTO;
    private TestDTO tsDTO;

    private List<Integer> quesList;
    ArrayList<QuestionPN> quesPnList;

    private boolean isTakingTest = false;

    private int userId = 2;

    public DoExamJPanel(DashboardFrame dbFrame) {
        this.dbFrame = dbFrame;

        initComponents();
        cardLayout = new CardLayout();
        currentQuestPN.setLayout(cardLayout);
        quesPnList = new ArrayList<>();
    }

    public void setExDTO(ExamDTO exDTO) {
        this.exDTO = exDTO;
    }

    public void setTsDTO(TestDTO tsDTO) {
        this.tsDTO = tsDTO;
    }

    public boolean getTakingTestStatus() {
        return isTakingTest;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        headerPanel = new javax.swing.JPanel();
        pnlBaiThiInfo = new javax.swing.JPanel();
        tsNameLabel = new javax.swing.JLabel();
        tsSubjectLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        timePN = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        submitBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        questListPN = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        currentQuestPN = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1206, 760));
        setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(new java.awt.Color(51, 255, 51));
        headerPanel.setPreferredSize(new java.awt.Dimension(1040, 140));
        headerPanel.setLayout(new java.awt.GridLayout(1, 0));

        pnlBaiThiInfo.setLayout(new java.awt.GridLayout(2, 0));

        tsNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tsNameLabel.setText("Ten bai thi: Kiem tra giua ky");
        pnlBaiThiInfo.add(tsNameLabel);

        tsSubjectLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tsSubjectLabel.setText("Chu de: Toan cao cap");
        pnlBaiThiInfo.add(tsSubjectLabel);

        headerPanel.add(pnlBaiThiInfo);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        timePN.setBackground(new java.awt.Color(204, 255, 255));
        timePN.setForeground(new java.awt.Color(0, 0, 0));
        timePN.setPreferredSize(new java.awt.Dimension(80, 80));
        timePN.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(timePN, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(timePN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        submitBtn.setText("Nộp bài");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);

        headerPanel.add(jPanel1);

        add(headerPanel, java.awt.BorderLayout.PAGE_START);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setAlignmentX(1.0F);
        jScrollPane2.setHorizontalScrollBar(null);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(204, 565));

        questListPN.setBackground(new java.awt.Color(102, 255, 204));
        questListPN.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 1, 6));
        questListPN.setPreferredSize(new java.awt.Dimension(200, 563));
        questListPN.setRequestFocusEnabled(false);
        jScrollPane2.setViewportView(questListPN);

        add(jScrollPane2, java.awt.BorderLayout.LINE_END);

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(804, 100));

        currentQuestPN.setPreferredSize(new java.awt.Dimension(800, 5000));
        currentQuestPN.setLayout(new java.awt.CardLayout());
        jScrollPane1.setViewportView(currentQuestPN);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        // TODO add your handling code here:
        submitTest();
    }//GEN-LAST:event_submitBtnActionPerformed

    // use to start the timer count down when pressed
    public void startTest() {
        isTakingTest = true;
        dbFrame.disableAllNavButtons();
        Runnable action = () -> {
            submitTest();
        };
        countdownPN = new CountdownTimer(tsDTO.getTestTime(), action);
        timePN.add(countdownPN, BorderLayout.CENTER);

        tsNameLabel.setText("Bài thi: " + tsDTO.getTestTitle());
        tsSubjectLabel.setText("Mã bài thi: " + tsDTO.getTestCode());
        countdownPN.startTimer();
        renderQuestList();
    }

    private void renderQuestList() {

        quesList = (ArrayList<Integer>) exDTO.getQuesIDList();
        int counter = 1;

        for (Integer qId : quesList) {
            String pnConstrain = String.valueOf(counter);
            RoundedButton btn = new RoundedButton();
            btn.setForeground(new java.awt.Color(0, 0, 0));
            btn.setText(pnConstrain);
            btn.setPreferredSize(new java.awt.Dimension(40, 40));
            questListPN.add(btn);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(currentQuestPN, String.valueOf(qId));
                }
            });
            counter++;
        }

        createQuestionPN();
    }

    private void createQuestionPN() {
        Integer counter = 1;
        for (Integer qId : quesList) {
            String pnConstrain = String.valueOf(qId);
            QuestionPN pn = new QuestionPN(counter, qId);

            quesPnList.add(pn);
            currentQuestPN.add(pn, pnConstrain);
            counter++;
        }
        cardLayout.show(currentQuestPN, "1");

    }

    private void submitTest() {
        isTakingTest = false;
        dbFrame.enableAllNavButtons();
        int counter = 1;
        int resultMark = 0;
        JSONArray ansJSONArray = new JSONArray();

        for (QuestionPN quesPn : quesPnList) {
            AnswersDTO ans = quesPn.getSelectedAnswer();
            if (ans != null) {
                if (ans.getIsRight() == 1) {
                    resultMark++;
                }
                counter++;
                ansJSONArray.put(ans.getAwID());
            }
        }
        int currentTakingTestTime = rsBUS.getHighestRsNum(userId, exDTO.getExCode());
        ResultDTO rsDTO = new ResultDTO();
        rsDTO.setExCode(exDTO.getExCode());
        rsDTO.setRsMark(BigDecimal.ONE);
        rsDTO.setRsDate(new Timestamp(System.currentTimeMillis()));
        rsDTO.setRsAnswers(ansJSONArray.toString());
        rsDTO.setRsNum(currentTakingTestTime + 1);
        rsDTO.setUserID(userId);
        rsBUS.addResult(rsDTO);
        JOptionPane.showMessageDialog(null, "Điểm: " + resultMark);
        clearPanelComponents();
    }

    public void clearPanelComponents() {

        this.removeAll(); // Remove all components
        initComponents();
        cardLayout = new CardLayout();
        currentQuestPN.setLayout(cardLayout);
        quesPnList = new ArrayList<>();
        this.revalidate(); // Revalidate the layout
        this.repaint();    // Repaint the panel

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel currentQuestPN;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlBaiThiInfo;
    private javax.swing.JPanel questListPN;
    private javax.swing.JButton submitBtn;
    private javax.swing.JPanel timePN;
    private javax.swing.JLabel tsNameLabel;
    private javax.swing.JLabel tsSubjectLabel;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
//        frame.add(new DoExamJPanel());
        frame.setVisible(true);
    }
}
