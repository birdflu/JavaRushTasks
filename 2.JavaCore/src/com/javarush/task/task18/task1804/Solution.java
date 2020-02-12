package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();

        FileInputStream inputStream = new FileInputStream(filename);

        int[] byteArray = new int[256];

        int threshold = Integer.MAX_VALUE;
        while (inputStream.available() > 0)
        {
            int data = inputStream.read();
            byteArray[data] = byteArray[data] + 1;
        }
        for (int i = 0; i < byteArray.length; i++)
            if ((byteArray[i] != 0) && threshold > byteArray[i]) threshold = byteArray[i];

        inputStream.close();
        for (int i = 0; i < byteArray.length; i++) {
            if (byteArray[i] == threshold) System.out.printf(i + " ");
        }
    }
}
