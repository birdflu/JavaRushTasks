package com.javarush.task.task39.task3909;

/*
Одно изменение
*/

public class Solution {
  public static void main(String[] args) {
    System.out.println(isOneEditAway("abcef", "abcdef"));
  }

  public static boolean isOneEditAway(String first, String second) {
    char[] f = first.toCharArray();
    char[] s = second.toCharArray();

    if (f.length == s.length) {
      int diff = 0;
      for (int i = 0; i < f.length; i++) {
        if (f[i] != s[i]) {
          diff++;
          if (diff > 1) return false;
        }
      }
      return true;
    }

    if (f.length - s.length == 1) {
      int errorCount = 0;
      for (int i = 0; i < s.length; i++) {
        if (f[i+errorCount] != s[i]) {
          errorCount++;
          i--;
          if (errorCount > 1) {
            return false;
          }
        }
      }
      return true;
    } if (f.length - s.length > 1) {
      return false;
    } else {
      return isOneEditAway(second, first);
    }
  }
}
