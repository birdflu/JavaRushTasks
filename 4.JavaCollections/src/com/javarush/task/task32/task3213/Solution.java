package com.javarush.task.task32.task3213;

import java.util.HashSet;
import java.util.Set;

/*
Больше 10? Вы нам не подходите
*/

public class Solution {
    public static Set<Integer> createSet() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            set.add(i+1);
        }
        return set;
    }

    public static Set<Integer> removeAllNumbersGreaterThan10(Set<Integer> set) {
        set.forEach(element -> {
            if (element > 10) {
                System.out.println(element);
                // set.remove(element);
            }
        });

        return set;
    }

    public static void main(String[] args) {
        removeAllNumbersGreaterThan10(createSet()).forEach(str -> System.out.println(str));
    }
}