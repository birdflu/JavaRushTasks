package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {
 /*       PersonScanner personScanner = new PersonScannerAdapter(new Scanner("Иванов Иван Иванович 31 12 1950\n" +
                "Петров Петр Петрович 31 12 1957"));
        try {
            Person person = personScanner.read();
            System.out.println(person.toString());
            person = personScanner.read();
            System.out.println(person.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }

    public static class PersonScannerAdapter implements PersonScanner {

        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner scanner) {
            fileScanner = scanner;
        }

        @Override
        public Person read() throws IOException {
            // Иванов Иван Иванович 31 12 1950
            String[] personElements = fileScanner.nextLine().split(" ");
            String firstName = personElements[1];
            String middleName = personElements[2];
            String lastName = personElements[0];
            SimpleDateFormat parser = new SimpleDateFormat("ddMMyyyy");
            Date birthday = new Date();
            try {
                birthday = parser.parse(personElements[3]+personElements[4]+personElements[5]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Person(firstName,middleName,lastName,birthday);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
