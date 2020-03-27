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
    
    protected void processIncomingMessage(String message) {
      ConsoleHelper.writeMessage(message);
    }
  
    protected void informAboutAddingNewUser(String userName) {
      ConsoleHelper.writeMessage("User " + userName + " connected.");
    }
  
    protected void informAboutDeletingNewUser(String userName) {
      ConsoleHelper.writeMessage("User " + userName + " leave.");
    }
  
    protected void notifyConnectionStatusChanged(boolean clientConnected) {
      Client.this.clientConnected = clientConnected;
      synchronized (Client.this){
        Client.this.notify();
      }
    }
  }
  
  protected String getServerAddress() {
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
  
  public void run() {
    SocketThread socketThread = getSocketThread();
    socketThread.setDaemon(true);
    socketThread.start();
    synchronized (this) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
        return;
      }}
      if (clientConnected) {
        System.out.println("Соединение установлено.");
        System.out.println("Для выхода наберите команду 'exit'.");
        
      } else System.out.println("Произошла ошибка во время работы клиента.");
    
      
    while (clientConnected) {
      String message = ConsoleHelper.readString();
      if ("exit".equals(message)) break;
      if (shouldSendTextFromConsole()) {
        sendTextMessage(message);
//        System.out.println(message + "!");
      }
    }
  }
  
  public static void main(String[] args) {
    Client client = new Client();
    client.run();
  }
  
}
