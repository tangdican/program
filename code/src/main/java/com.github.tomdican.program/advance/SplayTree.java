package com.github.tomdican.program.advance;

import static com.github.tomdican.program.tree.Tree2.preOrder;

import com.github.tomdican.program.Util;
import com.github.tomdican.program.tree.Node;

/**
 * source: https://www.geeksforgeeks.org/splay-tree-set-1-insert/
 *
 * find the recent val
 *
 */
public class SplayTree {

    public static void main(String[] args) {
        Node root = newNode(100);
        root.left = newNode(50);
        root.right = newNode(200);
        root.left.left = newNode(40);
        root.left.left.left = newNode(30);
        root.left.left.left.left = newNode(20);

        root = search(root, 20);
        Util.println("Preorder traversal of the modified Splay tree is \n");
        preOrder(root);

        Util.println("");
        root = search(root, 200);
        preOrder(root);
    }

    private static Node search(Node root, int val) {
        return splay(root, val);

    }

    private static Node splay(Node root, int val) {
        // Base cases: root is NULL or key is present at root
        if (root == null || root.val == val)
            return root;

        // Key lies in left subtree
        if (root.val > val)
        {
            // Key is not in tree, we are done
            if (root.left == null) return root;

            // Zig-Zig (Left Left)
            if (root.left.val > val)
            {
                // First recursively bring the key as root of left-left
                root.left.left = splay(root.left.left, val);

                // Do first rotation for root, second rotation is done after else
                root = rightRotate(root);
            }
            else if (root.left.val < val) // Zig-Zag (Left Right)
            {
                // First recursively bring the val as root of left-right
                root.left.right = splay(root.left.right, val);

                // Do first rotation for root.left
                if (root.left.right != null)
                    root.left = leftRotate(root.left);
            }

            // Do second rotation for root
            return (root.left == null)? root: rightRotate(root);
        }
        else // Key lies in right subtree
        {
            // Key is not in tree, we are done
            if (root.right == null) return root;

            // Zag-Zig (Right Left)
            if (root.right.val > val)
            {
                // Bring the val as root of right-left
                root.right.left = splay(root.right.left, val);

                // Do first rotation for root.right
                if (root.right.left != null)
                    root.right = rightRotate(root.right);
            }
            else if (root.right.val < val)// Zag-Zag (Right Right)
            {
                // Bring the val as root of right-right and do first rotation
                root.right.right = splay(root.right.right, val);
                root = leftRotate(root);
            }

            // Do second rotation for root
            return (root.right == null)? root: leftRotate(root);
        }
    }

    private static Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    private static Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    private static Node newNode(int val) {
        return new Node(val);
    }

}
