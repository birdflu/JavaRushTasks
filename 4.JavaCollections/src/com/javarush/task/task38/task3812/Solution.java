package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        boolean exist = c.isAnnotationPresent(PrepareMyTest.class);

        if (exist) {
            PrepareMyTest prepareMyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            for (String s : prepareMyTest.fullyQualifiedNames())
                System.out.println(s);
        }

        return exist;
    }

    public static boolean printValues(Class c) {
        boolean exist = c.isAnnotationPresent(PrepareMyTest.class);

        if (exist) {
            PrepareMyTest prepareMyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            for (Class clss : prepareMyTest.value())
                System.out.println(clss.getSimpleName());
        }

        return exist;
    }
}