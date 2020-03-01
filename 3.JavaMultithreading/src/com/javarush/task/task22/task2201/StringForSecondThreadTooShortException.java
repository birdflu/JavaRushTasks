package com.javarush.task.task22.task2201;

import java.io.Externalizable;

public class StringForSecondThreadTooShortException extends RuntimeException {
  public StringForSecondThreadTooShortException() {
  }

  public StringForSecondThreadTooShortException(Throwable cause) {
    super(cause);
  }
}
