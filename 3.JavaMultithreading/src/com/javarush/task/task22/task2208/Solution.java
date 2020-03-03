package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.String.format;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
 /*       Map<String,String> lhm = new HashMap<>() {{
            put("name", "Ivanov");
            put("country", "Ukraine");
            put("city", "Kiev");
            put("age", null);
        }};
        System.out.println(getQuery(lhm));*/
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String,String> s : params.entrySet()
             ) {
            // name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'
            if (s.getValue() != null) {
                result.append(format("%s = '%s'", s.getKey(), s.getValue()));
                result.append(" and ");
            }
        }
        if (result.length() > 0) result.delete(result.length()-5,result.length());
        return result.toString();
    }
}
