package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipRemoveCommand extends ZipCommand {
  @Override
  public void execute() throws Exception {
      ConsoleHelper.writeMessage("Удаление из архива.");
    
      ZipFileManager zipFileManager = getZipFileManager();
    
      ConsoleHelper.writeMessage("Введите полное имя файла или директории для удаления:");
      Path sourcePath = Paths.get(ConsoleHelper.readString());
      zipFileManager.removeFile(sourcePath);
    
      ConsoleHelper.writeMessage("Архив создан.");
    
  }
}
