package com.github.tomdican.program.greedy;

public class PrimMSTGraph {
    public static void main(String[] args) {

        /**
         *   2       3
         0 ---  1  ---- 2
         |    /  \      /
         |6  /3   \5   / 7
         |  /      \  /
         3 - - - - - 4
               4
         */
        GraphEdge.Graph adj = new GraphEdge.Graph(5, 7);
        adj.add(0,0,1,2);
        adj.add(1,0,3,6);
        adj.add(2,1,2,3);
        adj.add(3,1,3,3);
        adj.add(4,1,4,5);
        adj.add(5,2,4,7);
        adj.add(6,3,4,4);

        printMST(adj);
    }

    private static void printMST(GraphEdge.Graph adj) {
        int parent[] = new int[adj.V];
        int minWeight[] = new int[adj.V];
        boolean visited[] = new boolean[adj.V];
        for (int i = 0; i < adj.V; i++) {
            minWeight[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        minWeight[0]=0;

        for (int i = 0; i < adj.V - 1; i++) {

            int u = minWeightUnvisited(minWeight, visited);
            visited[u] = true;

            for (GraphEdge.Edge e: adj.edges) {
                if (e.src == u && visited[e.dest]==false
                        && e.weight < minWeight[e.dest]) {
                    parent[e.dest] = e.src;
                    minWeight[e.dest] = e.weight;
                }
            }
        }

        printMSTResult(parent,minWeight, adj);
    }

    private static void printMSTResult(int[] parent,int[] minWeight, GraphEdge.Graph adj) {
        System.out.println("Edge \tWeight");
        for (int i = 1;i<adj.V;i++) {
            if (parent[i] != -1) {
                System.out.println(parent[i] +" - "+ i+"\t"+
                        minWeight[i]);
            }
        }

    }

    private static int minWeightUnvisited(int[] minWeight, boolean[] visited) {
        int min = Integer.MAX_VALUE, min_index=-1;
        for (int v = 0; v < visited.length; v++)
            if (visited[v] == false && minWeight[v] < min) {
                min = minWeight[v];
                min_index = v;
            }
        return min_index;
    }
}
