package com.javarush.task.task20.task2025;


import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class Testing {
  @Test
  public void test100() {
    assertArrayEquals(Solution.getNumbers(Long.MIN_VALUE), new long[]{});
    assertArrayEquals(Solution.getNumbers(-7), new long[]{});
    assertArrayEquals(Solution.getNumbers(0), new long[]{});
    assertArrayEquals(Solution.getNumbers(1), new long[]{});
    assertArrayEquals(Solution.getNumbers(7), new long[]{1L, 2L, 3L, 4L, 5L, 6L});
    assertArrayEquals(Solution.getNumbers(100), new long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L});
    assertArrayEquals(Solution.getNumbers(407), new long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 153L, 370L, 371L});
    assertArrayEquals(Solution.getNumbers(1000), new long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 153L, 370L, 371L, 407L});
    assertArrayEquals(Solution.getNumbers(100000), new long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 153L, 370L, 371L, 407L,
            1634L, 8208L, 9474L, 54748L, 92727L, 93084L});
    assertArrayEquals(Solution.getNumbers(100000000), new long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 153L, 370L, 371L, 407L,
            1634L, 8208L, 9474L, 54748L, 92727L, 93084L, 548834L, 1741725L, 4210818L, 9800817L, 9926315L,
            24678050L, 24678051L, 88593477L});
    assertArrayEquals(Solution.getNumbers(Long.MAX_VALUE), new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634, 8208, 9474, 54748, 92727, 93084,
            548834, 1741725, 4210818, 9800817, 9926315, 24678050, 24678051, 88593477, 146511208,
            472335975, 534494836, 912985153, 4679307774L, 32164049650L, 32164049651L, 40028394225L,
            42678290603L, 44708635679L, 49388550606L, 82693916578L, 94204591914L, 28116440335967L,
            4338281769391370L, 4338281769391371L, 21897142587612075L, 35641594208964132L, 35875699062250035L,
            1517841543307505039L, 3289582984443187032L, 4498128791164624869L, 4929273885928088826L});
  }
}