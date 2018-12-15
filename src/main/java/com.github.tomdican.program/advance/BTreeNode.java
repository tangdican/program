package com.github.tomdican.program.advance;

import java.util.LinkedList;
import java.util.Queue;

public class BTreeNode {

    int[] values;  // An array of values
    int range;      // Minimum degree (defines the range for number of values)
    BTreeNode[] children; // An array of child pointers
    int curLen;     // Current number of values
    boolean leaf; // Is true when node is leaf. Otherwise false

    public BTreeNode(int range, boolean leaf) {
        this.range = range;
        this.leaf = leaf;

        // Allocate memory for maximum number of possible values
        // and child pointers
        values = new int[2 * range - 1];
        children = new BTreeNode[2 * range];

        // Initialize the number of values as 0
        curLen = 0;
    }

    void traverse() {
        // There are n values and n+1 children, travers through n values
        // and first n children
        int i;
        for (i = 0; i < curLen; i++) {
            // If this is not leaf, then before printing key[i],
            // traverse the subtree rooted with child C[i].
            if (leaf == false)
                children[i].traverse();
            System.out.println("<< << " + values[i]);
        }

        // Print the subtree rooted with last child
        if (leaf == false)
            children[i].traverse();
    }

    void traverseLine() {
        Queue<BTreeNode> queue = new LinkedList<>();
        queue.add(this);
        queue.add(null);
        while (queue.size()>=2) {
            BTreeNode temp = queue.poll();
            if (temp == null) {
                System.out.println("");
                queue.add(null);
            } else {
                for (int i = 0; i < temp.curLen; i++) {
                    System.out.print(temp.values[i] + ",");
                    if (!temp.leaf) {
                        queue.add(temp.children[i]);
                    }
                }
                if (!temp.leaf) {
                    queue.add(temp.children[temp.curLen]);
                }
                System.out.print("      ");
            }
        }
        System.out.println("");
    }

    BTreeNode search(int val) {
        // Find the first key greater than or equal to k
        int i = 0;
        while (i < curLen && val > values[i])
            i++;

        // If the found key is equal to k, return this node
        if (values[i] == val)
            return this;

        // If the key is not found here and this is a leaf node
        if (leaf == true)
            return null;

        // Go to the appropriate child
        return children[i].search(val);
    }

    public void splitChild(int i, BTreeNode root) {

        // Create a new node which is going to store (t-1) keys
        // of root
        BTreeNode z = new BTreeNode(root.range, root.leaf);
        z.curLen = range - 1;

        // Copy the last (t-1) keys of y to z
        for (int j = 0; j < range-1; j++)
            z.values[j] = root.values[j+range];

        // Copy the last t children of y to z
        if (root.leaf == false)
        {
            for (int j = 0; j < range; j++)
                z.children[j] = root.children[j+range];
        }

        // Reduce the number of keys in y
        root.curLen = range - 1;

        // Since this node is going to have a new child,
        // create space of new child
        for (int j = curLen; j >= i+1; j--)
            children[j+1] = children[j];

        // Link the new child to this node
        children[i+1] = z;

        // A key of y will move to this node. Find location of
        // new key and move all greater keys one space ahead
        for (int j = curLen-1; j >= i; j--)
            values[j+1] = values[j];

        // Copy the middle key of y to this node
        values[i] = root.values[range-1];

        // Increment count of keys in this node
        curLen = curLen + 1;
    }

    public void insertNonFull(int val) {
        // Initialize index as index of rightmost element
        int i = curLen-1;

        // If this is a leaf node
        if (leaf == true)
        {
            // The following loop does two things
            // a) Finds the location of new key to be inserted
            // b) Moves all greater keys to one place ahead
            while (i >= 0 && values[i] > val)
            {
                values[i+1] = values[i];
                i--;
            }

            // Insert the new key at found location
            values[i+1] = val;
            curLen = curLen+1;
        }
        else // If this node is not leaf
        {
            // Find the child which is going to have the new key
            while (i >= 0 && values[i] > val)
                i--;

            // See if the found child is full
            if (children[i+1].curLen == 2*range-1)
            {
                // If the child is full, then split it
                splitChild(i+1, children[i+1]);

                // After split, the middle key of C[i] goes up and
                // C[i] is splitted into two.  See which of the two
                // is going to have the new key
                if (values[i+1] < val)
                    i++;
            }
            children[i+1].insertNonFull(val);
        }
    }
}
