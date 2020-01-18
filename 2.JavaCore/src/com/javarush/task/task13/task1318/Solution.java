package com.javarush.task.task13.task1318;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.*;

/*
Чтение файла
*/

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String fileName = bufferedReader.readLine();

            FileInputStream inputStream = new FileInputStream (fileName);

            Scanner s = new Scanner(inputStream, StandardCharsets.UTF_8).useDelimiter("\\A");
            System.out.print(s.hasNext() ? s.next() : "");

            System.out.println();
            inputStream.close();
            bufferedReader.close();
        }

        catch(IOException ex){
            System.out.println(ex.getMessage());
        }


    }
}