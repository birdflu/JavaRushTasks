package com.javarush.task.task22.task2213;

public class Field {
  private int width;
  private int height;
  private int[][] matrix;

  public Field(int width, int height) {
    this.width = width;
    this.height = height;
    matrix = new int[height][width];
  }

  public void print(){
    String[] symbols = {".", "X"};
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        System.out.print(symbols[matrix[y][x]]);
      }
      System.out.println();
    }
  }

  // будет удалять из матрицы полностью заполненные строки и сдвигать вышележащие строки вниз;
  public void removeFullLines(){}

  // возвращает значение которое находится в матрице с координатами x и y;
  public Integer getValue(int x, int y){ return null;}

  // устанавливает переданное значение в ячейку массива (матрицы) с координатами x,
  public void setValue(int x, int y, int value){}

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int[][] getMatrix() {
    return matrix;
  }
}
