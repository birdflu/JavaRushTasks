package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = bufferedReader.readLine();
        String filename2 = bufferedReader.readLine();
        String filename3 = bufferedReader.readLine();

        FileInputStream inputFile = new FileInputStream(filename1);
        FileOutputStream outputFile1 = new FileOutputStream(filename2);
        FileOutputStream outputFile2 = new FileOutputStream(filename3);


        int inputFileSize = inputFile.available();
        int boundary = (int) ((inputFileSize+0.0)/2+0.5);

        byte[] buffer = new byte[boundary];
        int count = inputFile.read(buffer);
        outputFile1.write(buffer);
        outputFile1.close();

        buffer = new byte[inputFileSize-boundary];
        count = inputFile.read(buffer);
        outputFile2.write(buffer);
        outputFile2.close();

        inputFile.close();
    }
}
