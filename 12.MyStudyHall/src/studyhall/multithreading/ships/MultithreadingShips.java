package studyhall.multithreading.ships;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class MultithreadingShips implements Runnable {
  private Socket commandLineSocket;
  private BufferedReader in;
  private Thread sea = null;
  private Tunnel tunnel = null;
  
  private boolean execute(String commandString) throws InterruptedException {
    Command command = new Command(commandString);
    switch (command.getCommand()) {
      case "sea": executeSea(command); break;
      case "tunnel": executeTunnel(command); break;
      
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
    }
    return false;
  }
  
  private void executeSea(Command command) {
    if ("start".equals(command.getFirstArgumentKey()))
      if (sea == null || sea.getState() == Thread.State.TERMINATED) {
        sea = new Thread(new Sea());
        sea.start();
        System.out.println("Sea is launched!\n.");
      } else System.out.println(sea.getState());
    else if ("stop".equals(command.getFirstArgumentKey())){
      sea.interrupt();
      System.out.println("Sea is finished!\n");
    }
  }
  
  private void executeTunnel(Command command) {
    if ("create".equals(command.getFirstArgumentKey()))
      if (tunnel == null) {
        tunnel = new Tunnel(Integer.parseInt(command.getFirstArgumentValue()));
        System.out.println("Tunnel is created!\n.");
      } else System.out.println(sea.getState());
    else if ("drop".equals(command.getFirstArgumentKey())){
      tunnel = null;
      System.out.println("Tunnel is dropped!\n");
    }
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
        if (execute(command)) return;
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
