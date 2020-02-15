package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String outputFilename = reader.readLine();
        String input1Filename = reader.readLine();
        String input2Filename = reader.readLine();
        reader.close();

//        outputFilename = "..//forRemoveLines3.txt";
//        input1Filename = "..//forRemoveLines.txt";
//        input2Filename = "..//forRemoveLines2.txt";

        //Создаем поток-чтения-байт-из-файла
        FileInputStream inputStream1 = new FileInputStream(input1Filename);
        FileInputStream inputStream2 = new FileInputStream(input2Filename);

        // Создаем поток-записи-байт-в-файл
        FileOutputStream outputStream = new FileOutputStream(outputFilename);

        byte[] buffer = new byte[1000];
        while (inputStream1.available() > 0) //пока есть еще непрочитанные байты
        {
            // прочитать очередной блок байт в переменную buffer и реальное количество в count
            int count = inputStream1.read(buffer);
            outputStream.write(buffer, 0, count); //записать блок(часть блока) во второй поток
        }

        while (inputStream2.available() > 0) //пока есть еще непрочитанные байты
        {
            // прочитать очередной блок байт в переменную buffer и реальное количество в count
            int count = inputStream2.read(buffer);
            outputStream.write(buffer, 0, count); //записать блок(часть блока) во второй поток
        }

        inputStream1.close();
        inputStream2.close();
        outputStream.close();

    }
}
