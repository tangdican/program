package com.github.tomdican.program.graph;

import java.util.LinkedList;

public class Vertex {

    int V;
    LinkedList<Integer> adjListArray[];

    public Vertex(int v) {
        V = v;
        adjListArray = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjListArray[i] = new LinkedList<>();
        }
    }

    public void put(int src, int desc) {
       adjListArray[src].addLast(desc);
    }


}
