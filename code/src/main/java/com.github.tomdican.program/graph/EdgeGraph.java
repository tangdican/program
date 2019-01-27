package com.github.tomdican.program.graph;

import java.util.Arrays;

public class EdgeGraph {
    static class Graph {
        int V ,E;
        Edge[] edges;
        Graph(int v,int e) {
            V = v;
            E = e;
            edges = new Edge[e];
        }

        public void add(int e, int src, int dest) {
            edges[e] = new Edge();
            edges[e].dest = dest;
            edges[e].src = src;
        }

        public void add(int e, int src, int dest, int weight) {
            edges[e] = new Edge();
            edges[e].dest = dest;
            edges[e].src = src;
            edges[e].weight = weight;
        }

        public void sort() {
            Arrays.sort(edges);
        }

    }
    static class Edge implements Comparable<Edge> {
        int src,dest,weight;

        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }
}
