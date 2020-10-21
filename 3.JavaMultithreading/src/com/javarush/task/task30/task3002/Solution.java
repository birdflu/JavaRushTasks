package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/
public class Solution {

  public static void main(String[] args) {
    System.out.println(convertToDecimalSystem("0x16")); //22
    System.out.println(convertToDecimalSystem("012"));  //10
    System.out.println(convertToDecimalSystem("0b10")); //2
    System.out.println(convertToDecimalSystem("62"));   //62
  }

  public static String convertToDecimalSystem(String s) {

    //напишите тут ваш код
    return String.valueOf(Integer.parseInt(getNumber(s, getNumberSystem(s)), getNumberSystem(s)));
  }

  private static int getNumberSystem(String s) {
    StringBuilder str = new StringBuilder(s);

    if (str.charAt(0) == '0') {
      switch (str.charAt(1)) {
        case 'x':
          return 16;
        case 'b':
          return 2;
        default:
          return 8;
      }
    } else {
      return 10;
    }
  }

  private static String getNumber(String s, int numberSystem) {
    switch (numberSystem) {
      case 2:
      case 16:
        return s.substring(2);
      case 8:
        return s.substring(1);
      case 10:
        return s;
      default:
        return s;
    }
  }
}
