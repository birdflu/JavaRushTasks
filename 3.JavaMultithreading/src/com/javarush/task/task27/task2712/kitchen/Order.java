package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
  private final Tablet tablet;
  protected List<Dish> dishes;
  
  public Order(Tablet tablet) throws IOException {
    this.tablet = tablet;
    dishes = ConsoleHelper.getAllDishesForOrder();

//    dishes = List.of(Dish.Soup);                          // order 1, duration 2: {v1, v2}
//    dishes = List.of(Dish.Soup, Dish.Steak);              // order 2, duration 3: {v8}
//    dishes = List.of(Dish.Water, Dish.Juice, Dish.Fish);  // order 3, duration 8: {v1, v2, v7, v8}
//    dishes = new ArrayList() {{ add(Dish.Water); add(Dish.Juice); add(Dish.Fish); }};

  }
  
  public int getTotalCookingTime() {
    return dishes.stream().map(Dish::getDuration).reduce(Integer::sum).orElse(0);
  }
  
  public boolean isEmpty() {
    return dishes.isEmpty();
  }
  
  @Override
  public String toString() {
    return "Your order: [" +
            Dish.dishesString(this.dishes) +
            "] of " + tablet.toString();
  }

  public List<Dish> getDishes() {
    return dishes;
  }

  public Tablet getTablet() {
    return tablet;
  }
}
