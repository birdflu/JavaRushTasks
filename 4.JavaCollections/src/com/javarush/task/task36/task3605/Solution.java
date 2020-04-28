package com.javarush.task.task36.task3605;

import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        TreeSet<Integer> treeSet = new TreeSet();
        FileReader fr = new FileReader(filename);
        int c;
        int count = 0;
        while (( c = fr.read()) >= 0) {
            if (Character.isAlphabetic(c)) treeSet.add(Character.toLowerCase(c));
        }
        for (Integer s : treeSet) {
            if (count++ < 5)  System.out.print(((char)(int)(s)));
        }
    }
}
