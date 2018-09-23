package com.github.tomdican.program.tree;

import com.github.tomdican.program.Util;
import java.util.Arrays;

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
//        int arr[] = {2,3,5,1,4,8,10};
//        root = new int[arr.length];
//        createMinHeap(arr);
//        System.out.println("create min heap:");
//        Util.printArray(root);

 //       int min = getMin();

//        decreaseKey(1, len-1);
//        System.out.println("extract min heap:");
//        Util.printArray(root, 0, len-1);

//        extractMin();
//        System.out.println("extract min heap:");
//        Util.printArray(root, 0, len-1);


//        delete(4);
//        Util.printArray(root, 0, len-1);

        /**********************************************************/

        int arr[] = {12,3,5,1,14,8,10,13,2,4,6,9,7};
        root = Arrays.copyOf(arr,arr.length);
        len = arr.length;
        heapSort3(root);
        Util.printArray(root);

    }

    /**
     * input: 12,3,5,1,14,8,10,13,2,4,6,9,7
     *
     * output: 14,13,12,10,9,8,7,6,5,4,3,2,1,
     *
     *
     * @param arr
     */
    private static void heapSort3(int[] arr) {
        int lenl = len;
        buildAllMinHeap();

        for (int i = lenl-1; i >= 0; i--) {
            int minVal = extractMin();
            root[i] = minVal;
            minHeap(0);
        }
    }

    private static void buildAllMinHeap() {
        for (int i = len - 1; i >= 0 ; i--) {
            toTopMin(i);
        }
    }

    private static void toTopMin(int par) {
        int left = 2*par + 1;
        int right = 2*par + 2;

        while (left < len || right < len) {
            int minLoc = par;

            if (left < len && root[left] < root[minLoc]) {
                minLoc = left;
            }

            if (right < len && root[right] < root[minLoc]) {
                minLoc = right;
            }

            if (minLoc != par) {
                Util.exchange(root,minLoc, par);
                par = minLoc;
                left = 2*par + 1;
                right = 2*par + 2;
            } else {
                break;
            }
        }
    }

    /*****************************************************************/
    /**
     *
     * input:   2,3,5,1,4,8,10
     *
     * output:  1,2,5,3,4,8,10
     *
     * @param arr
     */
    private static void createMinHeap(int[] arr) {
        Node result = null;
        for (int i = 0; i < arr.length; i++) {
            insertMinHeap(arr[i]);
        }
    }


    /**
     * input :   1,2,5,3,4,8,10,
     *
     * output:   1,2,5,3,10,8,
     *
     * @param loc
     */
    private static void delete(int loc) {
        if (loc >= len) {
            return;
        }

        decreaseKey(-1, loc);
        extractMin();
    }


    /**
     * Decreases value of key at index 'loc' to new_val.
     *
     * input:    1,2,5,3,4,8,10,
     *
     * output:    1,2,1,3,4,8,5,
     *
     * @param newVal
     * @param loc
     */
    private static void decreaseKey(int newVal, int loc) {
        if (root[loc] > newVal) {
            root[loc] = newVal;
            int parentLoc = getParent(loc);
            int childLoc = loc;
            while (parentLoc >= 0) {
                if (root[childLoc] < root[parentLoc]) {
                    Util.exchange(root, childLoc, parentLoc);
                    childLoc = parentLoc;
                    parentLoc = getParent(childLoc);
                } else {
                    break;
                }
            }
        }
    }

    /**
     *
     *  Method to remove minimum element (or root) from min heap
     *
     * intput:   1,2,5,3,4,8,10,
     *
     * output:   2,3,5,10,4,8,
     *
     */
    private static int extractMin() {
        if (len < 1 ) {
            return -1;
        }
        int min = root[0];
        root[0] = root[--len];

        minHeap();
        return min;
    }

    /**
     *  move the val of the loc 0  to the right loc
     *
     *
     *  input:   10,2,5,3,4,8,
     *
     *  output:  2,3,5,10,4,8,
     *
     */
    private static void minHeap() {
       minHeap(0);
    }

    private static void minHeap(int i) {

        int parentLoc = i;
        int leftLoc = 2*parentLoc + 1;
        int rightLoc = 2*parentLoc + 2;
        while (leftLoc < len || rightLoc < len) {

            int minLoc = leftLoc;
            if (rightLoc < len && leftLoc < len) {
                if (root[rightLoc] < root[leftLoc] ) {
                    minLoc = rightLoc;
                }
            } else if ( rightLoc < len ) {
                minLoc = rightLoc;
            }

            if (root[minLoc] < root[parentLoc]) {
                Util.exchange(root, minLoc, parentLoc);
            }

            parentLoc = minLoc;
            leftLoc = 2*parentLoc + 1;
            rightLoc = 2*parentLoc + 2;
        }
    }

    /**
     * output: 1
     *
     * @return
     */
    private static int getMin() {

        return root[0];
    }

    /**
     * insert the value into the min heap

     * @param val
     */
    private static void insertMinHeap(int val) {
        root[len++] = val;
        int childLoc = len-1;
        int parentLoc = getParent(childLoc);

        while (parentLoc >= 0 && root[childLoc] < root[parentLoc]) {
            Util.exchange(root,childLoc,parentLoc);
            childLoc = parentLoc;
            parentLoc = getParent(childLoc);
        }
    }

    /**
     * get parant location of child location
     *
     * int i = 2p + 1    (i - 1 )/ 2  = p = i/2 - 1/2
     * int i = 2p + 2    (i - 2 )/ 2 = p = i/2 - 2/2
     *
     *
     * @param childLoc
     * @return
     */
    private static int getParent(int childLoc) {
        return (childLoc - (2 - (childLoc - (childLoc/2)*2)))/2;
    }


}
