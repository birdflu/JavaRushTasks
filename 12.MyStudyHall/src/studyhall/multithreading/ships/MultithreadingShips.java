package studyhall.multithreading.ships;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class MultithreadingShips implements Runnable{
  private Socket commandLineSocket;
  private BufferedReader in;
  private Thread sea = null;
  
  private boolean execute(String command) throws InterruptedException {
    switch (command) {
      case "start":
        if (sea == null || sea.getState() == Thread.State.TERMINATED) {
          sea = new Thread(new Sea());
          sea.start();
          System.out.println("Sea is launched!\n.");
        } else System.out.println(sea.getState());
        break;
      case "stop":
        sea.interrupt();
        System.out.println("Sea is finished!");
        break;
        
      case "exit":
        sea.interrupt();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Exit!");
        return true;
      default:
        try {
          System.out.println("Unknown command " + command + ".\n");
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        //break;
    }
    return false;
  }
  
  @Override
  public void run() {
    try {
      ServerSocket shipsSocket = new ServerSocket(4004);
      System.out.println("Multithreading ships are launched!\nFor exit enter \"exit\".");
      commandLineSocket = shipsSocket.accept();
      in = new BufferedReader(new InputStreamReader(commandLineSocket.getInputStream()));
      TimeUnit.SECONDS.sleep(2);
    
      while (true) {
        String command = in.readLine();
        System.out.println(command);
        if(execute(command)) return;
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    } finally {
      try {
        commandLineSocket.close();
        in.close();
        System.out.println("Multithreading ships are finished!\nFor exit enter \"exit\".");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
