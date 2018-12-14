package com.github.tomdican.program.tree.balance;

import com.github.tomdican.program.tree.BinarySearchTree;
import com.github.tomdican.program.tree.Node;
import com.github.tomdican.program.tree.Tree2;

public class AVLTree {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);

        /* The constructed AVL Tree would be
             30
            /  \
          20   40
         /  \     \
        10  25    50
        */
        System.out.print("Preorder traversal" +
            " of constructed tree is : ");
        // output: 10,20,25,30,40,50,
        tree.preOrder(tree.root);

        System.out.println("");
        /**
         *,30
         ,20,40
         ,10,25,50
         *
         */
        Tree2.printLevelOrderLineWithQueue(tree.root);

    }

    private Node root;

    // A utility function to get the height of the tree
    int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }


    private Node leftRotate(Node node) {
        Node root = node.right;
        Node left2 = root.left;

        root.left = node;
        node.right = left2;

        root.left.height = Math.max(height(root.left.left), height(root.left.right)) + 1;
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        return root;
    }

    private Node rightRotate(Node node) {
        Node root = node.left;
        Node right2 = root.right;

        root.right = node;
        node.left = right2;

        root.right.height = Math.max(height(root.right.left), height(root.right.right)) + 1;
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        return root;
    }

    private Node insert(Node root, int i) {
        if (root == null) {
            return new Node(i);
        }

        // insert
        if (root.val > i) {
            root.left = insert(root.left, i);
        } else if (root.val < i) {
            root.right = insert(root.right, i);
        } else {
            root.val = i;
        }
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // rotate
        int balance = getBalance(root);
        if (balance < -1 && root.right.val < i) {
            root = leftRotate(root);
        }
        if (balance < -1 && root.right.val > i) {
            root.right = rightRotate(root.right);
            root = leftRotate(root);
        }

        if (balance > 1 && root.left.val > i) {
            root = rightRotate(root);
        }
        if (balance > 1 && root.left.val < i) {
            root.left = leftRotate(root.left);
            root = rightRotate(root);
        }

        return root;
    }

    private void preOrder(Node root) {
        if (root == null) {
            return;
        }
        preOrder(root.left);
        System.out.print(root.val+",");
        preOrder(root.right);
    }

}
