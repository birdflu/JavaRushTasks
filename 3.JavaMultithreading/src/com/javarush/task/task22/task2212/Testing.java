package com.javarush.task.task22.task2212;

import org.junit.Test;

public class Testing {
  @Test
  public void test() {
        assert(Solution.checkTelNumber("+380501234567"));
        assert(Solution.checkTelNumber("(050)1234567"));
        assert(Solution.checkTelNumber("0501234567"));
        assert(Solution.checkTelNumber("050123-4567"));
        assert(Solution.checkTelNumber("01-2-3456789"));
        assert(Solution.checkTelNumber("0-1-2-3456789"));
        assert(Solution.checkTelNumber("+38(050)1234567"));
        assert(Solution.checkTelNumber("+38(050)123-45-67"));
        assert(Solution.checkTelNumber("+38050123-45-67"));
        assert(!Solution.checkTelNumber("+38)050(1234567"));
        assert(!Solution.checkTelNumber("+38(050)1-23-45-6-7"));
        assert(!Solution.checkTelNumber("050ххх4567"));
        assert(!Solution.checkTelNumber("050123456"));
        assert(!Solution.checkTelNumber("(0501)234567"));
        assert(!Solution.checkTelNumber("(0)501234567"));
        assert(!Solution.checkTelNumber("0-1-2-3-456789"));
        assert(!Solution.checkTelNumber("+38(050)123--4567"));
        assert(!Solution.checkTelNumber("+3-8(050)12345-67"));
        assert(!Solution.checkTelNumber("(050)12(345)67"));
        assert(!Solution.checkTelNumber(null));

  }
}
