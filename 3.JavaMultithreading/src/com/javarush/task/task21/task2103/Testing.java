package com.javarush.task.task21.task2103;


import org.testng.annotations.Test;

public class Testing {
  @Test
  public void test100() {
    assert(Solution.calculate(true,true,true,true));
    assert(Solution.calculate(true,true,true,false));

    assert(!Solution.calculate(true,true,false,true));
    assert(!Solution.calculate(true,true,false,false));

    assert(Solution.calculate(true,false,true,true));
    assert(Solution.calculate(true,false,true,false));

    assert(!Solution.calculate(true,false,false,true));
    assert(!Solution.calculate(true,false,false,false));

    assert(Solution.calculate(false,true,true,true));
    assert(Solution.calculate(false,true,true,false));

    assert(!Solution.calculate(false,true,false,true));
    assert(!Solution.calculate(false,true,false,false));

    assert(Solution.calculate(false,false,true,true));
    assert(Solution.calculate(false,false,true,false));

    assert(!Solution.calculate(false,false,false,true));
    assert(!Solution.calculate(false,false,false,false));


  }
}
