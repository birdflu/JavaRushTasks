package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
  @Override
  public boolean accept(File f) {
    return f.isDirectory()
            || f.getName().matches("(?i:.*\\." + "html" + "$)")
            || f.getName().matches("(?i:.*\\." + "htm" + "$)");
  }
  
  @Override
  public String getDescription() {
    return "HTML и HTM файлы";
  }
}
