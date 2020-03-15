package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/*
Проход по дереву файлов
*/
public class Solution {
    public static ArrayList<File> listFiles(File folder, ArrayList<File> listFiles) {
        for (File file : folder.listFiles())
            if (!file.isDirectory())
                listFiles.add(file);
            else
                listFiles(file, listFiles);
        return listFiles;
    }

    public static void main(String[] args) {
        File folder = new File(args[0]);
   //     File folder = new File("C:/temp/3101");
        File resultFile = new File(args[1]);
   //     File resultFile = new File("C:/temp/3101/1.txt");

        File renamedFile = new File(resultFile.getParent() + "/allFilesContent.txt");
        //if (FileUtils.isExist(resultFile))
        FileUtils.renameFile(resultFile, renamedFile);
        //else renamedFile.createNewFile();

        try (FileOutputStream outputStream = new FileOutputStream(resultFile.getParent() + "/allFilesContent.txt")){
            // outputStream = new FileOutputStream(renamedFile);
            ArrayList<File> list = new ArrayList<>();
            listFiles(folder, list);
            ArrayList<File> matchedList = new ArrayList<>();
            list.forEach(file -> {if (file.length() <= 50 && file.getName().contains(".txt") && !file.getAbsolutePath().equals(renamedFile.getAbsolutePath()))  matchedList.add(file);});

            Collections.sort(matchedList, (o1, o2) -> o1.getName().compareTo(o2.getName()));

            // matchedList.forEach(file -> System.out.println(file.getName()));
        for (File file : matchedList) {
            FileInputStream inputStream = new FileInputStream(file);

            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();

            outputStream.write(buffer);
            outputStream.write('\n');
     //       System.out.println(file.getName() + " writed");
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
