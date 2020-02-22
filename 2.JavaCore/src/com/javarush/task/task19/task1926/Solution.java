package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        //fileName = "..//digitwords.txt";
        bufferedReader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

        while (fileReader.ready()){
            char[] line = fileReader.readLine().toCharArray();
            char[] reverseline = new char[line.length];

            for (int i = 0; i < reverseline.length; i++) {
                reverseline[i] = line[reverseline.length-1-i];
            }

            System.out.println(String.valueOf(reverseline));
        }
        fileReader.close();
    }
}
