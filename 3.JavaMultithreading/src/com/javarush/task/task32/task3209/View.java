package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class View extends JFrame implements ActionListener {
  private Controller controller;
  private JTabbedPane tabbedPane = new JTabbedPane();
  private JTextPane htmlTextPane = new JTextPane();
  private JEditorPane plainTextPane = new JEditorPane();
  
  @Override
  public void actionPerformed(ActionEvent e) {
  
  }
  
  public Controller getController() {
    return controller;
  }
  
  public void setController(Controller controller) {
    this.controller = controller;
  }
  
  public void init() {
    initGui();
    FrameListener frameListener = new FrameListener(this);
    addWindowListener(frameListener);
    setVisible(true);
  }
  
  public void exit() {
    controller.exit();
  }
  
  public void initMenuBar() {
  
  }
  
  public void initEditor() {
    htmlTextPane.setContentType("text/html");
    JScrollPane htmlScrollPane = new JScrollPane(htmlTextPane);
    tabbedPane.add("HTML" , htmlScrollPane);
    JScrollPane plainScrollPane = new JScrollPane(plainTextPane);
    tabbedPane.add("Текст", plainScrollPane);
    Dimension preferredSize = new Dimension(400,300);
    tabbedPane.setPreferredSize(preferredSize);
    TabbedPaneChangeListener tPCListener = new TabbedPaneChangeListener(this);
    tabbedPane.addChangeListener(tPCListener);
    getContentPane().add(tabbedPane, BorderLayout.CENTER);
  }
  
  
  public void initGui() {
    initMenuBar();
    initEditor();
    pack();
  }
  
  public void selectedTabChanged() {
  
  }
}
