package com.javarush.task.task27.task2712.ad;

public class Advertisement {
  private Object content; // видео
  private String name; // имя/название
  private long initialAmount; // начальная сумма, стоимость рекламы в копейках. Используем long, чтобы избежать проблем с округлением
  private int hits; // количество оплаченных показов
  private int duration; // продолжительность в секундах
  private long amountPerOneDisplaying; // стоимость одного показа рекламного объявления в копейках
  
  public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
    this.content = content;
    this.name = name;
    this.initialAmount = initialAmount;
    this.hits = hits;
    this.duration = duration;
    this.amountPerOneDisplaying = this.initialAmount / this.hits;
  }

  public String getName() {
    return name;
  }
  
  public int getDuration() {
    return duration;
  }

  public long getAmountPerOneDisplaying() {
    return amountPerOneDisplaying;
  }
  
  public void revalidate() {
    if (hits > 0) hits--;
    else throw new UnsupportedOperationException();
  }

  public Advertisement amount (Advertisement a) {
   // amountPerOneDisplaying : i1/h1 + i2/h2 = (i1h2 + i2h1) / h1h2
    return new Advertisement(null, "amount"
            , (this.initialAmount*a.hits) + (a.initialAmount*this.hits)
            , this.hits * a.hits
            , this.duration + a.duration);
  }

  @Override
  public String toString() {
    return "{" +
            "\'" + name + '\'' +
            ", d=" + duration/60 +
            ", a=" + amountPerOneDisplaying +
            '}';
  }







}

