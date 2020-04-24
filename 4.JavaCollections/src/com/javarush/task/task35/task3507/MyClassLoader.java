package com.javarush.task.task35.task3507;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MyClassLoader extends ClassLoader{
  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    return super.loadClass(name);
  }
  
  public Class<?> load(Path path, String className) {
    try {
      byte[] b = Files.readAllBytes(path);
      // https://github.com/Domadin/JavaRushTasks/blob/74d038120f2145d212f265dba1323888531c74b2/4.JavaCollections/src/com/javarush/task/task35/task3507/Solution.java
      return defineClass(className, b, 0, b.length); //here main magic
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}
