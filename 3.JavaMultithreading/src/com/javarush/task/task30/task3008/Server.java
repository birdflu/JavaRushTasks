package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
  private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();
  
  private static class Handler extends Thread {
    private Socket socket;
  
    public Handler(Socket socket) {
      this.socket = socket;
    }
  
    private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
      while (true) {
        connection.send(new Message(MessageType.NAME_REQUEST));
        Message response = connection.receive();
        String name = response.getData();
        if ((response.getType() == MessageType.USER_NAME) &&
                (name != null) &&
                (!name.isEmpty()) &&
                (!connectionMap.containsKey(name))) {
          connectionMap.put(name, connection);
          connection.send(new Message(MessageType.NAME_ACCEPTED));
          return name;
        }
      }
    }
  
    private void notifyUsers(Connection connection, String userName) throws IOException {
      for (Map.Entry<String,Connection> entry: connectionMap.entrySet()
      ) {
        String name = entry.getKey();
        if (name != userName)
          connection.send(new Message(MessageType.USER_ADDED, name));
      }
    }
    
    private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
      while (true){
        Message response = connection.receive();
        String text = response.getData();
        if (response.getType() == MessageType.TEXT) {
          String newText = userName + ": " + text;
          sendBroadcastMessage(new Message(MessageType.TEXT, newText));
        } else {
          ConsoleHelper.writeMessage("The message is not text");
        }
      }
    }
  
    @Override
    public void run() {
      SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
      System.out.println("Remote connection with " + remoteSocketAddress.toString() + " done");
      String userName = null;
      try {
        Connection connection = new Connection(socket);
        userName = serverHandshake(connection);
        sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
        notifyUsers(connection,userName);
        serverMainLoop(connection, userName);
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      } finally {
        try {
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (userName != null) {
        final String userNameFinal = userName;
        connectionMap.entrySet().removeIf(s -> s.getKey().equals(userNameFinal));
        sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
      }
      System.out.println("Remote connection with " + remoteSocketAddress.toString() + " close");
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
