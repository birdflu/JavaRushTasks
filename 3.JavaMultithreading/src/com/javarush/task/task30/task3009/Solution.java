package com.javarush.task.task30.task3009;

/*
Палиндром?
*/

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
        System.out.println(getRadix("-1"));         //expected output: []
        System.out.println(getRadix("1"));          //expected output: [2, 3, 4, 5, 6, 7, 8,
                                                            // 9, 10, 11, 12, 13, 14, 15, 16, 17,
                                                            // 18, 19, 20, 21, 22, 23, 24, 25, 26,
                                                            // 27, 28, 29, 30, 31, 32, 33, 34, 35,
                                                            // 36]
        System.out.println(getRadix(""));           //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> result = new HashSet<>();
        BigInteger bigInteger;

        try {
            bigInteger = new BigInteger(number);
        } catch (Exception e) {
            return result;
        }

        for (int i = 2; i < 37; i++) {
            String n = bigInteger.toString(i);
            if (isPalindrome(n)) {
                result.add(i);
            }
        }
        return result;
    }

    private static boolean isPalindrome(String string) {
        int len = string.length();
        if (len <= 1) {
            return true;
        } else if (string.substring(0, 1).equals(string.substring(len - 1, len))) {
            return isPalindrome(string.substring(1, len-1));
        } else return false;
    }
}