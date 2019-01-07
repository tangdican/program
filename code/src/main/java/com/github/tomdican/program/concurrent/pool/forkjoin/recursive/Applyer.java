package com.github.tomdican.program.concurrent.pool.forkjoin.recursive;

import java.util.concurrent.RecursiveAction;

/**
 * source: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/RecursiveAction.html
 *
 * process：
 * 拆成两半，后半部分给新建的线程，直到拆分到长度为2或者等待任务大于3，
 * 每个线程拆分结束后，直接开始计算；
 * 抢子任务如果成功，就到本线程运行；
 * 本任务运行完成后，就通过next，跳到父任务线程运行，然后合并结果；
 * 最后主线程将所有结果合并汇总，结束。
 *
 *
 */
public class Applyer extends RecursiveAction {

    public static void main(String[] args) {
        double[] a = new double[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = 2;
        }

        Applyer applyer = new Applyer(a, 0, a.length, null);
        applyer.compute();

        System.out.println(applyer.result);

    }


    final double[] array;
    final int lo, hi;
    double result;
    Applyer next; // keeps track of right-hand-side tasks
    Applyer(double[] array, int lo, int hi, Applyer next) {
        this.array = array; this.lo = lo; this.hi = hi;
        this.next = next;
    }

    double atLeaf(int l, int h) {
        System.out.println(Thread.currentThread().getName()+" >>>>>>>>>>>>>>>>>>atleaf:"+l+","+h+",");
        double sum = 0;
        for (int i = l; i < h; ++i) // perform leftmost base step
            sum += array[i] * array[i];
        return sum;
    }

    protected void compute() {
        int l = lo;
        int h = hi;
        Applyer right = null;
        int c = 0;
        while (h - l > 1 && (c=getSurplusQueuedTaskCount()) <= 3) {
            int mid = (l + h) >>> 1;
            System.out.println(Thread.currentThread().getName()+":"+(right==null ? -1:right.hi)+","+l+","+h+","+mid+","+c);
            right = new Applyer(array, mid, h, right); // 用来连接
            right.fork();// 开启新线程来计算，next为h-2h
            h = mid;
        }
        double sum = atLeaf(l, h);
        while (right != null) {
           // System.out.println(">>>>>>>>>>>>>>"+Thread.currentThread().getName()+","+right.lo+","+right.hi+","+sum);
            if (right.tryUnfork()) // directly calculate if not stolen
                sum += right.atLeaf(right.lo, right.hi); // 当新建的线程没有计算，就本线程自己计算
            else {
                right.join();
                sum += right.result;// 从新线程中获取计算结果
            }
            right = right.next;
        }
        result = sum;
    }
}
