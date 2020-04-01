package com.javarush.games.game2048;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Key;

public class Game2048 extends Game {
  private static final int SIDE = 4;
  private  int[][] gameField = new  int[SIDE][SIDE];
  
  @Override
  public void initialize() {
    super.initialize();
    setScreenSize(SIDE, SIDE);
    createGame();
    drawScene();
  }
  
  private void createGame() {
  createNewNumber();
  createNewNumber();
  }

  
  private void drawScene() {
    for (int i = 0; i < gameField.length; i++) {
      for (int j = 0; j < gameField[0].length; j++) {
        setCellColoredNumber(j, i, gameField[i][j]);
      }
    }
  }
  
  private void createNewNumber() {
    while (true) {
      int y = getRandomNumber(SIDE);
      int x = getRandomNumber(SIDE);
      if (gameField[y][x] == 0) {
        gameField[y][x] = getRandomNumber(10) == 9 ? 4: 2;
        break;
      }
  }
  }
  
  private Color getColorByValue(int value) {
    Color[] colors = {Color.GREEN, Color.BLUE, Color.AQUA, Color.BLUEVIOLET,
            Color.CYAN, Color.DARKCYAN, Color.LIGHTCYAN, Color.BROWN,
            Color.SADDLEBROWN, Color.ROSYBROWN, Color.SANDYBROWN, Color.BLACK};
    int indx = value == 0 ? 0 : ((int) (Math.log(value) / Math.log(2)));
    return colors[indx];
  }
  
  private void setCellColoredNumber(int x, int y, int value) {
    setCellValueEx(x, y, getColorByValue(value), value == 0 ? "": String.valueOf(value));
  }
  
  private boolean compressRow(int[] row) {
    boolean isModified = false;
    for (int i = 1; i < row.length; i++) {
      for (int j = 0; j < i; j++) {
        if (row[j] == 0 && row[i] > 0) {
          row[j] = row[i];
          row[i] = 0;
          isModified = true;
        }
      }
    }
  return isModified;
  }
  
  private boolean mergeRow(int[] row) {
    boolean isModified = false;
    for (int i = 1; i < row.length; i++) {
      if (row[i-1] == row [i] && row[i] > 0) {
        row[i-1] = 2*row[i];
        row[i] = 0;
        isModified = true;
      }
    }
    return isModified;
  }
  
  @Override
  public void onKeyPress(Key key) {
    super.onKeyPress(key);
  
    if (key == Key.LEFT) {
      moveLeft();
      drawScene();
    } else if (key == Key.RIGHT) {
      moveRight();
      drawScene();
    } else if (key == Key.UP) {
      moveUp();
      drawScene();
    } else if (key == Key.DOWN) {
      moveDown();
      drawScene();
    }
  }
  
  private void moveDown() {
  }
  
  private void moveUp() {
  }
  
  private void moveRight() {
  }
  
  private void moveLeft() {
    int counter = 0;
    for (int i = 0; i < gameField.length; i++) {
      if (compressRow(gameField[i]) | mergeRow(gameField[i]) | compressRow(gameField[i]))
        counter++;
    }
    if (counter > 0) createNewNumber();
    
    }
    
    private void rotateClockwise() {
    int[][] gameFieldRotate = new int[SIDE][SIDE];;
      for (int i = 0; i < SIDE; i++){
        for (int j = 0; j < SIDE; j++){
          gameFieldRotate[i][j] = gameField[SIDE- j - 1][i];
        }
      }
      gameField = gameFieldRotate;
    }
  

}
