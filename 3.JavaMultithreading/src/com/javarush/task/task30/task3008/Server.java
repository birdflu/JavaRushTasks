package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
  private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();
  
  private static class Handler extends Thread {
    private Socket socket;
  
    public Handler(Socket socket) {
      this.socket = socket;
    }
  }
  
  public static void sendBroadcastMessage(Message message) {
    for (Map.Entry<String,Connection> entry: connectionMap.entrySet()
         ) {
      try {
        entry.getValue().send(message);
      } catch (IOException e) {
        System.out.println("The message is not sent");
      }
    }
  }
  
  public static void main(String[] args) {
    int port = ConsoleHelper.readInt();
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(port);
      System.out.println("Server run");
      
      while (true){
        Socket socket = serverSocket.accept();
        Handler handler = new Handler(socket);
        handler.start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        serverSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
}
