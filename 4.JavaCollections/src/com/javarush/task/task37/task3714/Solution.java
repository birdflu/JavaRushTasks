package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        int result = 0;
        Map<String, Integer> map = new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        for(int i = 0; i < s.length();i++){
            result+=map.get(String.valueOf(s.charAt(i)));
        }
    
        if (s.contains("IV"))
        {
            result-=2;
        }
        if (s.contains("IX"))
        {
            result-=2;
        }
        if (s.contains("XL"))
        {
            result-=10;
        }
        if (s.contains("XC"))
        {
            result-=10;
        }
        if (s.contains("CD"))
        {
            result-=100;
        }
        if (s.contains("CM"))
        {
            result-=100;
        }
        return result;
    }
    
}
