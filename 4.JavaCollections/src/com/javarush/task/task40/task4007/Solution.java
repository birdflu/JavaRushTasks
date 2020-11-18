package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        String[] dateTime = date.split(" ");
        if (dateTime.length == 2) {
            parseDate(dateTime[0]);
            parseTime(dateTime[1]);
        } else if (dateTime.length == 1) {
            if (parseDate(date)) {
                return;
            }
            else parseTime(date);
        }
    }

    protected static boolean parseDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Calendar localDate = Calendar.getInstance();
            localDate.setTime(simpleDateFormat.parse(date));
            printDate(localDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static boolean parseTime(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            Calendar localTime = Calendar.getInstance();
            localTime.setTime(simpleDateFormat.parse(date));
            printTime(localTime);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static void printDate(Calendar localDate) {
        System.out.printf("День: %d\n", localDate.get(Calendar.DAY_OF_MONTH));
        System.out.printf("День недели: %d\n", ((localDate.get(Calendar.DAY_OF_WEEK) + 5) % 7 + 1));
        System.out.printf("День месяца: %d\n", localDate.get(Calendar.DAY_OF_MONTH));
        System.out.printf("День года: %d\n", localDate.get(Calendar.DAY_OF_YEAR));
        System.out.printf("Неделя месяца: %d\n", localDate.get(Calendar.WEEK_OF_MONTH));
        System.out.printf("Неделя года: %d\n", localDate.get(Calendar.WEEK_OF_YEAR));
        System.out.printf("Месяц: %d\n", (localDate.get(Calendar.MONTH) + 1));
        System.out.printf("Год: %d\n", localDate.get(Calendar.YEAR));
    }

    private static void printTime(Calendar localTime) {

        System.out.printf("AM или PM: %s\n", (localTime.get(Calendar.AM_PM) == 1 ? "PM" : "AM"));
        System.out.printf("Часы: %d\n", localTime.get(Calendar.HOUR));
        System.out.printf("Часы дня: %d\n", localTime.get(Calendar.HOUR_OF_DAY));
        System.out.printf("Минуты: %d\n", localTime.get(Calendar.MINUTE));
        System.out.printf("Секунды: %d\n", localTime.get(Calendar.SECOND));
    }
}
