package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.*;

public class Solution {
    public static Boolean isServiceSymbol (int symbol){
        int[] serviceSymbols = {'!', '?', '.', ':', '-', ';', ',', '(', ')', ' ', 0, 0x0A, 0x0D};
        Boolean result = false;
        for (int element : serviceSymbols
             ) {
            if (element == symbol) {result = true; break;}
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputFileName = bufferedReader.readLine();

        bufferedReader.close();

        FileReader reader = new FileReader(inputFileName);

        int wordCount = 0;
        int[] wordArray = {0, 0, 0, 0, 0, 0, 0};

        // находим слово в начале текста
        for (int i = 0; i < 7; i++)
            if (reader.ready()) wordArray[i] = reader.read(); //читаем один символ (char будет расширен до int)
        if (wordArray[0] == 119 &&  // w
                wordArray[1] == 111 &&  // o
                wordArray[2] == 114 &&  // r
                wordArray[3] == 108 &&  // l
                wordArray[4] == 100 &&  // d
                isServiceSymbol(wordArray[5]))
        {wordCount++;
        }

        while (reader.ready()) //пока есть непрочитанные байты в потоке ввода
        {
            for (int i = 0; i < 6; i++) wordArray[i] = wordArray[i + 1];
            wordArray[6] = reader.read(); //читаем один символ (char будет расширен до int)

            // находим слово в середине текста
            if (isServiceSymbol(wordArray[0]) &&
                    wordArray[1] == 119 &&  // w
                    wordArray[2] == 111 &&  // o
                    wordArray[3] == 114 &&  // r
                    wordArray[4] == 108 &&  // l
                    wordArray[5] == 100 &&  // d
                    isServiceSymbol(wordArray[6]))
            {wordCount++;
            }
        }

        // находим слово в конце текста
        if (isServiceSymbol(wordArray[1]) &&
                wordArray[2] == 119 &&  // w
                wordArray[3] == 111 &&  // o
                wordArray[4] == 114 &&  // r
                wordArray[5] == 108 &&  // l
                wordArray[6] == 100)  // d
        {wordCount++;
        }

        //закрываем потоки после использования
        reader.close();
        System.out.println(wordCount);
    }
}

