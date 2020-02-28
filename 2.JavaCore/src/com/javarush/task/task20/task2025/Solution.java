package com.javarush.task.task20.task2025;

import java.util.*;

/*
Алгоритмы-числа
*/
public class Solution {
  public static long[] getNumbers(long N) {
    // Armstrong numbers
    // https://acmp.ru/article.asp?id_text=198
    // Long.MAX_VALUE = 9223372036854775807
    // 1350851717672992000
    // "9223372036854775807".length() = 19
    // we prepare matrix "d" of digit power 10x20: d[i][j] = j^i
    // in this way 8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8 = d[8][4] + d[2][4] + d[0][4] + d[8][4];
    long[][] d = new long[10][20];
    List<Long> numbers = new ArrayList<>();

    long[] result = {};
    if (N <= 1) return result;

    // fill the matrix with values
    for (int i = 0; i < 10; i++)
      for (int j = 0; j < 20; j++)
        d[i][j] = pow(i,j);

    // we can iterate not the numbers themselves, but the values
    // that can be obtained as a result of a power sum
    // from change of digits in places in number the power sum does not change.
    // I. e. for example, there is no need to consider all numbers from the class : 135, 153, 315, 351, 531 and 513;
    // it is enough to consider one of them, for example, the number 135;
    // calculate its power sum: sum(135) = 153, and then only make sure that the number 153 is the number of Armstrong.
    // the iteratation will be: all numbers are considered,
    // in which any digit is not less than the previous one and not more than the next. For example: 12, 1557, 333, etc.

    char [][] init = new char[][]{{'0'},{'1'},{'2'},{'3'},{'4'},{'5'},{'6'},{'7'},{'8'},{'9'}};
    dat(init, N, d, numbers);
    for (int i = 0; i < ((init.length < N) ? init.length:N); i++) {
      numbers.add(Long.valueOf(init[i][0]-48));
    }

    Collections.sort(numbers);
//    System.out.println(numbers.size() + " elements : " + numbers);

    result = new long[numbers.size() - 1];
    for (int i = 0; i < numbers.size() - 1; i++) {
      result[i] = numbers.get(i + 1).longValue();
    }

    return result;
  }
  public static char[][] values(char[] input, long N, long[][] d, List<Long> numbers) {
    long sum, checksum;
    String num, dirtyNum;
    // if input is 5567 then output is the sequence of 9-input[input.length-1] + 1 = 9-7 + 1 = 3 elements: 55677, 55678, 55679
    int lastdigit = input[input.length - 1] - 48;
    char[][] result = new char[9 - lastdigit + 1][input.length + 1];
    for (int i = 0; i < 9 - lastdigit + 1; i++) {
      for (int j = 0; j < input.length; j++) result[i][j] = input[j];
      result[i][input.length] = (char) (lastdigit + i + 48);
      sum = 0L;
      for (char c : result[i])
        sum = sum + d[c-48][input.length+1];
      if (sum > 0 & String.valueOf(sum).length() == input.length+1) {
        // check if sum is armstrong number
        checksum = 0L;
        for (char c : String.valueOf(sum).toCharArray()) {
          checksum = checksum + d[c-48][input.length+1];
          if (checksum > sum) break; // out of range sum;
        }
        if (checksum == sum & sum < N) numbers.add(sum);
      }
//      System.out.print(result[i]);  System.out.println(" => " + sum);
    }
    return result;
  }

  public static void dat(char[][] input, long N, long[][] d, List<Long> numbers) {
    for (char[] c: input) {
      if (c.length > 18 | c.length > String.valueOf(N).length() - 1) return;
      dat(values(c, N, d, numbers), N, d, numbers);
    }

  }

  public static long pow(long x, long n)
  {
    long a = x, p = 1;
    while (n > 0)
    {
      if ((n & 1) != 0)
        p *= a;
      a *= a;
      n >>= 1;
    }
    return p;
  }

  public static void main(String[] args) {
/*
    long startTime = new Date().getTime();
    System.out.println(Arrays.toString(getNumbers(7)));
    System.out.println(Arrays.toString(getNumbers(0)));
    System.out.println(Arrays.toString(getNumbers(Long.MAX_VALUE)));
    System.out.println(Arrays.toString(getNumbers(-21312)));
    long endTime = new Date().getTime();
    System.out.print((endTime - startTime) / 1000 + " seconds");
*/
  }
}