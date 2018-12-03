package com.github.tomdican.program.hash;

public class Hash {

    public static void main(String[] args) {

        int[] keys = {19,27,36,10,64};
        createHashTable(keys);

    }


    /**
     * double hashing
     *
     *
     * input: 19,27,36,10,64
     *
     * output:
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
