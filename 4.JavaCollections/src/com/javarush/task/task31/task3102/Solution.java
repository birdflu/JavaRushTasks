package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        ArrayList<String> files = new ArrayList<>();
        ArrayDeque<File> folders = new ArrayDeque<>();
        folders.add(new File(root));

        while (!folders.isEmpty()) {
            File folder = folders.pop();
            for (File file : folder.listFiles())
                if (!file.isDirectory())
                    files.add(file.getAbsolutePath());
                else folders.addLast(file);
        }
        return files;
    }

    public static void main(String[] args) {
        try {
            getFileTree("C:\\temp\\3101").forEach(fileName -> System.out.println(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
