package com.javarush.task.task39.task3913.tests;

import com.javarush.task.task39.task3913.Event;
import com.javarush.task.task39.task3913.LogParser;
import org.junit.Test;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertArrayEquals;

public class Testing {
  @Test
  public void test() {
    String logDirectory = Paths.get("").toAbsolutePath() + "\\src\\com\\javarush\\task\\task39\\task3913\\logs\\";
    LogParser logParser = new LogParser(Paths.get(logDirectory));
    Date after = new Date();
    Date before = new Date();
    try {
      after = new SimpleDateFormat("d.M.yyyy H:m:s").parse("11.12.2013 10:11:12");
      before = new SimpleDateFormat("d.M.yyyy H:m:s").parse("03.01.2014 03:45:23");
    } catch (ParseException e) {
      e.printStackTrace();
    }
    assert(logParser.getNumberOfUniqueIPs(after, before) == 2);
    assert(logParser.getNumberOfUniqueIPs(null, before) == 3 );
    assert(logParser.getNumberOfUniqueIPs(null, null) == 5);
  
    Object[] array = logParser.getIPsForUser("Eduard Petrovich Morozko", null, null).toArray();
    Arrays.sort(array);
    assertArrayEquals(array, new String[]{"127.0.0.1", "146.34.15.5"});
  
    assertArrayEquals(logParser.getIPsForUser("Eduard Petrovich Morozko", before, null).toArray(), new String[]{"146.34.15.5"});
  
    array = logParser.getIPsForEvent(Event.SOLVE_TASK, null, null).toArray();
    Arrays.sort(array);
    assertArrayEquals(array, new String[]{"12.12.12.12", "120.120.120.122", "192.168.100.2"});
  }
  }