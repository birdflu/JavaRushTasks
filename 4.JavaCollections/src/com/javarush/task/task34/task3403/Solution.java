package com.javarush.task.task34.task3403;

/*
Разложение на множители с помощью рекурсии

Тупое решение для валидатора:

    public void recurse(int n) {
        if (n<=1) return;
        int testDivisor = 1;
        while (true) {
            testDivisor++;
            if (testDivisor * testDivisor > n) {
                System.out.print(n + " ");
                break;
            }
            if (isDivides(n, testDivisor)) {
                System.out.print(testDivisor + " ");
                recurse(n / testDivisor);
                break;
            }
        }
        return;
    }

        public boolean isDivides (int a, int b){
        if (a % b == 0) return true;
        else return false;
    }
*/

public class Solution {
    public static void main(String[] args) {
        new Solution().recurse(144);
    }

    public void recurse(int n) {
        findDivisor(n, 2);
    }

    public void findDivisor  (int n, int testDivisor){
        if (testDivisor*testDivisor > n) {
            System.out.print(n + " ");
            return;
        }
        if (isDivides(n, testDivisor)) {
            System.out.print(testDivisor + " ");
            findDivisor(n/testDivisor, testDivisor);
            return;
        }
        findDivisor(n, ++testDivisor);
    }

    public boolean isDivides (int a, int b){
        if (a % b == 0) return true;
        else return false;
    }
}
