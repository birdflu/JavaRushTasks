package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream(args[0]);
        Map<Character,Integer> map = new HashMap<>();

        while (true) {
            int word = inputStream.read();
            if (word == -1) break;
            else if (map.containsKey((char)word)) map.put((char)word, map.get((char)word) + 1);
            else map.put((char)word, 1);

        }

        Map<Character,Integer> treeMap = new TreeMap<>(map);
        for (Map.Entry e: treeMap.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }

        inputStream.close();
    }

}
