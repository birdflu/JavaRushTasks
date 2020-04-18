package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
  private View view;
  private HTMLDocument document;
  private File currentFile;
  
  public Controller(View view) {
    this.view = view;
  }
  
  public void init() {
    createNewDocument();
  }
  
  public void exit() {
    System.exit(0);
  }
  
  public static void main(String[] args) {
    View view = new View();
    Controller controller = new Controller(view);
    view.setController(controller);
    controller.view = view;
    controller.view.init();
    controller.init();
  }
  
  public HTMLDocument getDocument() {
    return document;
  }
  
  public void resetDocument() {
  if (document != null) document.removeUndoableEditListener(view.getUndoListener());
    Document defaultDocument = new HTMLEditorKit().createDefaultDocument();
    document = (HTMLDocument) defaultDocument;
    document.addUndoableEditListener(view.getUndoListener());
    view.update();
  }
  
  public void setPlainText(String text) {
    resetDocument();
    StringReader reader = new StringReader(text);
    try {
      new HTMLEditorKit().read(reader, document, 0);
    } catch (IOException | BadLocationException e) {
      ExceptionHandler.log(e);
    }
  }
  
  public String getPlainText() {
    StringWriter writer = new StringWriter();
    try {
      new HTMLEditorKit().write(writer,document,0,document.getLength());
    } catch (IOException | BadLocationException e) {
      ExceptionHandler.log(e);
    }
    return writer.toString();
  }
  
  public void createNewDocument() {
    view.selectHtmlTab();
    resetDocument();
    view.setTitle("HTML редактор");
    view.resetUndo();
    currentFile = null;
  }
  
  public void openDocument() {
  }
  
  public void saveDocument() {
  
  }
  
  public void saveDocumentAs() {
    view.selectHtmlTab();
    JFileChooser fileChooser = new JFileChooser();
    if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(view)) {
      fileChooser.setFileFilter(new HTMLFileFilter());
      currentFile = fileChooser.getSelectedFile();
      view.setTitle(currentFile.getName());
      try {
        FileWriter writer = new FileWriter(currentFile);
        new HTMLEditorKit().write(writer,document,0,document.getLength());
      } catch (IOException | BadLocationException e) {
        ExceptionHandler.log(e);
      }
    }
  }
}
