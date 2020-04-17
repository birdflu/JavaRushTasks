package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
  private Controller controller;
  private JTabbedPane tabbedPane = new JTabbedPane();
  private JTextPane htmlTextPane = new JTextPane();
  private JEditorPane plainTextPane = new JEditorPane();
  private UndoManager undoManager = new UndoManager();
  private UndoListener undoListener = new UndoListener(undoManager);
  
  public View() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
      ExceptionHandler.log(e);
    }
  }
  
  public void init() {
    initGui();
    FrameListener frameListener = new FrameListener(this);
    addWindowListener(frameListener);
    setVisible(true);
  }
  
  public Controller getController() {
    return controller;
  }
  
  public void setController(Controller controller) {
    this.controller = controller;
  }
  
  public void exit() {
    controller.exit();
  }
  
  public void initMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    MenuHelper.initFileMenu(this, menuBar);
    MenuHelper.initEditMenu(this, menuBar);
    MenuHelper.initStyleMenu(this, menuBar);
    MenuHelper.initAlignMenu(this, menuBar);
    MenuHelper.initColorMenu(this, menuBar);
    MenuHelper.initFontMenu(this, menuBar);
    MenuHelper.initHelpMenu(this, menuBar);
    getContentPane().add(menuBar, BorderLayout.NORTH);
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
  
  @Override
  public void actionPerformed(ActionEvent e) {
  
  }

  public void undo() {
    try {
      undoManager.undo();
    } catch (Exception e) {
      ExceptionHandler.log(e);
    }
  }
  
  public void redo() {
    try {
      undoManager.redo();
    } catch (Exception e) {
      ExceptionHandler.log(e);
    }
  }
  
  public boolean canUndo() {
    return undoManager.canUndo();
  }
  
  public boolean canRedo() {
    return undoManager.canRedo();
  }
  
  public void resetUndo() {
    undoManager.discardAllEdits();
  }
  
  public UndoListener getUndoListener() {
    return undoListener;
  }
  
  
}