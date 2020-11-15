package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

class DepositCommand implements Command {
  @Override
  public void execute() {
    String currencyCode = ConsoleHelper.askCurrencyCode();
    String[] twoDigits = ConsoleHelper.getValidTwoDigits(currencyCode);
    int denomination = Integer.parseInt(twoDigits[0]);
    int count = Integer.parseInt(twoDigits[1]);

    CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode)
            .addAmount(denomination, count);
  }
}
