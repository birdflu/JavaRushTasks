package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrencyManipulator {
  private String currencyCode; // код валюты, например, USD. Состоит из трех букв.
  private Map<Integer, Integer> denominations; // это Map<номинал, количество>.

  public CurrencyManipulator(String currencyCode) {
    this.currencyCode = currencyCode;
    denominations = new HashMap<>();
  }

  public  void addAmount(int denomination, int count) {

    denominations.merge(denomination, count, Integer::sum);
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  @Override
  public String toString() {
    return "CurrencyManipulator{" +
            "currencyCode='" + currencyCode + '\'' +
            ", denominations=" + denominations.entrySet().stream()
            .map(e -> e.getKey().toString() + " " + e.getValue())
            .collect(Collectors.toList()) +
            '}';
  }
}
