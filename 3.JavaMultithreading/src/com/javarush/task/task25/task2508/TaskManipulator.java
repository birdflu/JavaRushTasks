package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
  private Thread thread = null;

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      System.out.format("\n%s", thread.getName());
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        // https://www.yegor256.com/2015/10/20/interrupted-exception.html
        Thread.currentThread().interrupt();
      }
      if (thread.getState() == Thread.State.TERMINATED && "fifth".equals(thread.getName())) {
        Thread.currentThread().interrupt();
        break;
      }
    }

  }


  @Override
  public void start(String threadName) {
    thread = new Thread(this, threadName);
    thread.start();
  }

  @Override
  public void stop() {
    thread.interrupt();
  }
}
