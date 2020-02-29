package com.javarush.task.task21.task2106;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Ошибка в equals/hashCode
*/
public class Solution {
    private int anInt;
    private String string;
    private double aDouble;
    private Date date;
    private Solution solution;

    public Solution(int anInt, String string, double aDouble, Date date, Solution solution) {
        this.anInt = anInt;
        this.string = string;
        this.aDouble = aDouble;
        this.date = date;
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution1 = (Solution) o;
        return anInt == solution1.anInt &&
                Double.compare(solution1.aDouble, aDouble) == 0 &&
                Objects.equals(string, solution1.string) &&
                Objects.equals(date, solution1.date) &&
                Objects.equals(solution, solution1.solution);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = anInt;
        temp = aDouble != +0.0d ? Double.doubleToLongBits(aDouble) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
       //result = 31 * result + (solution != null ? solution.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution(1, "Dima", 2.2d, new Date(), null));
        s.add(new Solution(2, "DimaD", 2.1d, new Date(), new Solution(2, "DimaD", 2.1d, new Date(), null)));
        s.add(new Solution(0,null,0d,null,null));
        System.out.println(s.contains(new Solution(1, "Dima", 2.2d, new Date(), null)));
        System.out.println(s.contains(new Solution(2, "DimaD", 2.1d, new Date(), new Solution(2, "DimaD", 2.1d, new Date(), null))));
        System.out.println(s.contains(new Solution(0,null,0d,null,null)));
        System.out.println(s.contains(new Solution(1, "Dima", 3.2d, new Date(), null)));
        System.out.println(s.contains(new Object()));
    }
}
