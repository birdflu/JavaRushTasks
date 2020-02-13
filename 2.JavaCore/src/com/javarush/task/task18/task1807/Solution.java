package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       String filename = bufferedReader.readLine();

       int Counter = 0;

       FileInputStream fileInputStream = new FileInputStream(filename);
       while (fileInputStream.available() > 0) {
           int b = fileInputStream.read();
           if (b == 44) Counter++;
       }
       fileInputStream.close();
       System.out.println(Counter);

    }
}
