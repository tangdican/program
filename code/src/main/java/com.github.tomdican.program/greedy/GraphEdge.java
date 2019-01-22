package com.github.tomdican.program.greedy;


import java.util.Arrays;

public class GraphEdge {
    static class Graph {
        int V ,E;
        GraphEdge.Edge[] edges;
        Graph(int v,int e) {
            V = v;
            E = e;
            edges = new GraphEdge.Edge[e];
        }

        public void add(int e, int src, int dest) {
            edges[e] = new GraphEdge.Edge();
            edges[e].dest = dest;
            edges[e].src = src;
        }

        public void add(int e, int src, int dest, int weight) {
            edges[e] = new GraphEdge.Edge();
            edges[e].dest = dest;
            edges[e].src = src;
            edges[e].weight = weight;
        }

        public void sort() {
            Arrays.sort(edges);
        }

    }

    static class Edge implements Comparable<GraphEdge.Edge> {
        int src,dest,weight;

        @Override
        public int compareTo(GraphEdge.Edge e) {
            return this.weight - e.weight;
        }
    }
}
