package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\r\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(fileName, true);
        // in.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("pass");
        stream.close();
    }

    public static void main(String[] args) throws Exception {
        Solution sol = new Solution("../sol.txt");
        sol.writeObject("Write data");
        sol.writeObject("Write next data - we have flush (but not close) in writeObject");


        String filePath = "C:/Temp/save.txt";
        ObjectOutputStream streamOut = new ObjectOutputStream(new FileOutputStream(filePath));
        streamOut.writeObject(sol);
        sol.close();
        streamOut.close();

        ObjectInputStream streamIn = new ObjectInputStream(new FileInputStream(filePath));
        Solution sol2 = (Solution) streamIn.readObject();
        sol2.writeObject("new data.");
        sol2.close();
        streamIn.close();

    }
}
