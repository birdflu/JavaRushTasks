package com.javarush.task.task38.task3811;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value= RetentionPolicy.RUNTIME)
@Target(value= ElementType.TYPE)

// (Class, interface (including annotation type), or enum declaration).
public @interface Ticket {

  enum Priority {
    LOW, MEDIUM, HIGH;
  }

  Priority priority() default Priority.MEDIUM;
  String createdBy() default "Amigo";
  String[] tags() default {};
}
