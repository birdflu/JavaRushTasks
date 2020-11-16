package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
  private final static Map<String, CurrencyManipulator> map = new HashMap<>();

//  static {
//    map.put("RUB", new CurrencyManipulator("RUB"));
//    map.get("RUB").addAmount(300, 2);
//    map.get("RUB").addAmount(200, 3);
//    map.get("RUB").addAmount(100, 1);
//  }

  private CurrencyManipulatorFactory() {
  }

  public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
    // В этом методе будем создавать нужный манипулятор, если он еще не существует, либо возвращать ранее созданный.
    // Регистр при поиске манипулятора валюты не должен учитываться.
    // Подумай, где лучше хранить все манипуляторы? Маленькая подсказка, поле должно называться map.
    CurrencyManipulator currencyManipulator =
            map.putIfAbsent(currencyCode, new CurrencyManipulator(currencyCode));

    return currencyManipulator == null
            ? map.get(currencyCode)
            : currencyManipulator;
  }

  public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
    return map.values();
  }
}
