package com.github.tomdican.program.graph;

import com.github.tomdican.program.tree.Tree;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Graph {

    public static void main(String[] args) {
     //   Vertex adj = createGraph();
      //  breathFirstSearch(adj,0);
//        depthFirstSearch(adj,0);
//        System.out.println("");
//        depthFirstSearchRecursively(adj,0,new boolean[adj.V]);
//        System.out.println("");
//        topologicalSort(adj);
//
////        int motherVertice = findMotherVertices(adj);
////        System.out.println();
////        System.out.println("mother vertice: "+motherVertice);
//
//
//        int pathNum = countPath(adj, 2, 3);
//        System.out.println();
//        System.out.println("path number: "+pathNum);


//// bidirectional search
//       Vertex adj2 = createGraph2();
//        if (biDirSearch(adj2,0, adj2.V-1, adj.V) == -1) {
//            System.out.println("Path don't exist between");
//        }


        // best first search
//        Vertex adj2 = createGraph2();
//        bestFirstSearch(adj2, 0);

        // detect cycle with the undirected graph
        //Vertex adj2 = createGraph2();
//        boolean b = detectCycleWithUndirected(adj2, 0, new boolean[adj2.V]);
//        System.out.println(b);

        // find k core
//        Vertex adj2 = createGraph2();
//        printKCore(adj2, 3);

        // detect cycle with the directed graph
//        Vertex adj = createGraph();
//        boolean b = detectCycleWithDirected(adj, 0, new boolean[adj.V],new boolean[adj.V]);
//        System.out.println(b);

        // detect the cycle in Disjoint Set (Or Union-Find)




    }


    private static boolean detectCycleWithDirected(Vertex adj, int start, boolean[] visited, boolean[] recStack) {

        // Call the recursive helper function to
        // detect cycle in different DFS trees
        for (int i = 0; i < adj.V; i++)
            if (detectCyclicDirected(adj, i, visited, recStack))
                return true;

        return false;
    }

    private static boolean detectCyclicDirected(Vertex adj,int i, boolean[] visited, boolean[] recStack) {
        if (recStack[i])
            return true;

        if (visited[i]) // detect not back edge
            return false;

        visited[i] = true;
        recStack[i] = true;

        List<Integer> children = adj.adjListArray[i];

        for (Integer c: children)
            if (detectCyclicDirected(adj, c, visited, recStack))
                return true;

        recStack[i] = false; // exit of the function stack

        return false;
    }

    private static void printKCore(Vertex adj,int k) {
        boolean[] visited = new boolean[adj.V];
        int[] degree = new int[adj.V];

        int startIndex = 0;
        int minDeg = adj.V;
        for (int i = 0; i < adj.V; i++) {
            degree[i] = adj.adjListArray[i].size();
            if (degree[i] < minDeg) {
                minDeg = degree[i];
                startIndex = i;
            }
        }

        setDegreeDFS(adj, startIndex, visited, degree, k);
        for (int i = 0; i < adj.V; i++) {
            if (!visited[i]) {
                setDegreeDFS(adj,i,visited,degree,k);
            }
        }

        // print k core
        for (int i = 0; i < adj.V; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < adj.V; i++) {
            if (degree[i] >= k) {
                System.out.print("," + i+"-->");
                LinkedList<Integer> list = adj.adjListArray[i];
                for (int o:list) {
                    if (degree[o] >= k && !visited[o]) {
                        System.out.print("," + o);
                    }
                //    visited[o] = true;
                }
                System.out.println("");
            }
        }
    }

    private static boolean setDegreeDFS(Vertex adj, int startIndex, boolean[] visited, int[] degree, int k) {
        visited[startIndex] = true;
        LinkedList<Integer> list = adj.adjListArray[startIndex];
        for (Integer o:list) {
            // delete parent node,decrement the child
            if (degree[startIndex] < k) {
                degree[o]--;
            }
            if (!visited[o]) {
                // delete child node,decrement the parent
                if (setDegreeDFS(adj, o, visited, degree, k)) {
                    degree[startIndex]--;
                }
            }
        }
        return degree[startIndex] < k;
    }

    private static boolean detectCycleWithUndirected(Vertex adj,int i, boolean[] visited) {
        visited[i] = true;
        LinkedList<Integer> list = adj.adjListArray[i];
        for (Integer one:list) {
            if (visited[one]) {
                return true;
            } else {
                return detectCycleWithUndirected(adj,one,visited);
            }
        }
        return false;
    }

    /**
     * bestFirstSearch
     *
     * output:0,4,1,6,5,2,3,7,8,9,10,11,12,13,14
     *
     * source: https://www.geeksforgeeks.org/best-first-search-informed-search/
     *
     * @param adj
     * @param start
     */
    private static void bestFirstSearch(Vertex adj, int start) {
        //Stack<Integer> stack = new Stack<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(20);
        boolean visited[] = new boolean[adj.V];
        pq.add(start);
        visited[start] = true;
        while (!pq.isEmpty()) {
            int s = pq.poll();
            System.out.print(","+s);
            LinkedList<Integer> links = adj.adjListArray[s];
            Iterator<Integer> iterator = links.iterator();

            while (iterator.hasNext()) {
                int r = iterator.next();
                if (!visited[r]) {
                    pq.add(r);
                    visited[r] = true;
                }
            }

        }
    }

    private static Vertex createGraph2() {
        // bidirectional search
//        // no of vertices in graph
//        int n=15;
//
//        // source vertex
//        int s=0;
//
//        // target vertex
//        int t=14;
//        Vertex adj = new Vertex(n);
//        addEdge2(adj,0, 4);
//        addEdge2(adj,1, 4);
//        addEdge2(adj,2, 5);
//        addEdge2(adj,3, 5);
//        addEdge2(adj,4, 6);
//        addEdge2(adj,5, 6);
//        addEdge2(adj,6, 7);
//        addEdge2(adj,7, 8);
//        addEdge2(adj,8, 9);
//        addEdge2(adj,8, 10);
//        addEdge2(adj,9, 11);
//        addEdge2(adj,9, 12);
//        addEdge2(adj,10, 13);
//        addEdge2(adj,10, 14);



        // find k core
        Vertex adj = new Vertex(9);
        addEdge2(adj,0, 1);
        addEdge2(adj,0, 2);
        addEdge2(adj,1, 2);
        addEdge2(adj,1, 5);
        addEdge2(adj,2, 3);
        addEdge2(adj,2, 4);
        addEdge2(adj,2, 5);
        addEdge2(adj,2, 6);
        addEdge2(adj,3, 4);
        addEdge2(adj,3, 6);
        addEdge2(adj,3, 7);
        addEdge2(adj,4, 6);
        addEdge2(adj,4, 7);
        addEdge2(adj,5, 6);
        addEdge2(adj,5, 8);
        addEdge2(adj,6, 7);
        addEdge2(adj,6, 8);




        printGraph(adj,adj.V);

        return adj;
    }


    static int biDirSearch(Vertex adj, int s, int t, int V) {
        // boolean array for BFS started from
        // source and target(front and backward BFS)
        // for keeping track on visited nodes
        boolean s_visited[] = new boolean[V];
        boolean t_visited[] = new boolean[V];

        // Keep track on parents of nodes
        // for front and backward search
        int s_parent[] = new int[V];
        int t_parent[] = new int[V];

        // queue for front and backward search
        Queue<Integer> s_queue = new LinkedList<>();
        Queue<Integer> t_queue = new LinkedList<>();

        int intersectNode = -1;

        // necessary initialization
        for(int i=0; i<V; i++)
        {
            s_visited[i] = false;
            t_visited[i] = false;
        }

        s_queue.add(s);
        s_visited[s] = true;

        // parent of source is set to -1
        s_parent[s]=-1;

        t_queue.add(t);
        t_visited[t] = true;

        // parent of target is set to -1
        t_parent[t] = -1;

        while (!s_queue.isEmpty() && !t_queue.isEmpty())
        {
            // Do BFS from source and target vertices
            BFS(adj, s_queue, s_visited, s_parent);
            BFS(adj, t_queue, t_visited, t_parent);

            // check for intersecting vertex
            intersectNode = isIntersecting(adj, s_visited, t_visited);

            // If intersecting vertex is found
            // that means there exist a path
            if(intersectNode != -1)
            {
                System.out.println( "<< " + " Path exist between << "+ s + "<<  and << " + t + "<< ");
                System.out.println("cout << Intersection at:  <<"+ intersectNode+" << ");

                // print the path and exit the program
                printPath(adj, s_parent, t_parent, s, t, intersectNode);
                return 0;
            }
        }
        return -1;
    }

    private static void printPath(Vertex adj, int[] s_parent, int[] t_parent, int s, int t,
        int intersectNode) {
        List<Integer> path = new LinkedList<>();
        path.add(intersectNode);
        int i = intersectNode;
        while (i != s)
        {
            path.add(s_parent[i]);
            i = s_parent[i];
        }
        Collections.reverse(path);
        i = intersectNode;
        while(i != t)
        {
            path.add(t_parent[i]);
            i = t_parent[i];
        }

        System.out.println("cout<<*****Path*****");
        for(int it = 0;it < path.size(); it++) {
            System.out.print("cout <<*"+ path.get(it) +" << ");
        }
        System.out.println("cout<<");
    }

    private static int isIntersecting(Vertex adj, boolean[] s_visited, boolean[] t_visited) {
        int intersectNode = -1;
        for(int i=0;i<adj.V;i++)
        {
            // if a vertex is visited by both front
            // and back BFS search return that node
            // else return -1
            if(s_visited[i] && t_visited[i])
                return i;
        }
        return -1;
    }

    private static void BFS(Vertex adj, Queue<Integer> s_queue, boolean[] visited, int[] parent) {
        int current = s_queue.poll();
        Iterator<Integer> iterator = adj.adjListArray[current].iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            // If adjacent vertex is not visited earlier
            // mark it visited by assigning true value
            if (!visited[next])
            {
                // set current as parent of this vertex
                parent[next] = current;

                // Mark this vertex visited
                visited[next] = true;

                // Push to the end of queue
                s_queue.add(next);
            }
        }
    }

    /**
     * start: 2 , dest: 3
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

    /**
     * store in stack like post-order,so print pre-order from right to left
     * print a path from start point to end point ,then another path of adjacent;
     * like pre-order
     *
     * input:
     *   vertex 0 :1,4,
         vertex 1 :2,3,4,
         vertex 2 :3,
         vertex 3 :4,
         vertex 4 :
     *
     * output: 0,1,2,3,4
     *
     * @param adj
     */
    static void topologicalSort(Vertex adj) {
        Stack<Integer> stack = new Stack();
        boolean visited[] = new boolean[adj.V];

        for (int i = 0; i < adj.V; i++)
            if (visited[i] == false) {
                topologicalSortUtil(adj,i,visited,stack);
            }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ",");
        }
    }

    static void topologicalSortUtil(Vertex adj, int v, boolean visited[], Stack<Integer> stack) {
        visited[v] = true;

        Iterator<Integer> iterator = adj.adjListArray[v].iterator();

        while (iterator.hasNext()) {
            int a = iterator.next();
            if (!visited[a])
                topologicalSortUtil(adj, a, visited, stack);
        }

        stack.push(v);
    }

    /***
     * print a path from start point to end point ,then another path of adjacent;
     * like pre-order
     *
     * input:
     *   vertex 0 :1,4,
         vertex 1 :2,3,4,
         vertex 2 :3,
         vertex 3 :4,
         vertex 4 :

     * output: ,0,1,2,3,4
     *
     * @param adj
     * @param start
     */
    private static void depthFirstSearchRecursively(Vertex adj, int start, boolean visited[]) {
        System.out.print(","+start);
        visited[start] = true;
        Iterator<Integer> iterator = adj.adjListArray[start].iterator();
        while (iterator.hasNext()) {
            int a = iterator.next();
            if (!visited[a]) {
                depthFirstSearchRecursively(adj, a, visited);
            }
        }
    }

    /***
     * print a path from start point to end point ,then another path of adjacent;
     * like pre-order
     *
     * input:
     *   vertex 0 :1,4,
         vertex 1 :2,3,4,
         vertex 2 :3,
         vertex 3 :4,
         vertex 4 :

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
//        int V = 5;
//        Vertex adj = new Vertex(V);
        // annotation the line when testing the findMotherVertices method
//        addEdge(adj, 0, 1);
//        addEdge(adj, 0, 4);
//        addEdge(adj, 1, 2);
//        addEdge(adj, 1, 3);
//        addEdge(adj, 1, 4);
//        addEdge(adj, 2, 3);
//        addEdge(adj, 3, 4);

        // count path
//        addEdge(adj, 0, 1);
//        addEdge(adj, 0, 2);
//        addEdge(adj, 0, 3);
//        addEdge(adj, 2, 0);
//        addEdge(adj, 2, 1);
//        addEdge(adj, 1, 3);


        // detect the cycle in directed graph
//        addEdge(adj, 0, 1);
//        addEdge(adj, 0, 4);
//        addEdge(adj, 1, 2);
//        addEdge(adj, 1, 3);
//        addEdge(adj, 1, 4);
//        addEdge(adj, 2, 3);
//        addEdge(adj, 3, 4);
//        addEdge(adj, 3, 0);


        // detect the cycle in Disjoint Set (Or Union-Find)
        int V = 3;
        Vertex adj = new Vertex(V);
        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 0);

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

    static void addEdge2(Vertex adj, int u, int v)
    {
        adj.put(u,v);
        adj.put(v,u);
    }
}
