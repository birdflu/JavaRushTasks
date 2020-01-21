package com.javarush.task.task14.task1411;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
User, Looser, Coder and Proger
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = null;

        ArrayList<String> keys = new ArrayList<>();

        //тут цикл по чтению ключей, пункт 1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String line = br.readLine();
            if (!"user".equals(line) &&  !"loser".equals(line) && !"coder".equals(line) && !"proger".equals(line))
                break;
            else keys.add(line);
        }
        {
            //создаем объект, пункт 2
            for (String key :keys) {
                if ("user".equals(key)) person = new Person.User();
                if ("loser".equals(key)) person = new Person.Loser();
                if ("coder".equals(key)) person = new Person.Coder();
                if ("proger".equals(key)) person = new Person.Proger();

                doWork(person);
            }
        }
    }

    public static void doWork(Person person) {
        // пункт 3
        if (person instanceof Person.User) ((Person.User) person).live();
        if (person instanceof Person.Loser) ((Person.Loser) person).doNothing();
        if (person instanceof Person.Coder) ((Person.Coder) person).coding();
        if (person instanceof Person.Proger) ((Person.Proger) person).enjoy();
    }
}
