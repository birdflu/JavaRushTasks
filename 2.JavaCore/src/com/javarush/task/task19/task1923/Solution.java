package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));//"..//digitwords.txt"));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(args[1]));//"..//digits.txt"));
        while (fileReader.ready()) {
            String[] words = fileReader.readLine().split(" ");
            for (String s : words) {
                if (s.matches(".*[\\d]+.*")) fileWriter.write(s + " ");//System.out.print(s + " ");
            }
        }

        fileReader.close();
        fileWriter.close();
    }
}
