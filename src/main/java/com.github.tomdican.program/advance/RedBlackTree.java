package com.github.tomdican.program.advance;

public class RedBlackTree {

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        tree.insert(7);
        tree.insert(3);
        tree.insert(18);
        tree.insert(10);
        tree.insert(22);
        tree.insert(8);
        tree.insert(11);
        tree.insert(26);
        tree.insert(2);
        tree.insert(6);
        tree.insert(13);

        tree.printLevelOrder();

        System.out.println("cout<<endl<<Deleting 18, 11, 3, 10, 22<<endl");

        tree.deleteByVal(18);
        tree.deleteByVal(11);
        tree.deleteByVal(3);
        tree.deleteByVal(10);
        tree.deleteByVal(22);

        tree.printLevelOrder();
    }


    RedBlackTreeNode root;
    public boolean BLACK = false;
    public boolean RED = true;
    private int size = 0;

    private void insert(int val) {
        RedBlackTreeNode newNode = new RedBlackTreeNode(val);
        if (root == null) {
            newNode.color = BLACK;
            root = newNode;
        } else {
            RedBlackTreeNode temp = search(val);
            if (temp.val == val) {
                return;
            }
            newNode.parent = temp;
            if (val < temp.val)
                temp.left = newNode;
            else
                temp.right = newNode;

            fixRedRed(newNode);
        }
    }


    private RedBlackTreeNode search(int val) {
        RedBlackTreeNode temp = root;
        while (temp != null) {
            if (val < temp.val) {
                if (temp.left == null)
                    break;
                else
                    temp = temp.left;
            } else if (val == temp.val) {
                break;
            } else {
                if (temp.right == null)
                    break;
                else
                    temp = temp.right;
            }
        }

        return temp;
    }


    private void fixRedRed(RedBlackTreeNode newNode) {
        // if newNode is root color it black and return
        if (newNode == root) {
            newNode.color = BLACK;
            return;
        }

        // initialize parent, grandparent, uncle
        RedBlackTreeNode parent = newNode.parent,
            grandparent = parent.parent,
            uncle = newNode.uncle();

        if (parent.color != BLACK) {
            if (uncle != null && uncle.color == RED) {
                // uncle red, perform recoloring and recurse
                parent.color = BLACK;
                uncle.color = BLACK;
                grandparent.color = RED;
                fixRedRed(grandparent);
            } else {
                // Else perform LR, LL, RL, RR
                if (parent.isOnLeft()) {
                    if (newNode.isOnLeft()) {
                        // for left right
                        swapColors(parent, grandparent);
                    } else {
                        leftRotate(parent);
                        swapColors(newNode, grandparent);
                    }
                    // for left left and left right
                    rightRotate(grandparent);
                } else {
                    if (newNode.isOnLeft()) {
                        // for right left
                        rightRotate(parent);
                        swapColors(newNode, grandparent);
                    } else {
                        swapColors(parent, grandparent);
                    }

                    // for right right and right left
                    leftRotate(grandparent);
                }
            }
        }

    }

    private void rightRotate(RedBlackTreeNode parent) {
        // new parent will be node's left child
        RedBlackTreeNode left = parent.left;

        // update root if current node is root
        if (parent == root)
            root = left;

        parent.moveDown(left);

        // connect x with new parent's right element
        parent.left = left.right;
        // connect new parent's right element with node
        // if it is not null
        if (left.right != null)
            left.right.parent = parent;

        // connect new parent with x
        left.right = parent;

    }

    private void leftRotate(RedBlackTreeNode parent) {
        // new parent will be node's right child
        RedBlackTreeNode right = parent.right;

        // update root if current node is root
        if (parent == root)
            root = right;

        parent.moveDown(right);

        // connect parent with new parent's left element
        parent.right = right.left;
        // connect new parent's left element with node
        // if it is not null
        if (right.left != null)
            right.left.parent = parent;

        // connect new parent with parent
        right.left = parent;
    }

    private void swapColors(RedBlackTreeNode parent, RedBlackTreeNode grandparent) {
        boolean temp;
        temp = parent.color;
        parent.color = grandparent.color;
        grandparent.color = temp;
    }

    private void deleteByVal(int val) {
        if (root == null)
            return;

        RedBlackTreeNode v = search(val);

        if (v.val != val) {
            System.out.println("cout << No node found to delete with value:<<"  + val + "<< endl");
            return;
        }

        deleteNode(v);
    }

    private void deleteNode(RedBlackTreeNode node) {
        RedBlackTreeNode replaceOne = findReplaceOne(node);

        // True when u and v are both black
        boolean uvBlack = ((replaceOne == null || replaceOne.color == BLACK) && (node.color == BLACK));
        RedBlackTreeNode parent = node.parent;

        if (replaceOne == null) {
            // u is NULL therefore v is leaf
            if (node == root) {
                // v is root, making root null
                root = null;
            } else {
                if (uvBlack) {
                    // u and v both black
                    // v is leaf, fix double black at v
                    fixDoubleBlack(node);
                } else {
                    // u or v is red
                    if (node.sibling() != null)
                        // sibling is not null, make it red"
                        node.sibling().color = RED;
                }

                // delete v from the tree
                if (node.isOnLeft()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
           // delete v;
            return;
        }

        if (node.left == null || node.right == null) {
            // v has 1 child
            if (node == root) {
                // v is root, assign the value of u to v, and delete u
                node.val = replaceOne.val;
                node.left = node.right = null;
              //  delete u;
            } else {
                // Detach v from tree and move u up
                if (node.isOnLeft()) {
                    parent.left = replaceOne;
                } else {
                    parent.right = replaceOne;
                }
               // delete v;
                replaceOne.parent = parent;
                if (uvBlack) {
                    // u and v both black, fix double black at u
                    fixDoubleBlack(replaceOne);
                } else {
                    // u or v red, color u black
                    replaceOne.color = BLACK;
                }
            }
            return;
        }

        // v has 2 children, swap values with successor and recurse
        swapValues(replaceOne, node);
        deleteNode(replaceOne);
    }

    private void fixDoubleBlack(RedBlackTreeNode node) {
        if (node == root)
            // Reached root
            return;

        RedBlackTreeNode sibling = node.sibling(), parent = node.parent;
        if (sibling == null) {
            // No sibiling, double black pushed up
            fixDoubleBlack(parent);
        } else {
            if (sibling.color == RED) {
                // Sibling red
                parent.color = RED;
                sibling.color = BLACK;
                if (sibling.isOnLeft()) {
                    // left case
                    rightRotate(parent);
                } else {
                    // right case
                    leftRotate(parent);
                }
                fixDoubleBlack(node);
            } else {
                // Sibling black
                if (sibling.hasRedChild()) {
                    // at least 1 red children
                    if (sibling.left != null && sibling.left.color == RED) {
                        if (sibling.isOnLeft()) {
                            // left left
                            sibling.left.color = sibling.color;
                            sibling.color = parent.color;
                            rightRotate(parent);
                        } else {
                            // right left
                            sibling.left.color = parent.color;
                            rightRotate(sibling);
                            leftRotate(parent);
                        }
                    } else {
                        if (sibling.isOnLeft()) {
                            // left right
                            sibling.right.color = parent.color;
                            leftRotate(sibling);
                            rightRotate(parent);
                        } else {
                            // right right
                            sibling.right.color = sibling.color;
                            sibling.color = parent.color;
                            leftRotate(parent);
                        }
                    }
                    parent.color = BLACK;
                } else {
                    // 2 black children
                    sibling.color = RED;
                    if (parent.color == BLACK)
                        fixDoubleBlack(parent);
                    else
                        parent.color = BLACK;
                }
            }
        }
    }

    private RedBlackTreeNode findReplaceOne(RedBlackTreeNode node) {
        // when node have 2 children
        if (node.left != null && node.right != null)
        return successor(node.right);

        // when leaf
        if (node.left == null && node.right == null)
        return null;

        // when single child
        if (node.left != null)
            return node.left;
        else
            return node.right;
    }

    private RedBlackTreeNode successor(RedBlackTreeNode node) {
        RedBlackTreeNode temp = node;

        while (temp.left != null)
            temp = temp.left;

        return temp;
    }

    private void swapValues(RedBlackTreeNode node1, RedBlackTreeNode node2) {
        int temp;
        temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    private void printLevelOrder() {
        root.printLevelOrderLineWithQueue();
    }

}