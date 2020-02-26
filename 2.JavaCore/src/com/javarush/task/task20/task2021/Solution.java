package com.javarush.task.task20.task2021;

import java.io.*;

/* 
Сериализация под запретом
*/
public class Solution implements Serializable {
    public static class SubSolution extends Solution {
        private void writeObject(ObjectOutputStream out) throws IOException {
            throw new NotSerializableException();
        }
        private void readObject(ObjectInputStream in) throws IOException {
            throw new NotSerializableException();
        }
    }

    public static void main(String[] args) throws IOException {
        SubSolution subSolution = new SubSolution();

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Temp\\save.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(subSolution);

        objectOutputStream.close();
    }
}
