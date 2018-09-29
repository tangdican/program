package com.github.tomdican.program.graph;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

    public static void main(String[] args) {
        Vertex adj = createGraph();
        breathFirstSearch(adj,0);


    }

    private static void breathFirstSearch(Vertex adj,int start) {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[adj.V];
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int s = queue.poll();
            System.out.print(","+s);
            LinkedList<Integer> links = adj.adjListArray[s];
            Iterator<Integer> iterator = links.iterator();
            while (iterator.hasNext()) {
                int r = iterator.next();
                if (!visited[r]) {
                    queue.add(r);
                    visited[r] = true;
                }

            }
        }
        System.out.println("");
    }

    private static Vertex createGraph() {
        int V = 5;
        Vertex adj = new Vertex(V);
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 4);
        addEdge(adj, 1, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 1, 4);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);

        printGraph(adj,V);
        return adj;
    }

    private static void printGraph(Vertex adj, int v) {
        for (int i = 0; i < adj.V ; i++) {
            System.out.print("vertex "+i+" :");
            for (Integer val:adj.adjListArray[i]) {
                System.out.print(val+",");
            }
            System.out.println();
        }
    }

    private static void addEdge(Vertex adj, int src, int desc) {
        adj.put(src, desc);
        adj.put(desc, src);
    }

}
