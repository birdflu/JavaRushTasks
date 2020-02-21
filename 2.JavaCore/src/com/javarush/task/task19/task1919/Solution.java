package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileReader1 = new BufferedReader(new FileReader("..//amount.txt"));
        SortedMap <String, Double> persons = new TreeMap();

        while (fileReader1.ready()){
            String[] elements = fileReader1.readLine().split(" ");
            persons.put(elements[0], persons.containsKey(elements[0]) ? persons.get(elements[0]) + Double.parseDouble(elements[1]) : Double.parseDouble(elements[1]));
        }

        for (Map.Entry<String, Double> e : persons.entrySet()) System.out.println(e.getKey() + " " + e.getValue());

        fileReader1.close();
    }
}
