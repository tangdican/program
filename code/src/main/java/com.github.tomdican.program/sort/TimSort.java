package com.github.tomdican.program.sort;

import com.github.tomdican.program.Util;

/**
 * source: java.util.TimSort
 *
 * 思路：
 * 1，将数组划分为小于32长度的区间，
 * 2，将某一区间先进行插入排序，
 * 3，当相伶两个排序后的区间进行归并，以此递归所有；
 *
 * 4，归并思路（源码把这个归并过程写的太复杂，作者想法有问题，源码应该保持简单的原则上，源码这么复杂说明没完全掌握其本质。
 * 思路其实非常简单，就是将两个区间的元素比较后合并放入一个区间）：
 *      所有区间存在stack中，归并一个就stack长度减一，直到长度小于2；
 *      归并两个区间时，当一次某一区间连续放入元素大于{# minGallop}个后，
 *      就改为copyarray放元素，当某一区间放入元素小于{# minGallop}个后，
 *      就改为while循环一个一个的放入元素；
 *
 *
 * 注意：有序元素（升或降）进行忽略的方法没有从源码中添加过来（countRunAndMakeAscending）
 *
 * 这种代码不想看第二次
 *
 */
public class TimSort {

    private static final int MIN_MERGE = 32;
    private static final int MIN_GALLOP = 7;
    private static int stackSize = 0;
    private static int[] runLen;
    private int[] runBase;
    private int minGallop = 7;

    public static void main(String[] args) {

        TimSort t = new TimSort();

        // binary sort
//        int[] a = {2,4,6,1,3,5};
//        t.binarySort(a,0,a.length,0);
//        Util.printArray(a);


        // tim sort
        int[] a1 = new int[65];
        for (int i = 0; i < a1.length; i+=3) {
            a1[i] = a1.length - i-1;
            a1[i+1] = a1.length - i-2;

            if (i+2 >= a1.length) {
                break;
            }
            a1[i+2] = a1.length - i;
        }
        t.init(a1.length);
        t.timSort(a1, 0, a1.length, 0);
        Util.printArray(a1);
    }

    private void init(int len) {
        int stackLen = (len <    120  ?  5 :
            len <   1542  ? 10 :
                len < 119151  ? 24 : 49);
        runBase = new int[stackLen];
        runLen = new int[stackLen];
    }

    private void timSort(int[] a, int lo, int hi, int start) {

        int nRemaining  = hi - lo;
        if (nRemaining < 2)
            return;  // Arrays of size 0 and 1 are always sorted

        // If array is small, do a "mini-TimSort" with no merges
        if (nRemaining < MIN_MERGE) {
            binarySort(a, lo, hi, lo);
            return;
        }

        int minRun = minRunLength(nRemaining);
        int runLen = minRun;
        do {
            // If run is short, extend to min(minRun, nRemaining)
            runLen = (nRemaining < minRun ? nRemaining : minRun);
            binarySort(a, lo, lo + runLen, lo);

            // Push run onto pending-run stack, and maybe merge
            pushRun(lo, runLen);
            mergeCollapse(a);

            // Advance to find next run
            lo += runLen;
            nRemaining -= runLen;
        } while (nRemaining > 0);

        // Merge all remaining runs to complete sort
        mergeForceCollapse(a);
    }

    private void mergeForceCollapse(int[] a) {
        while (stackSize > 1) {
            int n = stackSize - 2;
            if (n > 0 && runLen[n - 1] < runLen[n + 1])
                n--;
            mergeAt(a, n);
        }
    }

    private void mergeCollapse(int[] a) {
        while (stackSize > 1) {
            int n = stackSize - 2;
            // 合并2，3后，变为0，1，2，再合并0，1变为0，1；再合并0，1。
            if (n > 0 && runLen[n-1] <= runLen[n] + runLen[n+1]) {
                if (runLen[n - 1] < runLen[n + 1])
                    n--;
                mergeAt(a, n);
            } else if (runLen[n] <= runLen[n + 1]) {
                mergeAt(a, n);
            } else {
                break; // Invariant is established
            }
        }
    }

