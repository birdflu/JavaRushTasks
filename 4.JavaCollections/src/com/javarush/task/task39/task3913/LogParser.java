package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.EventQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery {
  private final Path logDir;
  private List<LogEntry> logEntries = new ArrayList<>();
  
  public LogParser(Path logDir) {
    this.logDir = logDir;
    try {
      loadData();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private List<File> listFilesForFolder(final File folder) {
    List<File> pathList = new ArrayList<>();
    for (final File fileEntry : folder.listFiles()) {
      if (fileEntry.isDirectory()) {
        listFilesForFolder(fileEntry);
      } else {
        if (".log".equals(getExtension(fileEntry)))
          pathList.add(fileEntry);
      }
    }
    return pathList;
  }
  
  private String getExtension(File fileEntry) {
    return fileEntry.getName().substring(fileEntry.getName().length() - 4);
  }
  
  private void loadData() throws IOException {
    for (File file : listFilesForFolder(logDir.toFile())) {
      List<LogEntry> entries = getEntries(file);
      this.logEntries.addAll(entries);
    }
  }
  
  private List<LogEntry> getEntries(File file) throws IOException {
    List<LogEntry> logEntries = new ArrayList<>();
    BufferedReader fileReader = new BufferedReader(new FileReader(file));
    while (fileReader.ready()) {
      LogEntry logEntry = new LogEntry(fileReader.readLine());
      logEntries.add(logEntry);
    }
    return logEntries;
  }
  
  private List<LogEntry> getEntries(Date after, Date before) {
    if (after == null) after = new Date(0L);
    if (before == null) before = new Date(Long.MAX_VALUE);
    
    List<LogEntry> entries = new ArrayList<>();
    for (LogEntry logEntry : this.logEntries) {
      if (logEntry.getDate().compareTo(after) >= 0 && logEntry.getDate().compareTo(before) <= 0) {
        entries.add(logEntry);
      }
    }
    
    return entries;
  }
  
  private Set<String> getIPs(String user, Event event, Status status, Date after, Date before) {
    Set<String> uniqueIPs = new HashSet<>();
    for (LogEntry logEntry : getEntries(after, before)) {
      if ((user != null && user.equals(logEntry.getUser())) ||
              (event != null && event == logEntry.getEvent()) ||
              (status != null && status == logEntry.getStatus()) ||
              (user == null && event == null && status == null)) {
        uniqueIPs.add(logEntry.getIp());
      }
    }
    return uniqueIPs;
  }
  
  private Set<String> getUsers(String ip, Event event, Status status, Integer task, Date after, Date before) {
    Set<String> uniqueUsers = new HashSet<>();
    for (LogEntry logEntry : getEntries(after, before)) {
      if ((ip != null && ip.equals(logEntry.getIp())) ||
              (event != null && task == null && event == logEntry.getEvent()) ||
              (event != null && task != null && status != null && event == logEntry.getEvent()
                      && task.equals(logEntry.getTask()) && status.equals(logEntry.getStatus())) ||
              (event != null && task != null && status == null && event == logEntry.getEvent()
                      && task.equals(logEntry.getTask())) ||
              (ip == null && event == null && status == null && task == null)) {
        uniqueUsers.add(logEntry.getUser());
      }
    }
    return uniqueUsers;
  }
  
  private Set<Date> getDates(String user, Event event, Status status, Integer task, Date after, Date before) {
    Set<Date> uniqueDates = new HashSet<>();
    for (LogEntry logEntry : getEntries(after, before)) {
      if ((user != null && event != null && task == null && user.equals(logEntry.getUser()) && event == logEntry.getEvent()) ||
              (user != null && event != null && task != null &&
                      user.equals(logEntry.getUser()) && event == logEntry.getEvent() && task.equals(logEntry.getTask())) ||
              (user != null && status != null && user.equals(logEntry.getUser()) && status == logEntry.getStatus()) ||
              (user == null && status != null && status == logEntry.getStatus())) {
        uniqueDates.add(logEntry.getDate());
      }
    }
    return uniqueDates;
  }
  
  private Date getFirstDate(String user, Event event, Integer task, Date after, Date before) {
    Set<Date> dates = getDates(user, event, null, task, after, before);
    if (dates.size() == 0) return null;
    else {
      List<Date> result = new ArrayList();
      result.addAll(dates);
      Collections.sort(result);
      return result.get(0);
    }
  }
  
  public Set<Event> getEvents(String ip, String user, Event event, Status status, Integer task, Date after, Date before) {
    Set<Event> uniqueEvents = new HashSet<>();
    for (LogEntry logEntry : getEntries(after, before)) {
      if ((ip != null && ip.equals(logEntry.getIp())) ||
              (user != null && user.equals(logEntry.getUser())) ||
              (status != null && status == logEntry.getStatus()) ||
              (ip == null && user == null && event == null && status == null && task == null)) {
        uniqueEvents.add(logEntry.getEvent());
      }
    }
    return uniqueEvents;
  }
  
  private Map<Integer, Integer> getAllTasksAndTheirNumberMap(Event event, Status status, Date after, Date before) {
    Map<Integer, Integer> map = new HashMap<>();
    for (LogEntry logEntry : getEntries(after, before)) {
      if (event == logEntry.getEvent() && status == logEntry.getStatus() ||
              (event == logEntry.getEvent() && status == null)) {
        Integer task = logEntry.getTask();
        if (map.containsKey(task)) { map.put(task, map.get(task) + 1); }
        else { map.put(task, 1); }
      }
    }
    return map;
  }
  
  @Override
  public int getNumberOfUniqueIPs(Date after, Date before) {
    return getUniqueIPs(after, before).size();
  }
  
  @Override
  public Set<String> getUniqueIPs(Date after, Date before) {
    return getIPs(null, null, null, after, before);
  }
  
  @Override
  public Set<String> getIPsForUser(String user, Date after, Date before) {
    return getIPs(user, null, null, after, before);
  }
  
  @Override
  public Set<String> getIPsForEvent(Event event, Date after, Date before) {
    return getIPs(null, event, null, after, before);
  }
  
  @Override
  public Set<String> getIPsForStatus(Status status, Date after, Date before) {
    return getIPs(null, null, status, after, before);
  }
  
  @Override
  public Set<String> getAllUsers() {
    return getUsers(null, null, null, null, null, null);
  }
  
  @Override
  public int getNumberOfUsers(Date after, Date before) {
    return getUsers(null, null, null, null, after, before).size();
  }
  
  @Override
  public int getNumberOfUserEvents(String user, Date after, Date before) {
    Set<Event> uniqueEvents = new HashSet<>();
    for (LogEntry logEntry : getEntries(after, before)) {
      if (user != null && user.equals(logEntry.getUser())) {
        uniqueEvents.add(logEntry.getEvent());
      }
    }
    return uniqueEvents.size();
  }
  
  @Override
  public Set<String> getUsersForIP(String ip, Date after, Date before) {
    return getUsers(ip, null, null, null, after, before);
  }
  
  @Override
  public Set<String> getLoggedUsers(Date after, Date before) {
    return getUsers(null, Event.LOGIN, null, null, after, before);
  }
  
  @Override
  public Set<String> getDownloadedPluginUsers(Date after, Date before) {
    return getUsers(null, Event.DOWNLOAD_PLUGIN, null, null, after, before);
  }
  
  @Override
  public Set<String> getWroteMessageUsers(Date after, Date before) {
    return getUsers(null, Event.WRITE_MESSAGE, null, null, after, before);
  }
  
  @Override
  public Set<String> getSolvedTaskUsers(Date after, Date before) {
    return getUsers(null, Event.SOLVE_TASK, null, null, after, before);
  }
  
  @Override
  public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
    return getUsers(null, Event.SOLVE_TASK, null, task, after, before);
  }
  
  @Override
  public Set<String> getDoneTaskUsers(Date after, Date before) {
    return getUsers(null, Event.DONE_TASK, null, null, after, before);
  }
  
  @Override
  public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
    //  На ОК проверять не нужно (валидатор)
    return getUsers(null, Event.DONE_TASK, null, task, after, before);
  }
  
  @Override
  public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
    return getDates(user, event, null, null, after, before);
  }
  
  @Override
  public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
    return getDates(null, null, Status.FAILED, null, after, before);
  }
  
  @Override
  public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
    return getDates(null, null, Status.ERROR, null, after, before);
  }
  
  @Override
  public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
    return getFirstDate(user, Event.LOGIN, null, after, before);
  }
  
  @Override
  public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
    return getFirstDate(user, Event.SOLVE_TASK, task, after, before);
  }
  
  @Override
  public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
    return getFirstDate(user, Event.DONE_TASK, task, after, before);
  }
  
  @Override
  public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
    return getDates(user, Event.WRITE_MESSAGE, null, null, after, before);
  }
  
  @Override
  public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
    return getDates(user, Event.DOWNLOAD_PLUGIN, null, null, after, before);
  }
  
  @Override
  public int getNumberOfAllEvents(Date after, Date before) {
    return getEvents(null, null, null, null, null, after, before).size();
  }
  
  @Override
  public Set<Event> getAllEvents(Date after, Date before) {
    return getEvents(null, null, null, null, null, after, before);
  }
  
  @Override
  public Set<Event> getEventsForIP(String ip, Date after, Date before) {
    return getEvents(ip, null, null, null, null, after, before);
  }
  
  @Override
  public Set<Event> getEventsForUser(String user, Date after, Date before) {
    return getEvents(null, user, null, null, null, after, before);
  }
  
  @Override
  public Set<Event> getFailedEvents(Date after, Date before) {
    return getEvents(null, null, null, Status.FAILED, null, after, before);
  }
  
  @Override
  public Set<Event> getErrorEvents(Date after, Date before) {
    return getEvents(null, null, null, Status.ERROR, null, after, before);
  }
  
  @Override
  public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
    Integer count = getAllSolvedTasksAndTheirNumber(after, before).get(task);
    return count == null? 0: count;
  }
  
  @Override
  public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
    Integer count = getAllDoneTasksAndTheirNumber(after, before).get(task);
    return count == null? 0: count;
  }
  
  @Override
  public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
    return getAllTasksAndTheirNumberMap(Event.SOLVE_TASK , null, after, before);
  }
  
  @Override
  public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
    return getAllTasksAndTheirNumberMap(Event.DONE_TASK , null, after, before);
  }
}