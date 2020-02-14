package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String inputFileName = bufferedReader.readLine();
            FileInputStream inputFile = new FileInputStream(inputFileName);
            if (inputFile.available() < 1000) {inputFile.close(); throw new DownloadException();}
        }
    }

    public static class DownloadException extends Exception {

    }
}
