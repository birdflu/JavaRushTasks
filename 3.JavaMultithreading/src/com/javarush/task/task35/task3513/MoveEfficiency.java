package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency>{
/*
  1) Первый ход является лучше второго, если после его совершения на поле находится
  больше пустых плиток, чем в результате второго хода.

  2) Первый ход является лучше второго, если общий счет после его совершения больше, чем счет
  полученный в результате второго хода.
*/


  private int numberOfEmptyTiles;
  private int score;
  private Move move;

  public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
    this.numberOfEmptyTiles = numberOfEmptyTiles;
    this.score = score;
    this.move = move;
  }

  public Move getMove() {
    return move;
  }

  @Override
  public int compareTo(MoveEfficiency o) {
    return numberOfEmptyTiles == o.numberOfEmptyTiles
            ? Integer.compare(score, o.score)
            : Integer.compare(numberOfEmptyTiles, o.numberOfEmptyTiles);
  }
}
