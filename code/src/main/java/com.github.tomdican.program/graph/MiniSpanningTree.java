package com.github.tomdican.program.graph;

import java.util.Arrays;

public class MiniSpanningTree {
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

    public static void main(String[] args) {
        Graph adj = createGraph();
        // is cyclic with find-union
       // System.out.println(iscyclic(adj));

        // Kruskal's algorithm to find Minimum Spanning Tree
        kruskalMST(adj);
    }

    /**
     * Kruskal's algorithm to find Minimum Spanning Tree
     *
     * output:
     *   edges 0 :2,3,4
         edges 1 :0,3,5
         edges 2 :
         edges 3 :0,1,10
         edges 4 :
     *
     *
     * @param adj
     */
    private static void kruskalMST(Graph adj) {
        adj.sort();

        Graph newAdj = new Graph(adj.V, adj.E);
        for (int i = 0; i < adj.E; i++) {
            newAdj.edges[i] = adj.edges[i];
            if (iscyclic(newAdj)) {
                newAdj.edges[i] = null;
            }
        }

        printGraph(newAdj);
    }

    private static void printGraph(Graph newAdj) {
        for (int i = 0; i < newAdj.E ; i++) {
            System.out.print("edges "+i+" :");
            if (newAdj.edges[i] != null) {
                System.out.print(newAdj.edges[i].src + "," + newAdj.edges[i].dest + ","+newAdj.edges[i].weight);
            }
            System.out.println();
        }
    }

    private static Graph createGraph() {

    // is cyclic with find-union
//        Graph adj = new Graph(3,3);
//        adj.add(0, 0 ,1);
//        adj.add(1, 1 ,2);
//        adj.add(2, 2 ,0);


        // Kruskal's algorithm to find Minimum Spanning Tree
        Graph adj = new Graph(4,5);
        adj.add(0, 0 ,1, 10);
        adj.add(1, 0 ,2, 6);
        adj.add(2, 0 ,3, 5);
        adj.add(3, 1 ,3, 15);
        adj.add(4, 2 ,3, 4);

        return adj;
    }


    /**
     * detect the cycle in Disjoint Set (Or Union-Find)
     *
     * input:
     *  vertex 0 :1,
     vertex 1 :2,
     vertex 2 :0,

     output: true
     *
     * @param adj
     * @return
     */
    static boolean iscyclic(Graph adj)
    {
        int[] parent = new int[adj.V];
        for (int i = 0; i < adj.V; i++) {
            parent[i] = -1;
        }

        for(int i=0;i<adj.E;i++)
        {
            if (adj.edges[i] == null) {
                continue;
            }

            int x = find(parent,adj.edges[i].src); // 都到达最终第一个开始节点 ，如果相等表示为圈
            int y = find(parent,adj.edges[i].dest);

            if(x==y)
                return true;
            union(parent,x,y);
        }
        return false;
    }

    static int find(int parent[],int x)
    {
        if(parent[x]==-1)
            return x;
        return find(parent,parent[x]);
    }

    static void union(int parent[],int x,int y)
    {
        int xs = find(parent,x);
        int ys = find(parent,y);
        parent[xs] = ys;
    }

}
