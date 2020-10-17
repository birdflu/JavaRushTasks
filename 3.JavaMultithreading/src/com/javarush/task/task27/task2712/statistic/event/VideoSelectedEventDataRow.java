package com.javarush.task.task27.task2712.statistic.event;

import com.javarush.task.task27.task2712.ad.Advertisement;

import java.util.Date;
import java.util.List;

public class VideoSelectedEventDataRow implements EventDataRow {
  List<Advertisement> optimalVideoSet; // список видео//роликов, отобранных для показа
  long amount; // сумма денег в копейках
  int totalDuration; // общая продолжительность показа отобранных рекламных роликов
  Date currentDate;

  public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration) {
    this.optimalVideoSet = optimalVideoSet;
    this.amount = amount;
    this.totalDuration = totalDuration;
    this.currentDate = new Date();
//    System.out.println("optimalVideoSet = " + optimalVideoSet);
//    System.out.println("amount = " + amount);
//    System.out.println("totalDuration = " + totalDuration);
  }

  @Override
  public EventType getType() {
    return EventType.SELECTED_VIDEOS;
  }
}
