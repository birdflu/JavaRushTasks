package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        //запоминаем настоящий PrintStream в специальную переменную
        PrintStream consoleStream = System.out;

        //Создаем динамический массив
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //создаем адаптер к классу PrintStream
        PrintStream stream = new PrintStream(outputStream);
        //Устанавливаем его как текущий System.out
        System.setOut(stream);

        //Вызываем функцию, которая ничего не знает о наших манипуляциях
        testString.printSomething();

        //Преобразовываем записанные в наш ByteArray данные в строку
        String input = outputStream.toString();

        //Возвращаем все как было
        System.setOut(consoleStream);

        //выводим результат выражения
        String[] elements = input.split(" ");
        String operation = elements[1];
        Integer operand1 = Integer.parseInt(elements[0]);
        Integer operand2 = Integer.parseInt(elements[2]);
        Integer operationResult;

        switch (operation) {
            case "+":
                operationResult = operand1 + operand2;
                break;
            case "-":
                operationResult = operand1 - operand2;
                break;
            case "*":
                operationResult = operand1 * operand2;
                break;
            default: operationResult = -1;
        }

        System.out.println(operand1 + " " + operation + " " + operand2 + " = " + operationResult);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

