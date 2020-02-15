package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Solution {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream(args[0]);
        // English alphabet  65-90, 97-122
        int count = 0;
        int spacecount = 0;
        while (true) {
            int word = inputStream.read();
            if (word == -1) break;
            else if (word == 32) {count++;
                spacecount++;}
            else count++;
        }
        System.out.println(((int)((spacecount+0.0)/count*10000+0.5)+0.0)/100);
        inputStream.close();
    }
}
