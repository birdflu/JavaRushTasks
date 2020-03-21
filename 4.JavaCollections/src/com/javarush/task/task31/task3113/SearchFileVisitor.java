package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
  private int foldersCount = 0; // Всего папок - [количество папок в директории и поддиректориях]
  private int filesCount = 0; // Всего файлов - [количество файлов в директории и поддиректориях]
  private long totalByte = 0; // Общий размер - [общее количество байт, которое хранится в директории]

  public int getFoldersCount() {
    return foldersCount;
  }

  public int getFilesCount() {
    return filesCount;
  }

  public long getTotalByte() {
    return totalByte;
  }

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    filesCount++;
    totalByte = totalByte + Files.size(file);
    return super.visitFile(file, attrs);
  }

  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
    foldersCount++;
    return super.postVisitDirectory(dir, exc);
  }
}
