package com.github.tomdican.program.tree;

import com.github.tomdican.program.Util;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ** binary search tree
 * 1) The left subtree of a node contains only nodes with keys
 * lesser than the node’s key.
 * 2) The right subtree of a node contains only nodes with keys
 * greater than the node’s key.
 * 3) The left and right subtree each must also be a binary search
 * tree.
 * 4) There must be no duplicate nodes.
 *
 * ** balanced binary search tree
 * 1) Left subtree of T is balanced
 * 2) Right subtree of T is balanced
 * 3) The difference between heights of left subtree and right subtree is not more than 1.
 *
 */
public class BinarySearchTree {

    private static Node root = null;
    private static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) {
        int[] arr = {9,1,5,7,2,4,3,6,8};
        //insertWithMid(arr);
        insertWithInorder2(arr);
        inOrder(arr, 0, root);
        Tree2.printLevelOrder(root);
//        createSumTree(root);
     //   convertToMinHeap(root);
        reverseBSTPath(root);
        Tree2.printLevelOrder(root);
    }

    /**
     * reverse bst path levelly
     *
     * input:
     *
     *   ,5
         ,3,8
         ,2,4,7,9
         ,1, , , ,6, , ,
    *******************************
     * output:
     *
         ,5
         ,8,3
         ,9,7,4,2
         ,6, , , ,1, , ,
     *
     *
     * @param node
     */
    private static void reverseBSTPath(Node node) {
        // reverse bst to get array
        queue.add(node);
        queue.add(null);
        int size = countSum(node);
        int arr[] = new int[size];
        int loc = 0;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current != null) {
                arr[loc++] = current.val;
                if (current.right != null) {
                    queue.add(current.right);
                }
                if (current.left != null) {
                    queue.add(current.left);
                }
            } else if (!queue.isEmpty()) {
                queue.add(null);
            }
        }

        // insert array into bst
        queue.clear();
        queue.add(node);
        queue.add(null);
        loc = 0;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current != null) {
                current.val = arr[loc++];
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            } else if (!queue.isEmpty()) {
                queue.add(null);
            }
        }
    }

    /**
     * Input: Binary Search Tree
     *     8
     *   /   \
     *  4     12
     * / \   /  \
     * 2 6  10  14
     *
     *
     * Output - Min Heap
     *      2
     *    /  \
     *   4    6
     *  / \  / \
     * 8 10 12 14
     *
     * @param root
     */
    private static void convertToMinHeap(Node root) {
        int size = countSum(root);
        int[] arr = new int[size];
        getArrayWithInOrder(arr, 0, root);
       // Util.printArray(arr);
       // root = createMinHeap(arr, root);
        createMinHeapWithLeftBig(arr, 0, root);
    }

    /**
     * min heap:
     * all the values in the left subtree of a node
     * should be less than all the values
     * in the right subtree of the node
     ***************
     * input:
     *
     *   ,5
         ,3,8
         ,2,4,7,9
         ,1, , , ,6, , ,
     *******************
     * output:
     *
         ,1
         ,2,6
         ,3,5,7,9
         ,4, , , ,8, , ,
     *
     * @param arr
     * @param loc
     * @param node
     * @return
     */
    private static int createMinHeapWithLeftBig(int[] arr, int loc, Node node) {
        if (node == null) {
            return loc;
        }
        node.val = arr[loc++];
        loc = createMinHeapWithLeftBig(arr, loc, node.left);
        loc = createMinHeapWithLeftBig(arr, loc, node.right);
        return loc;
    }

    /***
     *
     * input:
     *
     *   ,5
         ,3,8
         ,2,4,7,9
         ,1, , , ,6, , ,
     *********************
     *
     * output:
     *
         ,1
         ,2,3
         ,4,5,6,7
         ,8, , , ,9, , ,
     *
     * @param arr
     * @param root
     * @return
     */
    private static Node createMinHeap(int[] arr, Node root) {
        queue.add(root);
        int i = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node != null && i < arr.length) {
                node.val = arr[i++];
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return root;
    }

    private static int getArrayWithInOrder(int[] arr,int loc, Node node) {
        if (node == null) {
            return loc;
        }
        int i = getArrayWithInOrder(arr, loc, node.left);
        arr[i++] = node.val;
        return getArrayWithInOrder(arr, i, node.right);
    }

    private static int countSum(Node node) {
        if (node == null) {
            return 0;
        }
        int l = countSum(node.left);
        int r = countSum(node.right);
        return l + r + 1;
    }

    /**
     * create a sum tree
     *
     *      ,45
        ,10     ,30
      ,3,4    ,13,9
     ,1, , , ,6, , ,
     */
    private static int createSumTree(Node node) {
        if (node == null) {
            return 0;
        }
        int l = createSumTree(node.left);
        int r = createSumTree(node.right);
        int val = l + r + node.val;
        node.val = val;
        return val;
    }


    /**
     * construct a balanced binary search tree
     *
     * @param arr
     */
    private static void insertWithInorder2(int[] arr) {
        Arrays.sort(arr);
        constructBalancedStructure2(arr.length);
    }

    private static void constructBalancedStructure2(int size) {
        int num = size;
        int maxLevel = 0;
        for (int i = 0;num > 0 ; i++) {
            int levelNum = (int)Math.pow(Double.valueOf("2"), Double.valueOf(String.valueOf(i)));
            num -= levelNum;
            maxLevel++;
        }
        root = constructBalancedStructure3(size, root);
       // root = constructBalancedStructure2(size, maxLevel, root);
    }

    /**
     * consruct a balanced BST structure
     * by inorder
     *       6
     *     4   8
     *   2  5 7 9
     *  1 3
     * @param num
     * @param level
     * @param node
     * @return
     */
    private static Node constructBalancedStructure2(int num, int level, Node node) {
        if (num > 0) {

        }
        node = new Node();
//        int levelNum = (int)Math.pow(Double.valueOf("2"),
//            Double.valueOf(String.valueOf(level-1)));
//        node.left = constructBalancedStructure2(num / 2, level-1, node.left);
//        node.right = constructBalancedStructure2(num - num / 2 -1, level-1, node.right);
        return node;
    }


    /**
     * consruct a balanced BST structure
     * by inorder
     *        6
     *     4    8
     *   2  5  7 9
     *  1     3
     * @param num
     * @param node
     * @return
     */
    private static Node constructBalancedStructure3(int num, Node node) {
        if (num <= 0) {
            return null;
        }
        node = new Node();
        node.left = constructBalancedStructure3(num / 2, node.left);
        node.right = constructBalancedStructure3(num - num/2 -1, node.right);
        return node;
    }

    /**
     * construct a balanced binary search tree
     * O(NlgN)
     *
     * @param arr
     */
    private static void insertWithInorder(int[] arr) {
        Arrays.sort(arr);
        constructBalancedStructure(arr.length);
    }

    private static void constructBalancedStructure(int size) {
        int num = size;
        for (int i = 0;; i++) {
            int levelNum = (int)Math.pow(Double.valueOf("2"), Double.valueOf(String.valueOf(i)));
            num -= levelNum;
            if (num >= 0) {
                root = constructBalancedStructure(levelNum, i+1, root);
            }else if ((num + levelNum) > 0) {
                root = constructBalancedStructure(num + levelNum, i+1, root);
            }  else {
                break;
            }
        }
    }

    private static Node constructBalancedStructure(int num, int level, Node node) {
        if (level < 1) {
            return null;
        }

        if (level == 1 && num > 0) {
            node = new Node();
            return node;
        }

        if (node == null) {
            return null;
        }

        int levelNum = (int)Math.pow(Double.valueOf("2"),
            Double.valueOf(String.valueOf(level-1)));
        if (num < levelNum) {
            node.left = constructBalancedStructure(num, level-1, node.left);
            node.right = constructBalancedStructure(num - levelNum / 2, level - 1, node.right);
        } else {
            node.left = constructBalancedStructure(num, level-1, node.left);
            node.right = constructBalancedStructure(num, level - 1, node.right);
        }

        return node;
    }

    private static int inOrder(int[] arr, int loc, Node node) {
        if (node == null) {
            return loc;
        }

        loc = inOrder(arr, loc, node.left);
        node.val = arr[loc++];
        loc = inOrder(arr, loc, node.right);
        return loc;
    }

    /**
     * construct a balanced binary search tree
     * recursively insert the middle value
     * after sorting the array
     *
     * @param arr
     */
    private static void insertWithMid(int[] arr) {
        Arrays.sort(arr);
        insert(arr,0, arr.length-1);
    }

    private static void insert(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;
        root = insertOne(arr[mid], root);

        insert(arr, start, mid-1);
        insert(arr, mid + 1, end);
    }

    /**
     * insert a element
     * into the binary search tree
     *
     * @param a,node
     */
    private static Node insertOne(int a, Node child) {
        if (root == null) {
            root = new Node();
            root.val = a;
            return root;
        }
        if (child == null) {
            child = new Node();
            child.val = a;
            return child;
        }
        if (a > child.val) {
            child.right = insertOne(a, child.right);
        } else {
            child.left = insertOne(a, child.left);
        }
        return child;
    }

}
