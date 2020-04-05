package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
  public static final int WIDTH = 15;
  public static final int HEIGHT = 15;
  private static final int GOAL = 28;
  private Snake snake;
  private int turnDelay;
  private Apple apple;
  private boolean isGameStopped;
  private int score;

  @Override
  public void initialize() {
    super.initialize();
    setScreenSize(WIDTH, HEIGHT);
    createGame();
  }


  private void createGame() {
    this.snake = new Snake(WIDTH / 2, HEIGHT / 2);
    this.turnDelay = 300;
    this.isGameStopped = false;
    this.score = 0;
    setTurnTimer(turnDelay);
    createNewApple();
    setScore(score);
    drawScene();
  }

  private void drawScene() {
    for (int x = 0; x < WIDTH; x++) {
      for (int y = 0; y < HEIGHT; y++) {
        setCellValueEx(x, y, Color.DARKSEAGREEN, "");
      }
    }
    this.snake.draw(this);
    this.apple.draw(this);
  }

  private void createNewApple() {
    while (true) {
    Apple apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
    if (!snake.checkCollision(apple)){
      this.apple = apple;
      return;
    }
    }
  }

  private void gameOver() {
    stopTurnTimer();
    isGameStopped = true;
    showMessageDialog(Color.RED, "GAME OVER", Color.BLACK, 15);
  }

  private void win() {
    stopTurnTimer();
    isGameStopped = true;
    showMessageDialog(Color.RED, "YOU WIN", Color.BLACK, 15);
  }

  @Override
  public void onTurn(int step) {
    this.snake.move(this.apple);
    if (!apple.isAlive) {
      score = score + 5;
      setScore(score);
      turnDelay = turnDelay - 10;
      setTurnTimer(turnDelay);
      createNewApple();
    }
    if (!snake.isAlive) gameOver();
    if (snake.getLength() > GOAL) win();
    drawScene();
  }

  @Override
  public void onKeyPress(Key key) {
    if (key == Key.LEFT) snake.setDirection(Direction.LEFT);
    if (key == Key.RIGHT) snake.setDirection(Direction.RIGHT);
    if (key == Key.UP) snake.setDirection(Direction.UP);
    if (key == Key.DOWN) snake.setDirection(Direction.DOWN);
    if (key == Key.SPACE && isGameStopped == true) {
      createGame();
    }
  }
}
