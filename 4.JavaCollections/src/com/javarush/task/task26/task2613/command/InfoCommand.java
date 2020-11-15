package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

class InfoCommand implements Command{
  @Override
  public void execute() {
    if (CurrencyManipulatorFactory.getAllCurrencyManipulators().size() == 0)
      System.out.println("No money available.");
    for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
      if (manipulator.hasMoney()) {
        System.out.println(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
      } else {
        System.out.println("No money available.");
      }
    }
  }
}
