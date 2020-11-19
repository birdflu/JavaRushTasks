package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Model {
  public static final int FIELD_CELL_SIZE = 20;
  private EventListener eventListener;
  private GameObjects gameObjects;
  private int currentLevel = 1;
  private LevelLoader levelLoader = new LevelLoader(Paths.get(this.getClass().getPackage().getName().replace(".model", ".res.levels.txt")));

  public void setEventListener(EventListener eventListener) {
    this.eventListener = eventListener;
  }

  public GameObjects getGameObjects() {
    return gameObjects;
  }

  public void restartLevel(int level) {
    gameObjects = levelLoader.getLevel(level);
  }

  public void restart() {
    restartLevel(currentLevel);
  }

  public void startNextLevel() {
    currentLevel++;
    restartLevel(currentLevel);
  }

  public void move(Direction direction) {
    Player player = gameObjects.getPlayer();
    if (checkWallCollision(player, direction) || checkBoxCollisionAndMoveIfAvailable(direction))
      return;

    move(player, direction);

    checkCompletion();
  }

  private void move(Movable movable, Direction direction) {
    switch (direction) {
      case UP:
        movable.move(0, -Model.FIELD_CELL_SIZE);
        break;
      case DOWN:
        movable.move(0, Model.FIELD_CELL_SIZE);
        break;
      case RIGHT:
        movable.move(Model.FIELD_CELL_SIZE, 0);
        break;
      case LEFT:
        movable.move(-Model.FIELD_CELL_SIZE, 0);
        break;
    }
  }

  public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
    for (Wall wall : gameObjects.getWalls()) {
      if (gameObject.isCollision(wall, direction)) return true;
    }
    return false;
  }

  public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
    Player player = gameObjects.getPlayer();
    for (Box box : gameObjects.getBoxes())
      if (player.isCollision(box, direction)) {
        if (checkWallCollision(box, direction))
          return true;

        Set<Box> set = new HashSet<>(gameObjects.getBoxes());
        set.remove(box);

        for (Box boxs : set) {
          if (box.isCollision(boxs, direction))
            return true;
        }

        move(box, direction);
        break;
      }

    return false;
  }

  public void checkCompletion() {
    boolean flag = false;
    Set<Home> homes = gameObjects.getHomes();
    for (Box box : gameObjects.getBoxes()) {
      flag = false;
      for (Home home : homes) {
        if (box.getX() == home.getX() && box.getY() == home.getY()) {
          flag = true;
          break;
        }
      }
      if (!flag)
        break;
    }

    if (flag)
      eventListener.levelCompleted(currentLevel);
  }

}


