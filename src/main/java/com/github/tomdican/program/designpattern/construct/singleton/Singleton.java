package com.github.tomdican.program.designpattern.construct.singleton;

import com.github.tomdican.program.Util;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import javax.management.InstanceAlreadyExistsException;

/**
 * source: https://www.geeksforgeeks.org/java-singleton-design-pattern-practices-examples/
 *
 */
public class Singleton {

    /**
     * for a single thread
     */
    public static class Simple implements Serializable {
        private static Simple instance;
        protected String s;

        private Simple(){
            s = "Simple Singleton is Instantiated.";

            // prevent the reflection
//            if (instance != null) {
//                throw new InstantiationError("exist!");
//            }
        }

        public static Simple getInstance()
        {
            if (instance == null)
                instance = new Simple();

            return instance;
        }

        // implement readResolve method
        // to singleton
        protected Object readResolve()
        {
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
     * simple but not lazy instance
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
        //testInstance();

        // test reflection
       // testReflection();

        // test Serializable
        testSerializable();




    }

    private static void testSerializable() {
        try
        {
            Simple instance1 = Simple.getInstance();
            ObjectOutput out
                = new ObjectOutputStream(new FileOutputStream("file.text"));
            out.writeObject(instance1);
            out.close();

            // deserailize from file to object
            ObjectInput in
                = new ObjectInputStream(new FileInputStream("file.text"));

            Simple instance2 = (Simple) in.readObject();
            in.close();

            System.out.println("instance1 hashCode:- "
                + instance1.hashCode());
            System.out.println("instance2 hashCode:- "
                + instance2.hashCode());
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void testReflection() {
        Simple instance1 = Simple.getInstance();
        Simple instance2 = null;
        try
        {
            Constructor[] constructors =
                Simple.class.getDeclaredConstructors();
            for (Constructor constructor : constructors)
            {
                // Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                instance2 = (Simple) constructor.newInstance();
                break;
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("instance1.hashCode():- "
            + instance1.hashCode());
        System.out.println("instance2.hashCode():- "
            + instance2.hashCode());

    }

    private static void testInstance() {
        Simple instance1 = Simple.getInstance();
        Simple instance2 = Simple.getInstance();
        instance1.s = instance1.s.toUpperCase();
        Util.println(instance1.s+","+instance2.s);
        instance2.s = instance2.s.toLowerCase();
        Util.println(instance1.s+","+instance2.s);
    }
}
