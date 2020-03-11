package com.javarush.task.task25.task2514;

/* 
Первый закон Финэйгла: если эксперимент удался, что-то здесь не так...
*/
public class Solution {
    public static class YieldRunnable implements Runnable {
        private int index;

        public YieldRunnable(int index) {
            this.index = index;
        }

        public void run() {
            Thread.yield();
            System.out.println("begin-" + index);
            Thread.yield();
            System.out.println("end-" + index);
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        YieldRunnable yieldRunnable1 = new YieldRunnable(1);
        YieldRunnable yieldRunnable2 = new YieldRunnable(2);

        new Thread(yieldRunnable1).start();
        new Thread(yieldRunnable2).start();

    
    }
}
