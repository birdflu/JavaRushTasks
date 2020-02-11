package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();
    public static CorruptedDataException corruptedDataException = new CorruptedDataException();

//    ..\allLines.txt
//    ..\forRemove.txt


    public static void main(String[] args) {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String fileAllLinesName = null;
        String fileForRemoveLinesName = null;
        try {
            fileAllLinesName = consoleReader.readLine();
            fileForRemoveLinesName = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader;
        String line;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileAllLinesName))));
            while ((line = reader.readLine()) != null) allLines.add(line);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileForRemoveLinesName))));
            while ((line = reader.readLine()) != null) forRemoveLines.add(line);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(allLines.toString());
//        System.out.println(forRemoveLines.toString());

        try {
            (new Solution()).joinData();
        } catch (CorruptedDataException e) {
            e.printStackTrace();
        }

//
//        System.out.println(allLines.toString());
//        System.out.println(forRemoveLines.toString());

    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) allLines.removeAll(forRemoveLines);
        else {
            allLines.clear();
                throw corruptedDataException;
        }

    }
}
