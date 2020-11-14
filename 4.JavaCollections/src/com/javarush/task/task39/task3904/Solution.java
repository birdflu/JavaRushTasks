package com.javarush.task.task39.task3904;

/*
Лестница
*/

public class Solution {
// трибоначчи
  private static int n = 40;

  public static void main(String[] args) {
    System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
  }


  public static long numberOfPossibleAscents(int n) {
    if (n < 0) return 0;
    if (n == 0) return 1;
    if (n == 1) return 1;
    if (n == 2) return 2;

    long[] a = new long[n + 1];
    a[0] = 1;
    a[1] = 1;
    a[2] = 2;

    for (int i = 3; i <= n; i++) {
      a[i] = a[i - 1] + a[i - 2] + a[i - 3];
    }

    return a[n];
  }
/*  public static long numberOfPossibleAscents(int n) {
    if (n == 0) return 1;
    if (n == 1) return 1;
    if (n == 2) return 2;
    if (n == 3) return 4;

    return numberOfPossibleAscents(n - 1) + numberOfPossibleAscents(n - 2) + numberOfPossibleAscents(n - 3);
  }*/

}

