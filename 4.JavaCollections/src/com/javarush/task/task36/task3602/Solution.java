package com.javarush.task.task36.task3602;

import java.util.Arrays;
import java.util.Collections;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        for (Class clazz : Arrays.asList(Collections.class.getDeclaredClasses())
             ) {
            
            if (clazz.getSimpleName().equals("EmptyList"))
                return clazz;
        }
        return null;
    }
}
