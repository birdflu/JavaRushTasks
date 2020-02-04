package com.javarush.task.task16.task1629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static volatile BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws InterruptedException {
        Read3Strings t1 = new Read3Strings();
        Read3Strings t2 = new Read3Strings();

        //add your code here - добавьте код тут

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t1.printResult();
        t2.printResult();
    }

    public static class Read3Strings extends Thread{
        private  List list = new ArrayList<String>();

        @Override
        public void run() {
        //            super.run();
            try {
      //          while (reader.ready() )
                    if (!isInterrupted() )
                    {
                    list.add(reader.readLine());
                        list.add(reader.readLine());
                        list.add(reader.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void printResult() {
            System.out.print(list.get(0) + " ");
            System.out.print(list.get(1) + " ");
            System.out.println(list.get(2));
        }
    }

    //add your code here - добавьте код тут
}
