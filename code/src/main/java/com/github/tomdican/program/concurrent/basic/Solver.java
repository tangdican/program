package com.github.tomdican.program.concurrent.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

public class Solver {
    final int N;
  final float[][] data;
  final CyclicBarrier barrier;
  int[] sum;
  int sums;

  class Worker implements Runnable {
    int myRow;
    Worker(int row) { myRow = row; }
    public void run() {
      while (!done()) {
        processRow(myRow);

        try {
          barrier.await();
        } catch (InterruptedException ex) {
          return;
        } catch (BrokenBarrierException ex) {
          return;
        }
      }
    }

      private boolean done() {
          boolean b = true;
              int sumt = 0;
              for (int j = 0; j < N; j++) {
                  sumt += data[myRow][j];
              }
              if (sum[myRow] != sumt) {
                  b = false;
              }
          return b;

      }

      private void processRow(int myRow) {
        System.out.print("row" + myRow+":");
          for (int j = 0; j < N; j++) {
              sum[myRow] += data[myRow][j];
              System.out.print(" ,"+ data[myRow][j]);
          }
      }
  }

 public Solver(float[][] matrix) {
    data = matrix;
    N = matrix.length;
    sum = new int[N];
    Runnable barrierAction =
      new Runnable() { public void run() { mergeRows(); }};
    barrier = new CyclicBarrier(N, barrierAction);

    List<Thread> threads = new ArrayList<Thread>(N);
    for (int i = 0; i < N; i++) {
      Thread thread = new Thread(new Worker(i));
      threads.add(thread);
      thread.start();
    }
//
//    for (Thread thread : threads) {
//     try {
//      thread.join();
//     } catch (InterruptedException e) {
//      e.printStackTrace();
//     }
//    }
  }

 private void mergeRows() {
     System.out.println(" -->start sum:");
     for (int i = 0; i < N; i++) {
         sums += sum[i];
         System.out.print(" ."+sum[i]);
     }
     System.out.println(" -->"+sums);
 }
}
