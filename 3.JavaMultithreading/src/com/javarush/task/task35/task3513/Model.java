package com.javarush.task.task35.task3513;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Model {
  private static final int FIELD_WIDTH = 4;
  private Tile[][] gameTiles;
  protected int score = 0;
  protected int maxTile = 0;

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
            .flatMap(o -> o instanceof Object[] ? flatten((Object[]) o) : Stream.of(o));
  }

  private void compressTiles(Tile[] tiles) {
    int positiveCount = 0;
    for (int i = tiles.length - 1; i >= 0; i--) {
      if (tiles[i].value != 0) positiveCount++;
      if (tiles[i].value == 0 && positiveCount > 0) {
        Tile t = tiles[i + 1];
        tiles[i + 1] = tiles[i];
        tiles[i] = t;
        positiveCount--;
        i = i + 2;
      }
    }
  }

  private void mergeTiles(Tile[] tiles) {
    for (int i = 0; i < tiles.length - 1; i++) {
      if (tiles[i].value == 0) break;
      if (tiles[i].value == tiles[i + 1].value) {
        int newTileValue = tiles[i].value * 2;
        tiles[i].value = newTileValue;
        if (maxTile < newTileValue) maxTile = tiles[i].value;
        score = score + newTileValue;
        for (int j = i + 1; j < tiles.length - 1; j++) {
          tiles[j] = tiles[j + 1];
        }
        tiles[tiles.length - 1] = new Tile();
      }
    }
  }

}
