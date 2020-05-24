package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class LogParser implements IPQuery {
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
  
  @Override
  public int getNumberOfUniqueIPs(Date after, Date before) {
    return getUniqueIPs(after, before).size();
  }
  
  @Override
  public Set<String> getUniqueIPs(Date after, Date before) {
    return getIPs(null, null,null, after, before);
  }
  
  @Override
  public Set<String> getIPsForUser(String user, Date after, Date before) {
    return getIPs(user, null,null, after, before);
  }
  
  @Override
  public Set<String> getIPsForEvent(Event event, Date after, Date before) {
    return getIPs(null, event,null, after, before);
  }
  
  @Override
  public Set<String> getIPsForStatus(Status status, Date after, Date before) {
    return getIPs(null, null, status, after, before);
  }
}