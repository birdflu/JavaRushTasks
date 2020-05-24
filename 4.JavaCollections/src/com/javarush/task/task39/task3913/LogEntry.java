package com.javarush.task.task39.task3913;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogEntry {
  private String ip;
  private String user;
  private Date date; // day.month.year hour:minute:second
  private Event event;
  private int taskNumber = Integer.MIN_VALUE;
  private Status status;
  
  public LogEntry(String entry) {
    // 192.168.100.2	Vasya Pupkin	30.01.2014 12:56:22	SOLVE_TASK 18	ERROR
    String[] elements = entry.split("[ \\t]");
    
    int i = 0;
    this.ip = elements[i];
  
    i = i + 1;
    StringBuilder user = new StringBuilder();
    while (true) {
      if (Character.isDigit(elements[i].toCharArray()[0])) {
        break;
      } else {
        user.append(elements[i++]);
        user.append(" ");
      }
    }
    user.deleteCharAt(user.length() - 1);
    this.user = user.toString();
  
    date = parseDate(elements[i] + " " + elements[i + 1]);
  
    i = i + 2;
    this.event = Event.valueOf(elements[i]);
  
    i = i + 1;
    if (i < elements.length - 1) {
      this.taskNumber = Integer.parseInt(elements[i]);
      i = i + 1;
    }
  
    this.status = Status.valueOf(elements[i]);
  }
  
  private Date parseDate(String source) {
    Date date = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.yyyy H:m:s");
    try {
      date = dateFormat.parse(source);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }
  
  public String getIp() {
    return ip;
  }
  
  public Date getDate() {
    return date;
  }
  
  public String getUser() {
    return user;
  }
  
  public Event getEvent() {
    return event;
  }
  
  public Status getStatus() {
    return status;
  }
  
  @Override
  public String toString() {
    return "LogEntry{" +
            "ip='" + ip + '\'' +
            ", user='" + user + '\'' +
            ", date=" + date +
            ", event=" + event +
            ", taskNumber=" + (taskNumber == Integer.MIN_VALUE ? "null": taskNumber) +
            ", status=" + status +
            '}';
  }
}
