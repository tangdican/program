package com.github.tomdican.program.tree;

public class Node {
    public int val;
    public Node left;
    public Node right;

    public int height;

    public boolean color = true;

    public Node(int d) {
        val = d;
        height = 1;
    }

    public Node(boolean color) {
        this.color = color;
    }

    public Node() {

    }
}
