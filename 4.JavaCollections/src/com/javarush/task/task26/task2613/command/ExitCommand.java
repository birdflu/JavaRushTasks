package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command{
  @Override
  public void execute() throws InterruptOperationException {
    System.out.print("Do you want exit? (y/n): ");
    String answer = ConsoleHelper.readString();
    if (answer.equals("y"))
      ConsoleHelper.writeMessage("Good bye.");
    else if (answer.equals("n")) {
      return;
    } else {
      execute();
    }

//    1. Реализуй следующую логику в команде ExitCommand:
//    1.1. Спросить, действительно ли пользователь хочет выйти - варианты <y,n>.
//    1.2. Если пользователь подтвердит свои намерения, то попрощаться с ним.
//    1.3. Если пользователь не подтвердит свои намерения, то не прощаться с ним, а просто выйти.
//
//    Это всё хорошо, но бывают случаи, когда срочно нужно прервать операцию, например, если пользователь ошибся с выбором операции.
//            Для этого у нас есть InterruptOperationException.
//    2.Реализуй следующую логику:
//    2.1. Если пользователь в любом месте ввел текст 'EXIT' любым регистром, то выбросить InterruptOperationException.
//    2.2. Найди единственное место, куда нужно вставить эту логику. Реализуй функционал в этом единственном методе.
  }
}
