package com.javarush.task.task39.task3913streams;

import com.javarush.task.task39.task3913streams.query.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
  private final Path logDir;
  private final List<LogEntry> logEntries = new ArrayList<>();
  
  public LogParser(Path logDir) {
    this.logDir = logDir;
    loadData();
  }
  
  private List<File> listFilesForFolder(final File folder) {
    return Arrays.stream(Objects.requireNonNull(folder.listFiles()))
            .filter(file -> ".log".equals(getExtension(file)))
            .collect(Collectors.toList());
  }
  
  private String getExtension(File fileEntry) {
    return fileEntry.getName().substring(fileEntry.getName().length() - 4);
  }
  
  private void loadData() {
    this.logEntries.addAll(
            listFilesForFolder(logDir.toFile())
                    .stream()
                    .map(this::getEntries)
                    .flatMap(List::stream)
                    .collect(Collectors.toList())
    );
  }
  
  private List<LogEntry> getEntries(File file) {
    try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
      return stream
              .map(LogEntry::new)
              .collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }
  
  private List<LogEntry> getEntries(Date after, Date before) {
    return  this.logEntries.stream()
            .filter(e -> e.getDate().compareTo(after == null ? new Date(0L) : after) >= 0
                    && e.getDate().compareTo(before == null ? new Date(Long.MAX_VALUE) : before) <= 0)
            .collect(Collectors.toList());
    
  }
  
  private Set<String> getUsers(String ip, Event event, Status status, Integer task, Date after, Date before) {
    return getEntries(after, before).stream()
            .filter(e -> ip != null && ip.equals(e.getIp()) ||
                    event != null && task == null && event.equals(e.getEvent()) ||
                    task != null && task.equals(e.getTask()) ||
                    status != null && status.equals(e.getStatus()) ||
                    (ip == null && event == null && status == null && task == null)
            )
            .map(LogEntry::getUser).collect(Collectors.toSet());
  }
  
  private Set<Date> getDates(String ip, String user, Event event, Status status, Integer task, Date after, Date before) {
    return getEntries(after, before).stream()
            .filter(e -> ip != null && ip.equals(e.getIp()) ||
                    user != null && ip == null && event == null && task == null && user.equals(e.getUser()) ||
                    (user != null && event != null && task == null && user.equals(e.getUser()) && event == e.getEvent()) ||
                    (user != null && event != null && task != null &&
                            user.equals(e.getUser()) && event == e.getEvent() && task.equals(e.getTask())) ||
                    (user != null && status != null && user.equals(e.getUser()) && status == e.getStatus()) ||
                    (ip == null && user == null && status != null && event == null && status == e.getStatus()) ||
                    (ip == null && user == null && status == null && event != null && event == e.getEvent()) ||
                    (ip == null && user == null && event == null && status == null && task == null))
            .map(LogEntry::getDate).collect(Collectors.toSet());
  }
  
  private Date getFirstDate(String user, Event event, Integer task, Date after, Date before) {
    return getDates(null, user, event, null, task, after, before).stream()
            .sorted()
            .findFirst().orElse(null);
    
  }
  
  public Set<Event> getEvents(String ip, String user, Event event, Status status, Integer task, Date after, Date before) {
    return getEntries(after, before).stream()
            .filter(e -> ip != null && ip.equals(e.getIp()) ||
                    user != null && user.equals(e.getUser()) ||
                    event != null && task == null && event.equals(e.getEvent()) ||
                    task != null && task.equals(e.getTask()) ||
                    status != null && status.equals(e.getStatus()) ||
                    (ip == null && event == null && status == null && task == null)
            )
            .map(LogEntry::getEvent).collect(Collectors.toSet());
  }
  
  private Map<Integer, Integer> getAllTasksAndTheirNumberMap(Event event, Date after, Date before) {
    return getEntries(after, before).stream()
            .filter(e -> event.equals(e.getEvent()))
            .collect(Collectors.groupingBy(LogEntry::getTask, Collectors.reducing(0, e -> 1, Integer::sum)));
  }
  
  @Override
  public int getNumberOfUniqueIPs(Date after, Date before) {
    return getUniqueIPs(after, before).size();
  }
  
  @Override
  public Set<String> getUniqueIPs(Date after, Date before) {
    return getEntries(after, before).stream()
            .map(LogEntry::getIp)
            .collect(Collectors.toSet());
  }
  
  @Override
  public Set<String> getIPsForUser(String user, Date after, Date before) {
    return getEntries(after, before).stream()
            .filter(e -> user.equals(e.getUser()))
            .map(LogEntry::getIp)
            .collect(Collectors.toSet());
  }
  
  @Override
  public Set<String> getIPsForEvent(Event event, Date after, Date before) {
    return getEntries(after, before).stream()
            .filter(e -> event.equals(e.getEvent()))
            .map(LogEntry::getIp)
            .collect(Collectors.toSet());
  }
  
  @Override
  public Set<String> getIPsForStatus(Status status, Date after, Date before) {
    return getEntries(after, before).stream()
            .filter(e -> status.equals(e.getStatus()))
            .map(LogEntry::getIp)
            .collect(Collectors.toSet());
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
    return (int) getEntries(after, before).stream()
            .filter(e -> user != null && user.equals(e.getUser()))
            .map(LogEntry::getEvent)
            .distinct()
            .count();
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
    return getAllTasksAndTheirNumberMap(Event.SOLVE_TASK, after, before);
  }
  
  @Override
  public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
    return getAllTasksAndTheirNumberMap(Event.DONE_TASK, after, before);
  }
  
  @Override
  public Set<Object> execute(String query) {
    Query q = new Query(query);
    return getEntries(q.getConditionStartDate(), q.getConditionEndDate()).stream()
            .filter(e -> "ip".equals(q.getConditionKey()) && q.getConditionValue().equals(e.getIp()) ||
                    "user".equals(q.getConditionKey()) && q.getConditionValue().equals(e.getUser()) ||
                    "date".equals(q.getConditionKey()) && q.getConditionValueAsDate().equals(e.getDate()) ||
                    "event".equals(q.getConditionKey()) && Event.valueOf(q.getConditionValue()).equals(e.getEvent()) ||
                    "status".equals(q.getConditionKey()) && Status.valueOf(q.getConditionValue()).equals(e.getStatus()) ||
                    q.getConditionKey() == null)
            .map(logEntry -> logEntry.getObject(q.getFieldName())).collect(Collectors.toSet());
  }
}