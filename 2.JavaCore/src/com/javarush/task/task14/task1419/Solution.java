package com.javarush.task.task14.task1419;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //it's first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            char[] c = {1,2};
            c[3] = c[3];

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            FileReader fr = new FileReader("c:");

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            int i;
            String s = "2132132131232132132132131232131232132132131231231";
            i = Integer.parseInt(s);

        } catch (Exception e) {
            exceptions.add(e);
        }


        try {
            Exception ex2 = new Exception();
            throw ex2;
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            throw new IOException();
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            throw new NullPointerException();
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            throw new IndexOutOfBoundsException();
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            throw new SQLException();
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            throw new IllegalArgumentException() ;
        } catch (Exception e) {
            exceptions.add(e);
        }
    }
}
