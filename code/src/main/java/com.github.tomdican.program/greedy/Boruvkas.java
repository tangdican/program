package com.github.tomdican.program.greedy;

/**
 * Boruvka’s algorithm
 * topics on Minimum Spanning Tree
 *
 * source: https://www.geeksforgeeks.org/boruvkas-algorithm-greedy-algo-9/
 */
public class Boruvkas {
    public static void main(String[] args) {

         /* Let us create following weighted graph
             10
        0--------1
        |  \     |
       6|   5\   |15
        |      \ |
        2--------3
            4       */
        GraphEdge.Graph adj = new GraphEdge.Graph(4, 5);
        adj.add(0,0,1,10);
        adj.add(1,0,2,6);
        adj.add(2,0,3,5);
        adj.add(3,1,3,15);
        adj.add(4,2,3,4);

        boruvkaMST(adj);

    }

    private static void boruvkaMST(GraphEdge.Graph adj) {
        int parent[] = new int[adj.V];
        int rank[] = new int[adj.V];
        int cheapest[] = new int[adj.V];

        for (int v = 0; v < adj.V; ++v) {
            parent[v] = v;
            rank[v] = 0;
            cheapest[v] = -1;
        }
        int numTrees = adj.V;
        int MSTweight = 0;


        while (numTrees > 1)
        {
            // Everytime initialize cheapest array
            for (int v = 0; v < adj.V; ++v)
            {
                cheapest[v] = -1;
            }

            // Traverse through all edges and update
            // cheapest of every component
            for (int i=0; i<adj.E; i++)
            {
                // Find components (or sets) of two corners
                // of current edge
                int set1 = find(parent, adj.edges[i].src);
                int set2 = find(parent, adj.edges[i].dest);

                // If two corners of current edge belong to
                // same set, ignore current edge
                if (set1 == set2)
                    continue;
                else {
                    if (cheapest[set1] == -1 ||
                            adj.edges[cheapest[set1]].weight > adj.edges[i].weight)
                        cheapest[set1] = i;
                    if (cheapest[set2] == -1 ||
                            adj.edges[cheapest[set2]].weight > adj.edges[i].weight)
                        cheapest[set2] = i;
                }
            }

            // Consider the above picked cheapest edges and add them
            // to MST
            for (int i=0; i<adj.V; i++)
            {
                // Check if cheapest for current set exists
                if (cheapest[i] != -1)
                {
                    int set1 = find(parent, adj.edges[cheapest[i]].src);
                    int set2 = find(parent, adj.edges[cheapest[i]].dest);

                    if (set1 == set2)
                        continue;
                    MSTweight += adj.edges[cheapest[i]].weight;
                    System.out.printf("Edge %d-%d included in MST\n",
                            adj.edges[cheapest[i]].src, adj.edges[cheapest[i]].dest);

                    // Do a union of set1 and set2 and decrease number
                    // of trees
                    Union(parent,rank, set1, set2);
                    numTrees--;
                }
            }
        }

        System.out.printf("Weight of MST is %d\n", MSTweight);
        return;

    }

    private static void Union(int[] parent, int[] rank, int x, int y) {
        int xroot = find(parent, x);
        int yroot = find(parent, y);

        if (rank[xroot] < rank[yroot])
            parent[xroot] = yroot;
        else if (rank[xroot] > rank[yroot])
            parent[yroot] = xroot;
        else {
            parent[yroot] = xroot;
            rank[xroot]++;
        }
    }

    private static int find(int[] parent, int i) {
        if (parent[i] != i)
            parent[i] = find(parent, parent[i]);

        return parent[i];
    }
}
