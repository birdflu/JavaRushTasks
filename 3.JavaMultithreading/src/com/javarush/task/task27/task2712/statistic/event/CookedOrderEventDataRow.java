package com.javarush.task.task27.task2712.statistic.event;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CookedOrderEventDataRow implements EventDataRow{
  String tabletName; // имя планшета
  String cookName; // имя повара
  int cookingTimeSeconds; // время приготовления заказа в секундах
  List<Dish> cookingDishs; // список блюд для приготовления
  Date currentDate;

  public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishs) {
    this.tabletName = tabletName;
    this.cookName = cookName;
    this.cookingTimeSeconds = cookingTimeSeconds;
    this.cookingDishs = cookingDishs;
    this.currentDate = new Date();
//    this.currentDate.setTime(1421212929223L + (1_000_000_00L)*cookingTimeSeconds);

  }

  @Override
  public EventType getType() {
    return EventType.COOKED_ORDER;
  }


  @Override
  public Date getDate() {
    return currentDate;
  }

  @Override
  public int getTime() {
    return cookingTimeSeconds;
  }

  public Date getDateWithoutTime() {
    Calendar cal = Calendar.getInstance(); // locale-specific
    cal.setTime(currentDate);
    cal.set(Calendar.HOUR, 0);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.AM_PM, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return new Date(cal.getTimeInMillis());
  }

  public String getCookName() {
    return cookName;
  }

}
