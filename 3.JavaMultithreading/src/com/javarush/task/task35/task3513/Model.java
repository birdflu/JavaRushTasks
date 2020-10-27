package com.javarush.task.task35.task3513;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Model {
  private static final int FIELD_WIDTH = 4;
  private Tile[][] gameTiles;

  public Model() {
    resetGameTiles();
  }

  protected void resetGameTiles() {
    gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    for (int i = 0; i < FIELD_WIDTH; i++) {
      for (int j = 0; j < FIELD_WIDTH; j++) {
        gameTiles[i][j] = new Tile();
      }
    }

    addTile();
    addTile();
  }

  private List<Tile> getEmptyTiles() {
    return flatten(gameTiles).map(t -> (Tile) t).filter(Tile::isEmpty).collect(Collectors.toList());

  }

  private void addTile() {
    List<Tile> emptyTiles = getEmptyTiles();
    if (emptyTiles.size() > 0) {
      emptyTiles.get((int) (emptyTiles.size() * Math.random())).setValue(Math.random() < 0.9 ? 2 : 4);
    }
  }

  private static Stream<Object> flatten(Object[] array) {
    return Arrays.stream(array)
            .flatMap(o -> o instanceof Object[]? flatten((Object[])o): Stream.of(o));
  }
}
