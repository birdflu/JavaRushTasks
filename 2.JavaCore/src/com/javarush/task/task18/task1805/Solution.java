package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();

        FileInputStream inputStream = new FileInputStream(filename);

        int[] byteArray = new int[256];

        while (inputStream.available() > 0)
        {
            int data = inputStream.read();
            byteArray[data] = 1;
        }

        inputStream.close();

        for (int i = 0; i < byteArray.length; i++) {
            if (byteArray[i] == 1) System.out.printf(i + " ");
        }

    }
}
