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
    amountPerOneDisplaying = hits > 0 ? initialAmount / hits : 0;
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

  public String getName() {
    return name;
  }
  
  public int getDuration() {
    return duration;
  }

  public long getAmountPerOneDisplaying() {
    return amountPerOneDisplaying;
  }

  public int getHits() {
    return hits;
  }

  /**  Стоимость {@code getPricePerSecond} показа одной секунды рекламного ролика в тысячных частях копейки
   */
  public long getPricePerSecond() { return (getAmountPerOneDisplaying()*1000/duration); };

  @Override
  public String toString() {
    return "{" +
            "\'" + name + '\'' +
            ", d=" + duration +
            ", a=" + amountPerOneDisplaying +
            ", pps=" + getPricePerSecond() +
            ", hits+" + hits +
            '}';
  }
}

