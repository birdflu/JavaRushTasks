package com.javarush.task.task34.task3404;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Builder {
  private String expression;
  private int countOperation;
  private String resultExpression;

  public static class Insertion {
    protected int from;
    protected int to;
    protected String value;

    public Insertion(int from, int to, String value) {
      this.from = from;
      this.to = to;
      this.value = value;
    }

    @Override
    public String toString() {
      return String.format("from %d to %d value %s", this.from, this.to, this.value);
    }
  }

  protected enum ArgumentPosition {
    RIGHT,
    LEFT
  }

  public Builder(String expression, int countOperation) {
    this.expression = expression;
    this.countOperation = countOperation;
    this.resultExpression = expression;
    log("\nExpression %s with %d operations COME IN.", expression, countOperation);
  }

  public void walk() {
    ArrayList<Node> nodes = parseBraces(expression);

    Node candidate = getDigitLeave(nodes);
    if (candidate != null) {
      if (calcFunction(candidate, "Digit leave")) return;
    }

    candidate = getNonDigitLeave(nodes);
    if (candidate != null) {
      calcOperation(candidate, "Non digit leave");
      return;
    }

    candidate = getNonLeave(nodes);
    if (candidate != null) {
      calcOperation(candidate, "Non leave");
      return;
    }

    calcOperation(null, "Clear expression");
  }

  private boolean calcFunction(Node candidate, String s) {
    log("\n" + s + " %s", expression.substring(candidate.data.x, candidate.data.y + 1));
    if (candidate.data.x >= 3) {
      int from = candidate.data.x - 3;
      int to = candidate.data.y;
      String value = expression.substring(from, to + 1);
      return findAndCalcFunction(from, to, value);
    }
    return false;
  }

  private void calcOperation(Node candidate, String s) {
    if (candidate == null) {
      int from = 0;
      int to = expression.length() - 1;
      //if (expression.charAt(0) == '-') from = 1;
      String value = expression.substring(from);
      log("\n" + s + " %s", value);
      findAndCalcOperation(from, to, value);
    } else {
      int from = candidate.data.x;
      int to = candidate.data.y;
      String value = expression.substring(from, to + 1);
      log("\n" + s + " %s", value);
      findAndCalcOperation(from, to, value);
    }
  }

  private boolean findAndCalcFunction(int from, int to, String value) {
    Expression exp;
    Insertion insertion = new Insertion(from, to, value);
    if ((exp = findFunction(insertion)) != null) {
      resultExpression = calculate(exp);
      return true;
    }
    return false;
  }

  private void findAndCalcOperation(int from, int to, String value) {
    Expression exp;
    Insertion ins = removeExtraBraces(new Insertion(from, to, value));
    if (!value.equals(ins.value)){
      resultExpression = embed(ins);
      return;
    }

    if ((exp = findOperation(ins)) == null) {
      resultExpression = "0";
    } else {
      resultExpression = calculate(exp);
    }
  }

  private Insertion removeExtraBraces(Insertion insertion) {
    // (-(-0.25)) => (0.25)
    // ((-0.25)) => (-0.25)
    // ((0.25)) => (0.25)
    String v = insertion.value;
    int from = insertion.from;
    int to = insertion.to;
    if (v.length() > 4
            && v.charAt(v.length()-1) == ')'
            && v.charAt(v.length()-2) == ')'
            && v.charAt(0) == '(')
    {
      if (v.charAt(0) == '(' && v.charAt(1) == '-' && v.charAt(2) == '(' && v.charAt(1) == '-')
      {
        v = "(" + v.substring(4,v.length()-1);
        if (!isDigit(v.substring(1,v.length()-1))) return insertion;
        countOperation++;
        countOperation++;
      }
      if (v.charAt(0) == '(' && v.charAt(1) == '(')
      {
        v = v.substring(1,v.length()-1);
        if (!isDigit(v.substring(1,v.length()-1))) return insertion;
      }

    }
    return new Insertion(from, to, v);
  }

  private Node getNonLeave(ArrayList<Node> nodes) {
    for (Node node : nodes) {
      if (node.children.size() > 0 && isAllDigit(node.children)) {
        return node;
      }
    }
    return null;
  }

  private Node getNonDigitLeave(ArrayList<Node> nodes) {
    for (Node node : nodes) {
      String nodeData = expression.substring(node.data.x + 1, node.data.y);
      if (node.children.size() == 0 && !isDigit(nodeData)) {
        return node;
      }
    }
    return null;
  }

  private Node getDigitLeave(ArrayList<Node> nodes) {
    for (Node node : nodes) {
      String nodeData = expression.substring(node.data.x + 1, node.data.y);
      if (node.children.size() == 0 && isDigit(nodeData)) {
        return node;
      }
    }
    return null;
  }

  private boolean isAllDigit(ArrayList<Node> children) {
    boolean result = true;
    if (children == null || children.size() == 0) return false;
    else {
      for (Node node : children
      ) {
        result = result && isDigit(expression.substring(node.data.x + 1, node.data.y));

      }
    }
    return result;
  }

  private Expression findFunction(Insertion ins) {
    Expression exp = null;
    if (ins.to < this.expression.length()
            && hasSinCosTan(this.expression.substring(ins.from, ins.to + 1))) {
      boolean sign = isSigned(ins.from);
      Operand operand = Operand.get(this.expression.substring(ins.from, ins.from +3));
      Argument right = new Argument(isSigned(ins.from + 4), true, new StringBuilder(ins.value.substring(4,ins.value.length()-1)));
      exp = new Expression(ins.from , ins.to, sign, null, new Operation(operand, ins.from), right);

      log("\nExpression detected: %s", exp);
    }
    return exp;
  }

  private Expression findOperation(Insertion ins) {
    Operation operation = getOperation(ins);
    assert operation != null;
    int operationIndex = operation.getIndex();

    log("\nFind operation: %s, index: %d in insertion %s", operation.getOperand().getName(), operationIndex, ins.value);

    Argument rightArgument = parseArgument(operation, ins, ArgumentPosition.RIGHT);
    if (rightArgument == null)
      return findOperation(new Insertion(operationIndex + 1, ins.to, ins.value.substring(operationIndex + 1)));
    Argument leftArgument = parseArgument(operation, ins, ArgumentPosition.LEFT);

    assert leftArgument != null;
    Expression exp = new Expression(
            ins.from + operationIndex - leftArgument.getValue().length() - (leftArgument.isBraces() ? 2 : 0)
            , ins.from + operationIndex + rightArgument.getValue().length() + (rightArgument.isBraces() ? 2 : 0),
            false,
            leftArgument,
            operation,
            rightArgument);
    log("\nExpression detected: %s", exp);
    return exp;
  }

  private Operation getOperation(Insertion ins) {
    int sin = ins.value.indexOf(Operand.SIN.getName());
    if (sin >= 0) return new Operation(Operand.SIN, sin);

    int cos = ins.value.indexOf(Operand.COS.getName());
    if (cos >= 0) return new Operation(Operand.COS, cos);

    int tan = ins.value.indexOf(Operand.TAN.getName());
    if (tan >= 0) return new Operation(Operand.TAN, tan);

    int power = ins.value.indexOf(Operand.POWER.getName(), 1);
    if (power > 0) return new Operation(Operand.POWER, power);

    int multiply = ins.value.indexOf(Operand.MULTIPLY.getName(), 1);
    int divide = ins.value.indexOf(Operand.DIVIDE.getName(), 1);

    if (multiply > 0 && (divide == -1 || multiply < divide)) return new Operation(Operand.MULTIPLY, multiply);
    if (divide > 0 && (multiply == -1 || divide < multiply)) return new Operation(Operand.DIVIDE, divide);

    int plus = ins.value.indexOf(Operand.PLUS.getName(), 1);
    int minus = ins.value.indexOf(Operand.MINUS.getName(), 1);
    if (minus == 1 && (ins.value.charAt(0) == '(' || ins.value.charAt(0) == ')'))
      minus = ins.value.indexOf(Operand.MINUS.getName(), 2);

    if (minus == -1 && plus > 0 ||
            minus == 0 && plus > minus ||
            minus == 1 && (ins.value.charAt(0) == '(' || ins.value.charAt(0) == ')') && plus > minus ||
            minus > 1 && plus > -1 && plus < minus
    ) return new Operation(Operand.PLUS, plus);
    if (minus > 0 && (plus == -1 || minus < plus)) return new Operation(Operand.MINUS, minus);

    return null;
  }

  private Argument parseArgument(Operation operation, Insertion ins, ArgumentPosition position) {
    char[] arg = new char[0];
    char headBrace = '#';
    char tailBrace = '#';

    if (position == ArgumentPosition.RIGHT) {
      arg = ins.value.substring(operation.getIndex() + 1).toCharArray();
      headBrace = '(';
      tailBrace = ')';
    }
    if (position == ArgumentPosition.LEFT) {
      arg = ins.value.substring(0, operation.getIndex()).toCharArray();
      headBrace = ')';
      tailBrace = '(';
      char[] reverse = new char[arg.length];
      for (int i = 0; i < reverse.length; i++) reverse[i] = arg[arg.length - 1 - i];
      arg = reverse;
    }
    StringBuilder sbArg = new StringBuilder();
    boolean isBraces = false;
    boolean isSign = false;

    if (arg[0] == headBrace) {
      int i = 1;
      while ((i < arg.length) && (arg[i] == '.' || arg[i] == '-' || (arg[i] >= '0' && arg[i] <= '9'))) {
        sbArg.append(arg[i++]);

      }
      if (i >= arg.length) throw new IllegalArgumentException();
      if (arg[i] != tailBrace) return null; //throw new IllegalArgumentException();
      if (arg[i] == tailBrace) {
        isBraces = true;
        if (arg[1] == '-') isSign = true;
      }
    } else {
      for (int i = 0; i < arg.length; i++) {
        if (arg[i] == '-' || arg[i] == '.' || (arg[i] >= '0' && arg[i] <= '9')) {
          sbArg.append(arg[i]);
          if (arg[i] == '-') {
            // no need take a minus near 2 in 1-2+18 (reverse 81+2-1)
            if ((i < arg.length - 1) &&
                    (position == ArgumentPosition.RIGHT
                    || (arg[i + 1] >= '0' && arg[i + 1] <= '9')
                    || arg[i + 1] == ')'))
              sbArg.deleteCharAt(sbArg.length() - 1);
            break;
          }
        } else break;
      }
      if (position == ArgumentPosition.LEFT && sbArg.charAt(sbArg.length() - 1) == '-') {
        if (operation.getOperand() == Operand.MINUS || operation.getOperand() == Operand.PLUS) isSign = true;
        else {
          sbArg.deleteCharAt(sbArg.length() - 1);
          isSign = false;
        }
      }
    }

    return new Argument(isSign, isBraces, (position == ArgumentPosition.LEFT) ? sbArg.reverse() : sbArg);

  }

  private String calculate(Expression e) {
    double r = e.getRight().getDouble();
    if (r < 0) countOperation++;
    double result = -1;
    Operand op = e.getOp().getOperand();

    if (op == Operand.COS) result = Math.cos(Math.toRadians(r));
    if (op == Operand.SIN) result = Math.sin(Math.toRadians(r));
    if (op == Operand.TAN) result = Math.tan(Math.toRadians(r));

    if ((op == Operand.POWER)
            || (op == Operand.MULTIPLY) || (op == Operand.DIVIDE)
            || (op == Operand.PLUS) || (op == Operand.MINUS)) {
      double l = e.getLeft().getDouble();
      if (l < 0) countOperation++;

      if (op == Operand.POWER) result = Math.pow(l, r);
      if (op == Operand.MULTIPLY) result = l * r;
      if (op == Operand.DIVIDE) result = l / r;
      if (op == Operand.PLUS) result = l + r;
      if (op == Operand.MINUS) result = l - r;
    }
    countOperation++;
    return embedding(e, result);

  }

  private String addBraces(String round) {
    return "(" + round + ")";
  }

  private String embedding(Expression e, double result) {
    if ((result < 0) && (e.getFrom() > 0) && (expression.charAt(e.getFrom() - 1) != '(')) {
      return embed(new Insertion(e.getFrom(),
              e.getTo(),
              addBraces(round(result, 4))));
    } else {
      return embed(new Insertion(e.getFrom(),
              e.getTo(),
              round(result, 4)));
    }
  }

  public String embed(Insertion ins) {
    String result = expression.substring(0, ins.from)
            + ins.value
            + expression.substring(ins.to + 1);
    log("\nInsertion %s embeded to %s, new expression: %s",
            ins.toString(), expression, result);
    return result;
  }

  public static String round(double value, int scale) {
    return new BigDecimal(value)
            .setScale(scale, RoundingMode.HALF_UP)
            .stripTrailingZeros()
            .toPlainString();
  }

  private boolean isSigned(int from) {
    char[] head = expression.substring(0, from).toCharArray();
    if (head.length == 1 && head[head.length - 1] == '-') return true;
    char[] arg =  new StringBuilder(expression.substring(0, from)).reverse().toString().toCharArray();
    if (arg.length > 0){
      int i = 0;
      if (arg[i] == '-') {
        // no need take a minus near 2 in 89-cos(180)^2 ( 89-  reverse -98 )
        return (i >= arg.length - 1) || ((arg[i + 1] < '0' || arg[i + 1] > '9') && arg[i + 1] != ')');

      }
    }
    return false;
  }

  public ArrayList<Node> parseBraces(String str) {
    ArrayList<Node> nodes = new ArrayList<>();
    ArrayList<Integer> openBracesIndexes = new ArrayList<>();
    StringBuilder sb = new StringBuilder(str);
    for (int i = 0; i < sb.length(); i++) {
      if (sb.charAt(i) == '(') openBracesIndexes.add(i);
      if (sb.charAt(i) == ')') {
        int openBraceIndex = openBracesIndexes.get(openBracesIndexes.size() - 1);
        openBracesIndexes.remove(openBracesIndexes.size() - 1);
        nodes.add(new Node(openBraceIndex, i));
      }
    }
    nodes = bind(nodes);
    nodes.forEach(node -> log("\n" + node.toString()));
    return nodes;
  }

  public ArrayList<Node> bind(ArrayList<Node> nodes) {
    for (Node node : nodes) {
      for (Node innerNode : nodes) {
        if (innerNode.parent == null && node.canBeParentOf(innerNode)) {
          innerNode.parent = node;
          node.children.add(innerNode);
        }
      }
    }
    return nodes;
  }


  public static boolean isDigit(String expression) {
    return expression.matches("(-?(\\d*\\.\\d*))|(-?(\\d*))");
  }

  public static boolean hasSinCosTan(String expression) {
    return expression.matches("^(sin|tan|cos)\\(((-?(\\d*\\.\\d*))|(-?(\\d*)))\\)$");
  }

  public int getCountOperation() {
    return countOperation;
  }

  public String getResultExpression() {
    return resultExpression;
  }

  public void log(String format, Object... args) {
    boolean isDebug = false;
    if (isDebug) System.out.format(format, args);
  }
}
