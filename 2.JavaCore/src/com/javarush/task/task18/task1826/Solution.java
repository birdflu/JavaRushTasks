package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        String key = args[0]; // "-d";
        String fileName = args[1]; // "..\\Lion.avi.part2";
        String fileOutputName = args[2]; // "..\\Lion.avi.part3";

        FileInputStream inputStream = new FileInputStream(fileName);
        FileOutputStream outputStream = new FileOutputStream(fileOutputName);
        while (inputStream.available() > 0)
        {
            int bt = inputStream.read();
            if (key.equals("-e")) outputStream.write(bt+2);
            else if (key.equals("-d"))  outputStream.write(bt-2);
        }
        inputStream.close();
        outputStream.close();
    }
}
