package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

class WithdrawCommand implements Command {
  private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");


  @Override
  public void execute() throws InterruptOperationException {
    ConsoleHelper.writeMessage(res.getString("before"));
    String code = ConsoleHelper.askCurrencyCode();
    CurrencyManipulator currencyManipulator =
            CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
    int expectedAmount;
    while (true) {
      ConsoleHelper.writeMessage(res.getString("specify.amount"));
      String s = ConsoleHelper.readString();
      if (s.matches("^\\d+$")) {
        expectedAmount = Integer.parseInt(s);
        if (currencyManipulator.isAmountAvailable(expectedAmount)) {
          try {
            Map<Integer, Integer> denominations = currencyManipulator.withdrawAmount(expectedAmount);
            denominations.entrySet().stream()
                    .sorted((e1, e2) -> Integer.compare(e2.getKey(), e1.getKey()))
                    .map(e -> "\t" + e.getKey().toString() + " - " + e.getValue())
                    .collect(Collectors.toList()).forEach(ConsoleHelper::writeMessage);
            ConsoleHelper.writeMessage(String.format(
                    res.getString("success.format"),
                    expectedAmount,
                    currencyManipulator.getCurrencyCode()));
            break;
          } catch (NotEnoughMoneyException e) {
            ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            continue;
          }
        } else {
          ConsoleHelper.writeMessage(res.getString("not.enough.money"));
          continue;
        }
      }
      ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
    }
  }
}
