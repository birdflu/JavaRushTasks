package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bfr = new BufferedReader(isr);
        String fileName = bfr.readLine();
        FileInputStream fr=new FileInputStream(fileName);
        bfr = new BufferedReader(new InputStreamReader(fr));

        ArrayList<Integer> list = new ArrayList<>();

        while (bfr.ready()) {
            list.add(Integer.parseInt(bfr.readLine()));
        }

        boolean exit = false;
        while (!exit) {
            exit = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) > list.get(i + 1))

                {
                    Integer temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    exit = false;
                }
            }
        }
        for (int i = 0; i < list.size(); i++)
            if (list.get(i)%2 == 0) System.out.println(list.get(i).toString());
        fr.close();

    }
}
