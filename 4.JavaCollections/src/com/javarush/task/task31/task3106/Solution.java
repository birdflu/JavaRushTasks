package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
  public static void main(String[] args) throws IOException {
//    args = new String[4];
//    args[0] = "C:/work/jprojects/javarushtasks/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/data/video.mp4";
//    args[1] = "C:/work/jprojects/javarushtasks/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/data/archive/a.z01";
//    args[2] = "C:/work/jprojects/javarushtasks/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/data/archive/a.z02";
//    args[3] = "C:/work/jprojects/javarushtasks/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/data/archive/a.z03";

    ArrayList<String> fileList = new ArrayList<>();

    for (int i = 1; i < args.length; i++)
      fileList.add(args[i]);

    Collections.sort(fileList);
    ArrayList<FileInputStream> streams = new ArrayList<>(fileList.size());
    for (String fileName : fileList)
      streams.add(new FileInputStream(fileName));

    ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(Collections.enumeration(streams)));

    FileOutputStream fileOutputStream = new FileOutputStream(args[0]);
    int l;
    while ((zipInputStream.getNextEntry()) != null) {
      byte[] bytesFromEntry = new byte[8192];
      while ((l = zipInputStream.read(bytesFromEntry)) != -1) {
        fileOutputStream.write(bytesFromEntry, 0, l);
      }
      zipInputStream.closeEntry();
    }
    fileOutputStream.close();
    zipInputStream.close();
  }
}