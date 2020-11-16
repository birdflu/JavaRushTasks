package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

class InfoCommand implements Command{
  private ResourceBundle res = ResourceBundle.getBundle(this.getClass()
          .getPackage().getName()
          .replace(".command", ".resources.info_en"));

  @Override
  public void execute() {
    if (CurrencyManipulatorFactory.getAllCurrencyManipulators().size() == 0)
      ConsoleHelper.writeMessage("No money available.");
    for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
      if (manipulator.hasMoney()) {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
      } else {
        ConsoleHelper.writeMessage(res.getString("no.money"));
      }
    }
  }
}
