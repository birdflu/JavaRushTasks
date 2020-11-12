package com.javarush.task.task39.task3903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Неравноценный обмен
*/

public class Solution {
  final static boolean isDebug = false;

  public static void main(String[] args) throws IOException {

    if (!isDebug) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

      System.out.println("Please enter a number: ");

      long number = Long.parseLong(reader.readLine());
      System.out.println("Please enter the first index: ");
      int i = Integer.parseInt(reader.readLine());
      System.out.println("Please enter the second index: ");
      int j = Integer.parseInt(reader.readLine());

      System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    } else {
      //  53 : 110101
      //  39 : 100111
      System.out.println("The result of swapping bits is " + swapBits(53, 1, 4));

      //  37 : 100101
      //  49 : 110001
      System.out.println("The result of swapping bits is " + swapBits(37, 2, 4));
    }

  }

  public static long swapBits(long number, int i, int j) {
    String numberBinaryString = Long.toBinaryString(number);

    long iBit = (number >> i) & 1;
    long jBit = (number >> j) & 1;

    if (isDebug) {
      System.out.println(i + "th bit = " + iBit);
      System.out.println(j + "th bit = " + jBit);
    }

    if (iBit == jBit) {
      return number;
    } else {
      long modifier = ((long) Math.pow(2, i)) | ((long) Math.pow(2, j));
      long result = number ^ modifier;
      if (isDebug) {
        String mBinaryString = Long.toBinaryString(modifier);
        String resultBinaryString = Long.toBinaryString(result);
        System.out.printf("number %d : %s\n", number, numberBinaryString);
        System.out.printf("modifier %d : %s\n", modifier, mBinaryString);
        System.out.printf("result %d : %s\n", result, resultBinaryString);
      }
      return result;
    }
  }
}
