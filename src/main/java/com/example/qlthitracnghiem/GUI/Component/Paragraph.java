/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.GUI.Component;

import java.awt.FontMetrics;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author truon
 */
public class Paragraph extends JLabel {
  public Paragraph() {
    super();
    setText("");
  }

  public Paragraph(String text) {
    super();
    setText(text);
  }

  @Override
  public void setText(String text) {
    super.setText(String.format("<html><p>%s</p></html>", text));
  }

  public void setWidth(int width) {
    if (getFont() != null) {
      FontMetrics fm = getFontMetrics(getFont());
      int fontWidth = fm.stringWidth(getText());

      int lines = (int) Math.ceil(fontWidth / width);
      int height = fm.getHeight() * lines;
      System.out.println("Width: " + width);
      System.out.println("Height: " + height);

      setPreferredSize(new java.awt.Dimension(width, height));
    } else {
      System.out.println("Font is null");
    }
    revalidate();
  }
}
