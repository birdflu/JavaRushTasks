package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {
  public final int number;
  public static Logger logger = Logger.getLogger(Tablet.class.getName());
  private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

  public Tablet(int number) {
    this.number = number;
  }

  public Order createOrder() {
    Order order = null;
    try {
      order = new Order(this);
      processOrder(order);
    } catch (NoVideoAvailableException e) {
      logger.log(Level.INFO, "No video is available for the order " + order);
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Console is unavailable.");
    }
    return order;
  }

  private void processOrder(Order order) {
 //   ConsoleHelper.writeMessage(order.toString());
    try {
      queue.put(order);
    } catch (InterruptedException e) {
      ConsoleHelper.writeMessage(e.getMessage());
    }
    new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
  }

  public void createTestOrder() {
    TestOrder order = null;
    try {
      order = new TestOrder(this);
      processOrder(order);
    } catch (NoVideoAvailableException e) {
      logger.log(Level.INFO, "No video is available for the order " + order);
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Console is unavailable.");
    }
  }

  @Override
  public String toString() {
    return "Tablet{number=" + number + "}";
  }

  public void setQueue(LinkedBlockingQueue<Order> queue) {
    this.queue = queue;
  }
}
