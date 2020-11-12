package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(81));
    }

    public static boolean isPowerOfThree(int n) {
        return (Math.log(n) / Math.log(3)) * 100 % 100 == 0.00;
    }
}
