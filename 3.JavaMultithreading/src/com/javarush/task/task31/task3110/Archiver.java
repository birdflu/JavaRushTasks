package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Archiver {
  public static void main(String[] args) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Input new archive path: ");
    String archivePath = reader.readLine();
    ZipFileManager zipFileManager = new ZipFileManager(Paths.get(archivePath));
    System.out.println("Input path for archiving: ");
    String archiveSource = reader.readLine();
    zipFileManager.createZip(Paths.get(archiveSource));
  }
}