    private void mergeAt(int[] a, int i) {

        int base1 = runBase[i];
        int len1 = runLen[i];
        int base2 = runBase[i + 1];
        int len2 = runLen[i + 1];

        runLen[i] = len1 + len2;
        if (i == stackSize - 3) {
            runBase[i + 1] = runBase[i + 2];
            runLen[i + 1] = runLen[i + 2];
        }
        stackSize--;

        /*
         * a[base2]在a中【base1，base1+len1】范围中找到能插入的位置；
         *
         * 方法写的太复杂
         */
        int k = gallopRight(a[base2], a, base1, len1, 0);
        base1 += k;
        len1 -= k;
        if (len1 == 0)
            return;

        /*
         * a[base1 + len1 - 1] 在a中指定范围找到插入的位置；
         */
        len2 = gallopLeft(a[base1 + len1 - 1], a, base2, len2, len2 - 1);
        if (len2 == 0)
            return;

        // Merge remaining runs, using tmp array with min(len1, len2) elements
        if (len1 <= len2)
            mergeLo(a, base1, len1, base2, len2);
        else
            mergeHi(a, base1, len1, base2, len2);
    }

    private void mergeHi(int[] a, int base1, int len1, int base2, int len2) {
        // Copy second run into temp array
        int[] tmp = new int[a.length];
        int tmpBase = base1;
        arraycopy(a, base2, tmp, tmpBase, len2);

        int cursor1 = base1 + len1 - 1;  // Indexes into a
        int cursor2 = tmpBase + len2 - 1; // Indexes into tmp array
        int dest = base2 + len2 - 1;     // Indexes into a

        // Move last element of first run and deal with degenerate cases
        a[dest--] = a[cursor1--];
        if (--len1 == 0) {
            arraycopy(tmp, tmpBase, a, dest - (len2 - 1), len2);
            return;
        }
        if (len2 == 1) {
            dest -= len1;
            cursor1 -= len1;
            arraycopy(a, cursor1 + 1, a, dest + 1, len1);
            a[dest] = tmp[cursor2];
            return;
        }

        int minGallop = this.minGallop;    //  "    "       "     "      "
        outer:
        while (true) {
            int count1 = 0; // Number of times in a row that first run won
            int count2 = 0; // Number of times in a row that second run won

            /*
             * Do the straightforward thing until (if ever) one run
             * appears to win consistently.
             */
            do {
                if (tmp[cursor2] < a[cursor1]) {
                    a[dest--] = a[cursor1--];
                    count1++;
                    count2 = 0;
                    if (--len1 == 0)
                        break outer;
                } else {
                    a[dest--] = tmp[cursor2--];
                    count2++;
                    count1 = 0;
                    if (--len2 == 1)
                        break outer;
                }
            } while ((count1 | count2) < minGallop);

            /*
             * One run is winning so consistently that galloping may be a
             * huge win. So try that, and continue galloping until (if ever)
             * neither run appears to be winning consistently anymore.
             */
            do {
                count1 = len1 - gallopRight(tmp[cursor2], a, base1, len1, len1 - 1);
                if (count1 != 0) {
                    dest -= count1;
                    cursor1 -= count1;
                    len1 -= count1;
                    arraycopy(a, cursor1 + 1, a, dest + 1, count1);
                    if (len1 == 0)
                        break outer;
                }
                a[dest--] = tmp[cursor2--];
                if (--len2 == 1)
                    break outer;

                count2 = len2 - gallopLeft(a[cursor1], tmp, tmpBase, len2, len2 - 1);
                if (count2 != 0) {
                    dest -= count2;
                    cursor2 -= count2;
                    len2 -= count2;
                    arraycopy(tmp, cursor2 + 1, a, dest + 1, count2);
                    if (len2 <= 1)  // len2 == 1 || len2 == 0
                        break outer;
                }
                a[dest--] = a[cursor1--];
                if (--len1 == 0)
                    break outer;
                minGallop--;
            } while (count1 >= MIN_GALLOP | count2 >= MIN_GALLOP);
            if (minGallop < 0)
                minGallop = 0;
            minGallop += 2;  // Penalize for leaving gallop mode
        }  // End of "outer" loop
        this.minGallop = minGallop < 1 ? 1 : minGallop;  // Write back to field

        if (len2 == 1) {
            dest -= len1;
            cursor1 -= len1;
            System.arraycopy(a, cursor1 + 1, a, dest + 1, len1);
            a[dest] = tmp[cursor2];  // Move first elt of run2 to front of merge
        } else if (len2 == 0) {
            throw new IllegalArgumentException(
                "Comparison method violates its general contract!");
        } else {
            arraycopy(tmp, tmpBase, a, dest - (len2 - 1), len2);
        }
    }

