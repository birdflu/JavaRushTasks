package com.javarush.task.task31.task3110;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
  private Path rootPath;
  private List<Path> fileList;
  
  public FileManager(Path rootPath) throws IOException {
    this.rootPath = rootPath;
    this.fileList = new ArrayList<>();
    collectFileList(rootPath);
  }
  
  public List<Path> getFileList() {
    return fileList;
  }
  
  private void collectFileList(Path path) throws IOException {
    if (Files.isRegularFile(path)) {
      String absolutePath = path.toAbsolutePath().toString();
      String absoluteRoot = rootPath.toAbsolutePath().toString();
      String relativePath = absolutePath.replace(absoluteRoot, "").substring(1);
      fileList.add(Paths.get(relativePath));
      } else if (Files.isDirectory(path)) {
      DirectoryStream stream = Files.newDirectoryStream(path);
      for (Object filePath: stream
           ) {
        collectFileList((Path) filePath);
      }
      stream.close();
    }
  }
}
