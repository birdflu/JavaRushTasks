package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
  private Path levels;

  public LevelLoader(Path levels) {
    this.levels = levels;
  }

  public GameObjects getLevel(int level) {
    int k = Model.FIELD_CELL_SIZE / 2;

    Set<Wall> walls = new HashSet<>();
    walls.add(new Wall(20 * k, 30 * k));
    walls.add(new Wall(40 * k, 30 * k));
    walls.add(new Wall(60 * k, 30 * k));


    Set<Box> boxes = new HashSet<>();
    boxes.add(new Box(20 * k, 50 * k));

    Set<Home> homes = new HashSet<>();
    homes.add(new Home(20 * k, 80 * k));

    Player player = new Player(20 * k, 110 * k);

    return new GameObjects(walls, boxes, homes, player);

  }
}