    private void mergeLo(int[] a, int base1, int len1, int base2, int len2) {
        // Copy first run into temp array
        int[] tmp = new int[a.length];
        //int cursor1 = tmpBase; // Indexes into tmp array
        int cursor1 = base1; // Indexes into tmp array
        int cursor2 = base2;   // Indexes int a
        int dest = base1;      // Indexes int a
        arraycopy(a, base1, tmp, cursor1, len1);

        // Move first element of second run and deal with degenerate cases
        a[dest++] = a[cursor2++];
        if (--len2 == 0) {
            arraycopy(tmp, cursor1, a, dest, len1);
            return;
        }
        if (len1 == 1) {
            arraycopy(a, cursor2, a, dest, len2);
            a[dest + len2] = tmp[cursor1]; // Last elt of run 1 to end of merge
            return;
        }

        int minGallop = this.minGallop;    //  "    "       "     "      "
        outer:
        while (true) {
            int count1 = 0; // Number of times in a row that first run won
            int count2 = 0; // Number of times in a row that second run won

            /*
             * 根据索引dest，将a[cursor2]和tmp[cursor1]最小元素放入a中；
             * 适合a中前半部分元素和后半部分元素比较时一会大一会小的情况；
             */
            do {
                if (a[cursor2] < tmp[cursor1]) {
                    a[dest++] = a[cursor2++];
                    count2++;
                    count1 = 0;
                    if (--len2 == 0)
                        break outer;
                } else {
                    a[dest++] = tmp[cursor1++];
                    count1++;
                    count2 = 0;
                    if (--len1 == 1)
                        break outer;
                }
            } while ((count1 | count2) < minGallop);

            /*
             * 根据索引dest，将a[cursor2]和tmp[cursor1]最小元素放入a中；
             * 适合a中前半部分和后半部分元素比较，一直大或小的情况
             */
            do {
                count1 = gallopRight(a[cursor2], tmp, cursor1, len1, 0);
                if (count1 != 0) {
                    arraycopy(tmp, cursor1, a, dest, count1);
                    dest += count1;
                    cursor1 += count1;
                    len1 -= count1;
                    if (len1 <= 1) // len1 == 1 || len1 == 0
                        break outer;
                }
                a[dest++] = a[cursor2++];
                if (--len2 == 0)
                    break outer;

                count2 = gallopLeft(tmp[cursor1], a, cursor2, len2, 0);
                if (count2 != 0) {
                    arraycopy(a, cursor2, a, dest, count2);
                    dest += count2;
                    cursor2 += count2;
                    len2 -= count2;
                    if (len2 == 0)
                        break outer;
                }
                a[dest++] = tmp[cursor1++];
                if (--len1 == 1)
                    break outer;
                minGallop--;
            } while (count1 >= MIN_GALLOP | count2 >= MIN_GALLOP);
            if (minGallop < 0)
                minGallop = 0;
            minGallop += 2;  // Penalize for leaving gallop mode
        }  // End of "outer" loop
        this.minGallop = minGallop < 1 ? 1 : minGallop;  // Write back to field

        if (len1 == 1) {
            arraycopy(a, cursor2, a, dest, len2);
            a[dest + len2] = tmp[cursor1]; //  Last elt of run 1 to end of merge
        } else if (len1 == 0) {
            throw new IllegalArgumentException(
                "Comparison method violates its general contract!");
        } else {
            // 最后将前半部分剩下元素（都是最大的元素），直接复制到a中，
            arraycopy(tmp, cursor1, a, dest, len1);
        }
    }

