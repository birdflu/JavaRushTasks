package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("c:/temp/1.txt "));// testFile.log
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter stringWriter = new StringWriter();
        if (is == null) return stringWriter;
        int s;
        while((s = is.read()) > 0) {
            stringWriter.write(s);
        }
        return stringWriter;
    }
}