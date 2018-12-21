package com.github.tomdican.program.hash;

import java.util.ArrayList;

public class Map<K,V> {

    class MapNode<K, V> {
        K key;
        V value;
        MapNode<K, V> next;
        public MapNode(K key, V value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    ArrayList<MapNode<K, V> > buckets;
    int size;
    int numBuckets;
    final double DEFAULT_LOAD_FACTOR = 0.75;

    public Map() {
        initialize(5);
    }

    public Map(int num) {
       initialize(num);
    }

    private void initialize(int num) {
        numBuckets = num;
        buckets = new ArrayList<>(numBuckets);
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(null);
        }
        System.out.println("HashMap created");
        System.out.println("Number of pairs in the Map: " + size);
        System.out.println("Size of Map: " + numBuckets);
        System.out.println("Default Load Factor : " + DEFAULT_LOAD_FACTOR + "\n");
    }


    private int getBucketInd(K key) {
        // Using the inbuilt function from the object class
        int hashCode = key.hashCode();
        // array index = hashCode%numBuckets
        return (hashCode % numBuckets);
    }

    public void insert (K key, V value) {
        int bucketInd = getBucketInd(key);
        MapNode<K, V> head = buckets.get(bucketInd);
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        MapNode<K, V> newElementNode = new MapNode<K, V>(key, value);
        head = buckets.get(bucketInd);
        newElementNode.next = head;
        buckets.set(bucketInd, newElementNode);
        System.out.println("Pair(" + key + ", " + value + ") inserted successfully.\n");

        size++;
        double loadFactor = (1.0 * size) / numBuckets;
        System.out.println("Current Load factor = " + loadFactor);
        if (loadFactor > DEFAULT_LOAD_FACTOR) {
            System.out.println(loadFactor + " is greater than " + DEFAULT_LOAD_FACTOR);
            System.out.println("Therefore Rehashing will be done.\n");
            // Rehash
            rehash();
            System.out.println("New Size of Map: " + numBuckets + "\n");
        }
        System.out.println("Number of pairs in the Map: " + size);
        System.out.println("Size of Map: " + numBuckets + "\n");
    }

    private void rehash() {
        System.out.println("\n***Rehashing Started***\n");
        ArrayList<MapNode<K, V> > temp = buckets;
        buckets = new ArrayList<MapNode<K, V> >(2 * numBuckets);
        for (int i = 0; i < 2 * numBuckets; i++) {
            buckets.add(null);
        }
        size = 0;
        numBuckets *= 2;

        for (int i = 0; i < temp.size(); i++) {
            MapNode<K, V> head = temp.get(i);
            while (head != null) {
                K key = head.key;
                V val = head.value;
                insert(key, val);
                head = head.next;
            }
        }
        System.out.println("\n***Rehashing Ended***\n");
    }

    public void printMap()
    {
        ArrayList<MapNode<K, V> > temp = buckets;
        System.out.println(">>>>>>>>>>> print Current HashMap:");
        for (int i = 0; i < temp.size(); i++) {
            MapNode<K, V> head = temp.get(i);
            while (head != null) {
                System.out.println("key = " + head.key + ", val = " + head.value);
                head = head.next;
            }
        }
        System.out.println();
    }

    public boolean contain(K key) {
        return this.get(key) != null;
    }

    public V get(K key) {
        int bucketInd = getBucketInd(key);
        MapNode<K, V> head = buckets.get(bucketInd);

        while (head !=null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }

        return null;
    }

    public V remove(K key) {
        int bucketInd = getBucketInd(key);
        MapNode<K, V> head = buckets.get(bucketInd);

        MapNode<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        if (head == null) {
            return null;
        }


        if (prev != null) {
            prev.next = head.next;
            prev = head;
        } else {
            prev = head;
            head = head.next;
        }
        buckets.set(bucketInd, head);
        size--;

        return prev.value ;
    }


    public static void main(String[] args)
    {
        Map<Integer, String> map = new Map<Integer, String>();

        map.insert(1, "Geeks");
        map.printMap();

        map.insert(2, "forGeeks");
        map.printMap();

        map.insert(3, "A");
        map.printMap();

        map.insert(4, "Computer");
        map.printMap();

        map.insert(5, "Portal");
        map.printMap();
    }


}
