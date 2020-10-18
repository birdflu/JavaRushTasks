package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public enum Dish {
  Fish(25),
  Steak(30),
  Soup(15),
  Juice(5),
  Water(3);

  private int duration;
  
  public int getDuration() {
    return duration;
  }
  
  Dish(int duration) {
    this.duration = duration;
  }
  
  public static String allDishesToString() {
    return dishesString(Arrays.asList(Dish.values()));
  }
  
  public static String dishesString(List<Dish> dishes) {
    StringJoiner joiner = new StringJoiner(", ");
    for (Dish value : dishes) { joiner.add(value.toString());  }
    return joiner.toString();
  }
}
