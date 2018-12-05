package com.github.tomdican.program.hash;

public class Hash {

    public static void main(String[] args) {

//        int[] keys = {19,27,36,10,64};
//        createHashTable(keys);

        int arr1[] = {11, 1, 13, 21, 3, 7};
        int arr2[] = {11, 3, 7, 1};
        int arr3[] = {11, 3, 7, 2};
        System.out.println(isSubSet(arr1, arr2));
        System.out.println(isSubSet(arr1, arr3));

    }

    /**
     * is sub set
     *
     * input:
     * int arr1[] = {11, 1, 13, 21, 3, 7};
     int arr2[] = {11, 3, 7, 1};
     int arr3[] = {11, 3, 7, 2};

     output: true  false

     source: https://www.geeksforgeeks.org/find-whether-an-array-is-subset-of-another-array-set-1/
     *
     * @param parent
     * @param sub
     * @return
     */
    private static boolean isSubSet(int[] parent, int[] sub) {
        if (parent.length < sub.length) {
            return false;
        }

        Map map = new Map(2 * parent.length);
        for (int i = 0; i < parent.length; i++) {
            map.insert(parent[i], parent[i]);
        }

        boolean isSub = true;
        for (int i = 0; i < sub.length; i++) {
            if (!map.contain(sub[i])){
                isSub = false;
            }
        }
        return isSub;
    }


    /**
     * double hashing
     *
     * input: 19,27,36,10,64
     *
     * output:
     *
     *
     * g
     *  value[1]: 27,
        value[5]: 10,
        value[6]: 19,
        value[10]: 36,
        value[12]: 64,

     * source: https://www.geeksforgeeks.org/double-hashing/

     *
     * @param keys
     */
    private static void createHashTable(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            DoubleHash.insert(keys[i]);
        }
        DoubleHash.printHash();
    }

    private static class DoubleHash {
        private final static int maxLen = 13;
        private static int[] value = new int[maxLen];
        public static void insert(int val) {
            int index = hashCode(val);
            value[index] = val;
        }

        private static int hashCode(int val) {
            int index = hashCode1(val);
            int index2 = hashCode2(val);
            for (int i = 0; i < maxLen; i++) {
                int newIndex = (index + i * index2) % maxLen;
                if ( value[newIndex] == 0) {
                    return newIndex;
                }
            }
            return -1;
        }

        private static int hashCode1(int val) {
            return val % 13;
        }

        private static int hashCode2(int val) {
            return 7 - val % 7;
        }

        public static void printHash() {
            for (int i = 0; i < maxLen; i++) {
                if (value[i] != 0) {
                    System.out.println("value[" + i + "]: "+value[i] + ",");
                }
            }
        }
    }
}
