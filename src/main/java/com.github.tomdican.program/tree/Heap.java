package com.github.tomdican.program.tree;

import com.github.tomdican.program.Util;

/**
 *
 * 1) It’s a complete tree (All levels are completely filled except possibly the last level and the last level has all keys as left as possible). This property of Binary Heap makes them suitable to be stored in an array.

 2) A Binary Heap is either Min Heap or Max Heap. In a Min Binary Heap, the key at root must be minimum among all keys present in Binary Heap. The same property must be recursively true for all nodes in Binary Tree. Max Binary Heap is similar to MinHeap.
 *
 *
 Arr[(i-1)/2] 	Returns the parent node
 Arr[(2*i)+1] 	Returns the left child node
 Arr[(2*i)+2] 	Returns the right child node
 *
 */
public class Heap {
    static int[] root = null;
    static int len = 0;

    public static void main(String[] args) {
        int arr[] = {2,3,5,1,4,8,10};
        root = new int[arr.length];
        createMinHeap(arr);
        Util.printArray(root);
    }

    private static void createMinHeap(int[] arr) {
        Node result = null;
        for (int i = 0; i < arr.length; i++) {
             insertMinHeap(arr[i]);
        }
    }

    /**
     * int i = 2p + 1    (i - 1 )/ 2  = p = i/2 - 1/2
     * int i = 2p + 2    (i - 2 )/ 2 = p = i/2 - 2/2
     *
     * @param val
     */
    private static void insertMinHeap(int val) {
        root[len++] = val;
        int childLoc = len-1;
        int parentLoc = (childLoc - (2 - (childLoc - (childLoc/2)*2)))/2;

        while (parentLoc >= 0 && root[childLoc] < root[parentLoc]) {
            Util.exchange(root,childLoc,parentLoc);
            childLoc = parentLoc;
            parentLoc = (childLoc - (2 - (childLoc - (childLoc/2)*2)))/2;
        }
    }
}
