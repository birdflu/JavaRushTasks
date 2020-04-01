package com.javarush.games.game2048;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Key;

public class Game2048 extends Game {
  private static final int SIDE = 4;
  private  int[][] gameField;
  private boolean isGameStopped = false;
  
  @Override
  public void initialize() {
    super.initialize();
    setScreenSize(SIDE, SIDE);
    createGame();
    drawScene();
  }
  
  private void createGame() {
    gameField = new int[SIDE][SIDE];
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
    if (getMaxTileValue() == 2048) win();
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
    if(key == Key.SPACE){
      isGameStopped = false;
      createGame();
      drawScene();
      return;
    }
    
    if(!isGameStopped){
      if(!canUserMove()){
        gameOver();
        return;
      }
      if(key == Key.LEFT){
        moveLeft();
        drawScene();
      }
      else if(key == Key.RIGHT){
        moveRight();
        drawScene();
      }
      else if(key == Key.UP){
        moveUp();
        drawScene();
      }
      else if(key == Key.DOWN){
        moveDown();
        drawScene();
      }
    }
  }
  
  private void moveDown() {
    rotateClockwise();
    moveLeft();
    rotateClockwise();
    rotateClockwise();
    rotateClockwise();
  }
  
  private void moveUp() {
    rotateClockwise();
    rotateClockwise();
    rotateClockwise();
    moveLeft();
    rotateClockwise();
  }
  
  private void moveRight() {
    rotateClockwise();
    rotateClockwise();
    moveLeft();
    rotateClockwise();
    rotateClockwise();
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
    
    private int getMaxTileValue() {
      int maxTileValue = 0;
      for (int i = 0; i < SIDE; i++)
        for (int j = 0; j < SIDE; j++)
          if (gameField[i][j] > maxTileValue) maxTileValue = gameField[i][j];
      return maxTileValue;
  }
  
  private void win() {
    isGameStopped = true;
    showMessageDialog(Color.DARKORANGE, "You win!", Color.BLACK, 16);
  }
  
  private void gameOver() {
    isGameStopped = true;
    showMessageDialog(Color.DARKORANGE, "You loose!", Color.BLACK, 16);
  }
  
  private boolean canUserMove() {
    for (int i = 0; i < SIDE; i++)
      for (int j = 0; j < SIDE; j++)
        if (gameField[i][j] == 0 || isNeighborTwin(i,j))
          return true;
    return false;
  }
  
  private boolean isNeighborTwin (int y, int x) {
    int field = gameField[y][x];
    int right = (x == SIDE-1)? -1: gameField[y][x+1];
    int left = (x == 0)? -1: gameField[y][x-1];
    int down = (y == SIDE-1)? -1: gameField[y+1][x];
    int up = (y == 0)? -1: gameField[y-1][x];
    if (field == right || field == left || field == down || field == up)
      return true;
    return false;
  }
  
}
