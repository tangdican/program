package com.github.tomdican.program.designpattern.construct.singleton;

import com.github.tomdican.program.Util;

public class Singleton {

    /**
     * for a single thread
     */
    public static class Simple {
        private static Simple instance;
        protected String s;

        private Simple(){
            s = "Simple Singleton is Instantiated.";
        }

        public static Simple getInstance()
        {
            if (instance == null)
                instance = new Simple();

            return instance;
        }
    }

    /**
     * for multi thread
     * but cost too much
     */
    public static class SimpleSync {
        private static SimpleSync instance;

        private SimpleSync(){
            System.out.println("SimpleSync Singleton is Instantiated.");
        }

        public static synchronized SimpleSync getInstance()
        {
            if (instance == null)
                instance = new SimpleSync();

            return instance;
        }
    }

    /**
     * for multi thread
     * simple
     */
    public static class SimpleFinal {
        private static final SimpleFinal instance = new SimpleFinal();

        private SimpleFinal(){
            System.out.println("SimpleFinal Singleton is Instantiated.");
        }

        public static SimpleFinal getInstance()
        {
            return instance;
        }
    }

    /***
     * for multi thread
     * atomic,synchronized when instance is null
     */
    public static class SimpleAmotic {
        private static volatile SimpleAmotic instance;

        private SimpleAmotic(){
            System.out.println("SimpleAmotic Singleton is Instantiated.");
        }

        public static SimpleAmotic getInstance()
        {
            if (instance == null)
                synchronized (SimpleAmotic.class) {
                    if (instance == null) {
                        instance = new SimpleAmotic();
                    }
                }
            return instance;
        }
    }

    public static void main(String[] args) {
        // test instance loc
        Simple instance1 = Simple.getInstance();
        Simple instance2 = Simple.getInstance();
        instance1.s = instance1.s.toUpperCase();
        Util.println(instance1.s+","+instance2.s);
        instance2.s = instance2.s.toLowerCase();
        Util.println(instance1.s+","+instance2.s);



    }
}
