package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputFilename = reader.readLine();
        reader.close();
        //inputFilename = "../forRemoveLines.txt";
        String ID = args[0];
        //String ID = "2";
        //Создаем поток-чтения-байт-из-файла
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilename)));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] lines = line.split(" ");
            if (lines[0].equals(ID)) System.out.println(line);
        }
        reader.close();
    }
}
