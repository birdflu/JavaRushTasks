package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static Boolean isServiceSymbol (int symbol){
        int[] serviceSymbols = {'!', '"', '\'', '#', '$', '%', '&', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '/', '`', '{', '|', '}', '~',0x0A, 0x0D};
        Boolean result = false;
        for (int element : serviceSymbols
        ) {
            if (element == symbol) {result = true; break;}
        }
        return result;
    };

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputFileName = bufferedReader.readLine();
        String outputFileName = bufferedReader.readLine();

//        inputFileName = "..//Lion.avi.part2";
//        outputFileName = "..//Lion.avi.part3";
        bufferedReader.close();
        FileReader reader = new FileReader(inputFileName);
        FileWriter writer = new FileWriter(outputFileName);

        bufferedReader = new BufferedReader(reader);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        while (bufferedReader.ready()) //пока есть непрочитанные байты в потоке ввода
        {
            int value = bufferedReader.read();
            if (!isServiceSymbol(value)) bufferedWriter.write(value);
        }

        //закрываем потоки после использования
        bufferedReader.close();
        bufferedWriter.close();
        reader.close();
        writer.close();

    }
}
