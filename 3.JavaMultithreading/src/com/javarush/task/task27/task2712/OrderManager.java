package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {
  private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

  public OrderManager() {
    StatisticManager statisticManager = StatisticManager.getInstance();
    Thread daemon = new Thread(() -> {
      while (true) {
        if (!orderQueue.isEmpty()) {
          for (Cook cook : statisticManager.getCooks()) {
            if (!cook.isBusy()) {
              new Thread(() -> {
                if (!orderQueue.isEmpty())
                  cook.startCookingOrder(orderQueue.poll());
              }).start();
            }
          }
          try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        }
      }
    });
    daemon.setDaemon(true);
    daemon.start();
  }

  @Override
  public void update(Observable o, Object arg) {
    orderQueue.add((Order) arg);
  }
}
