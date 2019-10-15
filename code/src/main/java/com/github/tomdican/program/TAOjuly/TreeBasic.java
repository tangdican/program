package com.github.tomdican.program.TAOjuly;

import com.github.tomdican.program.tree.Node;

public class TreeBasic {
    public static void main(String[] args) {

    }

    //copyright@allantop 2014-1-22-20:01
    Node getLCA(Node root, Node node1, Node node2) {
        if (root == null)
            return null;
        if (root == node1 || root == node2)
            return root;

        Node left = getLCA(root.left, node1, node2);
        Node right = getLCA(root.right, node1, node2);

        if (left != null && right != null)
            return root;
        else if (left != null)
            return left;
        else if (right != null)
            return right;
        else
            return null;
    }

}
