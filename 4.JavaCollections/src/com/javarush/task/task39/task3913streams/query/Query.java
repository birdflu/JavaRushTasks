package com.javarush.task.task39.task3913streams.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Query {
  private String fieldName;
  private String conditionKey = null;
  private String conditionValue = null;
  private Date conditionStartDate = null;
  private Date conditionEndDate = null;
  
  public Query(String query) {
    // query = "get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"";
    int get_BeginIndex = query.indexOf("get ");
    int fieldBeginIndex = get_BeginIndex + "get ".length();
    int _for_BeginIndex = query.indexOf(" for ");
    int fieldEndIndex = -1;
    int keyBeginIndex = -1;
    int firstBraceIndex = -1;
    int keyEndIndex = -1;
    int secondBraceIndex = -1;
    int thirdBraceIndex = -1;
    int forthBraceIndex = -1;
    int fifthBraceIndex = -1;
    int sixthBraceIndex = -1;
  
    if (_for_BeginIndex == -1) {
      fieldEndIndex = query.length();
    } else {
      fieldEndIndex = _for_BeginIndex;
      keyBeginIndex = _for_BeginIndex + " for ".length();
      firstBraceIndex = query.indexOf("\"");
      keyEndIndex = firstBraceIndex - " = \"".length() + 1;
      secondBraceIndex = query.indexOf("\"", firstBraceIndex + 1);
      thirdBraceIndex = query.indexOf("\"", secondBraceIndex + 1);
      if (thirdBraceIndex > 0) {
        forthBraceIndex = query.indexOf("\"", thirdBraceIndex + 1);
        fifthBraceIndex = query.indexOf("\"", forthBraceIndex + 1);
        sixthBraceIndex = query.indexOf("\"", fifthBraceIndex + 1);
      }
    }
  
    fieldName = query.substring(fieldBeginIndex, fieldEndIndex);
    if (keyBeginIndex > 0) conditionKey = query.substring(keyBeginIndex, keyEndIndex);
    if (firstBraceIndex > 0) conditionValue = query.substring(firstBraceIndex + 1, secondBraceIndex);
    try {
      if (thirdBraceIndex > 0) {
        Date startDate = new SimpleDateFormat("d.M.yyyy H:m:s").parse(query.substring(thirdBraceIndex + 1, forthBraceIndex));
        //!!! between тут такой - даты after и before не должны попадать в результат
        long d = startDate.getTime();
        d = d + 500L;
        conditionStartDate = new Date(d);
      }
      if (fifthBraceIndex > 0) {
        Date endDate = new SimpleDateFormat("d.M.yyyy H:m:s").parse(query.substring(fifthBraceIndex + 1, sixthBraceIndex));
        long d = endDate.getTime();
        d = d - 500L;
        conditionEndDate = new Date(d);
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
  // Query{fieldName='ip', conditionKey='event', conditionValue='SOLVE_TASK',
    // conditionStartDate=Thu Jan 30 12:56:22 MSK 2014, conditionEndDate=Thu Oct 21 19:45:25 MSK 2021}
  
  }
  
  public String getFieldName() {
    return fieldName;
  }
  
  public String getConditionKey() {
    return conditionKey;
  }
  
  public String getConditionValue() {
    return conditionValue;
  }
  
  public Date getConditionStartDate() {
    return conditionStartDate;
  }
  
  public Date getConditionEndDate() {
    return conditionEndDate;
  }
  
  @Override
  public String toString() {
    return "Query{" +
            "fieldName='" + fieldName + '\'' +
            ", conditionKey='" + conditionKey + '\'' +
            ", conditionValue='" + conditionValue + '\'' +
            ", conditionStartDate=" + conditionStartDate +
            ", conditionEndDate=" + conditionEndDate +
            '}';
  }
}
