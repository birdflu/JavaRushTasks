package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileReader1 = new BufferedReader(new FileReader(args[0]));
        Map<String, Double> persons = new HashMap<>();

        while (fileReader1.ready()) {
            String[] elements = fileReader1.readLine().split(" ");
            persons.put(elements[0], persons.containsKey(elements[0]) ? persons.get(elements[0]) + Double.parseDouble(elements[1]) : Double.parseDouble(elements[1]));
        }

        Double maxSal = Double.MIN_VALUE;
        for (Map.Entry<String, Double> e : persons.entrySet())
            maxSal = e.getValue() > maxSal ? e.getValue() : maxSal;

        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Double> e : persons.entrySet())
            if (e.getValue().doubleValue() == maxSal.doubleValue()) result.add(e.getKey());

        Collections.sort(result);
        for (String r : result)
            System.out.println(r);

        fileReader1.close();
    }
}
