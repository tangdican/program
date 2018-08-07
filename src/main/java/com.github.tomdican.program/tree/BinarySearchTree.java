package com.github.tomdican.program.tree;

import java.util.Arrays;

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

    public static void main(String[] args) {
        int[] arr = {9,1,5,7,2,4,3,6,8};
        insert(arr);
        Tree2.printLevelOrder(root);
    }

    /**
     * construct a balanced binary search tree
     * recursively
     *
     * @param arr
     */
    private static void insert(int[] arr) {
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
