package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/*
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String outputFileName = "";
        String inputFilename = "";
        Map <Integer, String> inputFilenames = new TreeMap<>();

        while (true) {
            inputFilename = reader.readLine();
            if (inputFilename.equals("end")) {reader.close(); break;}
            else {
                if (outputFileName.length() == 0) outputFileName = inputFilename.split(".part")[0];
                inputFilenames.put(Integer.parseInt(inputFilename.split(".part")[1]),inputFilename);
            }
        }
        FileOutputStream outputStream = new FileOutputStream(outputFileName,true);
        for (String filename : inputFilenames.values()
        ) {
            FileInputStream inputStream = new FileInputStream(filename);
            byte[] buffer = new byte[1000];
            while (inputStream.available() > 0) //пока есть еще непрочитанные байты
            {
                // прочитать очередной блок байт в переменную buffer и реальное количество в count
                int count = inputStream.read(buffer);
                outputStream.write(buffer, 0, count); //записать блок(часть блока) во второй поток
            }
            inputStream.close();
        }
        outputStream.close();

    }
}
