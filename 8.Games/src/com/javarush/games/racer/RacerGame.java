package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class RacerGame extends Game {
  public static final int WIDTH = 64;
  public static final int HEIGHT = 64;
  public static final int CENTER_X = WIDTH / 2;
  public static final int ROADSIDE_WIDTH = 14;
  private RoadMarking roadMarking;
  private PlayerCar player;
  
  @Override
  public void initialize() {
    setScreenSize(WIDTH, HEIGHT);
    showGrid(false);
    createGame();
  }
  
  private void createGame() {
    roadMarking = new RoadMarking();
    player = new PlayerCar();
    drawScene();
    setTurnTimer(40);
  }
  
  private void drawScene() {
    drawField();
    roadMarking.draw(this);
    player.draw(this);
  }
  
  
  private void drawField() {
    for (int i = 0; i < WIDTH ; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        if (i == CENTER_X) {
          setCellColor(i, j, Color.WHITE);
        } else if(i >= ROADSIDE_WIDTH && i < (WIDTH - ROADSIDE_WIDTH)) {
          setCellColor(i, j, Color.DIMGREY);
        } else {
          setCellColor(i, j, Color.GREEN);
        }
      }
    }
  }
  
  @Override
  public void setCellColor(int x, int y, Color color) {
    if (x >= WIDTH || x < 0 || y >= HEIGHT || y < 0) {
      return;
    } else {
      super.setCellColor(x, y, color);
    }
  }
  
  private void moveAll() {
    roadMarking.move(player.speed);
    player.move();
  }
  
  @Override
  public void onTurn(int step) {
    moveAll();
    drawScene();
  }
  
  @Override
  public void onKeyPress(Key key) {
    if (key == Key.RIGHT) player.setDirection(Direction.RIGHT);
    if (key == Key.LEFT) player.setDirection(Direction.LEFT);
  }
  
  @Override
  public void onKeyReleased(Key key) {
    if (key == Key.RIGHT && player.getDirection() == Direction.RIGHT ||
            key == Key.LEFT && player.getDirection() == Direction.LEFT)
      player.setDirection(Direction.NONE);
    
  }
}

