package com.github.tomdican.program.tree.balance;

import com.github.tomdican.program.tree.Node;
import com.github.tomdican.program.tree.Tree2;

public class AVLTree {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // insert node
//        /* Constructing tree given in the above figure */
//        tree.root = tree.insert(tree.root, 10);
//        tree.root = tree.insert(tree.root, 20);
//        tree.root = tree.insert(tree.root, 30);
//        tree.root = tree.insert(tree.root, 40);
//        tree.root = tree.insert(tree.root, 50);
//        tree.root = tree.insert(tree.root, 25);
//
//        /* The constructed AVL Tree would be
//             30
//            /  \
//          20   40
//         /  \     \
//        10  25    50
//        */
//        System.out.print("Preorder traversal" +
//            " of constructed tree is : ");
//        // output: 10,20,25,30,40,50,
//        tree.preOrder(tree.root);
//
//        System.out.println("");
//        /**
//         *,30
//         ,20,40
//         ,10,25,50
//         *
//         */
//        Tree2.printLevelOrderLineWithQueue(tree.root);



        // delete node
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);
        tree.root = tree.insert(tree.root, 5);
        Tree2.printLevelOrderLineWithQueue(tree.root);
        tree.deleteNode(tree.root, 50);
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

    /**
     * source: https://www.geeksforgeeks.org/avl-tree-set-1-insertion/
     *
     * @param root
     * @param val
     * @return
     */
    private Node insert(Node root, int val) {
        if (root == null) {
            return new Node(val);
        }
        // insert
        if (root.val > val) {
            root.left = insert(root.left, val);
        } else if (root.val < val) {
            root.right = insert(root.right, val);
        } else {
            root.val = val;
        }

        root.height = 1 + Math.max(height(root.left), height(root.right));
        // rotate
        int balance = getBalance(root);
        if (balance < -1 && root.right.val < val) {
            root = leftRotate(root);
        }
        if (balance < -1 && root.right.val > val) {
            root.right = rightRotate(root.right);
            root = leftRotate(root);
        }

        if (balance > 1 && root.left.val > val) {
            root = rightRotate(root);
        }
        if (balance > 1 && root.left.val < val) {
            root.left = leftRotate(root.left);
            root = rightRotate(root);
        }

        return root;
    }



    Node deleteNode(Node root, int val) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;

        // delete
        if (root.val > val) {
            root.left = deleteNode(root.left, val);
        } else if (root.val < val) {
            root.right = deleteNode(root.right, val);
        } else {

            if (root.left == null && root.right != null) {
                root = root.right;
            } else if (root.right == null && root.left != null) {
                root = root.left;
            } else if (root.right != null && root.left != null) {
                int minVal = minValue(root.right).val;
                root.val = minVal;
                root.right = deleteNode(root.right, minVal);
            } else if (root.right == null && root.left == null) {
                root = null;
            }

        }

        if (root == null)
            return root;

        root.height = 1 + Math.max(height(root.left), height(root.right));

        // rotate
        int balance = getBalance(root);
        if (balance < -1 && getBalance(root.right) <= 0) {
            root = leftRotate(root);
        }
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            root = leftRotate(root);
        }

        if (balance > 1 && getBalance(root.left) >= 0) {
            root = rightRotate(root);
        }
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            root = rightRotate(root);
        }

        return root;
    }

    private Node minValue(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val+",");
        preOrder(root.left);
        preOrder(root.right);
    }

}
