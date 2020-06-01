package com.javarush.task.task39.task3913streams.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Query {
  private final String fieldName;
  private String conditionKey = null;
  private String conditionValue = null;
  private Date conditionStartDate = null;
  private Date conditionEndDate = null;
  
  private static class Element {
    int startIndex;
    int endIndex;
    
    public Element(int startIndex, int endIndex) {
      this.startIndex = startIndex;
      this.endIndex = endIndex;
    }
    
    public Element() {
      this.startIndex = -1;
      this.endIndex = -1;
    }
  }
  
  private static class OffsetElements {
    private final String query;
    private final Element field;
    private Element key = new Element();
    private Element value = new Element();
    private Element data1 = new Element();
    private Element data2 = new Element();
    
    public OffsetElements(String query) {
      // query = "get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"";
      this.query = query;
      Element getStatement = new Element(query.indexOf("get "), query.indexOf("get ") + "get ".length() - 1);
      Element forStatement = new Element(query.indexOf(" for "), -1);
      
      if (forStatement.startIndex == -1) {
        field = new Element(getStatement.endIndex + 1, query.length());
      } else {
        field = new Element(getStatement.endIndex + 1, forStatement.startIndex);
        value = new Element(query.indexOf("\""), getSlashIndex(query.indexOf("\"")));
        key = new Element(forStatement.startIndex + " for ".length(), value.startIndex - " = \"".length() + 1);
        
        data1 = new Element(getSlashIndex(value.endIndex), -1);
        if (data1.startIndex > 0) {
          data1.endIndex = getSlashIndex(data1.startIndex);
          data2 = new Element(getSlashIndex(data1.endIndex),
                  getSlashIndex(getSlashIndex(data1.endIndex)));
        }
      }
    }
    
    private int getSlashIndex(int startIndex) {
      return query.indexOf("\"", startIndex + 1);
    }
  }
  
  public Query(String query) {
    OffsetElements e = new OffsetElements(query);
    
    fieldName = query.substring(e.field.startIndex, e.field.endIndex);
    if (e.key.startIndex > 0) conditionKey = query.substring(e.key.startIndex, e.key.endIndex);
    if (e.value.startIndex > 0) conditionValue = query.substring(e.value.startIndex + 1, e.value.endIndex);
    try {
      //!!! between тут такой - даты after и before не должны попадать в результат
      long delta = 500L;
      if (e.data1.startIndex > 0) {
        Date startDate = parseDate(query, e.data1.startIndex + 1, e.data1.endIndex);
        conditionStartDate = new Date(startDate.getTime() + delta);
      }
      if (e.data2.startIndex > 0) {
        Date endDate = parseDate(query, e.data2.startIndex + 1, e.data2.endIndex);
        conditionEndDate = new Date(endDate.getTime() - delta);
      }
    } catch (ParseException exception) {
      exception.printStackTrace();
    }
  }
  
  private Date parseDate(String query, int from, int to) throws ParseException {
    return new SimpleDateFormat("d.M.yyyy H:m:s").parse(query.substring(from, to));
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
  
  public Date getConditionValueAsDate() {
    Date date = null;
    try {
      date = new SimpleDateFormat("d.M.yyyy H:m:s").parse(conditionValue);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
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
