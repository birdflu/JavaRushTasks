package com.javarush.task.task39.task3913streams;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class LogEntry {
  private String ip;
  private String user;
  private Date date; // day.month.year hour:minute:second
  private Event event;
  private Integer task;
  private Status status;
  
  private class FullName {
    int nextIndex;
    String fullName;
  
    public FullName(int nextIndex, String fullName) {
      this.nextIndex = nextIndex;
      this.fullName = fullName;
    }
  }
  
  public LogEntry(String entry) {
    // 192.168.100.2	Vasya Pupkin	30.01.2014 12:56:22	SOLVE_TASK 18	ERROR
    String[] elements = entry.split("[ \\t]");
    
    this.ip = parseIP(elements, 0);

    FullName fullName = parseFullName(elements, 1);
    this.user = fullName.fullName;
    this.date = parseDate(elements, fullName.nextIndex);
    this.event = parseEvent(elements, fullName.nextIndex + 2);
  
    if (fullName.nextIndex + 3 < elements.length - 1) {
      this.task = parseTask(elements, fullName.nextIndex + 3);
      this.status = parseStatus(elements, fullName.nextIndex + 4);
    }
    else
      this.status = parseStatus(elements, fullName.nextIndex + 3);
    
  }
  
  private Status parseStatus(String[] elements, int i) {
    return Status.valueOf(elements[i]);
  }
  
  private int parseTask(String[] elements, int i) {
    return Integer.parseInt(elements[i]);
  }
  
  private FullName parseFullName(String[] elements, int i) {
    StringJoiner user = new StringJoiner(" ");
    while (true) {
      if (Character.isDigit(elements[i].toCharArray()[0])) {
        break;
      } else {
        user.add(elements[i++]);
      }
    }
    return new FullName(i, user.toString());
  }
  
  private Event parseEvent(String[] elements, int i) {
    return Event.valueOf(elements[i]);
  }
  
  private String parseIP(String[] elements, int pos) {
    return elements[pos];
  }
  
  private Date parseDate(String[] elements, int pos) {
    String source = elements[pos] + " " + elements[pos + 1];
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
  
  public Integer getTask() {
    return task;
  }
  
  public Object getObject(String objectName) {
    if ("ip".equals(objectName)) return getIp();
    if ("user".equals(objectName)) return getUser();
    if ("date".equals(objectName)) return getDate();
    if ("event".equals(objectName)) return getEvent();
    if ("status".equals(objectName)) return getStatus();
    return null;
  }
  
  @Override
  public String toString() {
    return "LogEntry{" +
            "ip='" + ip + '\'' +
            ", user='" + user + '\'' +
            ", date=" + date +
            ", event=" + event +
            ", taskNumber=" + task +
            ", status=" + status +
            '}';
  }
}
