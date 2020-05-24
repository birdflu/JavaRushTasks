package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        String logDirectory = Paths.get("").toAbsolutePath() + "\\out\\production\\4.JavaCollections\\com\\javarush\\task\\task39\\task3913\\logs\\";
        LogParser logParser = new LogParser(Paths.get(logDirectory));
        try {
            Date after = new SimpleDateFormat("d.M.yyyy H:m:s").parse("11.12.2013 10:11:12");
            Date before = new SimpleDateFormat("d.M.yyyy H:m:s").parse("03.01.2014 03:45:23");
            System.out.println(logParser.getIPsForEvent(Event.SOLVE_TASK, null, null));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
    }
}