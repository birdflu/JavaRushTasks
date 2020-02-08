package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

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
        try {
            SimpleDateFormat simpleOutDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            SimpleDateFormat simpleInDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

            switch (args[0]) {
                case "-i":
                    if (Integer.parseInt(args[1]) >= 0)
                        System.out.println(allPeople.get(Integer.parseInt(args[1])).getName() + " " +
                                sexDecode(allPeople.get(Integer.parseInt(args[1])).getSex()) + " " +
                                simpleOutDateFormat.format(allPeople.get(Integer.parseInt(args[1])).getBirthDate()));
                    break;
                case "-c":
                    if (args[2].equals("м")) {
                        allPeople.add(Person.createMale(args[1], simpleInDateFormat.parse(args[3])));
                        System.out.println((allPeople.size() - 1));
                    } else if (args[2].equals("ж")) {
                        allPeople.add(Person.createFemale(args[1], simpleInDateFormat.parse(args[3])));
                        System.out.println((allPeople.size() - 1));
                    } else printHelp();
                    break;
                case "-u": //-u id name sex bd
                    if ((allPeople.size() > Integer.parseInt(args[1])) & (Integer.parseInt(args[1]) >= 0))
                        if (args[3].equals("м")) {
                            allPeople.get(Integer.parseInt(args[1])).setName(args[2]);
                            allPeople.get(Integer.parseInt(args[1])).setSex(Sex.MALE);
                            allPeople.get(Integer.parseInt(args[1])).setBirthDate(simpleInDateFormat.parse(args[4]));
                        } else if (args[3].equals("ж")) {
                            allPeople.get(Integer.parseInt(args[1])).setName(args[2]);
                            allPeople.get(Integer.parseInt(args[1])).setSex(Sex.FEMALE);
                            allPeople.get(Integer.parseInt(args[1])).setBirthDate(simpleInDateFormat.parse(args[4]));
                        } else printHelp();
                    break;
                case "-d":
                    if (Integer.parseInt(args[1]) >= 0)
                        allPeople.get(Integer.parseInt(args[1])).setSex(null);
                    allPeople.get(Integer.parseInt(args[1])).setBirthDate(null);
                    allPeople.get(Integer.parseInt(args[1])).setName(null);
                    break;
                default:
                    printHelp();
            }

 //        printDebugMessages();

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

    private static void printDebugMessages() {
        System.out.println( allPeople.get(0).getName());
        System.out.println( allPeople.get(0).getBirthDate());
        System.out.println(allPeople.get(0).getSex());
        System.out.println( allPeople.get(1).getName());
        System.out.println( allPeople.get(1).getBirthDate());
        System.out.println(allPeople.get(1).getSex());
        if (allPeople.size()>2)
        {
            System.out.println( allPeople.get(2).getName());
            System.out.println( allPeople.get(2).getBirthDate());
            System.out.println(allPeople.get(2).getSex());

        }

    }

    private static void printHelp() {
        System.out.println("Please, use parameters: bd\n" +
                "        -c name sex bd\n" +
                "        -u id name sex bd\n" +
                "        -d id\n" +
                "        -i id");
    }
}
