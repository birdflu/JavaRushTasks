package com.javarush.task.task35.task3513;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Model {
  private static final int FIELD_WIDTH = 4;
  private Tile[][] gameTiles;
  protected int score = 0;
  protected int maxTile = 0;
  private Stack<Tile[][]> previousStates = new Stack();
  private Stack<Integer> previousScores = new Stack();
  private boolean isSaveNeeded = true;

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

  private boolean compressTiles(Tile[] tiles) {
    int positiveCount = 0;
    boolean result = false;
    for (int i = tiles.length - 1; i >= 0; i--) {
      if (tiles[i].value != 0) positiveCount++;
      if (tiles[i].value == 0 && positiveCount > 0) {
        Tile t = tiles[i + 1];
        tiles[i + 1] = tiles[i];
        tiles[i] = t;
        result = true;
        positiveCount--;
        i = i + 2;
      }
    }
    return result;
  }

  private boolean mergeTiles(Tile[] tiles) {
    boolean result = false;
    for (int i = 0; i < tiles.length - 1; i++) {
      if (tiles[i].value == 0) break;
      if (tiles[i].value == tiles[i + 1].value) {
        int newTileValue = tiles[i].value * 2;
        tiles[i].value = newTileValue;
        for (int j = i + 1; j < tiles.length - 1; j++) {
          tiles[j] = tiles[j + 1];
        }
        tiles[tiles.length - 1] = new Tile();

        if (maxTile < newTileValue) maxTile = tiles[i].value;
        score = score + newTileValue;
        result = true;
      }
    }
    return result;
  }

  private void rotateClockwise() {
    Tile[][] gameTilesRotate = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    for (int i = 0; i < FIELD_WIDTH; i++) {
      for (int j = 0; j < FIELD_WIDTH; j++) {
        gameTilesRotate[i][j] = gameTiles[FIELD_WIDTH - j - 1][i];
      }
    }
    gameTiles = gameTilesRotate;
  }

  protected void left() {
    boolean isAddTile = false;
    for (int i = 0; i < gameTiles.length; i++) {
      if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
        isAddTile = true;
      }
    }
    if (isAddTile) addTile();
  }

  protected void down() {
    rotateClockwise();
    left();
    rotateClockwise();
    rotateClockwise();
    rotateClockwise();
  }

  protected void up() {
    rotateClockwise();
    rotateClockwise();
    rotateClockwise();
    left();
    rotateClockwise();
  }

  protected void right() {
    rotateClockwise();
    rotateClockwise();
    left();
    rotateClockwise();
    rotateClockwise();
  }

  protected boolean canMove() {
    for (int i = 0; i < FIELD_WIDTH; i++)
      for (int j = 0; j < FIELD_WIDTH; j++)
        if (gameTiles[i][j].isEmpty() || isNeighborTwin(i, j))
          return true;
    return false;
  }

  private boolean isNeighborTwin(int y, int x) {
    int field = gameTiles[y][x].value;
    int right = (x == FIELD_WIDTH - 1) ? -1 : gameTiles[y][x + 1].value;
    int left = (x == 0) ? -1 : gameTiles[y][x - 1].value;
    int down = (y == FIELD_WIDTH - 1) ? -1 : gameTiles[y + 1][x].value;
    int up = (y == 0) ? -1 : gameTiles[y - 1][x].value;
    if (field == right || field == left || field == down || field == up)
      return true;
    return false;
  }

  private void saveState(Tile[][] tiles) {
    Tile[][] previousTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

    for (int i = 0; i < FIELD_WIDTH; i++) {
      for (int j = 0; j < FIELD_WIDTH; j++) {
        previousTiles[i][j] = tiles[i][j];
      }
    }

    previousStates.push(previousTiles);
    previousScores.push(score);
    isSaveNeeded = false;
  }

  public void rollback() {
    if (!previousStates.empty() && !previousScores.empty()) {
      gameTiles = previousStates.pop();
      score = previousScores.pop();
    }
  }

  public Tile[][] getGameTiles() {
    return gameTiles;
  }

  @Override
  public String toString() {
    return "Model{" +
            "gameTiles=\n" + Arrays.stream(gameTiles[0]).map(t -> t.value).collect(Collectors.toList()) +
            "\n" + Arrays.stream(gameTiles[1]).map(t -> t.value).collect(Collectors.toList()) +
            "\n" + Arrays.stream(gameTiles[2]).map(t -> t.value).collect(Collectors.toList()) +
            "\n" + Arrays.stream(gameTiles[3]).map(t -> t.value).collect(Collectors.toList()) +
            ", score=" + score +
            ", maxTile=" + maxTile +
            '}';
  }
}
