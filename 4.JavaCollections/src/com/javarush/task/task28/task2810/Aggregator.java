package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.model.Strategy;

public class Aggregator {
  public static void main(String[] args) {
    Strategy hhStrategy = new HHStrategy();
    Provider provider = new Provider(hhStrategy);
    Controller controller = new Controller(provider);
    controller.scan();

  }
}
