package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputFilename = reader.readLine();
        //inputFilename = "..\\Lion.avi.part1";
        // -u id productName price quantity
        //String foo = "-u 19847989 Ложка для сноубордистов, размер 10173.99 5432";
        //String fooo = "-d 198478";
        reader.close();

        if (args.length > 0) {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilename));

            class Record {
                int id;
                String line;

                public Record(int id, String line) {
                    this.id = id;
                    this.line = line;
                }
            }

            String line;
            List<Record> records = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {
                records.add(new Record(Integer.parseInt(line.substring(0, 8).trim()), line));
            }

            bufferedReader.close();

            FileWriter f = new FileWriter(inputFilename);
            BufferedWriter bufferedWriter = new BufferedWriter(f);
            String[] elements = args;
            //String[] elements = fooo.split(" ");
            // парсим входную строку с данными
            String operation = elements[0];
            int id = Integer.parseInt(elements[1]);

            if (operation.equals("-u")) {
                for (Record record : records
                ) {
                    if (record.id == id) {
                        String quantity = elements[elements.length - 1];
                        String price = elements[elements.length - 2];
                        String productName = "";
                        for (int i = 2; i < elements.length - 2; i++) {
                            productName = productName + elements[i] + " ";
                        }
                        record.line =
                                (id + "        ").substring(0, 8) +
                                        (productName + "                              ").substring(0, 30) +
                                        (price + "        ").substring(0, 8) +
                                        (quantity + "    ").substring(0, 4);

                    }
                }
            } else if (operation.equals("-d")) {
                records.removeIf(record -> record.id == id);
            }
            for (Record record : records) {
                bufferedWriter.write(record.line);
                bufferedWriter.newLine();}
            bufferedWriter.close();
            f.close();
        }
    }

}
