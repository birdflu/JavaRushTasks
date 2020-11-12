package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("лазербоРехеробрезал"));
    }

    public static boolean isPalindromePermutation(String s) {
        StringBuilder noDuplicate = getWithoutDuplicates(new StringBuilder(s.toLowerCase()));
        return noDuplicate.length() <= 1;
    }

    private static StringBuilder getWithoutDuplicates(StringBuilder stringBuilder) {
//        System.out.println(stringBuilder.toString() + " " + stringBuilder.length());

        for (int i = 0; i < stringBuilder.length(); i++) {
            String pairFirst = stringBuilder.substring(i, i+1);
            int pairLastIndex = stringBuilder.indexOf(pairFirst, i+1);
            if (pairLastIndex < 0 ) {
                if (i != 0) {
                    return stringBuilder;
                }
            } else {
                stringBuilder.deleteCharAt(pairLastIndex);
                stringBuilder.deleteCharAt(i);
                getWithoutDuplicates(stringBuilder);
            }
        }
        return stringBuilder;
    }
}
