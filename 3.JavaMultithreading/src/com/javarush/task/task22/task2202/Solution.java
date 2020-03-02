package com.javarush.task.task22.task2202;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        int firstSpaceIndex = 0;
        int fourthSpaceIndex = 0;
        int fifthSpaceIndex = 0;
        int spaceCount = 0;
        if (string == null) throw new TooShortStringException();
        char[] elements = string.toCharArray();
        for (int i = 0; i < elements.length; i++)
            if (elements[i] == ' ') {
                if (spaceCount == 0) firstSpaceIndex = i;
                if (spaceCount == 3) fourthSpaceIndex = i;
                if (spaceCount == 4) fifthSpaceIndex = i;
                spaceCount++;
            }
        if (firstSpaceIndex*fourthSpaceIndex == 0) throw new TooShortStringException();
        else if (fifthSpaceIndex == 0) return string.substring(firstSpaceIndex+1);
        else return string.substring(firstSpaceIndex+1,fifthSpaceIndex);
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
