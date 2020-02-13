package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputFileName = bufferedReader.readLine();
        String outputFileName = bufferedReader.readLine();

        FileInputStream inputFile = new FileInputStream(inputFileName);
        FileOutputStream outputFile = new FileOutputStream(outputFileName);

        byte[] buffer = new byte[inputFile.available()];
        inputFile.read(buffer);
        inputFile.close();

        for (int i = buffer.length-1; i >=0 ; i--) {
            outputFile.write(buffer[i]);
        }

        outputFile.close();
    }
}
