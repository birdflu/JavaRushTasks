package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        int position  = Integer.parseInt(args[1]);
        String text = args[2];

        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        String t = "";
        if (raf.length() >= position+text.length()) {
            raf.seek(position);
            byte[] bytes = new byte[text.length()];
            raf.read(bytes, 0, bytes.length);
            t = new String(bytes);

        }
        raf.seek(raf.length());
        if (text.equals(t)) raf.write("true".getBytes());
        else raf.write("false".getBytes());

        raf.close();
    }

}
