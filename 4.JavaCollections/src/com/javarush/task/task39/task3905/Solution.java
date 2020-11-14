package com.javarush.task.task39.task3905;

/*
Залей меня полностью
*/

import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
  public static void main(String[] args) {
    Color[][] matrix =
            {{Color.INDIGO, Color.ORANGE, Color.ORANGE, Color.INDIGO, Color.ORANGE, Color.ORANGE},
             {Color.INDIGO, Color.ORANGE, Color.ORANGE, Color.INDIGO, Color.INDIGO, Color.INDIGO},
             {Color.ORANGE, Color.INDIGO, Color.INDIGO, Color.INDIGO, Color.INDIGO, Color.INDIGO},
             {Color.ORANGE, Color.ORANGE, Color.INDIGO, Color.INDIGO, Color.INDIGO, Color.INDIGO},
             {Color.INDIGO, Color.INDIGO, Color.ORANGE, Color.ORANGE, Color.INDIGO, Color.ORANGE}};

    boolean result = new PhotoPaint().paintFill(matrix, 3, 1, Color.YELLOW);

    String dataView = Arrays
            .stream(matrix)
            .map(Arrays::toString)
            .collect(Collectors.joining(System.lineSeparator()));

    System.out.println(dataView);
    System.out.println("result = " + result);
  }


}
