package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
  private List<Horse> horses;
  public static Hippodrome game;
  public List<Horse> getHorses() {
    return horses;
  }

  public Hippodrome(List<Horse> horses) {
    this.horses = horses;
  }

  public void run() throws InterruptedException {
    for (int i = 0; i < 100; i++) {
      move();
      print();
      Thread.sleep(200);
    }
  };
  public void move(){
    horses.forEach(h -> h.move());
  };

  public void print(){
    horses.forEach(h -> h.print());
    for (int i = 0; i < 10; i++) System.out.println();
  };

  public Horse getWinner(){
    return (
            (horses.get(0).getDistance() > horses.get(1).getDistance()) ?
                    (horses.get(0).getDistance() > horses.get(2).getDistance()) ? horses.get(0) : horses.get(2) :
                    (horses.get(1).getDistance() > horses.get(2).getDistance()) ? horses.get(1) : horses.get(2));
  };

  public void printWinner(){
    System.out.format("Winner is %s!", getWinner().getName());
  };

  public static void main(String[] args) throws InterruptedException {
    List<Horse> horses = new ArrayList<>();
    horses.add(new Horse("Ophelia", 3, 0));
    horses.add(new Horse("Felicia", 3, 0));
    horses.add(new Horse("Janet", 3, 0));

    game = new Hippodrome(horses);
    game.run();
    game.printWinner();
  }


}
