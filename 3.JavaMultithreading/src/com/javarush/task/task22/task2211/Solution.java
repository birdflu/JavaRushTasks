package com.javarush.task.task22.task2211;

import java.io.*;

/* 
Смена кодировкиF
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String filename1 = args[0];
        String filename2 = args[1];

        FileInputStream inputStream = new FileInputStream(filename1);
        OutputStream outputStream = new FileOutputStream(filename2);

        byte[] buffer = new byte[1000];
        while (inputStream.available() > 0) //пока есть еще непрочитанные байты
        {
            // прочитать очередной блок байт в переменную buffer и реальное количество в count
            inputStream.read(buffer);
            String s = new String(buffer, "Windows-1251");
            buffer = s.getBytes("UTF-8");
            int count = buffer.length;
            // при перекодировке число значимых байт изменяется, поэтому нужно их посчитать перед записью
            for (int i = buffer.length - 1; i >= 0; i--) {
                if (buffer[i] == 0000) count--;
                else break;
            }
            outputStream.write(buffer,0,count); //записать блок(часть блока) во второй поток
        }
        inputStream.close();
        outputStream.close();
    }
}
