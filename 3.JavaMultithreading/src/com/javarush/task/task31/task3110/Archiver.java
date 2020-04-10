package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.*;

import java.io.IOException;

public class Archiver {
    public static void main(String[] args) throws IOException {
/*
        Path rootPath = Paths.get("C:\\temp\\zend");
        Path path = Paths.get("C:\\temp\\zend\\DCIM\\m_01.jpg");
    
        System.out.println(rootPath.getFileName());
        System.out.println(path.getFileName());
        System.out.println(rootPath.toAbsolutePath());
        System.out.println(path.toAbsolutePath());
        System.out.println(path.toAbsolutePath().toString().replace(rootPath.toAbsolutePath().toString(), ""));
        */
        
        Operation operation = null;
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }

        } while (operation != Operation.EXIT);
    }


    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Выберите операцию:");
        ConsoleHelper.writeMessage(String.format("\t %d - упаковать файлы в архив", Operation.CREATE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - добавить файл в архив", Operation.ADD.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - удалить файл из архива", Operation.REMOVE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - распаковать архив", Operation.EXTRACT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - просмотреть содержимое архива", Operation.CONTENT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - выход", Operation.EXIT.ordinal()));

        return Operation.values()[ConsoleHelper.readInt()];
    }
}