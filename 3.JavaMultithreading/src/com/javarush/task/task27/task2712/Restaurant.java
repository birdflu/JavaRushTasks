package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
  private final static int ORDER_CREATING_INTERVAL = 100;
  public static void main(String[] args) {
//    Tablet tablet = new Tablet(5);
//    Cook cook = new Cook("Amigo");
//    Waiter waiter = new Waiter();
//    tablet.addObserver(cook);
//    cook.addObserver(waiter);
//    tablet.createOrder();
    List<Tablet> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(new Tablet(i));
    }
    Cook[] cooks = new Cook[2];
    Waiter waiter = new Waiter();
    for (int i = 0; i < 2; i++) {
      cooks[i] = new Cook("cook №" + i);
      cooks[i].addObserver(waiter);
    }
    for (int i = 0; i < 10; i++) {
      list.get(i).addObserver(cooks[i % 2]);
    }
    Thread thread = new Thread(new RandomOrderGeneratorTask(list, ORDER_CREATING_INTERVAL));
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
