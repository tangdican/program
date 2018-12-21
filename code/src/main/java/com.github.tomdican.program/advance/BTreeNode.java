package com.github.tomdican.program.advance;

import java.util.LinkedList;
import java.util.Queue;

/**
 * source: https://www.geeksforgeeks.org/b-tree-set-1-introduction-2/
 */
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

    /**
     * source: https://www.geeksforgeeks.org/b-tree-set-3delete/
     * @param val
     */
    void remove(int val) {

            int idx = findKey(val);

            // The key to be removed is present in this node
            if (idx < curLen && values[idx] == val)
            {

                // If the node is a leaf node - removeFromLeaf is called
                // Otherwise, removeFromNonLeaf function is called
                if (leaf)
                    removeFromLeaf(idx);
                else
                    removeFromNonLeaf(idx);
            }
            else
            {

                // If this node is a leaf node, then the key is not present in tree
                if (leaf)
                {
                    System.out.println("cout << The key << " + val  + "<< is does not exist in the tree");
                    return;
                }

                // The key to be removed is present in the sub-tree rooted with this node
                // The flag indicates whether the key is present in the sub-tree rooted
                // with the last child of this node
                boolean flag = ( (idx==curLen)? true : false );

                // If the child where the key is supposed to exist has less that t keys,
                // we fill that child
                if (children[idx].curLen < range)
                fill(idx);

                // If the last child has been merged, it must have merged with the previous
                // child and so we recurse on the (idx-1)th child. Else, we recurse on the
                // (idx)th child which now has atleast t keys
                if (flag && idx > curLen) {
                    children[idx - 1].remove(val);
                }
                else {
                    children[idx].remove(val);
                }
            }
        }

    private void fill(int idx) {
        // If the previous child(C[idx-1]) has more than t-1 keys, borrow a key
        // from that child
        if (idx!=0 && children[idx-1].curLen>=range) {
            borrowFromPrev(idx);
        }

        // If the next child(C[idx+1]) has more than t-1 keys, borrow a key
        // from that child
        else if (idx!=curLen && children[idx+1].curLen>=range) {
            borrowFromNext(idx);
        }

        // Merge C[idx] with its sibling
        // If C[idx] is the last child, merge it with with its previous sibling
        // Otherwise merge it with its next sibling
        else {
            if (idx != curLen)
                merge(idx);
            else
                merge(idx-1);
        }
    }

    private void borrowFromNext(int idx) {
        BTreeNode child=children[idx];
        BTreeNode sibling=children[idx+1];

        // keys[idx] is inserted as the last key in C[idx]
        child.values[(child.curLen)] = values[idx];

        // Sibling's first child is inserted as the last child
        // into C[idx]
        if (!(child.leaf))
            child.children[(child.curLen)+1] = sibling.children[0];

        //The first key from sibling is inserted into keys[idx]
        values[idx] = sibling.values[0];

        // Moving all keys in sibling one step behind
        for (int i=1; i<sibling.curLen; ++i)
            sibling.values[i-1] = sibling.values[i];

        // Moving the child pointers one step behind
        if (!sibling.leaf)
        {
            for(int i=1; i<=sibling.curLen; ++i)
                sibling.children[i-1] = sibling.children[i];
        }

        // Increasing and decreasing the key count of C[idx] and C[idx+1]
        // respectively
        child.curLen += 1;
        sibling.curLen -= 1;
    }

    private void borrowFromPrev(int idx) {
        BTreeNode child=children[idx];
        BTreeNode sibling=children[idx-1];

        // The last key from C[idx-1] goes up to the parent and key[idx-1]
        // from parent is inserted as the first key in C[idx]. Thus, the  loses
        // sibling one key and child gains one key

        // Moving all key in C[idx] one step ahead
        for (int i=child.curLen-1; i>=0; --i)
            child.values[i+1] = child.values[i];

        // If C[idx] is not a leaf, move all its child pointers one step ahead
        if (!child.leaf)
        {
            for(int i=child.curLen; i>=0; --i)
                child.children[i+1] = child.children[i];
        }

        // Setting child's first key equal to keys[idx-1] from the current node
        child.values[0] = values[idx-1];

        // Moving sibling's last child as C[idx]'s first child
        if(!child.leaf)
            child.children[0] = sibling.children[sibling.curLen];

        // Moving the key from the sibling to the parent
        // This reduces the number of keys in the sibling
        values[idx-1] = sibling.values[sibling.curLen-1];

        child.curLen += 1;
        sibling.curLen -= 1;
    }

    private void removeFromNonLeaf(int idx) {
        int val = values[idx];

        // If the child that precedes k (C[idx]) has atleast t values,
        // find the predecessor 'pred' of k in the subtree rooted at
        // children[idx]. Replace k by pred. Recursively delete pred
        // in children[idx]
        if (children[idx].curLen >= range)
        {
            int pred = getPred(idx);
            values[idx] = pred;
            children[idx].remove(pred);
        }

        // If the child children[idx] has less that t values, examine children[idx+1].
        // If children[idx+1] has atleast t values, find the successor 'succ' of k in
        // the subtree rooted at children[idx+1]
        // Replace k by succ
        // Recursively delete succ in children[idx+1]
    else if  (children[idx+1].curLen >= range)
        {
            int succ = getSucc(idx);
            values[idx] = succ;
            children[idx+1].remove(succ);
        }

        // If both children[idx] and children[idx+1] has less that t values,merge k and all of children[idx+1]
        // into children[idx]
        // Now children[idx] contains 2t-1 values
        // Free children[idx+1] and recursively delete k from children[idx]
    else
        {
            merge(idx);
            children[idx].remove(val);
        }
    }

    private void merge(int idx) {
        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx+1];

        // Pulling a key from the current node and inserting it into (t-1)th
        // position of children[idx]
        child.values[child.curLen] = values[idx];

        // Copying the values from children[idx+1] to children[idx] at the end
        for (int i=0; i<sibling.curLen; ++i)
            child.values[i+child.curLen+1] = sibling.values[i];

        // Copying the child pointers from children[idx+1] to children[idx]
        if (!child.leaf)
        {
            for(int i=0; i<=sibling.curLen; ++i)
                child.children[i+child.curLen+1] = sibling.children[i];
        }

        // Moving all values after idx in the current node one step before -
        // to fill the gap created by moving values[idx] to children[idx]
        for (int i=idx+1; i<curLen; ++i)
            values[i-1] = values[i];

        // Moving the child pointers after (idx+1) in the current node one
        // step before
        for (int i=idx+2; i<=curLen; ++i)
            children[i-1] = children[i];

        // Updating the key count of child and the current node
        child.curLen += sibling.curLen+1;
        curLen--;
    }

    private int getSucc(int idx) {
        // Keep moving the left most node starting from children[idx+1] until we reach a leaf
        BTreeNode cur = children[idx+1];
        while (!cur.leaf)
            cur = cur.children[0];

        // Return the first key of the leaf
        return cur.values[0];
    }

    private int getPred(int idx) {
        // Keep moving to the right most node until we reach a leaf
        BTreeNode cur =children[idx];
        while (!cur.leaf)
            cur = cur.children[cur.curLen];

        // Return the last key of the leaf
        return cur.values[cur.curLen-1];
    }

    private void removeFromLeaf(int idx) {
        // Move all the values after the idx-th pos one place backward
        for (int i=idx+1; i<curLen; ++i)
            values[i-1] = values[i];
        // Reduce the count of values
        curLen--;

    }

    private int findKey(int val) {
        int idx=0;
        while (idx<curLen && values[idx] < val)
            ++idx;
        return idx;
    }


}
