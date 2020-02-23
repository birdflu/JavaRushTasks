package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
//        fileName = "..//properties.txt";
        bufferedReader.close();
        FileInputStream fileInputStream = new FileInputStream(fileName);
        load(fileInputStream);
        fileInputStream.close();
/*
        for (Map.Entry<String,String> e: properties.entrySet())
            System.out.println(e.getKey() + " : " + e.getValue());
*/
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();
        prop.putAll(properties);
        prop.store(outputStream, (new Date().toString()));
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();
        prop.load(inputStream);
        for (Map.Entry e: prop.entrySet()
        ) {
            properties.put(e.getKey().toString(), e.getValue().toString());
        }
    }

    public static void main(String[] args) {
/*
        (new Solution()).fillInPropertiesMap();
        (new Solution()).save(new FileOutputStream("c://Temp//properties2.txt"));
*/
    }
}
