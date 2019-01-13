package com.github.tomdican.program.graph;

public class MiniSpanningTree {
    static class Graph {
        int V ,E;
        Edge[] edges;
        Graph(int v,int e) {
            V = v;
            E = e;
            edges = new Edge[V];
        }

        public void add(int e, int src, int dest) {
            edges[e] = new Edge();
            edges[e].dest = dest;
            edges[e].src = src;
        }
    }
    static class Edge {
        int src,dest;
    }

    public static void main(String[] args) {
        Graph adj = createGraph();

        System.out.println(iscyclic(adj));
    }

    private static Graph createGraph() {
        Graph adj = new Graph(3,3);
        adj.add(0, 0 ,1);
        adj.add(1, 1 ,2);
        adj.add(2, 2 ,0);

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

            int x = find(parent,adj.edges[i].src);
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
