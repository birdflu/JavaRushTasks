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
  
  
}