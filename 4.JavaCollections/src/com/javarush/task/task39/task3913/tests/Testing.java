package com.javarush.task.task39.task3913.tests;

import com.javarush.task.task39.task3913.Event;
import com.javarush.task.task39.task3913.LogParser;
import com.javarush.task.task39.task3913.Status;
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
    //  Метод getNumberOfUniqueIPs(Date, Date) должен возвращать количество уникальных IP адресов за выбранный период.
    assert (logParser.getNumberOfUniqueIPs(after, before) == 2);
    assert (logParser.getNumberOfUniqueIPs(null, before) == 3);
    assert (logParser.getNumberOfUniqueIPs(null, null) == 5);
    //  Метод getUniqueIPs(Date, Date) класса LogParser должен возвращать множество, содержащее все не повторяющиеся IP адреса за выбранный период.
    //  Метод getIPsForUser(String, Date, Date) класса LogParser должен возвращать IP адреса, с которых работал переданный пользователь за выбранный период.
    assertArrayEquals(sort(logParser.getIPsForUser("Eduard Petrovich Morozko", null, null).toArray()),
            new String[]{"127.0.0.1", "146.34.15.5"});
    assertArrayEquals(logParser.getIPsForUser("Eduard Petrovich Morozko", before, null).toArray(),
            new String[]{"146.34.15.5"});
    //  Метод getIPsForEvent(Event, Date, Date) класса LogParser должен возвращать IP адреса, с которых было произведено переданное событие за выбранный период.
    assertArrayEquals(sort(logParser.getIPsForEvent(Event.SOLVE_TASK, null, null).toArray()),
            new String[]{"12.12.12.12", "120.120.120.122", "192.168.100.2"});
    //  Метод getIPsForStatus(Status, Date, Date) класса LogParser должен возвращать IP адреса, события с которых закончилось переданным статусом за выбранный период.
    assertArrayEquals(sort(logParser.getIPsForStatus(Status.FAILED, null, null).toArray()),
            new String[]{"127.0.0.1", "146.34.15.5"});
    //  Метод getAllUsers() должен возвращать множество содержащее всех пользователей.
    assertArrayEquals(sort(logParser.getAllUsers().toArray()), new String[]{"Amigo", "Eduard Petrovich Morozko", "Vasya Pupkin"});
    //  Метод getNumberOfUsers(Date, Date) должен возвращать количество уникальных пользователей за выбранный период.
    assert (logParser.getNumberOfUsers(after, before) == 1);
    //  Метод getNumberOfUserEvents(String, Date, Date) должен возвращать количество уникальных событий от переданного пользователя за выбранный период.
    assert (logParser.getNumberOfUserEvents("Eduard Petrovich Morozko", after, before) == 2);
    //  Метод getUsersForIP(String, Date, Date) должен возвращать множество содержащее пользователей, которые работали с переданного IP адреса за выбранный период.
    assertArrayEquals(sort(logParser.getUsersForIP("146.34.15.5", after, before).toArray()),
            new String[]{"Eduard Petrovich Morozko"});
    assertArrayEquals(sort(logParser.getUsersForIP("127.0.0.1", null, before).toArray()),
            new String[]{"Amigo", "Eduard Petrovich Morozko"});
    //  Метод getLoggedUsers(Date, Date) должен возвращать множество содержащее пользователей, которые были залогинены за выбранный период.
    assertArrayEquals(sort(logParser.getLoggedUsers(null, before).toArray()),
            new String[]{"Amigo", "Eduard Petrovich Morozko"});
    //  Метод getDownloadedPluginUsers(Date, Date) должен возвращать множество содержащее пользователей, которые скачали плагин за выбранный период.
    assertArrayEquals(sort(logParser.getDownloadedPluginUsers(null, before).toArray()),
            new String[]{"Eduard Petrovich Morozko"});
    //  Метод getWroteMessageUsers(Date, Date) должен возвращать множество содержащее пользователей, которые отправили сообщение за выбранный период.
    assertArrayEquals(sort(logParser.getWroteMessageUsers(null, null).toArray()),
            new String[]{"Eduard Petrovich Morozko", "Vasya Pupkin"});
    //  Метод getSolvedTaskUsers(Date, Date) должен возвращать множество содержащее пользователей, которые решали любую задачу за выбранный период.
    assertArrayEquals(sort(logParser.getSolvedTaskUsers(null, null).toArray()),
            new String[]{"Amigo", "Vasya Pupkin"});
    //  Метод getSolvedTaskUsers(Date, Date, int task) должен возвращать множество содержащее пользователей, которые решали задачу с номером task за выбранный период.
    assertArrayEquals(sort(logParser.getSolvedTaskUsers(null, null, 18).toArray()),
            new String[]{"Amigo", "Vasya Pupkin"});
    //  Метод getDoneTaskUsers(Date, Date) должен возвращать множество содержащее пользователей, которые решили любую задачу за выбранный период.
    assertArrayEquals(sort(logParser.getDoneTaskUsers(null, null).toArray()),
            new String[]{"Eduard Petrovich Morozko", "Vasya Pupkin"});
    //  Метод getDoneTaskUsers(Date, Date, int task) должен возвращать множество содержащее пользователей, которые решили задачу с номером task за выбранный период.
    //  На ОК проверять не нужно
    assertArrayEquals(sort(logParser.getDoneTaskUsers(null, null, 15).toArray()),
            new String[]{"Vasya Pupkin"});
    assert(logParser.getDoneTaskUsers(null, null, 48).size() == 1);
  }
  
  private Object[] sort(Object[] array) {
    Arrays.sort(array);
    return array;
  }
}