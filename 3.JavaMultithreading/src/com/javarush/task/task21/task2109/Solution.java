package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        protected B clone() throws CloneNotSupportedException {
            if (this.getClass().getSimpleName().equals("B")) throw new CloneNotSupportedException();
            else return (B) super.clone();
        }

        public String getName() {
            return name;
        }
    }

    public static class C extends B implements Cloneable{
        public C(int i, int j, String name) {
            super(i, j, name);
        }
    }

    public static void main(String[] args) {
/*
        B b = new B(2,3, "B2");
        B cloneB = null;
        try {
            cloneB = b.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(b);
        System.out.println(cloneB);

        C c = new C(2,3, "C2");
        C cloneC = null;
        try {
            cloneC = (C) c.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(c);
        System.out.println(cloneC);
*/

    }
}
