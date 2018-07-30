package com.github.tomdican.program.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * insert from left to right
 *      4
 *    5   1
 *  2  3
 * 8 7
 */
public class Tree2 {
    private static Node root = null;
    private static Stack<Node> stack = new Stack<>();
    private static Queue<Node> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        int[] arr = {4,5,1,2,3,8,7};
        createLeftToRight(arr);
       // create(arr);
//        find(arr[4], root);
//
//        System.out.println("preOrder:");
//        preOrder(root);
//        System.out.println("");
//
//        System.out.println("inOrder:");
//        inOrder(root);
//        System.out.println("");
//        System.out.println("postOrder:");
//        postOrder(root);

  //      printLevelNodes(root , 3);
//        int maxLevel = getMaxLevel(root, 1);
//        System.out.println("maxLeve:" + maxLevel);

//        int maxLevel = getMaxLevel2(root);
//        System.out.println("maxLeve:" + maxLevel);

        // printLevelOrder(root);
        printLevelOrderLineWithQueue(root);

    }

    public static void printLevelOrderWithQueue(Node node) {
        if (node == null) {
            return;
        }
        queue.add(node);
        Node temp = null;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.print("," + temp.val);
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
    }

    public static void printLevelOrderLineWithQueue(Node node) {
        if (node == null) {
            return;
        }
        Node line = new Node();
        line.val = -1;// mark to print next line;
        queue.add(node);
        queue.add(line);
        Node temp = null;
        while (queue.size()>=2) {
            temp = queue.poll();
            if (temp.val == -1){
                System.out.println("");
                queue.add(line);
            } else {
                System.out.print("," + temp.val);
            }
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
        System.out.println("");
    }


    public static void printLevelOrder(Node node) {
        int maxLevel = getMaxLevel(node, 1);
        for (int i = 0; i < maxLevel; i++) {
            printLevelNodes(node, i + 1);
            System.out.println("");
        }
    }

    public static int getMaxLevel(Node node, int maxLevel) {
        if (node == null) {
            return --maxLevel;
        }
        int leftMax = maxLevel;
        int rightMax = maxLevel;
        int leftMaxLevel = getMaxLevel(node.left, ++leftMax);
        int rightMaxLevel = getMaxLevel(node.right, ++rightMax);

        if (leftMaxLevel > rightMaxLevel) {
            return leftMaxLevel;
        } else {
            return rightMaxLevel;
        }
    }

    public static int getMaxLevel2(Node node) {
        if (node == null) {
            return 0;
        }
        int leftLevel = getMaxLevel2(node.left);
        int rightLevel = getMaxLevel2(node.right);

        if (leftLevel > rightLevel) {
            return leftLevel + 1;
        } else {
            return rightLevel + 1;
        }
    }

    public static void printLevelNodes(Node node, int level) {
        if (node == null || level <= 0 ) {
            return;
        }
        if (level == 1) {
            System.out.print("," + node.val);
        }
        printLevelNodes(node.left, level - 1);

        printLevelNodes(node.right, level - 1);
    }

    public static void preOrder(Node node) {
        if (node == null) {
            return;
        }
        preOrder(node.left);
        System.out.print("," + node.val);
        preOrder(node.right);

    }

    public static void inOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print("," + node.val);
        inOrder(node.left);
        inOrder(node.right);
    }

    public static void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.print("," + node.val);
    }

    public static void find(int val, Node node) {
        if (node == null) {
            return;
        }

        if (node.val == val) {
            System.out.println("val:"+val);
        }

        find(val, node.left);
        find(val, node.right);

    }

    public static void create(int[] arr) {
        int i = 0;
        if (root == null) {
            root = new Node();
            root.val = arr[i];
            i++;
        }
        while (i < arr.length) {
            insert(arr[i++]);
        }
    }

    // a bug: insert left always
    public static void insert(int val) {
        Node current = root;
        Node parent = root;
        boolean isLeft = true;
        while (current != null) {
            if (current.left == null) {
                parent = current;
                current = current.left;
                isLeft = true;
            } else if (current.right == null) {
                parent = current;
                current = current.right;
                isLeft = false;
            } else {
                parent = current;
                current = current.left;
            }
        }
        if (isLeft) {
            parent.left = new Node();
            parent.left.val = val;
        } else {
            parent.right = new Node();
            parent.right.val = val;
        }
    }

    public static void createLeftToRight(int[] arr) {
        int i = 0;
        if (root == null) {
            root = new Node();
            root.val = arr[i];
            i++;
        }
        while (i < arr.length) {
            insertLeftToRight(root, arr[i++]);
        }
    }

    public static void insertLeftToRight(Node node, int val) {
        if (node == null) {
            node = new Node();
            node.val = val;
            return;
        }
        Node root = node;
        int level = getMaxLevelFromInsert(node);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 0;
        while (!queue.isEmpty()) {
            Node tempNode = queue.peek();
            if (i == level - 1) {
                if (tempNode.left == null) {
                    tempNode.left = new Node();
                    tempNode.left.val = val;
                    return;
                } else if (tempNode.right == null) {
                    tempNode.right = new Node();
                    tempNode.right.val = val;
                    return;
                } else {
                    queue.poll();
//                    if (!queue.isEmpty()) {
//                        Node tempNode2 = queue.peek();
//                        if (tempNode2.left == null) {
//                            tempNode2.left = new Node();
//                            tempNode2.left.val = val;
//                            return;
//                        } else if (tempNode2.right == null) {
//                            tempNode2.right = new Node();
//                            tempNode2.right.val = val;
//                            return;
//                        } else {
//                            queue.poll();
//                            queue.poll();
//                        }
//                        i--;
//                    }
                }
            } else if (i < level - 1) {

                if (tempNode.left != null) {
                    queue.add(tempNode.left);
                }
                if (tempNode.right != null) {
                    queue.add(tempNode.right);
                }
                i++;

            }
        }
    }

    // insert value from left to right
    public static int getMaxLevelFromInsert(Node root) {
        Node current = root;
        int maxLevel = 0;
        while (current != null) {
            current = current.left;
            maxLevel++;
        }
        return maxLevel;
    }
}
