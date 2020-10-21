package com.javarush.task.task30.task3010;

import java.math.BigInteger;

public class Solution {
  public static void main(String[] args) {
    //напишите тут ваш код
    try {
      System.out.println(getRadix(args[0], 2));
    } catch (Exception e) {
    }
//    System.out.println(getRadix("00", 2));
//    System.out.println(getRadix("12AS08z", 2));
//    System.out.println(getRadix("12AS08Z/", 2));
  }

  private static String getRadix(String number, int i) {
    if (1 < i && i < 37) {
      try {
        new BigInteger(number, i);
        return String.valueOf(i);
      } catch (Exception e) {
        return getRadix(number, i + 1);
      }
    } else return "incorrect";
  }
}