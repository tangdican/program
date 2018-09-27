package com.github.tomdican.program.graph;

public class Graph {

    public static void main(String[] args) {
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
