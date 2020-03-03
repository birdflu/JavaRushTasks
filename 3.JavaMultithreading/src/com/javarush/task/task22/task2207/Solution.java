package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputFileName = bufferedReader.readLine();

    //    inputFileName = "..//1.txt";
        bufferedReader.close();

        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        List<StringBuilder> strings = new ArrayList<>();
        while (reader.ready()) //пока есть непрочитанные байты в потоке ввода
        {
            for ( String s : reader.readLine().split(" ")) {
                strings.add(new StringBuilder(s));
            }
        }

        //закрываем потоки после использования
        reader.close();

        HashSet<Pair> hashSet = new HashSet<>();

        for (int i = 0; i < strings.size() ; i++) {
            for (int j = 0; j < strings.size(); j++) {
                Pair pair = new Pair();
                pair.first = strings.get(i).toString();
                pair.second = strings.get(j).toString();
                if (i != j && pair.first.equals(strings.get(j).reverse().toString())) hashSet.add(pair);
            }
        }
        for (Pair pair : hashSet
             ) {
            result.add(pair);
//            System.out.println(pair.toString());
        }

        }



    public static class Pair {
        String first;
        String second;

        public Pair() {

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;
        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                        first == null ? second :
                            second == null ? first :
                                first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
