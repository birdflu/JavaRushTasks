package com.javarush.task.task34.task3404;

public class Operation {
  private Operand operand;
  private int index;

  public Operation(Operand operand, int index) {
    this.operand = operand;
    this.index = index;
  }

  public Operand getOperand() {
    return operand;
  }

  public int getIndex() {
    return index;
  }
}
