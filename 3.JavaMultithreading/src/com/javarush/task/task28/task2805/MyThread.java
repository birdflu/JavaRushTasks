package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
  private static AtomicInteger priority = new AtomicInteger(0);
  
  {
    this.setPriority(priority.incrementAndGet());
    if (priority.get() == 10) priority.set(0);
  }
  
  public MyThread() {
    super();
  }
  
  public MyThread(Runnable target) {
    super(target);
  }
  
  public MyThread(ThreadGroup group, Runnable target) {
    super(group, target);
    if (this.getPriority() > group.getMaxPriority())
      this.setPriority(group.getMaxPriority());
  }
  
  public MyThread(String name) {
    super(name);
  }
  
  public MyThread(ThreadGroup group, String name) {
    super(group, name);
    if (this.getPriority() > group.getMaxPriority())
      this.setPriority(group.getMaxPriority());
  }
  
  public MyThread(Runnable target, String name) {
    super(target, name);
  }
  
  public MyThread(ThreadGroup group, Runnable target, String name) {
    super(group, target, name);
    if (this.getPriority() > group.getMaxPriority())
      this.setPriority(group.getMaxPriority());
  }
  
  public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
    super(group, target, name, stackSize);
    if (this.getPriority() > group.getMaxPriority())
      this.setPriority(group.getMaxPriority());
  }
  
}
