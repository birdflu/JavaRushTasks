package com.javarush.task.task39.task3913streams;

import com.javarush.task.task39.task3913streams.query.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
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
              (status != null && status == logEntry.getStatus()) ||
              (ip == null && event == null && status == null && task == null)) {
        uniqueUsers.add(logEntry.getUser());
      }
    }
    return uniqueUsers;
  }
  
  private Set<Date> getDates(String ip, String user, Event event, Status status, Integer task, Date after, Date before) {
    Set<Date> uniqueDates = new HashSet<>();
    for (LogEntry logEntry : getEntries(after, before)) {
      if ((ip != null && ip.equals(logEntry.getIp())) ||
              user != null && ip == null && event == null && task == null && user.equals(logEntry.getUser()) ||
              (user != null && event != null && task == null && user.equals(logEntry.getUser()) && event == logEntry.getEvent()) ||
              (user != null && event != null && task != null &&
                      user.equals(logEntry.getUser()) && event == logEntry.getEvent() && task.equals(logEntry.getTask())) ||
              (user != null && status != null && user.equals(logEntry.getUser()) && status == logEntry.getStatus()) ||
              (ip == null && user == null && status != null && event == null && status == logEntry.getStatus()) ||
              (ip == null && user == null && status == null && event != null && event == logEntry.getEvent()) ||
              (ip == null && user == null && event == null && status == null && task == null)) {
        uniqueDates.add(logEntry.getDate());
      }
    }
    return uniqueDates;
  }
  
  private Date getFirstDate(String user, Event event, Integer task, Date after, Date before) {
    Set<Date> dates = getDates(null, user, event, null, task, after, before);
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
  
  public Set<Status> getStatuses(String ip, String user, Event event, Integer task, Date after, Date before) {
    Set<Status> uniqueStatuses = new HashSet<>();
    for (LogEntry logEntry : getEntries(after, before)) {
      if ((ip != null && ip.equals(logEntry.getIp())) ||
              (user != null && user.equals(logEntry.getUser())) ||
              (event != null && event == logEntry.getEvent()) ||
              (ip == null && user == null && event == null && task == null)) {
        uniqueStatuses.add(logEntry.getStatus());
      }
    }
    return uniqueStatuses;
  }
  
  private Map<Integer, Integer> getAllTasksAndTheirNumberMap(Event event, Status status, Date after, Date before) {
    Map<Integer, Integer> map = new HashMap<>();
    for (LogEntry logEntry : getEntries(after, before)) {
      if (event == logEntry.getEvent() && status == logEntry.getStatus() ||
              (event == logEntry.getEvent() && status == null)) {
        Integer task = logEntry.getTask();
        if (map.containsKey(task)) {
          map.put(task, map.get(task) + 1);
        } else {
          map.put(task, 1);
        }
      }
    }
    return map;
  }
  
  public Set<Status> getAllStatuses(Date after, Date before) {
    return getStatuses(null, null, null, null, after, before);
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
    return getDates(null, user, event, null, null, after, before);
  }
  
  @Override
  public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
    return getDates(null, null, null, Status.FAILED, null, after, before);
  }
  
  @Override
  public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
    return getDates(null, null, null, Status.ERROR, null, after, before);
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
    return getDates(null, user, Event.WRITE_MESSAGE, null, null, after, before);
  }
  
  @Override
  public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
    return getDates(null, user, Event.DOWNLOAD_PLUGIN, null, null, after, before);
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
    return count == null ? 0 : count;
  }
  
  @Override
  public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
    Integer count = getAllDoneTasksAndTheirNumber(after, before).get(task);
    return count == null ? 0 : count;
  }
  
  @Override
  public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
    return getAllTasksAndTheirNumberMap(Event.SOLVE_TASK, null, after, before);
  }
  
  @Override
  public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
    return getAllTasksAndTheirNumberMap(Event.DONE_TASK, null, after, before);
  }
  
  @Override
  public Set<Object> execute(String query) {
    Query q = new Query(query);
    if (q.getConditionKey() == null) {
      if ("ip".equals(q.getFieldName())) return new HashSet<>(getUniqueIPs(null, null));
      if ("user".equals(q.getFieldName())) return new HashSet<>(getAllUsers());
      if ("date".equals(q.getFieldName())) return new HashSet<>(getDates(null, null, null, null, null, null, null));
      if ("event".equals(q.getFieldName())) return new HashSet<>(getAllEvents(null, null));
      if ("status".equals(q.getFieldName())) return new HashSet<>(getAllStatuses(null, null));
    } else {
      if ("ip".equals(q.getFieldName())) {
        // System.out.println(q.toString());
        if ("user".equals(q.getConditionKey()))
          return new HashSet<>(getIPs(q.getConditionValue(), null, null, q.getConditionStartDate(), q.getConditionEndDate()));
        if ("date".equals(q.getConditionKey())) {
          Date date;
          try {
            date = new SimpleDateFormat("d.M.yyyy H:m:s").parse(q.getConditionValue());
            if (q.getConditionStartDate() != null && q.getConditionEndDate() != null &&
                    date.after(q.getConditionStartDate()) && date.before(q.getConditionEndDate()) ||
                    (q.getConditionStartDate() == null && q.getConditionEndDate() == null))
              return new HashSet<>(getUniqueIPs(date, date));
            else {
              Set<String> empty = new HashSet<>();
              return new HashSet<>(empty);
            }
          } catch (ParseException e) {
            e.printStackTrace();
          }
        }
        if ("event".equals(q.getConditionKey()))
          return new HashSet<>(getIPs(null, Event.valueOf(q.getConditionValue()), null, q.getConditionStartDate(), q.getConditionEndDate()));
        if ("status".equals(q.getConditionKey()))
          return new HashSet<>(getIPs(null, null, Status.valueOf(q.getConditionValue()), q.getConditionStartDate(), q.getConditionEndDate()));
      }
      if ("user".equals(q.getFieldName())) {
        if ("ip".equals(q.getConditionKey()))
          return new HashSet<>(getUsersForIP(q.getConditionValue(), q.getConditionStartDate(), q.getConditionEndDate()));
        if ("date".equals(q.getConditionKey())) {
          Date date;
          try {
            date = new SimpleDateFormat("d.M.yyyy H:m:s").parse(q.getConditionValue());
            if (q.getConditionStartDate() != null && q.getConditionEndDate() != null &&
                    date.after(q.getConditionStartDate()) && date.before(q.getConditionEndDate()) ||
                    (q.getConditionStartDate() == null && q.getConditionEndDate() == null))
              return new HashSet<>(getUsers(null, null, null, null, date, date));
            else {
              Set<String> empty = new HashSet<>();
              return new HashSet<>(empty);
            }
          } catch (ParseException e) {
            e.printStackTrace();
          }
        }
        if ("event".equals(q.getConditionKey()))
          return new HashSet<>(getUsers(null, Event.valueOf(q.getConditionValue()), null, null, q.getConditionStartDate(), q.getConditionEndDate()));
        if ("status".equals(q.getConditionKey()))
          return new HashSet<>(getUsers(null, null, Status.valueOf(q.getConditionValue()), null, q.getConditionStartDate(), q.getConditionEndDate()));
      }
      if ("date".equals(q.getFieldName())) {
        if ("ip".equals(q.getConditionKey()))
          return new HashSet<>(getDates(q.getConditionValue(), null, null, null, null, q.getConditionStartDate(), q.getConditionEndDate()));
        if ("user".equals(q.getConditionKey()))
          return new HashSet<>(getDates(null, q.getConditionValue(), null, null, null, q.getConditionStartDate(), q.getConditionEndDate()));
        if ("event".equals(q.getConditionKey()))
          return new HashSet<>(getDates(null, null, Event.valueOf(q.getConditionValue()), null, null, q.getConditionStartDate(), q.getConditionEndDate()));
        if ("status".equals(q.getConditionKey()))
          return new HashSet<>(getDates(null, null, null, Status.valueOf(q.getConditionValue()), null, q.getConditionStartDate(), q.getConditionEndDate()));
      }
      if ("event".equals(q.getFieldName())) {
        if ("ip".equals(q.getConditionKey()))
          return new HashSet<>(getEvents(q.getConditionValue(), null, null, null, null, q.getConditionStartDate(), q.getConditionEndDate()));
        if ("user".equals(q.getConditionKey()))
          return new HashSet<>(getEvents(null, q.getConditionValue(), null, null, null, q.getConditionStartDate(), q.getConditionEndDate()));
        if ("date".equals(q.getConditionKey())) {
          Date date;
          try {
            date = new SimpleDateFormat("d.M.yyyy H:m:s").parse(q.getConditionValue());
            if (q.getConditionStartDate() != null && q.getConditionEndDate() != null &&
                    date.after(q.getConditionStartDate()) && date.before(q.getConditionEndDate()) ||
                    (q.getConditionStartDate() == null && q.getConditionEndDate() == null))
              return new HashSet<>(getAllEvents(date, date));
            else {
              Set<Event> empty = new HashSet<>();
              return new HashSet<>(empty);
            }
          } catch (ParseException e) {
            e.printStackTrace();
          }
        }
        if ("status".equals(q.getConditionKey()))
          return new HashSet<>(getEvents(null, null, null, Status.valueOf(q.getConditionValue()), null, q.getConditionStartDate(), q.getConditionEndDate()));
      }
      if ("status".equals(q.getFieldName())) {
        if ("ip".equals(q.getConditionKey()))
          return new HashSet<>(getStatuses(q.getConditionValue(), null, null, null, q.getConditionStartDate(), q.getConditionEndDate()));
        if ("user".equals(q.getConditionKey()))
          return new HashSet<>(getStatuses(null, q.getConditionValue(), null, null, q.getConditionStartDate(), q.getConditionEndDate()));
        if ("date".equals(q.getConditionKey())) {
          Date date;
          try {
            date = new SimpleDateFormat("d.M.yyyy H:m:s").parse(q.getConditionValue());
            if (q.getConditionStartDate() != null && q.getConditionEndDate() != null &&
                    date.after(q.getConditionStartDate()) && date.before(q.getConditionEndDate()) ||
                    (q.getConditionStartDate() == null && q.getConditionEndDate() == null))
              return new HashSet<>(getStatuses(null, null, null, null, date, date) );
            else {
              Set<Status> empty = new HashSet<>();
              return new HashSet<>(empty);
            }
          } catch (ParseException e) {
            e.printStackTrace();
          }
        }
        if ("event".equals(q.getConditionKey()))
          return new HashSet<>(getStatuses(null, null, Event.valueOf(q.getConditionValue()), null, q.getConditionStartDate(), q.getConditionEndDate()));
      }
    }
    
    return null;
  }
}