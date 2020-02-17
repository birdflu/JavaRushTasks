package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputFilename = reader.readLine();
        // inputFilename = "c:\\Temp\\Lion.avi.part1";
        // -c productName price quantity
        // String foo = "-c Куртка для сноубордистов, размер 10173.99 1234";
        // String fooo = "-c Куртка, размер 10173.777799 1223234";
        reader.close();

        if (args.length > 0) {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilename));

            String line;
            int max_id = Integer.MIN_VALUE;
            int id = max_id;
            while ((line = bufferedReader.readLine()) != null) {
                id = Integer.parseInt(line.substring(0, 8).trim());
                if (id > max_id) max_id = id;
            }

            bufferedReader.close();

            // line = fooo;
            FileWriter f = new FileWriter(inputFilename, true);
            BufferedWriter bufferedWriter = new BufferedWriter(f);
            String[] elements = args;
            String quantity = elements[elements.length - 1];
            String price = elements[elements.length - 2];
            String productName = "";
            for (int i = 1; i < elements.length - 2; i++) {
                productName = productName + elements[i] + " ";
            }

            String result =
                    ((max_id + 1) + "        ").substring(0, 8) +
                            (productName + "                              ").substring(0, 30) +
                            (price + "        ").substring(0, 8) +
                            (quantity + "    ").substring(0, 4);
            bufferedWriter.newLine();
            bufferedWriter.write(result);
            bufferedWriter.close();
            f.close();
        }
    }



}
