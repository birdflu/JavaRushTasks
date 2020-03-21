package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/* 
Что внутри папки?
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        Path path = Paths.get(input);
        if (!Files.isDirectory(path)) {
            System.out.format("%s - не папка", path.toAbsolutePath());
            return;
        }
        SearchFileVisitor searchFileVisitor = new SearchFileVisitor();
        Files.walkFileTree(path, searchFileVisitor);
        System.out.format("Всего папок - %s\n", searchFileVisitor.getFoldersCount()-1);
        System.out.format("Всего файлов - %s\n", searchFileVisitor.getFilesCount());
        System.out.format("Общий размер - %s\n", searchFileVisitor.getTotalByte());
    }
}
