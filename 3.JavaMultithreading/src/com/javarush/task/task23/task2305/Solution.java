package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution solution1 = new Solution();
        InnerClass innerClass11 = new Solution().new InnerClass();
        InnerClass innerClass12 = new Solution().new InnerClass();
        solution1.innerClasses[0] = innerClass11;
        solution1.innerClasses[1] = innerClass12;

        Solution solution2 = new Solution();
        InnerClass innerClass21 = new Solution().new InnerClass();
        InnerClass innerClass22 = new Solution().new InnerClass();
        solution2.innerClasses[0] = innerClass21;
        solution2.innerClasses[1] = innerClass22;

        Solution[] solutions = {solution1, solution2};
       return solutions;
    }

    public static void main(String[] args) {

    }
}
