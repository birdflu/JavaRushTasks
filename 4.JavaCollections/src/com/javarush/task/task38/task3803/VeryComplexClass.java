package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
  public void methodThrowsClassCastException() {
    String s = "s";
    Object o = s;
    Integer a = (Integer) (o);
  }
  
  public void methodThrowsNullPointerException() {
    Integer i = null;
    int j = i;
    
  }
  
  public static void main(String[] args) {
    // new VeryComplexClass().methodThrowsClassCastException();
    // new VeryComplexClass().methodThrowsNullPointerException();
  }
}
