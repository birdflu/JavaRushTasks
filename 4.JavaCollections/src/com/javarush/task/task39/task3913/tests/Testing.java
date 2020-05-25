package com.javarush.task.task39.task3913.tests;

import com.javarush.task.task39.task3913.Event;
import com.javarush.task.task39.task3913.LogParser;
import com.javarush.task.task39.task3913.Status;
import org.junit.Test;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

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
    //  Метод getUniqueIPs(Date, Date) класса LogParser должен возвращать множество, содержащее все не повторяющиеся
    //  IP адреса за выбранный период.
  
    //  Метод getIPsForUser(String, Date, Date) класса LogParser должен возвращать IP адреса,
    //  с которых работал переданный пользователь за выбранный период.
    assertArrayEquals(sort(logParser.getIPsForUser("Eduard Petrovich Morozko", null, null).toArray()),
            new String[]{"127.0.0.1", "146.34.15.5"});
    assertArrayEquals(logParser.getIPsForUser("Eduard Petrovich Morozko", before, null).toArray(),
            new String[]{"146.34.15.5"});
  
    //  Метод getIPsForEvent(Event, Date, Date) класса LogParser должен возвращать IP адреса,
    //  с которых было произведено переданное событие за выбранный период.
    assertArrayEquals(sort(logParser.getIPsForEvent(Event.SOLVE_TASK, null, null).toArray()),
            new String[]{"12.12.12.12", "120.120.120.122", "192.168.100.2"});
  
    //  Метод getIPsForStatus(Status, Date, Date) класса LogParser должен возвращать IP адреса,
    //  события с которых закончилось переданным статусом за выбранный период.
    assertArrayEquals(sort(logParser.getIPsForStatus(Status.FAILED, null, null).toArray()),
            new String[]{"127.0.0.1", "146.34.15.5"});
  
    //  Метод getAllUsers() должен возвращать множество содержащее всех пользователей.
    assertArrayEquals(sort(logParser.getAllUsers().toArray()), new String[]{"Amigo", "Eduard Petrovich Morozko", "Vasya Pupkin"});
  
    //  Метод getNumberOfUsers(Date, Date) должен возвращать количество уникальных пользователей за выбранный период.
    assert (logParser.getNumberOfUsers(after, before) == 1);
  
    //  Метод getNumberOfUserEvents(String, Date, Date) должен возвращать количество уникальных событий
    //  от переданного пользователя за выбранный период.
    assert (logParser.getNumberOfUserEvents("Eduard Petrovich Morozko", after, before) == 2);
  
    //  Метод getUsersForIP(String, Date, Date) должен возвращать множество содержащее пользователей,
    //  которые работали с переданного IP адреса за выбранный период.
    assertArrayEquals(sort(logParser.getUsersForIP("146.34.15.5", after, before).toArray()),
            new String[]{"Eduard Petrovich Morozko"});
    assertArrayEquals(sort(logParser.getUsersForIP("127.0.0.1", null, before).toArray()),
            new String[]{"Amigo", "Eduard Petrovich Morozko"});
  
    //  Метод getLoggedUsers(Date, Date) должен возвращать множество содержащее пользователей,
    //  которые были залогинены за выбранный период.
    assertArrayEquals(sort(logParser.getLoggedUsers(null, before).toArray()),
            new String[]{"Amigo", "Eduard Petrovich Morozko"});
  
    //  Метод getDownloadedPluginUsers(Date, Date) должен возвращать множество содержащее пользователей,
    //  которые скачали плагин за выбранный период.
    assertArrayEquals(sort(logParser.getDownloadedPluginUsers(null, before).toArray()),
            new String[]{"Eduard Petrovich Morozko"});
  
    //  Метод getWroteMessageUsers(Date, Date) должен возвращать множество содержащее пользователей,
    //  которые отправили сообщение за выбранный период.
    assertArrayEquals(sort(logParser.getWroteMessageUsers(null, null).toArray()),
            new String[]{"Eduard Petrovich Morozko", "Vasya Pupkin"});
  
    //  Метод getSolvedTaskUsers(Date, Date) должен возвращать множество содержащее пользователей,
    //  которые решали любую задачу за выбранный период.
    assertArrayEquals(sort(logParser.getSolvedTaskUsers(null, null).toArray()),
            new String[]{"Amigo", "Vasya Pupkin"});
  
    //  Метод getSolvedTaskUsers(Date, Date, int task) должен возвращать множество содержащее пользователей,
    //  которые решали задачу с номером task за выбранный период.
    assertArrayEquals(sort(logParser.getSolvedTaskUsers(null, null, 18).toArray()),
            new String[]{"Amigo", "Vasya Pupkin"});
  
    //  Метод getDoneTaskUsers(Date, Date) должен возвращать множество содержащее пользователей,
    //  которые решили любую задачу за выбранный период.
    assertArrayEquals(sort(logParser.getDoneTaskUsers(null, null).toArray()),
            new String[]{"Eduard Petrovich Morozko", "Vasya Pupkin"});
  
    //  Метод getDoneTaskUsers(Date, Date, int task) должен возвращать множество содержащее пользователей,
    //  которые решили задачу с номером task за выбранный период.
    //  На ОК проверять не нужно
    assertArrayEquals(sort(logParser.getDoneTaskUsers(null, null, 15).toArray()),
            new String[]{"Vasya Pupkin"});
    assert (logParser.getDoneTaskUsers(null, null, 48).size() == 1);
  
    try {
      //   Метод getDatesForUserAndEvent(String, Event, Date, Date) должен возвращать множество дат,
      //   когда переданный пользователь произвел переданное событие за выбранный период.
      assertArrayEquals(sort(logParser.getDatesForUserAndEvent("Eduard Petrovich Morozko", Event.WRITE_MESSAGE, after, before).toArray()),
              new Date[]{new SimpleDateFormat("d.M.yyyy H:m:s").parse("11.12.2013 10:11:12"),
                      new SimpleDateFormat("d.M.yyyy H:m:s").parse("12.12.2013 21:56:30")});
    
      //   Метод getDatesWhenSomethingFailed(Date, Date) должен возвращать множество дат,
      //   когда любое событие не выполнилось за выбранный период.
      assertArrayEquals(sort(logParser.getDatesWhenSomethingFailed(after, before).toArray()),
              new Date[]{new SimpleDateFormat("d.M.yyyy H:m:s").parse("11.12.2013 10:11:12")});
    
      //   Метод getDatesWhenErrorHappened(Date, Date) должен возвращать множество дат,
      //   когда любое событие закончилось ошибкой за выбранный период.
      assertArrayEquals(sort(logParser.getDatesWhenErrorHappened(null, null).toArray()),
              new Date[]{new SimpleDateFormat("d.M.yyyy H:m:s").parse("30.01.2014 12:56:22")});
    
      //   Метод getDateWhenUserLoggedFirstTime(String, Date, Date) должен возвращать дату,
      //   когда переданный пользователь впервые залогинился за выбранный период. Если такой даты в логах нет - null.
      assert (logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko", null, before).compareTo(
              new SimpleDateFormat("d.M.yyyy H:m:s").parse("03.01.2014 03:45:23")) == 0);
    
      //   Метод getDateWhenUserSolvedTask(String, int, Date, Date) должен возвращать дату,
      //   когда переданный пользователь впервые попытался решить задачу с номером task за выбранный период. Если такой даты в логах нет - null.
      assert (logParser.getDateWhenUserSolvedTask("Vasya Pupkin", 18, null, null).compareTo(
              new SimpleDateFormat("d.M.yyyy H:m:s").parse("30.01.2014 12:56:22")) == 0);
      assert (logParser.getDateWhenUserSolvedTask("Vasya Pupkin", 18, null, before) == null);
    
      //   Метод getDateWhenUserDoneTask(String, int, Date, Date) должен возвращать дату,
      //   когда переданный пользователь впервые решил задачу с номером task за выбранный период. Если такой даты в логах нет - null.
      assert (logParser.getDateWhenUserDoneTask("Vasya Pupkin", 15, null, before).compareTo(
              new SimpleDateFormat("d.M.yyyy H:m:s").parse("30.08.2012 16:08:40")) == 0);
      assert (logParser.getDateWhenUserDoneTask("Vasya Pupkin", 18, null, before) == null);
    
      //   Метод getDatesWhenUserWroteMessage(String, Date, Date) должен возвращать множество дат,
      //   когда переданный пользователь написал сообщение за выбранный период.
      assertArrayEquals(sort(logParser.getDatesWhenUserWroteMessage("Eduard Petrovich Morozko", after, before).toArray()),
              new Date[]{new SimpleDateFormat("d.M.yyyy H:m:s").parse("11.12.2013 10:11:12"),
                      new SimpleDateFormat("d.M.yyyy H:m:s").parse("12.12.2013 21:56:30")});
    
      //   Метод getDatesWhenUserDownloadedPlugin(String, Date, Date) должен возвращать множество дат,
      //   когда переданный пользователь скачал плагин за выбранный период.
      assertArrayEquals(sort(logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko", null, before).toArray()),
              new Date[]{new SimpleDateFormat("d.M.yyyy H:m:s").parse("13.09.2013 5:04:50")});
    } catch (ParseException e) {
      e.printStackTrace();
    }

