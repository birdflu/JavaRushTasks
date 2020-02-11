package com.javarush.task.task34.task3404;

public enum Operand {
  SIN("sin"),
  COS("cos"),
  TAN("tan"),
  POWER("^"),
  MULTIPLY("*"),
  DIVIDE("/"),
  PLUS("+"),
  MINUS("-");

  private String name;

  Operand(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public static Operand get(String name){
    if (name.equals(Operand.COS.getName())) return Operand.COS;
    if (name.equals(Operand.SIN.getName())) return Operand.SIN;
    if (name.equals(Operand.TAN.getName())) return Operand.TAN;
    if (name.equals(Operand.POWER.getName())) return Operand.POWER;
    return null;
  }
}


