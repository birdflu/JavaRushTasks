package studyhall.multithreading.ships;

public class Executor {
  public static void main(String[] args) {
    MultithreadingShips multithreadingShips = new MultithreadingShips();
    Thread ships = new Thread(multithreadingShips);
    ships.start();
    
  }
}
