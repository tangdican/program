package com.github.tomdican.program.concurrent.basic;

import java.util.concurrent.Semaphore;

public class SemaphoresPool {
    protected Object[] items;
    protected boolean[] used ;

    public SemaphoresPool() {
        this(MAX_AVAILABLE);
    }

    public SemaphoresPool(int cap) {
        items = new Object[cap];
        used = new boolean[cap];
        available = new Semaphore(cap, true);
        MAX_AVAILABLE = cap;

        for (int i = 0; i < cap; i++) {
            items[i] = i;
        }
    }

  private static int MAX_AVAILABLE = 100;
  private final Semaphore available;

  public Object getItem() throws InterruptedException {
    available.acquire();
    return getNextAvailableItem();
  }

  public void putItem(Object x) {
    if (markAsUnused(x))
      available.release();
  }

   protected synchronized Object getNextAvailableItem() {
    for (int i = 0; i < MAX_AVAILABLE; ++i) {
      if (!used[i]) {
         used[i] = true;
         return items[i];
      }
    }
    return null; // not reached
  }

  protected synchronized boolean markAsUnused(Object item) {
    for (int i = 0; i < MAX_AVAILABLE; ++i) {
      if (item == items[i]) {
         if (used[i]) {
           used[i] = false;
           return true;
         } else
           return false;
      }
    }
    return false;
  }
}
