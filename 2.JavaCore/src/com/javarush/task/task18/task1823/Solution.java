package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputFilename;
        while (true) {
            inputFilename = reader.readLine();
            if (inputFilename.equals("exit")) break; else (new ReadThread (inputFilename)).start();
        }
        reader.close();

    }

    public static class ReadThread extends Thread {
        private String fileName;
        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут
        public void run()
        {
     //       System.out.println("I’m " + this.fileName);
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(this.fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Map<Integer,Integer> map = new HashMap<>();

            while (true) {
                int word = 0;
                try {
                    word = inputStream.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (word == -1) break;
                else if (map.containsKey(word)) map.put(word, map.get(word) + 1);
                else map.put(word, 1);
            }

            // founding maximum
            Integer bt = 0;
            Integer maxCount = 0;
            for (Map.Entry<Integer, Integer> e : map.entrySet())
                  {
                if (e.getValue() > maxCount) {bt = e.getKey(); maxCount = e.getValue();}
            }
            resultMap.put(this.fileName,bt);
            char charBt = (char) ((int) bt);
          //  System.out.println("add " + this.fileName + ": " + charBt);

            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
       }
    }
}
