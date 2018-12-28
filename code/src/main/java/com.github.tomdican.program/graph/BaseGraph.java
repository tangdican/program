package com.github.tomdican.program.graph;

import java.util.LinkedList;
import java.util.List;

public class BaseGraph<T extends AdjListNode> {
    private int V;
    private List<T> adj[];

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

    public List<T>[] getAdj() {
        return adj;
    }

    public List<T> getAdj(int v) {
        return adj[v];
    }

    public void addEdge(int src, T dest) {
        this.adj[src].add(dest);
    }

    BaseGraph(int V) // Constructor
    {
        this.V = V;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }
}
