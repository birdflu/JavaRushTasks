package com.javarush.task.task34.task3410.model;

import java.awt.*;

public abstract class CollisionObject extends GameObject {
  public CollisionObject(int x, int y) {
    super(x, y);
  }

  public boolean isCollision(GameObject gameObject, Direction direction) {
    final int cellSize = Model.FIELD_CELL_SIZE;
    int x = gameObject.getX();
    int y = gameObject.getY();
    // break - для валидатора

    switch (direction) {
      case UP:
        if (y == this.getY() - cellSize && this.getX() == x) return true; break;
      case DOWN:
        if (y == this.getY() + cellSize && this.getX() == x) return true; break;
      case RIGHT:
        if (x == this.getX() + cellSize && this.getY() == y) return true; break;
      case LEFT:
        if (x == this.getX() - cellSize && this.getY() == y) return true; break;
    }

    return false;
  }

  @Override
  public void draw(Graphics graphics) {

  }

}
