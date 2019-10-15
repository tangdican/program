package com.github.tomdican.program.graph;

/**
 * Dijkstras shortest path
 *
 * source: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
 */
public class Dijkstras {
    public static void main(String[] args) {
        int graph[][] = new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        dijkstra(graph, 0, 9);
    }

    private static void dijkstra(int[][] graph, int src, int V) {
        int dist[] = new int[V]; // The output array. dist[i] will hold

        Boolean visited[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        dist[src] = 0;
        for (int count = 0; count < V-1; count++) {
            // find the smallest v without visited
            // 局部最优
            int u = minDistance(dist, visited, V);
            visited[u] = true;
            for (int v = 0; v < V; v++)
                // unvisited , set dist[v] to the smallest path
                // u点全局最优
                if (!visited[v] && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
        printSolution(dist, V);
    }

    static void printSolution(int dist[], int V) {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i+" tt "+dist[i]);
    }

    static int minDistance(int dist[], Boolean visited[], int V) {
        int min = Integer.MAX_VALUE, min_index=-1;
        for (int v = 0; v < V; v++)
            if (visited[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }
}
