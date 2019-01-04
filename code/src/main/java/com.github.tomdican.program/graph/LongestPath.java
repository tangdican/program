package com.github.tomdican.program.graph;


import java.util.Stack;

public class LongestPath {

    public static void main(String[] args) {
        BaseGraph<AdjNode> g = new BaseGraph<>(7);
//        g.addEdge(0, new AdjNode(1, 5));
//        g.addEdge(0, new AdjNode(2, 3));
//        g.addEdge(1, new AdjNode(3, 6));
//        g.addEdge(1, new AdjNode(2, 2));
//        g.addEdge(2, new AdjNode(4, 4));
//        g.addEdge(2, new AdjNode(5, 2));
//        g.addEdge(2, new AdjNode(3, 7));
//        g.addEdge(3, new AdjNode(5, 1));
//        g.addEdge(3, new AdjNode(4, -1));
//        g.addEdge(4, new AdjNode(5, -2));

        // 0 1 3 4
        // 0 1 3 5
        // 0 1 4 3 5
        // 0 2 4 3 5
        // 0 2 4 3
        // 0 2 5
        // 0 2 3 5
        // 0 2 3 4
        // 0 2 6
        g.addEdge(0, new AdjNode(1, 1));
        g.addEdge(0, new AdjNode(2, 1));
        g.addEdge(1, new AdjNode(3, 1));
        g.addEdge(1, new AdjNode(4, 1));
        g.addEdge(2, new AdjNode(4, 1));
        g.addEdge(2, new AdjNode(5, 1));
        g.addEdge(2, new AdjNode(3, 1));
        g.addEdge(2, new AdjNode(6, 1));
        g.addEdge(3, new AdjNode(5, 1));
        g.addEdge(3, new AdjNode(4, 1));
        g.addEdge(4, new AdjNode(3, 1));

        int s = 2;
        graph = g;
        // 4 5 3 1 2 0

        // 1 3(1 4(1
        // 3 5(2 4(2
        // 5
        // 4 3(3
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
        visited[v] = true;

        for (AdjNode adj: graph.getAdj(v)) {
            if (!visited[adj.getV()])
                topologicalSortUtil(adj.getV(), visited, stack);
        }

        stack.push(v);
    }

    private static void longestPath(int s) {

        int V = graph.getV();
        Stack<Integer>  stack = new Stack<>();
        int dist[] = new int[V];

        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);

        for (int i = 0; i < V; i++)
            dist[i] = -1;
        dist[s] = 0;

        while (!stack.empty()) {
            int u = stack.pop();

            if (dist[u] != -1) {
                for (AdjNode adj: graph.getAdj(u)) {
                    if (dist[adj.getV()] < dist[u] + adj.getWeight())
                        dist[adj.getV()] = dist[u] + adj.getWeight();
                }
            }
        }

        for (int i = 0; i < V; i++) {
            System.out.print(",["+i+"]" + dist[i]);
        }
    }
}
