package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;

public class Client {
  protected Connection connection;
  private volatile boolean clientConnected = false;
  
  public class SocketThread extends Thread {
  
  }
  
  protected String getServerAddress() {
/*    String serverAddress = null;
    serverAddress = ConsoleHelper.readString();
    if (!serverAddress.matches("/^(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[0-9]{2}|[0-9])(\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[0-9]{2}|[0-9])){3}$/")
            && !"localhost".equals(serverAddress))
      throw new IllegalArgumentException();*/
    return ConsoleHelper.readString();
  }
  
  protected int getServerPort() {
    return ConsoleHelper.readInt();
  }
  
  protected String getUserName() {
    return ConsoleHelper.readString();
  }
  
  protected boolean shouldSendTextFromConsole() {
    return true;
  }
  
  protected SocketThread getSocketThread() {
    return new SocketThread();
  }
  
  protected void sendTextMessage(String text) {
    try {
      connection.send(new Message(MessageType.TEXT, text));
    } catch (IOException e) {
      e.printStackTrace();
      clientConnected = false;
    }
  }
 
}
