package com.javarush.task.task39.task3913.query;

public class Query {
  private String fieldName;
  private String conditionKey = null;
  private String conditionValue = null;
  
  public Query(String query) {
    String[] queryTokens = query.split(" ");
    if (queryTokens.length == 2) {
      this.fieldName = queryTokens[1];
    } else {
      this.fieldName = queryTokens[1];
      this.conditionKey = queryTokens[3];
      this.conditionValue = "";
      queryTokens[5] = queryTokens[5].substring(1);
      int lastIndex = queryTokens.length - 1;
      for (int i = 5; i < lastIndex; i++) {
        this.conditionValue = this.conditionValue + queryTokens[i] + " ";
      }
      queryTokens[lastIndex] = queryTokens[lastIndex].substring(0, queryTokens[lastIndex].length() - 1);
      this.conditionValue = this.conditionValue + queryTokens[lastIndex];
    }
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
}
