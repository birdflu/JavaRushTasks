package com.javarush.task.task27.task2712.statistic.event;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.util.Date;
import java.util.List;

public class CookedOrderEventDataRow implements EventDataRow{
  String tabletName; // имя планшета
  String cookName; // имя повара
  int cookingTimeSeconds; // время приготовления заказа в секундах
  List<Dish> cookingDishs; // список блюд для приготовления
  Date currentDate = new Date();

  public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishs) {
    this.tabletName = tabletName;
    this.cookName = cookName;
    this.cookingTimeSeconds = cookingTimeSeconds;
    this.cookingDishs = cookingDishs;
  }
}
