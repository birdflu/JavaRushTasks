package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
  private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

  public static void writeMessage(String message) {
    System.out.println(message);
  }

  public static String readString() {
    while (true)
      try {
        return bis.readLine();
      } catch (IOException e) {
      }
  }

  public static String askCurrencyCode() {
//    Этот метод должен предлагать пользователю ввести код валюты, проверять, что код содержит 3 символа.
//    Если данные некорректны, то сообщить об этом пользователю и повторить.
//    Если данные валидны, то перевести код в верхний регистр и вернуть.
    System.out.print("Input currency: ");
    while (true) {
      String code = readString();
      if (code.length() != 3) {
        System.out.println("Wrong code!");
        return askCurrencyCode();
      }
      return code.toUpperCase();
    }
  }

  public static Operation askOperation() {
//  Спросить у пользователя операцию.
//  Если пользователь вводит 1, то выбирается команда INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT;
//  Используйте метод, описанный в п.1.
//  Обработай исключение - запроси данные об операции повторно.
    System.out.print("Input operation: ");
    int operationCode;
    while (true) {
      String operation = readString();
      try {
        operationCode = Integer.parseInt(operation);
        if (1 > operationCode || operationCode > 4) {
          System.out.println("Wrong data!");
          return askOperation();
        } else {
          return Operation.getAllowableOperationByOrdinal(operationCode);
        }
      } catch (NumberFormatException e) {
        System.out.println("Wrong data!");
        return askOperation();
      }
    }
  }

  public static String[] getValidTwoDigits(String currencyCode) {
//    Этот метод должен предлагать пользователю ввести два целых положительных числа.
//    Первое число - номинал, второе - количество банкнот.
//    Никаких валидаторов на номинал нет. Т.е. 1200 - это нормальный номинал.
//    Если данные некорректны, то сообщить об этом пользователю и повторить.
//
//    Пример вводимых данных:
//    200 5
    System.out.print("Input nominal and banknote count: ");
    while (true) {
      String[] data = readString().split(" ");

      if (data.length != 2) {
        System.out.println("Wrong data!");
        return getValidTwoDigits(currencyCode);
      }

      try {
        int nominal = Integer.parseInt(data[0]);
        int banknote = Integer.parseInt(data[1]);
        if (nominal < 1 || banknote < 1) {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException e) {
        System.out.println("Wrong data!");
        return getValidTwoDigits(currencyCode);
      }

      return data;
    }
  }


}
