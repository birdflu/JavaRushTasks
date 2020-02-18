package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputFileName = bufferedReader.readLine();
        String outputFileName = bufferedReader.readLine();

//        inputFileName = "..//Lion.avi.part2";
//        outputFileName = "..//Lion.avi.part3";
        bufferedReader.close();

        FileReader reader = new FileReader(inputFileName);
        FileWriter writer = new FileWriter(outputFileName);

        Boolean isWrite = false;
        while (reader.ready()) //пока есть непрочитанные байты в потоке ввода
        {
            int data = reader.read(); //читаем один символ (char будет расширен до int)
            if (isWrite) writer.write(data); //пишем один символ (int будет обрезан/сужен до char)
            isWrite = !isWrite;
        }

        //закрываем потоки после использования
        reader.close();
        writer.close();

    }
}
