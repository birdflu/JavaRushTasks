package com.javarush.task.task31.task3109;

import java.util.Properties;

public class NullObject extends Properties {
  private Exception exception;

  public NullObject(Exception e) {
    this.exception = e;
  }
}