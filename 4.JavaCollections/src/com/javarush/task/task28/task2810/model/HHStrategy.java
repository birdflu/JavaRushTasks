package com.javarush.task.task28.task2810.model;

/** Класс будет реализовывать конкретную стратегию работы с сайтом http://hh.ua/ и http://hh.ru/.*/
public class HHStrategy implements Strategy {
  private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%s";

  public void get() {
    System.out.println(String.format(URL_FORMAT, "Kiev", 3));

  }
}


