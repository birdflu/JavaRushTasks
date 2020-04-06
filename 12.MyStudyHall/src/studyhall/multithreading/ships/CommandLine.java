package studyhall.multithreading.ships;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class CommandLine {
  
  public static void main(String[] args) {
    Socket commandLineSocket;
    BufferedReader reader;
    BufferedWriter ship;
  
    try {
      commandLineSocket = new Socket("localhost", 4004);
      reader = new BufferedReader(new InputStreamReader(System.in));
      ship = new BufferedWriter(new OutputStreamWriter(commandLineSocket.getOutputStream()));
      
      while (true) {
        System.out.println("next:\n");
        String command = reader.readLine() + "\n";
        if ("exit\n".equals(command)) {
          ship.write(command);
          ship.flush();
          TimeUnit.SECONDS.sleep(1);
          return;
        } else {
          ship.write(command);
          ship.flush();
        }
        
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
