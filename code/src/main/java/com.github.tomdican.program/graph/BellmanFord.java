package com.github.tomdican.program.graph;

public class BellmanFord {
    public static void main(String[] args) {
        EdgeGraph.Graph graph = new EdgeGraph.Graph(5, 8);
        graph.add(0,0,1,-1);
        graph.add(1,0,2,4);
        graph.add(2,1,2,3);
        graph.add(3,1,3,2);
        graph.add(4,1,4,2);
        graph.add(5,3,2,5);
        graph.add(6,3,1,1);
        graph.add(7,4,3,-3);

        bellmanFord(graph, 0);
    }

    private static void bellmanFord(EdgeGraph.Graph graph, int src) {
        int V = graph.V, E = graph.E;
        int dist[] = new int[V];

        for (int i=0; i<V; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;

        for (int i=1; i<V; ++i) {
            for (int j=0; j<E; ++j) {
                int u = graph.edges[j].src;
                int v = graph.edges[j].dest;
                int weight = graph.edges[j].weight;
                if (dist[u]!=Integer.MAX_VALUE && // visited
                        dist[u]+weight<dist[v]) // smaller weight
                    dist[v]=dist[u]+weight;
            }
        }

        for (int j=0; j<E; ++j) {
            int u = graph.edges[j].src;
            int v = graph.edges[j].dest;
            int weight = graph.edges[j].weight;
            if (dist[u] != Integer.MAX_VALUE &&
                    dist[u]+weight < dist[v])
                System.out.println("Graph contains negative weight cycle");
        }
        printArr(dist, V);
    }

    static void printArr(int dist[], int V) {
        System.out.println("Vertex   Distance from Source");
        for (int i=0; i<V; ++i)
            System.out.println(i+"\t\t"+dist[i]);
    }
}
