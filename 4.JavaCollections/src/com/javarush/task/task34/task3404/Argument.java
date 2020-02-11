package com.javarush.task.task34.task3404;

public class Argument {
  private boolean isSign;
  private boolean isBraces;
  private StringBuilder value;

  public Argument(boolean isSign, boolean isBraces, StringBuilder value) {
    this.isSign = isSign;
    this.isBraces = isBraces;
    this.value = value;
  }

  public boolean isBraces() {
    return isBraces;
  }

  public StringBuilder getValue() {
    return value;
  }

  public double getDouble() {
    return Double.parseDouble(value.toString());
  }

  @Override
  public String toString() {
    return "Argument{" +
            "isSign=" + isSign +
            ", isBraces=" + isBraces +
            ", value=" + value +
            '}';
  }
}