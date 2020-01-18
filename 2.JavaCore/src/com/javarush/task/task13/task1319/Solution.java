package com.javarush.task.task13.task1319;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

/* 
Запись в файл с консоли
*/

public class Solution {
    public static void main(String[] args) {
    try {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String filename = reader.readLine();
        String line = "";
        FileWriter fileWriter = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fileWriter);

        while (!line.equals("exit")) {
            line = reader.readLine();
            bw.write(line + "\n");
        }

        bw.close();
        reader.close();
        fileWriter.close();
    }

    catch (Exception e){
        e.printStackTrace();
    }

    }
}



