package com.javarush.task.task27.task2712.kitchen;


import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
  private String name;
  private boolean busy;
  private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();
  
  public Cook(String name) { this.name = name; }
  
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

  public void setQueue(LinkedBlockingQueue<Order> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      while (!Thread.currentThread().isInterrupted()) {
        while (queue.isEmpty()) {
          Thread.sleep(10);
        }
        Order order = queue.poll();
        if (order!=null) {
          startCookingOrder(order);
        }
      }
    } catch (InterruptedException e) {}
  }
}
