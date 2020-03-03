package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(printArray(getTokens("level22.lesson13.task01", ".")));
    }
    public static String [] getTokens(String query, String delimiter) {
        ArrayList<String> result = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(query, delimiter);
        while (stringTokenizer.hasMoreTokens()){
            result.add(stringTokenizer.nextToken());
        }
        return result.toArray(new String[result.size()]);
    }

    public static String printArray (String[] array){
        String result = array[0];
        for (int i = 1; i < array.length; i ++) result += ", "+ array[i];
        return result;
    }
}