    private int gallopLeft(int key, int[] a, int base, int len, int hint) {
        int lastOfs = 0;
        int ofs = 1;
        if (key > a[base + hint]) {
            // Gallop right until a[base+hint+lastOfs] < key <= a[base+hint+ofs]
            int maxOfs = len - hint;
            while (ofs < maxOfs && key > a[base + hint + ofs]) {
                lastOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = maxOfs;
            }
            if (ofs > maxOfs)
                ofs = maxOfs;

            // Make offsets relative to base
            lastOfs += hint;
            ofs += hint;
        } else { // key <= a[base + hint]
            // Gallop left until a[base+hint-ofs] < key <= a[base+hint-lastOfs]
            final int maxOfs = hint + 1;
            while (ofs < maxOfs && key <= a[base + hint - ofs]) {
                lastOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = maxOfs;
            }
            if (ofs > maxOfs)
                ofs = maxOfs;

            // Make offsets relative to base
            int tmp = lastOfs;
            lastOfs = hint - ofs;
            ofs = hint - tmp;
        }
        assert -1 <= lastOfs && lastOfs < ofs && ofs <= len;

        /*
         * Now a[base+lastOfs] < key <= a[base+ofs], so key belongs somewhere
         * to the right of lastOfs but no farther right than ofs.  Do a binary
         * search, with invariant a[base + lastOfs - 1] < key <= a[base + ofs].
         */
        lastOfs++;
        while (lastOfs < ofs) {
            int m = lastOfs + ((ofs - lastOfs) >>> 1);

            if (key > a[base + m])
                lastOfs = m + 1;  // a[base + m] < key
            else
                ofs = m;          // key <= a[base + m]
        }
        assert lastOfs == ofs;    // so a[base + ofs - 1] < key <= a[base + ofs]
        return ofs;
    }


    private int gallopRight(int key, int[] a, int base, int len, int hint) {
        int ofs = 1;
        int lastOfs = 0;
        if (key < a[base + hint]) {
            // Gallop left until a[b+hint - ofs] <= key < a[b+hint - lastOfs]
            int maxOfs = hint + 1;
            while (ofs < maxOfs && key < a[base + hint - ofs]) {
                lastOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = maxOfs;
            }
            if (ofs > maxOfs)
                ofs = maxOfs;

            // Make offsets relative to b
            int tmp = lastOfs;
            lastOfs = hint - ofs;
            ofs = hint - tmp;
        } else { // a[b + hint] <= key
            // Gallop right until a[b+hint + lastOfs] <= key < a[b+hint + ofs]
            int maxOfs = len - hint;
            while (ofs < maxOfs && key >= a[base + hint + ofs]) {
                lastOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = maxOfs;
            }
            if (ofs > maxOfs)
                ofs = maxOfs;

            // Make offsets relative to b
            lastOfs += hint;
            ofs += hint;
        }

        lastOfs++;
        while (lastOfs < ofs) {
            int m = lastOfs + ((ofs - lastOfs) >>> 1);

            if (key < a[base + m])
                ofs = m;          // key < a[b + m]
            else
                lastOfs = m + 1;  // a[b + m] <= key
        }
        return ofs;
    }

    private void pushRun(int runBase, int runLen) {
        this.runBase[stackSize] = runBase;
        this.runLen[stackSize] = runLen;
        stackSize++;
    }

    private int minRunLength(int n) {
        int r = 0;      // Becomes 1 if any 1 bits are shifted off
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }


    /**
     * insert sort for the small length array
     *
     * input: 6,4,2,1,3,5,
     * output: 1,2,3,4,5,6,
     *
     * source: java.util.TimSort: binarySort
     *
     * @param a
     * @param lo
     * @param hi
     * @param start
     */
    private void binarySort(int[] a, int lo, int hi, int start) {
        if (hi > a.length) {
            hi = a.length;
        }
        if (start == lo)
            start++;
        for ( ; start < hi; start++) {
            int pivot = a[start];

            // find the specific position
            int left = lo;
            int right = start;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (pivot < a[mid])
                    right = mid;
                else
                    left = mid + 1;
            }

            // move and insert into the specific position
            int n = start - left;
            arraycopy(a, left, a,left + 1, n);
            a[left] = pivot;
        }
    }

    public void arraycopy(int[]a, int src,int[] b, int dest, int len) {
        for (int i = len; i > 0; i--) {
            b[dest + i - 1] = a[src + i - 1];
        }
    }

}
