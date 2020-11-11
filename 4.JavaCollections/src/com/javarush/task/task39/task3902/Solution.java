package com.javarush.task.task39.task3902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Биты были биты
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter a number: ");

        long l = Long.parseLong(reader.readLine());
        String result = isWeightEven(l) ? "even" : "odd";
        System.out.println("The entered number has " + result + "ones");

/*
        for (int i = 0; i < l; i++) {

            String binaryString = Long.toBinaryString(i);
            long count = binaryString.chars().filter(x -> x == '1').count();
            long i2 = i & (i - 1);
            System.out.println(i + " : " + binaryString + " : " + count + " : " + ((count%2 == 0) ? "even" : "") + " : " + isWeightEven(i));

        }
*/

    }

    public static boolean isWeightEven(long number) {
//        https://habr.com/ru/post/276957/
        int res = 0;
        while (number > 0) {
            res++;
            number = number & (number - 1);
        }
        return res%2 == 0;
    }
}
