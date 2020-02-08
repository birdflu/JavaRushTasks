package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
//        -c name sex bd
//        -u id name sex bd
//        -d id
//        -i id

            SimpleDateFormat simpleOutDateFormat = new SimpleDateFormat("dd-MMM-y", Locale.ENGLISH);
            SimpleDateFormat simpleInDateFormat = new SimpleDateFormat("dd/MM/y", Locale.ENGLISH);
        try {
            switch (args[0]) {
                case "-i":
                    synchronized (allPeople){
                    for (int i = 0; i < (args.length-1); i++) {
                        if (Integer.parseInt(args[1 + i]) >= 0)
                            System.out.println(allPeople.get(Integer.parseInt(args[1 + i])).getName() + " " +
                                    sexDecode(allPeople.get(Integer.parseInt(args[1 + i])).getSex()) + " " +
                                    simpleOutDateFormat.format(allPeople.get(Integer.parseInt(args[1+i])).getBirthDate()));
                    }}
                    break;
                case "-c":
                    synchronized (allPeople){
                    for (int i = 0; i < (args.length-1)/3; i++) {
                        if (args[2+i*3].equals("м")) {
                            allPeople.add(Person.createMale(args[1+i*3], simpleInDateFormat.parse(args[3+i*3])));
                            System.out.print(allPeople.size() - 1 + " ");
                        } else if (args[2+i*3].equals("ж")) {
                            allPeople.add(Person.createFemale(args[1+i*3], simpleInDateFormat.parse(args[3+i*3])));
                            System.out.print(allPeople.size() - 1 + " ");
                        } //else printHelp();
                    }}
                    break;
                case "-u": //-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
                    synchronized (allPeople){
                    for (int i = 0; i < (args.length-1)/4; i++) {
                        if ((allPeople.size() > Integer.parseInt(args[1+i*4])) & (Integer.parseInt(args[1+i*4]) >= 0))
                            if (args[3+i*4].equals("м")) {
                                allPeople.get(Integer.parseInt(args[1+i*4])).setName(args[2+i*4]);
                                allPeople.get(Integer.parseInt(args[1+i*4])).setSex(Sex.MALE);
                                allPeople.get(Integer.parseInt(args[1+i*4])).setBirthDate(simpleInDateFormat.parse(args[4+i*4]));
                            } else if (args[3+i*4].equals("ж")) {
                                allPeople.get(Integer.parseInt(args[1+i*4])).setName(args[2+i*4]);
                                allPeople.get(Integer.parseInt(args[1+i*4])).setSex(Sex.FEMALE);
                                allPeople.get(Integer.parseInt(args[1+i*4])).setBirthDate(simpleInDateFormat.parse(args[4+i*4]));
                            } else printHelp();
                    }}
                    break;
                case "-d":
                    synchronized (allPeople){
                    for (int i = 0; i < (args.length-1); i++) {
                        if (Integer.parseInt(args[1+i]) >= 0)
                            allPeople.get(Integer.parseInt(args[1+i])).setSex(null);
                        allPeople.get(Integer.parseInt(args[1+i])).setBirthDate(null);
                        allPeople.get(Integer.parseInt(args[1+i])).setName(null);
                    }}
                    break;
                default:
                    printHelp();
            }

        //     printDebugMessages();

        } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                printHelp();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            printHelp();
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }
    private static String sexDecode(Sex s) {
        if (s == Sex.MALE) return "м";
        else if (s == Sex.FEMALE) return "ж";
        else return "N/A";
    }

    private static void printHelp() {
        System.out.println("Please, use parameters: bd\n" +
                "-c name1 sex1 bd1 name2 sex2 bd2 ... bd\n" +
                "-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ... bd\n" +
                "-d id1 id2 id3 id4 ... bd\n" +
                "-i id1 id2 id3 id4 ...");
    }
    private static void printDebugMessages() {
        for (int i = 0; i < allPeople.size(); i++) {
            System.out.println("Person id=" + i);
            System.out.println(allPeople.get(i).getName());
            System.out.println(allPeople.get(i).getBirthDate());
            System.out.println(allPeople.get(i).getSex());
        }
    }
}