//    Метод getNumberOfAllEvents(Date, Date) должен возвращать количество уникальных событий за выбранный период.
    assert (logParser.getNumberOfAllEvents(after, before) == 2);

//    Метод getAllEvents(Date, Date) должен возвращать множество уникальных событий за выбранный период.
    assertArrayEquals(sort(logParser.getAllEvents(after, before).toArray()),
            new Event[]{Event.LOGIN, Event.WRITE_MESSAGE});

//    Метод getEventsForIP(String, Date, Date) должен возвращать множество уникальных событий,
//    которые происходили с переданного IP адреса за выбранный период.
    assertArrayEquals(sort(logParser.getEventsForIP("146.34.15.5", after, before).toArray()),
            new Event[]{Event.LOGIN, Event.WRITE_MESSAGE});

//    Метод getEventsForUser(String, Date, Date) должен возвращать множество уникальных событий,
//    которые произвел переданный пользователь за выбранный период.
    assertArrayEquals(sort(logParser.getEventsForUser("Eduard Petrovich Morozko", after, before).toArray()),
            new Event[]{Event.LOGIN, Event.WRITE_MESSAGE});

//    Метод getFailedEvents(Date, Date) должен возвращать множество уникальных событий,
//    у которых статус выполнения FAILED за выбранный период.
    assertArrayEquals(sort(logParser.getFailedEvents(null, null).toArray()),
            new Event[]{Event.WRITE_MESSAGE, Event.DONE_TASK});

