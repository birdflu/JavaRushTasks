package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Парсер реквестов
*/

public class Solution {
  public static void main(String[] args) throws IOException {
    //add your code here
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String str = reader.readLine();
    String out = "";
    String objvalue = "";
    String[] strArray = str.split("\\?");
    strArray = strArray[1].split("\\&");

    for (String s : strArray
    ) {
      String[] subS = s.split("=");
      if ("obj".equals(subS[0])) objvalue = subS[1];
      if (out.length() > 0) out = out + " " + subS[0];
      else out = subS[0];
    }

    System.out.println(out);
    if (objvalue.length() > 0)
      try {
        alert(Double.parseDouble(objvalue));
      } catch (NumberFormatException e) {
        alert(objvalue);
      }

  }

  public static void alert(double value) {
    System.out.println("double: " + value);
  }

  public static void alert(String value) {
    System.out.println("String: " + value);
  }
}
