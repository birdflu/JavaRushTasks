package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputFileName = bufferedReader.readLine();

//        inputFileName = "C://temp//1.txt";
        bufferedReader.close();

        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        ArrayList<String> words = new ArrayList<>();
        while (reader.ready()) //пока есть непрочитанные байты в потоке ввода
        {
            for ( String s : reader.readLine().split(" ")) {
                words.add(s);
            }
        }
        reader.close();

        StringBuilder result = getLine(words.toArray(new String[words.size()]));
        System.out.println(result.toString());
    }

    public static StringBuilder arrayToString (ArrayList<StringBuilder> array) {
        if (array == null || array.size() == 0) return null;
        StringBuilder result = new StringBuilder();
        result.append(array.get(0));
        for (int i = 1; i < array.size(); i++) {
            result.append(" " + array.get(i));
        }
        return result;
    }

    public static ArrayList<StringBuilder> getArrayCopyWithoutElement (ArrayList<StringBuilder> donor, int deletedElementIndex){
        ArrayList<StringBuilder> recipient = new ArrayList<>();
        for (StringBuilder e : donor) recipient.add(e);
        recipient.remove(deletedElementIndex);
        return recipient;
    }

    public static ArrayList<StringBuilder> getArrayCopy (ArrayList<StringBuilder> donor){
        ArrayList<StringBuilder> recipient = new ArrayList<>();
        for (StringBuilder e : donor) recipient.add(e);
        return recipient;
    }

    public static StringBuilder find (ArrayList<StringBuilder> foundedWords, ArrayList<StringBuilder> candidates, int stopCount, ArrayList<StringBuilder> resultArray) {
        if (candidates.size() == 0 || foundedWords.size() >= stopCount) {
            if (foundedWords.size() == stopCount) resultArray.add(arrayToString(foundedWords));
            return arrayToString(foundedWords);
        }

        for (StringBuilder c : candidates){
            char wordHead = Character.toLowerCase(foundedWords.get(0).charAt(0));
            char wordTail = Character.toLowerCase(foundedWords.get(foundedWords.size()-1).charAt(foundedWords.get(foundedWords.size()-1).length()-1));

//            System.out.print("searching among [" + arrayToString(candidates) + "] for : " + arrayToString(foundedWords)) ;
//            System.out.println();

            for (int i = 0; i < candidates.size(); i++) {
                char candidateHead = Character.toLowerCase(candidates.get(i).charAt(0));
                char candidateTail = Character.toLowerCase(candidates.get(i).charAt(candidates.get(i).length()-1));

                if (wordTail == candidateHead) {
                    ArrayList<StringBuilder> next = getArrayCopy(foundedWords);
                    next.add(candidates.get(i));
                    find (next,getArrayCopyWithoutElement(candidates,i), stopCount, resultArray);
                }
                else
                if (candidateTail == wordHead) {
                    ArrayList<StringBuilder> next = getArrayCopy(foundedWords);
                    next.add(0,candidates.get(i));
                    find (next,getArrayCopyWithoutElement(candidates,i), stopCount, resultArray);
                }
            }

            return arrayToString(foundedWords);

        }
        return null;
    }

    public static StringBuilder getLine(String... words) {
        if (words == null || words.length == 0) return new StringBuilder("");
        ArrayList<StringBuilder> wordList = new ArrayList<>();
        for (String word: words) wordList.add(new StringBuilder(word));

        ArrayList<StringBuilder> resultArray = new ArrayList();
        for (int i = 0; i < words.length; i++) {
            ArrayList<StringBuilder> foundedWords = new ArrayList<>();
            foundedWords.add(new StringBuilder(words[i]));
            find(foundedWords, getArrayCopyWithoutElement(wordList,i), wordList.size(), resultArray);
        }

        return resultArray.get(0);
    }
}
