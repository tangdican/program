package com.github.tomdican.program.advance;

import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTreeNode {
    public int val;
    public RedBlackTreeNode left;
    public RedBlackTreeNode right;

    public boolean color;
    public boolean BLACK = false;
    public boolean RED = true;
    public RedBlackTreeNode parent;

    public RedBlackTreeNode(int val) {
        this.val = val;
        color = RED;
    }
    public RedBlackTreeNode() {

    }

    public RedBlackTreeNode uncle() {
        // If no parent or grandparent, then no uncle
        if (parent == null || parent.parent == null)
        return null;

        if (parent.isOnLeft())
            // uncle on right
            return parent.parent.right;
        else
            // uncle on left
            return parent.parent.left;
    }

    public boolean isOnLeft() {
        return this == parent.left;
    }

    public void moveDown(RedBlackTreeNode child) {
        if (parent != null) {
            if (isOnLeft()) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
        child.parent = parent;
       // parent = child;
    }

    public void printLevelOrderLineWithQueue() {
        Queue<RedBlackTreeNode>  queue = new LinkedList<>();
        RedBlackTreeNode node = this;
        if (node == null) {
            return;
        }
        RedBlackTreeNode line = new RedBlackTreeNode();
        line.val = -1;// mark to print next line;
        queue.clear();
        queue.add(node);
        queue.add(line);
        RedBlackTreeNode temp = null;
        while (queue.size()>=2) {
            temp = queue.poll();
            if (temp.val == -1){
                System.out.println("");
                queue.add(line);
            } else {
                System.out.print("," + temp.val + "(" + (temp.color==RED ? "red" : "black") +")");
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

    public RedBlackTreeNode sibling() {
        // sibling null if no parent
        if (parent == null)
            return null;

        if (isOnLeft())
            return parent.right;

        return parent.left;
    }

    public boolean hasRedChild() {

        return (left != null && left.color == RED) ||
            (right != null && right.color == RED);

    }
}
