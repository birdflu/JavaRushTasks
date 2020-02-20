package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = bufferedReader.readLine();
        String file2 = bufferedReader.readLine();
//        file1 = "..//file1.txt";
//        file2 = "..//file2.txt";
        bufferedReader.close();

        BufferedReader fileReader1 = new BufferedReader(new FileReader(file1));
        BufferedReader fileReader2 = new BufferedReader(new FileReader(file2));

        // Condition "Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME." let us view only two step forward after Type.SAME occurs
        // That's why use CurrentString and NextString variables for each file enough
        // When we try find the Type.SAME string pair, we use FileReader.readLine() function and there are no way to return back read pointer. That's why we use reader counter for each file named fileStringInQueue
        // These variables, in fact, take values 0,1 or 2 only
        String file1CurrentString = "", file2CurrentString = "", file1NextString = "", file2NextString = "";
        int file1StringInQueue = 0, file2StringInQueue = 0;

        while (true) {
            // work with the ends
            if (!fileReader1.ready() & !fileReader2.ready() & file1StringInQueue <= 0 & file2StringInQueue <= 0) break;
            else
                // last string was removed from file2
                if ((fileReader1.ready() | file1StringInQueue > 0 ) & !fileReader2.ready() & file2StringInQueue <= 0) {
                    lines.add(new LineItem(Type.REMOVED, file1StringInQueue > 0 ? file1NextString : fileReader1.readLine()));
                    break;
                } else
                    // last string was added to file2
                    if (!fileReader1.ready() & file1StringInQueue <= 0 & (fileReader2.ready() | file2StringInQueue > 0)) {
                        lines.add(new LineItem(Type.ADDED, file2StringInQueue > 0 ? file2NextString : fileReader2.readLine()));
                        break;
                    } else // if (fileReader1.ready() & fileReader2.ready())
                    {
                        // investigate main string set: string was remove or add id the middle of the file
                        if (file1StringInQueue <= 0) {file1CurrentString = fileReader1.readLine(); file1StringInQueue++;}
                        if (file2StringInQueue <= 0) {file2CurrentString = fileReader2.readLine(); file2StringInQueue++;}

                        if (file1CurrentString.equals(file2CurrentString)) {
                            lines.add(new LineItem(Type.SAME, file1CurrentString));
                            if (file1StringInQueue-- > 0) file1CurrentString = file1NextString;
                            if (file2StringInQueue-- > 0) file2CurrentString = file2NextString;

                        } else {
                            file1NextString = fileReader1.readLine();
                            file2NextString = fileReader2.readLine();
                            file1StringInQueue++;
                            file2StringInQueue++;

                            if (file1CurrentString.equals(file2NextString)) {
                                lines.add(new LineItem(Type.ADDED, file2CurrentString));
                                file2CurrentString = file2NextString;
                                file2StringInQueue--;

                            } else if (file1NextString.equals(file2CurrentString)) {
                                lines.add(new LineItem(Type.REMOVED, file1CurrentString));
                                file1CurrentString = file1NextString;
                                file1StringInQueue--;
                            }
                        }
                     }
        }

        fileReader1.close();
        fileReader2.close();

        // lines.stream().map(l -> l.type + " " + l.line).forEach(System.out::println);

    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
