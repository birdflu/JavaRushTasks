package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake extends GameObject {
  private static final String HEAD_SIGN = "\uD83D\uDC7E";
  private static final String BODY_SIGN = "\u26AB";
  private List<GameObject> snakeParts = new ArrayList<>();
  private Direction direction = Direction.LEFT;
  public boolean isAlive = true;

  public void draw(Game game) {
    game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, Color.NONE, HEAD_SIGN, (isAlive ? Color.BLACK : Color.RED), 75);
    for (int i = 1; i < snakeParts.size(); i++) {
      game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, (isAlive ? Color.BLACK : Color.RED), 75);
    }

  }

  public Snake(int x, int y) {
    super(x, y);
    snakeParts.add(new GameObject(x, y));
    snakeParts.add(new GameObject(x + 1, y));
    snakeParts.add(new GameObject(x + 2, y));
  }

  public void setDirection(Direction direction) {
    if (((direction == Direction.LEFT || direction == Direction.RIGHT)
            && (this.direction == Direction.UP || this.direction == Direction.DOWN))
            || ((direction == Direction.UP || direction == Direction.DOWN)
            && (this.direction == Direction.LEFT || this.direction == Direction.RIGHT))) {

      switch (this.direction) {
        case LEFT:
        case RIGHT:
          if (snakeParts.get(0).x == snakeParts.get(1).x) return;
          break;
        case UP:
        case DOWN:
          if (snakeParts.get(0).y == snakeParts.get(1).y) return;
          break;
      }
      this.direction = direction;
    }
  }

  public void move(Apple apple) {
    GameObject newHead = createNewHead();
    if (newHead.x < 0 || newHead.x >= SnakeGame.WIDTH
            || newHead.y < 0 || newHead.y >= SnakeGame.HEIGHT) {
      this.isAlive = false;
    } else if (newHead.x == apple.x && newHead.y == apple.y) {
      apple.isAlive = false;
      if (checkCollision(newHead)) {
        this.isAlive = false;
        return;
      }
      snakeParts.add(0, newHead);
    } else {
      if (checkCollision(newHead)) {
        this.isAlive = false;
        return;
      }
      snakeParts.add(0, newHead);
      removeTail();
    }
  }

  public GameObject createNewHead() {
    int headX = snakeParts.get(0).x;
    int headY = snakeParts.get(0).y;

    if (this.direction == Direction.UP) return new GameObject(headX, headY - 1);
    if (this.direction == Direction.LEFT) return new GameObject(headX - 1, headY);
    if (this.direction == Direction.DOWN) return new GameObject(headX, headY + 1);
    if (this.direction == Direction.RIGHT) return new GameObject(headX + 1, headY);
    return new GameObject(0, 0);
  }

  public void removeTail() {
    this.snakeParts.remove(this.snakeParts.size() - 1);
  }

  public boolean checkCollision(GameObject newHead) {
    for (int i = 0; i < snakeParts.size(); i++) {
      if (snakeParts.get(i).x == newHead.x && snakeParts.get(i).y == newHead.y)
        return true;
    }
    return false;
  }

  public int getLength() {
    return snakeParts.size();
  }
}
