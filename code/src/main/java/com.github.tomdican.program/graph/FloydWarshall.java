package com.github.tomdican.program.graph;

/**
 * find shortest distances between every pair of vertices
 * in a given edge weighted directed Graph
 *
 * source: https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/
 */
public class FloydWarshall {
    final static int INF = 99999;
    public static void main(String[] args) {

        /* Let us create the following weighted graph
           10
        (0)------->(3)
        |         /|\
        5 |          |
        |          | 1
        \|/         |
        (1)------->(2)
           3           */

        int graph[][] = {
                {0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}};

        floydWarshall(graph,4);


    }

    /**
     *
     * output:
     0      5      8      9
     INF      0      3      4
     INF    INF      0      1
     INF    INF    INF      0
     *
     *
     */
    private static void floydWarshall(int[][] graph,int V) {
        int i, j, k;

        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if (graph[i][k] + graph[k][j] < graph[i][j])
                        graph[i][j] = graph[i][k] + graph[k][j];
                    // 01 12 02  k=1 i=0 02=5+3=8
                    // 02 23 03 k=2 i=0 03=8+1=9
                    // 12 23 13 k=2 i=1 13=3+1=4
                }
            }
        }
        printSolution(graph,V);
    }

    private static void printSolution(int[][] dist, int V) {
        System.out.println("The following matrix shows the shortest "+
                "distances between every pair of vertices");
        for (int i=0; i<V; ++i) {
            for (int j=0; j<V; ++j) {
                if (dist[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+"   ");
            }
            System.out.println();
        }

    }
}
