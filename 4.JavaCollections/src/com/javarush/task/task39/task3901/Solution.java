package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Please enter your string: ");
    String s = bufferedReader.readLine();
//    String s = "abcdeaouiz";  // 9
    System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
  }

  public static int lengthOfLongestUniqueSubstring(String s) {
    if (s == null || s.isEmpty()) return 0;
    int maxLength = 0;

    for (int substringStartIndex = 0; substringStartIndex < s.length(); substringStartIndex++) {
      Set<Character> set = new HashSet<>();
      for (int i = substringStartIndex; i < s.length(); i++) {
        char ch = s.charAt(i);
        if (set.contains(ch)) {
          break;
        } else {
          set.add(ch);
        }

      }
      maxLength = set.size() > maxLength ? set.size() : maxLength;
    }

    return maxLength;
  }
}
