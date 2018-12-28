package com.github.tomdican.program.graph;


import java.util.Stack;

public class LongestPath {

    public static void main(String[] args) {
        BaseGraph<AdjNode> g = new BaseGraph<>(6);
        g.addEdge(0, new AdjNode(1, 5));
        g.addEdge(0, new AdjNode(2, 3));
        g.addEdge(1, new AdjNode(3, 6));
        g.addEdge(1, new AdjNode(2, 2));
        g.addEdge(2, new AdjNode(4, 4));
        g.addEdge(2, new AdjNode(5, 2));
        g.addEdge(2, new AdjNode(3, 7));
        g.addEdge(3, new AdjNode(5, 1));
        g.addEdge(3, new AdjNode(4, -1));
        g.addEdge(4, new AdjNode(5, -2));

        int s = 1;
        graph = g;
        longestPath(s);
    }


   static class AdjNode extends AdjListNode{
        private int weight;

        public AdjNode(int _v, int _w)
        {
            this.weight = _w;
            setV(_v);
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        int getWeight() { return weight; }

    }



    static BaseGraph<AdjNode> graph;

    static void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
        // Mark the current node as visited
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        for (AdjNode adj: graph.getAdj(v)) {
            AdjNode node = adj;
            if (!visited[node.getV()])
                topologicalSortUtil(node.getV(), visited, stack);
        }

        // Push current vertex to stack which stores topological
        // sort
        stack.push(v);
    }

    private static void longestPath(int s) {

        int V = graph.getV();
        Stack<Integer>  stack = new Stack<>();
        int dist[] = new int[V];

        // Mark all the vertices as not visited
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Call the recursive helper function to store Topological
        // Sort starting from all vertices one by one
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);

        // Initialize distances to all vertices as infinite and
        // distance to source as 0
        for (int i = 0; i < V; i++)
            dist[i] = -1;
        dist[s] = 0;

        // Process vertices in topological order
        while (!stack.empty()) {
            // Get the next vertex from topological order
            int u = stack.pop();

            // Update distances of all adjacent vertices
            if (dist[u] != -1) {
                for (AdjNode adj: graph.getAdj(u)) {
                    if (dist[adj.getV()] < dist[u] + adj.getWeight())
                        dist[adj.getV()] = dist[u] + adj.getWeight();
                }
            }
        }

        // print the longest distance
        for (int i = 0; i < V; i++) {
            System.out.print("," + dist[i]);
        }
    }
}
