package com.javarush.task.task33.task3310.strategy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
  Path path;
  
  public FileBucket() {
    try {
      path = Files.createTempFile("filebacket", ".file");
      Files.deleteIfExists(path);
      Files.createFile(path);
      path.toFile().deleteOnExit();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public long getFileSize() {
    // он должен возвращать размер файла на который указывает path.
    try {
      return Files.size(path);
    } catch (IOException e) {
      return 0;
    }
  }
  
  public void putEntry(Entry entry) {
    // должен сериализовывать переданный entry в файл. Учти, каждый entry может содержать еще один entry.
    try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
      oos.writeObject(entry);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public Entry getEntry() {
    // - должен забирать entry из файла. Если файл имеет нулевой размер, вернуть null
    System.out.println(getFileSize());
    if (getFileSize() > 0)
      try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
        return (Entry) ois.readObject();
      } catch (Exception e) {
        return null;
      }
    else return null;
  }
  
  public void remove() {
    //  - удалять файл на который указывает path
    try {
      Files.delete(path);
    } catch (IOException e) {
    
    }
  }
  
}
