package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

public class LoginCommand implements Command{

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

    long userCardNumber = 123456789012L;
    long userPin = 1234L;

    if (userCardNumber == cardNumber && userPin == pin) {
      ConsoleHelper.writeMessage("Access passed.");
    } else {
      System.out.println("Access denied!");
      execute();
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
