package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
  private final static int ORDER_CREATING_INTERVAL = 100;
  public static void main(String[] args) {

    Cook cook1 = new Cook("Amigo1");
    Cook cook2 = new Cook("Amigo2");

    OrderManager orderManager = new OrderManager();

    StatisticManager.getInstance().register(cook1);
    StatisticManager.getInstance().register(cook2);

    Waiter waiter = new Waiter();

    cook1.addObserver(waiter);
    cook2.addObserver(waiter);

    List<Tablet> tablets = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      Tablet tablet = new Tablet(i);
      tablet.addObserver(orderManager);
      tablets.add(tablet);
    }

    Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
    thread.start();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    thread.interrupt();

    DirectorTablet directorTablet = new DirectorTablet();
    directorTablet.printAdvertisementProfit();
    directorTablet.printCookWorkloading();
    directorTablet.printActiveVideoSet();
    directorTablet.printArchivedVideoSet();

  }
}
