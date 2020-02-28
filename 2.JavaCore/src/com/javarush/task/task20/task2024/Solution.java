package com.javarush.task.task20.task2024;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/* 
Знакомство с графами
*/
public class Solution implements Serializable {
    //private static final long serialVersionUID = 0L;
    int node;
    List<Solution> edges = new LinkedList<>();

    public Solution(int node) {
        this.node = node;
    }

    public Solution() {
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution s1 = new Solution(1);
        Solution s2 = new Solution(2);
        Solution s3 = new Solution(3);
        Solution s4 = new Solution(4);
        Solution s5 = new Solution(5);
        Solution s6 = new Solution(6);

        s1.edges.add(s2);
        s2.edges.add(s2);
        s2.edges.add(s4);
        s2.edges.add(s5);
        s4.edges.add(s1);
        s4.edges.add(s5);
        s5.edges.add(s4);
        s6.edges.add(s3);

        List<Solution> graph = new LinkedList<>();;
        graph.add(s1);
        graph.add(s2);
        graph.add(s3);
        graph.add(s4);
        graph.add(s5);
        graph.add(s6);

        for (Solution s : graph)
            for (Solution k: s.edges) System.out.println(s.node + "->" + k.node);

        //save person to file
        FileOutputStream fileOutput = new FileOutputStream("..//graphSerialize.dat");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
        for (Solution s : graph)
            outputStream.writeObject(s);
        fileOutput.close();
        outputStream.close();

        //load person from file
        FileInputStream fiStream = new FileInputStream("..//graphSerialize.dat");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);

        List<Solution> loadedGraph = new LinkedList<>();;

        for (Solution s : graph)
            loadedGraph.add((Solution) objectStream.readObject());
        fiStream.close();
        objectStream.close();

        System.out.println("loaded : ");
        for (Solution s : loadedGraph)
            for (Solution k: s.edges) System.out.println(s.node + "->" + k.node);


    }


}
