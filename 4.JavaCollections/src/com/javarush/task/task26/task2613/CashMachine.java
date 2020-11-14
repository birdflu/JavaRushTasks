package com.javarush.task.task26.task2613;

import java.util.Locale;

public class CashMachine {
  public static void main(String[] args) {
    Locale.setDefault(Locale.ENGLISH);
    String currencyCode = ConsoleHelper.askCurrencyCode();
    String[] twoDigits = ConsoleHelper.getValidTwoDigits(currencyCode);

    CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory
            .getManipulatorByCurrencyCode(currencyCode);
    currencyManipulator.addAmount(Integer.parseInt(twoDigits[0]), Integer.parseInt(twoDigits[1]));
//    System.out.println(currencyManipulator.toString());

  }
}
