package com.javarush.task.task27.task2712.kitchen;


import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;

public class Cook extends Observable  {
  private String name;
  private boolean busy;
  
  public Cook(String name) {
    this.name = name;
  }
  
  @Override
  public String toString() {
    return name;
  }
  
  public void startCookingOrder(Order order) {
    busy = true;
    ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
    StatisticManager.getInstance().register(new CookedOrderEventDataRow(
            order.toString(),
            this.name,
            order.getTotalCookingTime()*60,
            order.getDishes()));

    try {
      Thread.sleep(order.getTotalCookingTime() * 10);
    } catch (InterruptedException e) {ConsoleHelper.writeMessage(e.getMessage());}

    setChanged();
    notifyObservers(order);
    busy = false;
  }

  public boolean isBusy() {
    return busy;
  }
}
