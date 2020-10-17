package com.javarush.task.task27.task2712.statistic.event;

import com.javarush.task.task27.task2712.ad.Advertisement;

import java.util.Calendar;
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
//    this.currentDate.setTime(1421212929223L + (1_000_000_00L)*totalDuration/150);
  }

  @Override
  public EventType getType() {
    return EventType.SELECTED_VIDEOS;
  }


  @Override
  public Date getDate() {
    return currentDate;
  }

  @Override
  public int getTime() {
    return totalDuration;
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

  public long getAmount() {
    return amount;
  }
}
