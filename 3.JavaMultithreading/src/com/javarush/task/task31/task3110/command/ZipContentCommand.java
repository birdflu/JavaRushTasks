package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.FileProperties;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.util.List;

public class ZipContentCommand extends ZipCommand {
  @Override
  public void execute() throws Exception {
    ConsoleHelper.writeMessage("Содержимое архива:");
    ZipFileManager zipFileManager = getZipFileManager();
    List<FileProperties> filesList = zipFileManager.getFilesList();
    filesList.forEach(fileProperties -> System.out.println(fileProperties));
    ConsoleHelper.writeMessage( "Содержимое архива прочитано.");
  }
}
