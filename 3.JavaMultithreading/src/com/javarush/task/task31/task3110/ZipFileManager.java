package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
  private Path zipFile;
  
  public ZipFileManager(Path zipFile) {
    this.zipFile = zipFile;
  }
  
  public void createZip(Path source) throws Exception {
    Path zipDirectory = zipFile.getParent();
    if (Files.notExists(zipDirectory))
      Files.createDirectories(zipDirectory);
    
    try (ZipOutputStream outputStream = new ZipOutputStream(Files.newOutputStream(zipFile))
    ) {
      if (Files.isRegularFile(source))
        addNewZipEntry(outputStream, source.getParent(), source.getFileName());
      else if (Files.isDirectory(source)) {
        FileManager fileManager = new FileManager(source);
        List<Path> fileList = fileManager.getFileList();
        for (Path fileName : fileList) {
          addNewZipEntry(outputStream, source, fileName);
        }
      } else throw new PathIsNotFoundException();
    }
  }
  
  private void addNewZipEntry(ZipOutputStream zipOutputStream,
                              Path filePath,
                              Path fileName) throws Exception {
    
    Path fullPath = filePath.resolve(fileName);
    try (InputStream inputStream = Files.newInputStream(fullPath)
    ) {
      ZipEntry entry = new ZipEntry(fileName.toString());
      zipOutputStream.putNextEntry(entry);
      copyData(inputStream, zipOutputStream);
      zipOutputStream.closeEntry();
    }
  }
  
  private void copyData(InputStream in, OutputStream out) throws Exception {
    byte[] buffer = new byte[1000];
    while (in.available() > 0) {
      int count = in.read(buffer);
      out.write(buffer, 0, count);
    }
  }
}
