package com.javarush.task.task27.task2712.kitchen;

import java.util.StringJoiner;

public enum Dish {
  Fish,
  Steak,
  Soup,
  Juice,
  Water;
  
  public static String allDishesToString() {
    StringJoiner dishes = new StringJoiner(", ");
    for (Dish value : Dish.values()) { dishes.add(value.toString());  }
    return dishes.toString();
  }
}
