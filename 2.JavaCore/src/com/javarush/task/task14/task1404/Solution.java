package com.javarush.task.task14.task1404;

/*
Коты
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        ArrayList<String> keys = new ArrayList<>();
        Scanner s = new Scanner(System.in);

        String key = ".";

        while (! key.equals("")) {
            key = s.nextLine();
            if (! key.equals("")) keys.add(key);
        }

        for (String k : keys) {
            Cat cat = CatFactory.getCatByKey(k);
            System.out.println(cat.toString());

        }

    }

    static class CatFactory {
        static Cat getCatByKey(String key) {
            Cat cat;
            if ("vaska".equals(key)) {
                cat = new MaleCat("Василий");
            } else if ("murka".equals(key)) {
                cat = new FemaleCat("Мурочка");
            } else if ("kiska".equals(key)) {
                cat = new FemaleCat("Кисюлька");
            } else {
                cat = new Cat(key);
            }
            return cat;
        }
    }

    static class Cat {
        private String name;

        protected Cat(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public String toString() {
            return "Я уличный кот " + getName();
        }
    }

    static class MaleCat extends Cat {
        MaleCat(String name) {
            super(name);
        }

        public String toString() {
            return "Я - солидный кошак по имени " + getName();
        }
    }

    static class FemaleCat extends Cat {
        FemaleCat(String name) {
            super(name);
        }

        public String toString() {
            return "Я - милая кошечка по имени " + getName();
        }
    }
}
