package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution savedObject = new Solution (20);

        //save person to file
        FileOutputStream fileOutput = new FileOutputStream("c://temp//solutionExternalize.dat");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
        outputStream.writeObject(savedObject);
        fileOutput.close();
        outputStream.close();

        //load person from file
        Solution loadedObject = new Solution (20);
        FileInputStream fiStream = new FileInputStream("c://temp//solutionExternalize.dat");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);
        loadedObject = (Solution) objectStream.readObject();
        fiStream.close();
        objectStream.close();

        System.out.println(savedObject.string);
        System.out.println(loadedObject.string);

        if (savedObject.string.equals(loadedObject.string)) System.out.println("equal!");
//        System.out.println(new Solution(4));
    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
