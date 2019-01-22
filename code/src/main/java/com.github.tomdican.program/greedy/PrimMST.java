package com.github.tomdican.program.greedy;

/**
 * Prims minimum spanning tree
 *
 * source: https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
 */
public class PrimMST {
    private static final int V=5;

    void printMST(int parent[], int n, int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i]+" - "+ i+"\t"+
                    graph[i][parent[i]]);
    }

    int minKey(int key[], boolean mstSet[]) {
        int min = Integer.MAX_VALUE, min_index=-1;
        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        return min_index;
    }

    void primMST(int graph[][]) {
        int parent[] = new int[V];
        int key[] = new int [V];
        boolean mstSet[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;
        for (int count = 0; count < V-1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < V; v++)
                if (graph[u][v]!=0 && mstSet[v] == false &&
                        graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }
        printMST(parent, V, graph);
    }

    public static void main (String[] args) {

        PrimMST t = new PrimMST();

        /**
         *   2        3
          0 ---  1  ---- 2
          |    /  \      /
          |6  /8   \5   / 7
          |  /      \  /
          3 - - - - - 4
               9

         */
        int graph[][] = new int[][] {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};

        t.primMST(graph);
    }
}
