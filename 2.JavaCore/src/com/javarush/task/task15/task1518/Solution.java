package com.javarush.task.task15.task1518;

/* 
Статики 3
*/

public class Solution {
    public static  class Cat{
        public  String name;
    }
    public static Cat cat;
    static {
        cat = new Cat();
        cat.name = "Vasya";
        System.out.println(cat.name);
    }

        public static void main(String[] args) {

    }

    public class MethodInit3 {
        //! int j = g(i); // Illegal forward reference
        int i = f();
        int f() { return 11; }
        int g(int n) { return n * 10; }
    }
}
