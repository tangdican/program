package com.github.tomdican.program.advance;

public class BTree {

    public static void main(String[] args) {

        BTree t = new BTree(3); // A B-Tree with minium degree 3

        for (int i = 10; i <= 10*53; i+=10) {
            t.insert(i);
        }
       // t.insert(280);

        System.out.println( "Traversal of the constucted tree is ");
        t.traverseLine();

        int k = 60;
        System.out.println((t.search(k) != null) ? "cout << Present" : "cout << Not Present");

        k = 15;
        System.out.println((t.search(k) != null)? "cout << Present" : "cout << Not Present");

    }

    private BTreeNode search(int k) {
        if (root == null)
            return null;
        return root.search(k);
    }

    private void traverse() {
        if (root == null)
            return;
        root.traverse();
    }

    private void traverseLine() {
        if (root == null)
            return;
        root.traverseLine();
    }

    public BTree(int range) {
        this.range = range;
    }

    BTreeNode root;
    int range;

    // The main function that inserts a new key in this B-Tree
    void insert(int val)
    {
        // If tree is empty
        if (root == null)
        {
            // Allocate memory for root
            root = new BTreeNode(range, true);
            root.values[0] = val;  // Insert key
            root.curLen = 1;  // Update number of values in root
        }
        else // If tree is not empty
        {
            // If root is full, then tree grows in height
            if (root.curLen == 2*range-1)
            {
                // Allocate memory for new root
                BTreeNode temp = new BTreeNode(range, false);

                // Make old root as child of new root
                temp.children[0] = root;

                // Split the old root and move 1 key to the new root
                temp.splitChild(0, root);

                // New root has two children now.  Decide which of the
                // two children is going to have new key
                int i = 0;
                if (temp.values[0] < val)
                    i++;
                temp.children[i].insertNonFull(val);

                // Change root
                root = temp;
            }
            else  // If root is not full, call insertNonFull for root
                root.insertNonFull(val);
        }
    }

}
