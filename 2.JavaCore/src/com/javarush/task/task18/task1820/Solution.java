package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String inputFilename = reader.readLine();
        String outputFilename = reader.readLine();
        reader.close();

        //Создаем поток-чтения-байт-из-файла
        FileInputStream inputStream = new FileInputStream(inputFilename);

        // Создаем поток-записи-байт-в-файл
        FileOutputStream outputStream = new FileOutputStream(outputFilename, true);
        String word = "";
        int i;
        while(true){
            i = inputStream.read();
            if (i == ' ' || i == -1){
                outputStream.write(("" + Math.round(Float.parseFloat(word)) + " ").getBytes());
                if (i == -1) break;
                word = "";
            } else {
                word = word + (char) i;
            }

        }

        inputStream.close();
        outputStream.close();
    }
}
