package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Solution {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream(args[0]);
        // English alphabet  65-90, 97-122
        int count = 0;
        while (true) {
            int word = inputStream.read();
            if (word == -1) break;
            else if ((word >=65 && word <=90) || (word >=97 && word <=122))  count++;
        }
        System.out.println(count);
        inputStream.close();
    }
}
