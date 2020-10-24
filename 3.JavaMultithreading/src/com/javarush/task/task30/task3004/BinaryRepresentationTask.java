package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {
  final int x;

  public BinaryRepresentationTask(int x)  { this.x = x; }

  @Override
  protected String compute() {
 /* example
     final int n;
 *   Fibonacci(int n) { this.n = n; }
 *   protected Integer compute() {
 *     if (n <= 1)
 *       return n;
 *     Fibonacci f1 = new Fibonacci(n - 1);
 *     f1.fork();
 *     Fibonacci f2 = new Fibonacci(n - 2);
 *     return f2.compute() + f1.join();
 */

    int a = x % 2;
    int b = x / 2;
    String result = String.valueOf(a);

    if (b <= 0)
      return result;

    BinaryRepresentationTask brt1 = new BinaryRepresentationTask(a);
    brt1.fork();

    BinaryRepresentationTask brt2 = new BinaryRepresentationTask(b);
    return brt2.compute() + brt1.join();


  }
}
