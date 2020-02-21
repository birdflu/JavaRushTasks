package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader fileReader = new BufferedReader(new FileReader("..\\birthdays.txt"));
        ArrayList<Character> charactersList = new ArrayList<>();
        char[] chars = new char[200];
        while (fileReader.ready()) {
            fileReader.read(chars);
            for (char c : chars) {
                if(c != '\r' && c != '\n' && c != '\u0000') charactersList.add(c);
            }
        }
        fileReader.close();

//        for (char c : charactersList) System.out.print(c);
//        System.out.println("");

        ArrayList<String> namesList = new ArrayList<>();
        ArrayList<String> datesList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = true;
        for (int i = 0; i < charactersList.size(); i++) {
            char c = charactersList.get(i);
            char nextChar = 0;
            if(i != charactersList.size()-1) nextChar = charactersList.get(i + 1);
            if (flag) {
                if(!Character.isDigit(c) || (Character.isSpaceChar(c) && Character.isLetter(nextChar))) {
                    stringBuilder.append(c);
                }
                if(Character.isSpaceChar(c) && Character.isDigit(nextChar)) {
                    namesList.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    flag = false;
                }
            }
            else {
                if(Character.isDigit(c) || Character.isSpaceChar(c)) {
                    stringBuilder.append(c);
                }
                if(Character.isLetter(c)) {
                    datesList.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(c);
                    flag = true;
                }
            }
        }
        datesList.add(stringBuilder.toString());

//        for (String s : namesList) System.out.println(s);
//        for (String s : datesList) System.out.println(s);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyy");

        for (int i = 0; i < namesList.size(); i++) {
            Date d = dateFormat.parse(datesList.get(i));
            PEOPLE.add(new Person(namesList.get(i), d));
        }

        for (Person person : PEOPLE) {
            String name = person.getName();
            String date = dateFormat.format(person.getBirthDate());
            System.out.println("<" + name + ">" + date);
        }
        PEOPLE.forEach(person -> System.out.println(person.getName() + " " + person.getBirthDate()));
    }
}
