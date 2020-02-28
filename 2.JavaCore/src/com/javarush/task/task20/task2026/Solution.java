package com.javarush.task.task20.task2026;

/*
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");

        byte[][] a3 = new byte[][]{
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 0}
        };

        byte[][] a4 = new byte[][]{ // x | , y ->
                {1, 1, 1, 1},       // x = 0
                {1, 1, 1, 1},       // x = 1
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };

        int count3 = getRectangleCount(a3);
        System.out.println("count = " + count3 + ". Должно быть 2");
        int count4 = getRectangleCount(a4);
        System.out.println("count = " + count4 + ". Должно быть 1");
    }

    private static void fillRectangle(Coordinates init, byte[][] a, byte[][] steps) {
        Coordinates end = new Coordinates(steps.length-1,steps[0].length-1);
        for (int x = init.x; x <= end.x; x++)
            if (a[x][init.y] == 0) end.x = x;
        for (int y = init.y; y <= end.y; y++)
            if (a[init.x][y] == 0) end.y = y;

        for (int x = init.x; x <= end.x; x++) {
            for (int y = init.y; y <= end.y; y++) {
                steps[x][y] = 1;
            }
        }
    }

    private static int findRectangle(byte[][] a, byte[][] steps) {
        int counter = 0;
        for (int x = 0; x < steps.length; x++) {
            for (int y = 0; y < steps[0].length; y++) {
                if (x == steps.length - 1 & y == steps[0].length - 1 & steps[x][y] == 1) return counter;
                if (steps[x][y] == 1) continue;
                else if (a[x][y] == 0) steps[x][y] = 1;
                    else if (a[x][y] == 1) {
                        Coordinates init = new Coordinates(x,y);
                        counter++;
                        fillRectangle(init, a, steps);
                        counter = counter + findRectangle(a, steps);
                    }
            }
        }
        return counter;
    }

    public static int getRectangleCount(byte[][] a)
    {
        // table of steps that have been completed
        byte[][] steps = new byte[a.length][a[0].length];
        for (byte[] rows: steps) for (byte cell : rows) cell = 0;

        int count = findRectangle(a, steps);
    return count;
    }

}
