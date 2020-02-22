package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String inputFileName = args[0]; //"..//digitwords.txt";
        String outputFileName = args[1]; //"..//wordwithcomma.txt";
        BufferedReader fileReader = new BufferedReader(new FileReader(inputFileName));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outputFileName));
        boolean isLeadComma = false;

        while (fileReader.ready()){
            String[] words = fileReader.readLine().split(" ");
            for (int i = 0; i < words.length; i++) {
                if (words[i].length() > 6) {
                    fileWriter.write(((isLeadComma) ? ",": "") + words[i]);
                    if (!isLeadComma) isLeadComma = true;
                }
            }
        }
        fileReader.close();
        fileWriter.close();
    }
}
