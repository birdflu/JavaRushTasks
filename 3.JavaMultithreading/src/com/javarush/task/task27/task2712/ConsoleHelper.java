package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
  private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  
  public static void writeMessage(String message) {
    System.out.println(message);
  }
  
  public static String readString() throws IOException {
    return reader.readLine();
  }
  
  public static List<Dish> getAllDishesForOrder() throws IOException {
    List<Dish> order = new ArrayList<>();
    writeMessage("Menu: " + Dish.allDishesToString() + ".");
    writeMessage("Enter the dish name or 'exit' to complete:");
    String line = readString();
    while (!line.equals("exit")) {
      try { order.add(Dish.valueOf(line)); }
      catch (IllegalArgumentException e){
        writeMessage("The dish doesn't exist! Enter an existing meal or 'exit', please:");
      }
      line = readString();
    }
    reader.close();
    return order;
  }
  

}