//    Метод getErrorEvents(Date, Date) должен возвращать множество уникальных событий,
//    у которых статус выполнения ERROR за выбранный период.
    assertArrayEquals(sort(logParser.getErrorEvents(null, null).toArray()),
            new Event[]{Event.SOLVE_TASK});

//    Метод getNumberOfAttemptToSolveTask(int, Date, Date) должен возвращать количество попыток
//    решить задачу с номером task за выбранный период.
    assert (logParser.getNumberOfAttemptToSolveTask(18, null, null) == 3);

//    Метод getNumberOfSuccessfulAttemptToSolveTask(int, Date, Date) должен возвращать количество
//    успешных решений задачи с номером task за выбранный период.
    assert (logParser.getNumberOfSuccessfulAttemptToSolveTask(15, null, null) == 1);
    assert (logParser.getNumberOfSuccessfulAttemptToSolveTask(48, null, null) == 1);

//    Метод getAllSolvedTasksAndTheirNumber(Date, Date) должен возвращать мапу
//    (номер_задачи : количество_попыток_решить_ее) за выбранный период.
    assertTrue(logParser.getAllSolvedTasksAndTheirNumber(null, null).equals(
            new HashMap<Integer, Integer>() {{
              put(1, 1);
              put(18, 3);
            }}));

//    Метод getAllDoneTasksAndTheirNumber(Date, Date) должен возвращать мапу
//    (номер_задачи : сколько_раз_ее_решили) за выбранный период.
    assertTrue(logParser.getAllDoneTasksAndTheirNumber(null, null).equals(
            new HashMap<Integer, Integer>() {{
              put(15, 1);
              put(48, 1);
            }}));

