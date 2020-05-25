package com.javarush.task.task39.task3913.query;

public class Query {
  private  String fieldName;
  private  String conditionKey = null;
  private  String conditionValue = null;
  
  public Query(String query) {
    // get ip for user = "Vasya"
    String[] queryTokens = query.split(" ");
    if (queryTokens.length == 2) {
      this.fieldName = queryTokens[1];
    } else if (queryTokens.length == 6) {
      this.fieldName = queryTokens[1];
      this.conditionKey = queryTokens[3];
      this.conditionValue = queryTokens[5];
    } else { throw new VerifyError(); };
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
