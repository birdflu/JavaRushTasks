package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {

//  private ResourceBundle validCreditCards = ResourceBundle.getBundle(this.getClass()
//          .getPackage().getName()
//          .replace(".command", ".resources.verifiedCards"));

  // validator
  private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class
          .getPackage().getName() + ".resources.verifiedCards");

  @Override
  public void execute() throws InterruptOperationException {
    //  3. Создай LoginCommand по аналогии с другими командами, в котором захардкодь номер карточки с пином 123456789012 и 1234 соответственно.
    //  4. Реализуй следующую логику для команды LoginCommand:
    //  4.1. Пока пользователь не введет валидные номер карты и пин - выполнять следующие действия:
    //  4.2. Запросить у пользователя сначала номер кредитной карты, состоящий из 12 цифр, потом пин - состоящий из 4 цифр.
    //  4.3. Вывести юзеру сообщение о невалидных данных, если они такими являются.
    //  4.4. Если данные валидны, то проверить их на соответствие захардкоженным (123456789012 и 1234).
    //  4.5. Если данные в п. 4.4. идентифицированы, то сообщить, что верификация прошла успешно.
    //  4.6. Если данные в п. 4.4. НЕ идентифицированы, то вернуться к п.4.1.

    ConsoleHelper.writeMessage("Input card number: ");
    Long cardNumber = getValidValue(12);

    ConsoleHelper.writeMessage("Input pin: ");
    Long pin = getValidValue(4);

    if (valid(cardNumber, pin)) {
      ConsoleHelper.writeMessage("Access passed.");
    } else {
      System.out.println("Access denied!");
      execute();
    }
  }

  protected boolean valid(Long cardNumber, Long pin) {
    if (!validCreditCards.containsKey(String.valueOf(cardNumber))) {
      return false;
    } else try {
      validCreditCards.getString(String.valueOf(cardNumber)).equals(String.valueOf(pin));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  protected Long getValidValue(int size) throws InterruptOperationException {
    String string = ConsoleHelper.readString();
    try {
      if (string.length() != size) {
        ConsoleHelper.writeMessage("Wrong data!");
        return getValidValue(size);
      }
      return Long.parseLong(string);
    } catch (NumberFormatException e) {
      ConsoleHelper.writeMessage("Wrong data!");
      return getValidValue(size);
    }
  }


}
