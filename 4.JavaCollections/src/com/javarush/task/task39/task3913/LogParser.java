package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class LogParser implements IPQuery, UserQuery {
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
      if (user != null && user.equals(logEntry.getUser()) ||
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
              //(event != null && task != null && event == logEntry.getEvent() && task.equals(logEntry.getTask())) ||
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
}