//    Вызов метода execute("get ip") класса LogParser должен возвращать
//    множество (Set<String>) содержащее все уникальные IP адреса.
    assertArrayEquals(sort(logParser.execute("get ip").toArray()),
            new String[]{"12.12.12.12", "120.120.120.122", "127.0.0.1", "146.34.15.5", "192.168.100.2"});

//    Вызов метода execute("get user") класса LogParser должен возвращать
//    множество (Set<String>) содержащее всех уникальных пользователей.
    assertArrayEquals(sort(logParser.execute("get user").toArray()),
            new String[]{"Amigo", "Eduard Petrovich Morozko", "Vasya Pupkin"});

//    Вызов метода execute("get date") класса LogParser должен возвращать
//    множество (Set<Date>) содержащее все уникальные даты.
    try {
      assertArrayEquals(sort(logParser.execute("get date").toArray()),
              new Date[]{new SimpleDateFormat("d.M.yyyy H:m:s").parse("30.08.2012 16:08:13"),
                      new SimpleDateFormat("d.M.yyyy H:m:s").parse("30.08.2012 16:08:40"),
                      new SimpleDateFormat("d.M.yyyy H:m:s").parse("13.09.2013 5:04:50"),
                      new SimpleDateFormat("d.M.yyyy H:m:s").parse("11.12.2013 10:11:12"),
                      new SimpleDateFormat("d.M.yyyy H:m:s").parse("12.12.2013 21:56:30"),
                      new SimpleDateFormat("d.M.yyyy H:m:s").parse("03.01.2014 03:45:23"),
                      new SimpleDateFormat("d.M.yyyy H:m:s").parse("30.01.2014 12:56:22"),
                      new SimpleDateFormat("d.M.yyyy H:m:s").parse("14.11.2015 07:08:01"),
                      new SimpleDateFormat("d.M.yyyy H:m:s").parse("19.03.2016 00:00:00"),
                      new SimpleDateFormat("d.M.yyyy H:m:s").parse("05.01.2021 20:22:55"),
                      new SimpleDateFormat("d.M.yyyy H:m:s").parse("14.10.2021 11:38:21"),
                      new SimpleDateFormat("d.M.yyyy H:m:s").parse("21.10.2021 19:45:25"),
                      new SimpleDateFormat("d.M.yyyy H:m:s").parse("29.2.2028 5:4:7"),
              });
    } catch (ParseException e) {
      e.printStackTrace();
    }

//    Вызов метода execute("get event") класса LogParser должен возвращать
//    множество (Set<Event>) содержащее все уникальные события.
    assertArrayEquals(sort(logParser.execute("get event").toArray()),
            new Event[]{Event.LOGIN, Event.DOWNLOAD_PLUGIN, Event.WRITE_MESSAGE, Event.SOLVE_TASK, Event.DONE_TASK});

//    Вызов метода execute("get status") класса LogParser должен возвращать
//    множество (Set<Status>) содержащее все уникальные статусы.
    assertArrayEquals(sort(logParser.execute("get status").toArray()),
            new Status[]{Status.OK, Status.FAILED, Status.ERROR});

//    Вызов метода execute с параметром "get ip for user = "[any_user]"" должен возвращать
//    множество уникальных IP адресов, с которых работал пользователь с именем [any_user].
    assertArrayEquals(sort(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\"").toArray()),
            new String[]{"127.0.0.1", "146.34.15.5"});

//    Вызов метода execute с параметром "get ip for date = "[any_date]"" должен возвращать
//    множество уникальных IP адресов, события с которых произведены в указанное время [any_date].
    assertArrayEquals(sort(logParser.execute("get ip for date = \"30.01.2014 12:56:22\"").toArray()),
            new String[]{"192.168.100.2"});


//    Вызов метода execute с параметром "get ip for event = "[any_event]"" должен возвращать
//    множество уникальных IP адресов, у которых событие равно [any_event].
    assertArrayEquals(sort(logParser.execute("get ip for event = \"SOLVE_TASK\"").toArray()),
            new String[]{"12.12.12.12", "120.120.120.122", "192.168.100.2"});
  
  }
  
  private Object[] sort(Object[] array) {
    Arrays.sort(array);
    return array;
  }
}