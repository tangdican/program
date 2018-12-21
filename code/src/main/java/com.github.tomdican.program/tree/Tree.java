package com.github.tomdican.program.tree;

/**
 * inOrder
 */
public class Tree {
    static Node root = null;

    public static void create(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            insert(arr[i++]);
        }
    }

    public static void insert(int val) {
        if (root == null) {
            root = new Node();
            root.val = val;
            return;
        }
        Node current = root;
        Node parent = root;
        boolean isLeft = false;
        while (current != null) {
            parent = current;
            if (val >= current.val) {
                isLeft = false;
                current = current.right;
            } else if (val < current.val) {
                isLeft = true;
                current = current.left;
            }
        }
        if (current == null) {
            current = new Node();
            current.val = val;
            if (isLeft) {
                parent.left = current;
            } else {
                parent.right = current;
            }
        }
    }

    public static int find(int val) {
        Node current = root;
        while (current != null) {
            if (val > current.val) {
                current = current.right;
            } else if (val < current.val){
                current = current.left;
            } else {
                return current.val;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {4,5,1,2,3,8,7,2,11,2,11,8,9,22,33,1,33,1,3};
        create(arr);
        int f = find(arr[1]);
        System.out.println(f);

    }
}
