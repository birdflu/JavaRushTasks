package com.javarush.task.task39.task3905;

public class PhotoPaint {
  public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
    if (y < 0 || y >= image.length || x < 0 || x >= image[0].length || image[y][x] == desiredColor) {
      return false;
    } else {
      Color color = image[y][x];
      image[y][x] = desiredColor;
      if (y + 1 < image.length && image[y + 1][x] == color) {
        paintFill(image, x, y + 1, desiredColor);
      }
      if (x + 1 < image[0].length && image[y][x + 1] == color) {
        paintFill(image, x + 1, y, desiredColor);
      }

      if (y > 0 && image[y - 1][x] == color) {
        paintFill(image, x, y - 1, desiredColor);
      }
      if (x > 0 && image[y][x - 1] == color) {
        paintFill(image, x - 1, y, desiredColor);
      }
      return true;
    }
  }

}
