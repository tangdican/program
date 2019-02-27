package com.github.tomdican.program.concurrent.basic;

import java.util.concurrent.*;

/**
 * source: https://cloud.tencent.com/developer/article/1129638
 * @param <V>
 */
public class CacheBean<V> {
    // 缓存计算的结果
    private final static ConcurrentMap<String, Future<Object>> cache = new ConcurrentHashMap<>();

    // 延迟队列来判断那些缓存过期
    private final static DelayQueue<DelayedItem<String>> delayQueue = new DelayQueue<>();

    // 缓存时间
    private final int ms;

    static {
        // 定时清理过期缓存
        Thread t = new Thread() {
            @Override
            public void run() {
                dameonCheckOverdueKey();
            }
        };
        t.setDaemon(true);
        t.start();
    }

    private final Computable<V> c;

    /**
     * @param c Computable
     */
    public CacheBean(Computable<V> c) {
        this(c, 60 * 1000);
    }

    /**
     * @param c Computable
     * @param ms 缓存多少毫秒
     */
    public CacheBean(Computable<V> c, int ms) {
        this.c = c;
        this.ms = ms;
    }

    public V compute(final String key) throws InterruptedException {

        while (true) {
            //根据key从缓存中获取值
            Future<V> f = (Future<V>) cache.get(key);
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    public V call() {
                        return (V) c.compute(key);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(eval);
                //如果缓存中存在此可以，则返回已存在的value
                f = (Future<V>) cache.putIfAbsent(key, (Future<Object>) ft);
                if (f == null) {
                    //向delayQueue中添加key，并设置该key的存活时间
                    delayQueue.put(new DelayedItem<>(key, ms));
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(key, f);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 检查过期的key，从cache中删除
     */
    private static void dameonCheckOverdueKey() {
        DelayedItem<String> delayedItem;
        while (true) {
            try {
                delayedItem = delayQueue.take();
                if (delayedItem != null) {
                    cache.remove(delayedItem.getT());
                    System.out.println(System.nanoTime() + " remove " + delayedItem.getT() + " from cache");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public interface Computable<V> {
        V compute(String k);
    }
}



