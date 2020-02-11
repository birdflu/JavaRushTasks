package com.javarush.task.task34.task3404;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Testing {
  @Test
  public void test() {
    assert (("1 0").equals(test("1", 0)));
    assert (("-1 0").equals(test("-1", 0)));
    assert (("-0.31 0").equals(test("-0.3051", 0)));
    assert (("1.5 0").equals(test("1.5", 0)));
    assert (("-0.3 0").equals(test("-0.305", 0)));
    assert (("0.31 0").equals(test("(0.3051)", 0)));
    assert (("0.31 0").equals(test(" ( 0.3051 ) ", 0)));
    assert (("1 1").equals(test("tan(45)", 0)));
    assert (("-1 2").equals(test("tan(-45)", 0)));
    assert (("4.03 1").equals(test("2+2.032", 0)));
    assert (("-1 1").equals(test("2-3", 0)));
    assert (("-4 3").equals(test("2-3-3", 0)));
    assert (("-3 3").equals(test("-2+3-4", 0)));
    assert (("-9 4").equals(test("-2-3-4", 0)));
    assert (("-1 0").equals(test("(-1)", 0)));
    assert (("-3 2").equals(test("(-1-2)", 0)));
    assert (("-1 1").equals(test("(1-2)", 0)));
    assert (("-3 3").equals(test("(1-2)-2", 0)));
    assert (("-1 3").equals(test("-2-(-1)", 0)));
    assert (("-5 6").equals(test("-2-(-1+2)-2", 0)));
    assert (("1 9").equals(test("-2-(-1+2)-(-2-2)", 0)));
    assert (("-2 3").equals(test("(1-2)*2", 0)));
    assert (("2 4").equals(test("(1-2)*(-2)", 0)));
    assert (("3 1").equals(test("6/2", 0)));
    assert (("3 3").equals(test("6/(6-2)*(2)", 0)));
    assert (("-6 4").equals(test("6/(2-4)*(2)", 0)));
    assert (("6 7").equals(test("6/(2-4)*(-2)", 0)));
    assert (("4 3").equals(test("(1+1)*(1+1)", 0)));
    assert (("-0.5 6").equals(test("-sin(2*(-5+1.5*4)+28)", 0)));
    assert (("12 8").equals(test("1+(1+(1+1)*(1+1))*(1+1)+1", 0)));
    assert (("1 7").equals(test("tan(44+sin(89-cos(180)^2))", 0)));
    assert (("-14 10").equals(test("-2+(-2+(-2)-2*(2+2))", 0)));
    assert (("1 7").equals(test("sin(80+(2+(1+1))*(1+1)+2)", 0)));
    assert (("6 11").equals(test("1+4/2/2+2^2+2*2-2^(2-1+1)", 0)));
    assert (("6 4").equals(test("10-2^(2-1+1)", 0)));
    assert (("2048 4").equals(test("2^10+2^(5+5)", 0)));
    assert (("72.96 8").equals(test("1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1", 0)));
    assert (("0 1").equals(test("0.000025+0.000012", 0)));
    assert (("0 1").equals(test("0.000025+0.000012", 0)));
    assert (("-3 21").equals(test("-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)", 0)));
    assert (("0.5 3").equals(test("cos(3 + 19*3)", 0)));
    assert (("8302231.36 14").equals(test("2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)", 0)));
    assert (("-3 3").equals(test("(-1 + (-2))", 0)));
    assert (("-0.5 6").equals(test("-sin(2*(-5+1.5*4)+28)", 0)));
    assert (("0 3").equals(test("sin(100)-sin(100)", 0)));
    assert (("-22 4").equals(test("-(-22+22*2)", 0)));
    assert (("-0.25 2").equals(test("-2^(-2)", 0)));
    assert (("2.5 10").equals(test("-(-2^(-2))+2+(-(-2^(-2)))", 0)));
    assert (("4 3").equals(test("(-2)*(-2)", 0)));
    assert (("1 3").equals(test("(-2)/(-2)", 0)));
    assert (("-0.5 2").equals(test("sin(-30)", 0)));
    assert (("0.87 2").equals(test("cos(-30)", 0)));
    assert (("-0.58 2").equals(test("tan(-30)", 0)));
    assert (("6.5 6").equals(test("2+8*(9/4-1.5)^(1+1)", 0)));
    assert (("0.01 0").equals(test("0.005 ", 0)));
    assert (("0 0").equals(test("0.0049 ", 0)));
    assert (("0.3 1").equals(test("0+0.304", 0)));
  }

  public String test(String expression, int countOperation) {
    Solution solution = new com.javarush.task.task34.task3404.Solution();
    //запоминаем настоящий PrintStream в специальную переменную
    PrintStream consoleStream = System.out;
    //Создаем динамический массив
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    //создаем адаптер к классу PrintStream
    PrintStream stream = new PrintStream(outputStream);
    //Устанавливаем его как текущий System.out
    System.setOut(stream);
    //Вызываем функцию, которая ничего не знает о наших манипуляциях
    solution.recurse(expression, countOperation);
    //Преобразовываем записанные в наш ByteArray данные в строку
    String result = outputStream.toString();
    //Возвращаем все как было
    System.setOut(consoleStream);
    //выводим ее
    System.out.println(result);
    return result;
  }
}