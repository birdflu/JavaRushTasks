package com.javarush.task.task19.task1908;

/* 
Выделяем числа
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

        bufferedReader = new BufferedReader(reader);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        Boolean isFirstWrite = true; // идентификатор первого обращения к файлу для записи чтоб обработать концевые пробелы
        while (bufferedReader.ready()) //пока есть непрочитанные байты в потоке ввода
        {
            String[] words = bufferedReader.readLine().split(" ");
            for (String w : words
                 ) {
                try {
                    Integer digit = Integer.parseInt(w);
                    if (isFirstWrite) { bufferedWriter.write("" + digit); isFirstWrite = false;}
                    else bufferedWriter.write(" " + digit);
                }
                catch (NumberFormatException e){
                    ;
                }
            }
        }


        //закрываем потоки после использования
        bufferedReader.close();
        bufferedWriter.close();
        reader.close();
        writer.close();

    }
}
