package com.javarush.task.task32.task3204;
/*
  Генератор паролей
*/

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Solution {
    public enum Token {
        UPPER,
        LOWER,
        DIGIT
    }

    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        return generatePassword(8);
    }

    private static ByteArrayOutputStream generatePassword(int maxsize) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        int digitCount = rnd(1,maxsize/3);
        int upperCount = rnd(1,maxsize/3*2-digitCount);
        int lowerCount = maxsize-digitCount-upperCount;

        for (int i = 0; i < digitCount; i++) {
            bytes.write(get(Token.DIGIT));
        }
        for (int i = 0; i < upperCount; i++) {
            bytes.write(get(Token.UPPER));
        }
        for (int i = 0; i < lowerCount; i++) {
            bytes.write(get(Token.LOWER));
        }

        try {
            bytes = shift(shift(bytes, 3),4);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    public static int get(Token t) {
        if (t == Token.DIGIT) return rnd(48, 57);
        if (t == Token.UPPER) return rnd(65, 90);
        if (t == Token.LOWER) return rnd(97, 122);
        return -1;
    }

    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    protected static ByteArrayOutputStream shift(ByteArrayOutputStream bos, int shift) throws IOException {
        String s = new String(bos.toByteArray());
        if (s.length() <= shift) return bos;
        String tail = s.substring(shift);
        String head = s.substring(0,shift);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        result.write((tail + head).getBytes());
        return result;
    }

}

