package com.javarush.task.task34.task3404;

public class Expression {
  private int from;
  private int to;
  private boolean sign;
  private Argument left;
  private Operation op;
  private Argument right;

  public Expression(int from, int to, boolean sign, Argument left, Operation op, Argument right) {
    this.from = from;
    this.to = to;
    this.sign = sign;
    this.left = left;
    this.op = op;
    this.right = right;
  }

  public int getFrom() {
    return from;
  }

  public int getTo() {
    return to;
  }

  public Argument getLeft() {
    return left;
  }

  public Operation getOp() {
    return op;
  }

  public Argument getRight() {
    return right;
  }

  @Override
  public String toString() {

    String l = left==null?"null":left.toString();
    String r = right==null?"null":right.toString();

    return "Expression{" +
            "from=" + from +
            ", to=" + to +
            ", sign=" + sign +
            ", left=" + l +
            ", opIndx=" + op.getIndex() +
            ", op=" + op.getOperand() +
            ", right=" + r +
            '}';
  }
}
