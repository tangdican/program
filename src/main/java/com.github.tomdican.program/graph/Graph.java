package com.github.tomdican.program.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {

    public static void main(String[] args) {
        Vertex adj = createGraph();
      //  breathFirstSearch(adj,0);
      //  depthFirstSearch(adj,0);

//        int motherVertice = findMotherVertices(adj);
//        System.out.println();
//        System.out.println("mother vertice: "+motherVertice);


        int pathNum = countPath(adj, 2, 3);
        System.out.println();
        System.out.println("path number: "+pathNum);
    }

    /**
     *
     * input:
     *   vertex 0 :1,2,3,
         vertex 1 :3,
         vertex 2 :0,1,
         vertex 3 :
         vertex 4 :

     ****************************
     *
         output:

         ,2,1,3
         ,0,3
         ,1,3
         path number: 3

     *
     *  source: https://www.geeksforgeeks.org/count-possible-paths-two-vertices/
     *
     * @param adj
     * @param src
     * @param desc
     * @return
     */
    private static int countPath(Vertex adj, int src, int desc) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[adj.V];
        stack.push(src);
        visited[src] = true;
        int pathNum = 0;
        String prexPath = "";
        while (!stack.isEmpty()) {
            Integer v = stack.pop();
            System.out.print(","+ v);
            Iterator<Integer> iterator = adj.adjListArray[v].iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (!visited[next]) {
                    if (next == desc) {
                        pathNum++;
                        visited[v] = false;
                        System.out.println(","+ next);
                    } else {
                        stack.push(next);
                        visited[next] = true;
                    }

                }
            }
        }
        return pathNum;
    }

    /**
     * input:
     *
     *   vertex 0 :4,
         vertex 1 :2,3,4,
         vertex 2 :3,
         vertex 3 :4,
         vertex 4 :
         ,0,4,1,3,2
     ******************************
     output:
     mother vertice: 1
     *
     * @param adj
     * @return
     */
    private static int findMotherVertices(Vertex adj) {
        boolean visited[] = new boolean[adj.V];
        int motherV = 0;
        for (int i = 0; i < adj.V; i++) {
            if (!visited[i]) {
                DFSWithVisited(adj, i, visited);
                motherV = i;
            }
        }
        return motherV;
    }

    /****
     * dfs with visited
     * @param adj
     * @param start
     * @param visited
     */
    private static void DFSWithVisited(Vertex adj, int start, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;
        while (!stack.isEmpty()) {
            int s = stack.pop();
            System.out.print(","+s);
            LinkedList<Integer> links = adj.adjListArray[s];
            Iterator<Integer> iterator = links.iterator();

            while (iterator.hasNext()) {
                int r = iterator.next();
                if (!visited[r]) {
                    stack.add(r);
                    visited[r] = true;
                }
            }

        }
    }

    /***
     * output: ,0,4,3,2,1
     *
     * @param adj
     * @param start
     */
    private static void depthFirstSearch(Vertex adj, int start) {
        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[adj.V];
        stack.push(start);
        visited[start] = true;
        while (!stack.isEmpty()) {
            int s = stack.pop();
            System.out.print(","+s);
            LinkedList<Integer> links = adj.adjListArray[s];
            Iterator<Integer> iterator = links.iterator();

            while (iterator.hasNext()) {
                int r = iterator.next();
                if (!visited[r]) {
                    stack.add(r);
                    visited[r] = true;
                }
            }

        }
    }

    /**
     * output: ,0,1,4,2,3
     *
     * @param adj
     * @param start
     */
    private static void breathFirstSearch(Vertex adj,int start) {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[adj.V];
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int s = queue.poll();
            System.out.print(","+s);
            LinkedList<Integer> links = adj.adjListArray[s];
            Iterator<Integer> iterator = links.iterator();
            while (iterator.hasNext()) {
                int r = iterator.next();
                if (!visited[r]) {
                    queue.add(r);
                    visited[r] = true;
                }

            }
        }
        System.out.println("");
    }

    /**
     *  vertex 0 :1,4,
        vertex 1 :0,2,3,4,
        vertex 2 :1,3,
        vertex 3 :1,2,4,
        vertex 4 :0,1,3,
     *
     * @return
     */
    private static Vertex createGraph() {
        int V = 5;
        Vertex adj = new Vertex(V);
        // annotation the line when testing the findMotherVertices method
//        addEdge(adj, 0, 1);
//        addEdge(adj, 0, 4);
//        addEdge(adj, 1, 2);
//        addEdge(adj, 1, 3);
//        addEdge(adj, 1, 4);
//        addEdge(adj, 2, 3);
//        addEdge(adj, 3, 4);

        // count path
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 0, 3);
        addEdge(adj, 2, 0);
        addEdge(adj, 2, 1);
        addEdge(adj, 1, 3);

        printGraph(adj,V);
        return adj;
    }

    private static void printGraph(Vertex adj, int v) {
        for (int i = 0; i < adj.V ; i++) {
            System.out.print("vertex "+i+" :");
            for (Integer val:adj.adjListArray[i]) {
                System.out.print(val+",");
            }
            System.out.println();
        }
    }

    private static void addEdge(Vertex adj, int src, int desc) {
        adj.put(src, desc);
      //  adj.put(desc, src);
    }

}
