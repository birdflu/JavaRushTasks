package com.javarush.task.task26.task2613;

import java.util.Map;

public class CurrencyManipulator {
  private String currencyCode; // код валюты, например, USD. Состоит из трех букв.
  private Map<Integer, Integer> denominations; // это Map<номинал, количество>.

  public CurrencyManipulator(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }
}
