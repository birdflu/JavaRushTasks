package studyhall.multithreading.ships;

import static java.lang.Thread.sleep;

public class Sea implements Runnable {
  int createdShipsCount = 0;
  public Ship create() {
    createdShipsCount++;
    System.out.format("%d ships are created.\n", createdShipsCount);
    return new Ship();
  }
  
  @Override
  public void run() {
    System.out.println("Sea is started!");
    while (!Thread.interrupted()) {
      System.out.println(Thread.currentThread().getState());
      create();
      try {
        sleep(1000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        //e.printStackTrace();
      }
    }
    System.out.println("Sea is interrupted!");
  }
}
