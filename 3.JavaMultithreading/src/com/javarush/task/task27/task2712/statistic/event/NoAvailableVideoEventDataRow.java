package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

public class NoAvailableVideoEventDataRow implements EventDataRow {
  int totalDuration; //время приготовления заказа в секундах
  Date currentDate = new Date();

  public NoAvailableVideoEventDataRow(int totalDuration) {
    this.totalDuration = totalDuration;
  }
}
