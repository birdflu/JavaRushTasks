package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {
  public static final int WIDTH = 64;
  public static final int HEIGHT = 64;
  private Rocket rocket;
  private GameObject landscape;
  
  @Override
  public void initialize() {
    setScreenSize(WIDTH, HEIGHT);
    createGame();
    showGrid(false);
  }
  
  private void createGame() {
    createGameObjects();
    setTurnTimer(50);
    drawScene();
  }
  
  private void drawScene() {
    for (int x = 0; x < WIDTH; x++) {
      for (int y = 0; y < HEIGHT; y++) {
        setCellColor(x, y, Color.BLACK);
      }
    }
    landscape.draw(this);
    rocket.draw(this);
    
  }
  
  private void createGameObjects() {
    landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);
    rocket  = new Rocket(WIDTH/2, 0);
  }
  
  @Override
  public void onTurn(int step) {
    rocket.move();
    drawScene();
  }
  
  @Override
  public void setCellColor(int x, int y, Color color) {
    if (x > 0 && y > 0 && x < WIDTH && y < HEIGHT)
      super.setCellColor(x, y, color);
  }
}