package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same").forEach(System.out::println);
        System.out.println();
        int[][] crossword2 = new int[][]{
                {'r', 'm', 'a', 'r', 'r', 'e'},
                {'m', 'e', 'a', 'e', 'e', 'm'},
                {'s', 'a', 'm', 'e', 's', 'o'},
                {'m', 'o', 'p', 'o', 'o', 'h'},
                {'h', 'r', 'e', 'm', 'h', 'h'}
        };
        detectAllWords(crossword2, "home", "same", "homer").forEach(System.out::println);

/*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)

home - (5, 3) - (5, 0)
home - (0, 4) - (3, 1)
home - (4, 4) - (1, 1)
same - (0, 2) - (3, 2)
homer - (0, 4) - (4, 0)
homer - (4, 4) - (0, 0)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> wordList = new ArrayList<>();
        for (String nextWord : words) {
            char[] word = nextWord.toCharArray();
            for (int j= 0; j < crossword.length; j++){
                for (int i = 0; i < crossword[j].length; i++){
                    if (word[0] == ((char)crossword[j][i])){
                        Word wordTest = null;
                        wordTest = finder1(crossword, word, nextWord, i, j);
                        if (wordTest != null){
                            wordList.add(wordTest);
                        }
                        wordTest = finder2(crossword, word, nextWord, i, j);
                        if (wordTest != null){
                            wordList.add(wordTest);
                        }
                        wordTest = finder3(crossword, word, nextWord, i, j);
                        if (wordTest != null){
                            wordList.add(wordTest);
                        }
                        wordTest = finder4(crossword, word, nextWord, i, j);
                        if (wordTest != null){
                            wordList.add(wordTest);
                        }
                        wordTest = finder5(crossword, word, nextWord, i, j);
                        if (wordTest != null){
                            wordList.add(wordTest);
                        }
                        wordTest = finder6(crossword, word, nextWord, i, j);
                        if (wordTest != null){
                            wordList.add(wordTest);
                        }
                        wordTest = finder7(crossword, word, nextWord, i, j);
                        if (wordTest != null){
                            wordList.add(wordTest);
                        }
                        wordTest = finder8(crossword, word, nextWord, i, j);
                        if (wordTest != null){
                            wordList.add(wordTest);
                        }
                    }
                }
            }
        }
//        for (Word next : wordList){
//            System.out.println(next);
//        }
        return wordList;
    }
    public static Word finder1 (int[][] crossword, char[] wordChar, String word, int i, int j){
        int k = 0;
        int e = wordChar.length - 1;
        Word temp = null;
        if (i + 1 < crossword[j].length && wordChar[k+1] == ((char)crossword[j][i+1])){
            if (i + e < crossword[j].length && wordChar[e] == ((char)crossword[j][i+e])){
                temp = new Word(word);
                temp.setStartPoint(i, j);
                temp.setEndPoint((i + (e)), j); }
        }
        return temp;
    }
    public static Word finder2 (int[][] crossword, char[] wordChar, String word, int i, int j){
        int k = 0;
        int e = wordChar.length - 1;
        Word temp = null;
        if (i - 1 >= 0 && wordChar[k+1] == ((char)crossword[j][i-1])){
            if (i - e >= 0 && wordChar[e] == ((char)crossword[j][i-e])){
                temp = new Word(word);
                temp.setStartPoint(i, j);
                temp.setEndPoint((i - e),j); }
        }
        return temp;
    }
    public static Word finder3 (int[][] crossword, char[] wordChar, String word, int i, int j){
        int k = 0;
        int e = wordChar.length - 1;
        Word temp = null;
        if (j +1 < crossword.length && wordChar[k+1] == ((char)crossword[j+1][i])){
            if (j + e < crossword.length && wordChar[e] == ((char)crossword[j+e][i])){
                temp = new Word(word);
                temp.setStartPoint(i, j);
                temp.setEndPoint(i,(j + e)); }
        }
        return temp;
    }
    public static Word finder4 (int[][] crossword, char[] wordChar, String word, int i, int j){
        int k = 0;
        int e = wordChar.length - 1;
        Word temp = null;
        if (j - 1 >= 0 && wordChar[k+1] == ((char)crossword[j-1][i])){
            if (j - e >= 0 && wordChar[e] == ((char)crossword[j-e][i])){
                temp = new Word(word);
                temp.setStartPoint(i, j);
                temp.setEndPoint(i,(j - e)); }
        }
        return temp;
    }
    public static Word finder5 (int[][] crossword, char[] wordChar, String word, int i, int j){
        int k = 0;
        int e = wordChar.length - 1;
        Word temp = null;
        if ((i +1 < crossword[j].length && j + 1 < crossword.length)&& wordChar[k+1] == ((char)crossword[j+1][i+1])){
            if ((i + e < crossword[j].length && j + e < crossword.length)&& wordChar[e] == ((char)crossword[j+e][i+e])){
                temp = new Word(word);
                temp.setStartPoint(i, j);
                temp.setEndPoint((i + e),(j + e)); }
        }
        return temp;
    }
    public static Word finder6 (int[][] crossword, char[] wordChar, String word, int i, int j){
        int k = 0;
        int e = wordChar.length - 1;
        Word temp = null;
        if ((i + 1 < crossword[j].length && j - 1 >= 0) && wordChar[k+1] == ((char)crossword[j-1][i+1])){
            if ((i + e < crossword[j].length && j - e >= 0) && wordChar[e] == ((char)crossword[j-e][i+e])){ temp = new Word(word);
                temp.setStartPoint(i, j);
                temp.setEndPoint((i + e),(j - e)); }
        }
        return temp;
    }
    public static Word finder7 (int[][] crossword, char[] wordChar, String word, int i, int j){
        int k = 0;
        int e = wordChar.length - 1;
        Word temp = null;
        if ((j + 1 < crossword.length && i - 1 >= 0) && wordChar[k+1] == ((char)crossword[j+1][i-1])){
            if ((j + e < crossword.length && i - e >= 0) && wordChar[e] == ((char)crossword[j+e][i-e])){
                temp = new Word(word);
                temp.setStartPoint(i, j);
                temp.setEndPoint((i - e),(j + e)); }
        }
        return temp;
    }
    public static Word finder8 (int[][] crossword, char[] wordChar, String word, int i, int j){
        int k = 0;
        int e = wordChar.length - 1;
        Word temp = null;
        if ((i - 1 >= 0 && j - 1 >= 0) && wordChar[k+1] == ((char)crossword[j-1][i-1])){
            if ((i - e >= 0 && j - e >= 0) && wordChar[e] == ((char)crossword[j-e][i-e])){
                temp = new Word(word);
                temp.setStartPoint(i, j);
                temp.setEndPoint((i - e),(j - e)); }
        }
        return temp;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
