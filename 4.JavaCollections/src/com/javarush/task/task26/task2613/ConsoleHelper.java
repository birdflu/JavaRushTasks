package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
  private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

  public static void writeMessage(String message) {
    System.out.println(message);
  }

  private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

  public static String readString() throws InterruptOperationException {
    while (true)
      try {
        String line = bis.readLine();
        if ("EXIT".equals(line.toUpperCase())) {
          throw new InterruptOperationException();
        }
        return line;
      }  catch (IOException e) {
      }
  }

  public static String askCurrencyCode() throws InterruptOperationException {
    writeMessage(res.getString("choose.currency.code"));
    while (true) {
      String code = readString();
      if (code.length() != 3) {
        System.out.println("Please specify valid data.");
        return askCurrencyCode();
      }
      return code.toUpperCase();
    }
  }


  public static Operation askOperation() throws InterruptOperationException {
    writeMessage(res.getString("choose.operation"));
    int operationCode;
    while (true) {
      String operation = readString();
      try {
        operationCode = Integer.parseInt(operation);
        if (1 > operationCode || operationCode > 4) {
          writeMessage(res.getString("invalid.data"));
          return askOperation();
        } else {
          Operation o = Operation.getAllowableOperationByOrdinal(operationCode);
          switch (o) {
            case INFO: {
              writeMessage(res.getString("operation.INFO"));
              break;
            }
            case DEPOSIT: {
              writeMessage(res.getString("operation.DEPOSIT"));
              break;
            }
            case WITHDRAW: {
              writeMessage(res.getString("operation.WITHDRAW"));
              break;
            }
            case EXIT: {
              writeMessage(res.getString("operation.EXIT"));
            }
          }
          return o;
        }
      } catch (NumberFormatException e) {
        writeMessage(res.getString("invalid.data"));
        return askOperation();
      }
    }
  }

  public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
    writeMessage(res.getString("choose.denomination.and.count.format"));
    while (true) {
      String[] data = readString().split(" ");

      if (data.length != 2) {
        writeMessage(res.getString("invalid.data"));
        return getValidTwoDigits(currencyCode);
      }

      try {
        int nominal = Integer.parseInt(data[0]);
        int banknote = Integer.parseInt(data[1]);
        if (nominal < 1 || banknote < 1) {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException e) {
        writeMessage(res.getString("invalid.data"));
        return getValidTwoDigits(currencyCode);
      }

      return data;
    }
  }

  public static void printExitMessage() {
    writeMessage("Good bye.");
  }


}
