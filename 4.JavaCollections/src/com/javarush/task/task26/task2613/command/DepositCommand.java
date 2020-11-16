package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
  private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

  @Override
  public void execute() throws InterruptOperationException {
    ConsoleHelper.writeMessage(res.getString("before"));
    String currencyCode = ConsoleHelper.askCurrencyCode();
    String[] twoDigits = ConsoleHelper.getValidTwoDigits(currencyCode);
    int denomination = Integer.parseInt(twoDigits[0]);
    int count = Integer.parseInt(twoDigits[1]);
    System.out.printf(res.getString("success.format"), denomination, String.valueOf(count));

    CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode)
            .addAmount(denomination, count);
  }
}
