package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {
  
  public static void main(String[] args) {
  //  System.out.println(Arrays.toString(sort(new Integer[]{13, 8, 15, 5, 17})));
  }
  
  public static Integer[] sort(Integer[] array) {
    //implement logic here
    Arrays.sort(array);
    double median;
    if (array.length % 2 == 0)
      median = ((double) array[array.length / 2] + (double) array[array.length / 2 - 1]) / 2;
    else
      median = (double) array[array.length / 2];
    
    Comparator<Integer> compareByMediana = new Comparator<Integer>() {
      public int compare(Integer o1, Integer o2) {
        return Integer.compare(Math.abs(o1 - (int) median), Math.abs(o2 - (int) median));
      }
    };
    
    Arrays.sort(array, compareByMediana);
    return array;
  }
  
}
