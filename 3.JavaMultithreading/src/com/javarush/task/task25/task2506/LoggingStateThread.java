package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread{
  private final Thread target;

  public LoggingStateThread(Thread target) {
    this.target = target;
  }

  @Override
  public void run() {
    State currentState = target.getState();
    System.out.println(currentState);
    while (true) {
      if (target.getState() != currentState){
        currentState = target.getState();
        System.out.println(currentState);
        if (currentState == State.TERMINATED) break;
      }
    }
  }
}
