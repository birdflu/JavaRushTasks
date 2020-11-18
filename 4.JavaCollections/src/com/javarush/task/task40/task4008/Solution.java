package com.javarush.task.task40.task4008;

/*
Работа с Java 8 DateTime API
*/

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
//        printDate("9.10.2017 5:56:45");
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
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d.M.yyyy"));
            printDate(localDate);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean parseTime(String date) {
        try {
            LocalTime localTime = LocalTime.parse(date, DateTimeFormatter.ofPattern("H:m:s"));
            printTime(localTime);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static void printDate(LocalDate localDate) {
        System.out.printf("День: %d\n", localDate.getDayOfMonth());
        System.out.printf("День недели: %d\n", localDate.getDayOfWeek().getValue());
        System.out.printf("День месяца: %d\n", localDate.getDayOfMonth());
        System.out.printf("День года: %d\n", localDate.getDayOfYear());
        System.out.printf("Неделя месяца: %d\n", localDate.get(ChronoField.ALIGNED_WEEK_OF_MONTH));
        System.out.printf("Неделя года: %d\n", localDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
        System.out.printf("Месяц: %d\n", localDate.getMonthValue());
        System.out.printf("Год: %d\n", localDate.getYear());
    }

    private static void printTime(LocalTime localTime) {
        System.out.printf("AM или PM: %s\n", localTime.get(ChronoField.AMPM_OF_DAY) == 1 ? "PM" : "AM");
        System.out.printf("Часы: %d\n", localTime.get(ChronoField.HOUR_OF_AMPM));
        System.out.printf("Часы дня: %d\n", localTime.getHour());
        System.out.printf("Минуты: %d\n", localTime.getMinute());
        System.out.printf("Секунды: %d\n", localTime.getSecond());
    }

}
