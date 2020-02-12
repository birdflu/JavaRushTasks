package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        // ..\WP_20190817_10_02_22_Pro.jpg
        // ..\forRemoveLines.txt
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();

        FileInputStream inputStream = new FileInputStream(filename);

        int[] byteArray = new int[256];
        int threshold = 0;
        while (inputStream.available() > 0)
        {
            int data = inputStream.read();
            byteArray[data] = byteArray[data] + 1;
            if (threshold < byteArray[data]) threshold = byteArray[data];
        }

        inputStream.close();
        for (int i = 0; i < byteArray.length; i++) {
            if (byteArray[i] == threshold) System.out.printf(i + " ");
        }
    }
}
