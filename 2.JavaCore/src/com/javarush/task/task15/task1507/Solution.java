package com.javarush.task.task15.task1507;

/* 
ООП - Перегрузка
*/

public class Solution {
    public static void main(String[] args) {
        printMatrix(2, 3, "8");
    }

    public static void printMatrix(int m, int n, String value) {
        System.out.println("Заполняем объектами String");
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(int m, int n, Object value) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value);
            }
            System.out.println();
        }
    }

    public static void printMatrix(int m, int n, Integer value) {
        printMatrix(m, n, Integer.toString(value));
    }
    public static void printMatrix(int m, int n, int value) {
        printMatrix(m, n, Integer.toString(value));
    }
    public static void printMatrix(int m, int n, double value) {
        printMatrix(m, n, Double.toString(value));
    }
    public static void printMatrix(int m, int n, Double value) {
        printMatrix(m, n, Math.round(value));
    }
    public static void printMatrix(int m, int n, float value) {
        printMatrix(m, n, Math.round(value));
    }
    public static void printMatrix(int m, int n, Float value) {
        printMatrix(m, n, Math.round(value));
    }
    public static void printMatrix(float m, int n, int value) {
        printMatrix(Math.round(m), n, value);
    }
    public static void printMatrix(float m, double n, int value) {
        printMatrix(Math.round(m), Math.round(n), value);
    }

}
