package com.javarush.task.task26.task2613;

import java.util.Map;

public class CurrencyManipulatorFactory {
  private static Map<String, CurrencyManipulator> map;

  private CurrencyManipulatorFactory() {
  }

  public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
    // В этом методе будем создавать нужный манипулятор, если он еще не существует, либо возвращать ранее созданный.
    // Регистр при поиске манипулятора валюты не должен учитываться.
    // Подумай, где лучше хранить все манипуляторы? Маленькая подсказка, поле должно называться map.

    return null;
  }
}
