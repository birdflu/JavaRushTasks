package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {

        /*
        The source data is
            Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
            </span></b></span><span>Super</span><span>girl</span>
        Firstly, remove /r and /n
        the data will be
            Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span><span>Super</span><span>girl</span>
        Secondly, found head and tail tag indexes and fill array lists, they will be
            headTagIndexes : 17 50 87 105
            tailTagIndexes : 69 80 98 115

            for (i = 0; i < headTagIndexes.length; i++)
                for (j = 0; j < tailTagIndexes.length; j ++)

            orderedTagIndexes : 17 50 69 80 87 98 105 115
            foreach (headIndex : headTagIndexes)
                take next index from orderedTagIndexes (for 17 take 50)
                if next index is head then openTagsCount++;
                else {
                        if next index is tail then openTagsCount--;
                        if openTagsCount = 0 then write substring from headIndex to index;
                        break;
                     }
             1. substring (17, 80)
             2. substring (50, 69)
             3. substring (87, 98)
             4. substring (105, 115)
         */
        // String tag = "span";
        String tag = args[0];
        String headTag = "<" + tag;
        String tailTag = "</" + tag + ">";
        String HTML = "";

        List<String> strings = new ArrayList<>();
        List<Integer> headTagIndexes = new ArrayList<>();
        List<Integer> tailTagIndexes = new ArrayList<>();
        List<Integer> orderedTagIndexes = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        // fileName = "c://temp//span.html";
        bufferedReader.close();

        int index = 0;

        bufferedReader = new BufferedReader(new FileReader(fileName));
        while (bufferedReader.ready()) {
            HTML = HTML + bufferedReader.readLine();
        }

        HTML = HTML.replaceAll("\\n","").replaceAll("\\r,","");
        // System.out.println(HTML);
        bufferedReader.close();

        String[] tagLines  = HTML.split(headTag);
        // for (int i = 1; i < tagLines.length; i++) System.out.println(headTag + tagLines[i]);

        int headTagIndex = -1;
        while (true){
            headTagIndex = HTML.indexOf(headTag, headTagIndex+1);
            if (headTagIndex == -1) break;
            else headTagIndexes.add(headTagIndex);
        }

        int tailTagIndex = -1;
        while (true){
            tailTagIndex = HTML.indexOf(tailTag, tailTagIndex+1);
            if (tailTagIndex == -1) break;
            else tailTagIndexes.add(tailTagIndex);
        }

        // for (int i: headTagIndexes) System.out.print(i + " ");
        // System.out.println();
        // for (int i: tailTagIndexes) System.out.print(i + " ");

        for (int i: headTagIndexes) orderedTagIndexes.add(i);
        for (int i: tailTagIndexes) orderedTagIndexes.add(i);
        Collections.sort(orderedTagIndexes);

        // System.out.println();
        // for (int i: orderedTagIndexes) System.out.print(i + " ");
        // System.out.println();

        /*orderedTagIndexes : 17 50 69 80 87 98 105 115
        foreach (headIndex : headTagIndexes)
        take next index from orderedTagIndexes (for 17 take 50)
        if next index is head then openTagsCount++;
                else {
            if next index is tail then openTagsCount--;
            if openTagsCount = 0 then write substring from headIndex to index;
            break;
        }
        1. substring (17, 80)
        2. substring (50, 69)
        3. substring (87, 98)
        4. substring (105, 115)
*/
        int openTagsCount = 0;
        for (int headIndex = 0; headIndex < headTagIndexes.size(); headIndex++)
            for (int indx = 0; indx < orderedTagIndexes.size(); indx++) {
                Integer hTagIndex = headTagIndexes.get(headIndex);
                Integer oTagIndex = orderedTagIndexes.get(indx);
                if (oTagIndex < hTagIndex) continue; // pass heads which was used before
                if (headTagIndexes.contains(oTagIndex)) { // if next index is head then openTagsCount++;
                    // System.out.println(oTagIndex + " is head");
                    openTagsCount++;
                }
                else {
                    // System.out.println(oTagIndex + " is tail");
                    openTagsCount--;
                    if (openTagsCount == 0) {
                        System.out.println(HTML.substring(hTagIndex,oTagIndex+tailTag.length()));
                        break;
                    }
                }
            }
    }
}
