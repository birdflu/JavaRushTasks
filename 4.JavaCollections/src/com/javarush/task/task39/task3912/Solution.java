package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
  public static void main(String[] args) {
/*
    int[][] matrix = {{1, 0, 0, 1, 0, 0},
            {1, 0, 0, 1, 1, 1},
            {0, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 1},
            {1, 1, 0, 0, 1, 0}};
*/
        int[][] matrix = new int[15][20];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = (int) (Math.random() + 0.7);
            }

    String dataView = Arrays
            .stream(matrix)
            .map(Arrays::toString)
            .collect(Collectors.joining(System.lineSeparator()));
    System.out.println(dataView);

    System.out.println("maxSquare(matrix) = " + maxSquare(matrix));

  }

  public static int maxSquare(int[][] matrix) {
    int maxDimension = 0;
    for (int i = 0; i < matrix.length; i++)
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 1) {
          int dimension = getDimension(matrix, i, j);
//          System.out.printf("i=%d, j=%d, square =%d\n", i, j, dimension);
          j = j + dimension - 1;
          maxDimension = Math.max(dimension, maxDimension);
        }
      }

    return maxDimension*maxDimension;
  }

  private static int getDimension(int[][] matrix, int i, int j) {
    int dimension = 1;
    while ((i + dimension) < matrix.length && (j + dimension < matrix[0].length)) {

      for (int k = i; k < i + dimension + 1; k++) {
        if (matrix[k][j + dimension] == 0) return dimension;
      }

      for (int k = j; k < j + dimension + 1; k++) {
        if (matrix[i + dimension][k] == 0) return dimension;
      }

      dimension++;
    }

    return dimension;
  }
}
