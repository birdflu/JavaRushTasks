package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.stream.Collectors;

class WithdrawCommand implements Command{
  @Override
  public void execute() throws InterruptOperationException {
    String currencyCode = ConsoleHelper.askCurrencyCode();
    CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
    int expectedAmount;
    while (true){
      ConsoleHelper.writeMessage("Input sum:");
      try {
        expectedAmount = Integer.parseInt((ConsoleHelper.readString()));
        if (currencyManipulator.isAmountAvailable(expectedAmount)) {

          Map<Integer, Integer> denominations = currencyManipulator.withdrawAmount(expectedAmount);
          denominations.entrySet().stream()
                  .sorted((e1, e2) -> Integer.compare(e2.getKey(), e1.getKey()))
                  .map(e -> "\t" + e.getKey().toString() + " - " + e.getValue())
                  .collect(Collectors.toList()).forEach(System.out::println);
          ConsoleHelper.writeMessage("Operation complete.");

          break;
        }
      } catch (NumberFormatException e) {
        ConsoleHelper.writeMessage("Wrong data!");
      } catch (NotEnoughMoneyException | ConcurrentModificationException e ) {
        ConsoleHelper.writeMessage("Withdraw impossible.");
      }
    }
  }
}
