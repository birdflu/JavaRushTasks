package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input1Filename = reader.readLine();
        String input2Filename = reader.readLine();
        reader.close();

//        input1Filename = "..//forRemoveLines.txt";
//        input2Filename = "..//forRemoveLines2.txt";

        FileInputStream inputStream = new FileInputStream(input1Filename);
        byte[] buffer1 = new byte[inputStream.available()];
        inputStream.read(buffer1);
        inputStream.close();

        inputStream = new FileInputStream(input2Filename);
        byte[] buffer2 = new byte[inputStream.available()];
        inputStream.read(buffer2);
        inputStream.close();

        // Создаем поток-записи-байт-в-файл
        FileOutputStream outputStream = new FileOutputStream(input1Filename);
        outputStream.write(buffer2);
        outputStream.write(buffer1);


        outputStream.close();

    }

}
