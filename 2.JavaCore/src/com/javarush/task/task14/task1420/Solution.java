package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (a < 0 || b < 0 ) throw new Exception();
            System.out.println(nod(a,b));
    }

    static int nod (int a, int b) {
        int min = (a>b? b: a);
        int max = (a<b? b: a);
        int r;
        while (true){
            r =  (max%min);
            if (r == 0 ) break;
            else {max = min; min = r;}}
        return min;
    }
}
