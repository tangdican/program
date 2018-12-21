package com.github.tomdican.program.advance;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SimpleTreeMap<K,V> {

    public static void main(String[] args) {
        SimpleTreeMap<Integer, String> treeMap = new SimpleTreeMap<Integer, String>();
        treeMap.put(1, "语文");
        treeMap.put(2, "数学");
        treeMap.put(3, "英语");
        treeMap.put(4, "政治");
        treeMap.put(5, "物理");
        treeMap.put(6, "化学");
        treeMap.put(7, "生物");
        treeMap.put(8, "体育");
        treeMap.printLevelOrderLineWithQueue();

//        System.out.println();
//        System.out.println("remove 7 >>>>>>>>>>>>>>>>>>");
//        treeMap.remove(7);
//        treeMap.printLevelOrderLineWithQueue();
//
//        System.out.println();
//        System.out.println("remove 6 >>>>>>>>>>>>>>>>>>");
//        treeMap.remove(6);
//        treeMap.printLevelOrderLineWithQueue();
//
//        System.out.println();
//        System.out.println("remove 8 >>>>>>>>>>>>>>>>>>");
//        treeMap.remove(8);
//        treeMap.printLevelOrderLineWithQueue();

        System.out.println();
        System.out.println("remove 5 >>>>>>>>>>>>>>>>>>");
        treeMap.remove(5);
        treeMap.printLevelOrderLineWithQueue();

    }


    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    static class Entry<K,V> {
        K key;
        V value;
        SimpleTreeMap.Entry<K,V> left;
        Entry<K,V> right;
        Entry<K,V> parent;
        boolean color = BLACK;

        Entry() { }

        Entry(K key, V value, Entry<K,V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;

            return valEquals(key,e.getKey()) && valEquals(value,e.getValue());
        }

        static final boolean valEquals(Object o1, Object o2) {
            return (o1==null ? o2==null : o1.equals(o2));
        }

        public int hashCode() {
            int keyHash = (key==null ? 0 : key.hashCode());
            int valueHash = (value==null ? 0 : value.hashCode());
            return keyHash ^ valueHash;
        }

        public String toString() {
            return key + "=" + value;
        }
    }


    SimpleTreeMap.Entry<K,V> root;
    private transient int size = 0;
    private transient int modCount = 0;


    public V get(Object key) {
        Entry<K,V> p = getEntry(key);
        return (p==null ? null : p.value);
    }

    private Entry<K,V> getEntry(Object key) {
        if (key == null)
            throw new NullPointerException();
        @SuppressWarnings("unchecked")
        Comparable<? super K> k = (Comparable<? super K>) key;
        Entry<K,V> p = root;
        while (p != null) {
            int cmp = k.compareTo(p.key);
            if (cmp < 0)
                p = p.left;
            else if (cmp > 0)
                p = p.right;
            else
                return p;
        }
        return null;
    }

    public V put(K key, V value) {
        SimpleTreeMap.Entry<K,V> t = root;
        if (t == null) {
            root = new SimpleTreeMap.Entry<>(key, value, null);
            size = 1;
            modCount++;
            return null;
        }
        int cmp;
        SimpleTreeMap.Entry<K,V> parent;

        if (key == null)
            throw new NullPointerException();
        @SuppressWarnings("unchecked")
        Comparable<? super K> k = (Comparable<? super K>) key;
        do {
            parent = t;
            cmp = k.compareTo(t.key);
            if (cmp < 0)
                t = t.left;
            else if (cmp > 0)
                t = t.right;
            else
                return t.setValue(value);
        } while (t != null);

        SimpleTreeMap.Entry<K,V> e = new SimpleTreeMap.Entry<>(key, value, parent);
        if (cmp < 0)
            parent.left = e;
        else
            parent.right = e;
        fixAfterInsertion(e);
        size++;
        modCount++;
        return null;
    }

    private void fixAfterInsertion(Entry<K, V> x) {

        x.color = RED;

        while (x != null && x != root && x.parent.color == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                Entry<K,V> y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                Entry<K,V> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }


    public V remove(Object key) {
        Entry<K,V> p = getEntry(key);
        if (p == null)
            return null;

        V oldValue = p.value;
        deleteEntry(p);
        return oldValue;
    }

    private void deleteEntry(Entry<K, V> p) {
        modCount++;
        size--;

        // If strictly internal, copy successor's element to p and then make p
        // point to successor.
        if (p.left != null && p.right != null) {
            Entry<K,V> s = successor(p);
            p.key = s.key;
            p.value = s.value;
            p = s;
        } // p has 2 children

        // Start fixup at replacement node, if it exists.
        Entry<K,V> replacement = (p.left != null ? p.left : p.right);

        if (replacement != null) {
            // Link replacement to parent
            replacement.parent = p.parent;
            if (p.parent == null)
                root = replacement;
            else if (p == p.parent.left)
                p.parent.left  = replacement;
            else
                p.parent.right = replacement;

            // Null out links so they are OK to use by fixAfterDeletion.
            p.left = p.right = p.parent = null;

            // Fix replacement
            if (p.color == BLACK)
                fixAfterDeletion(replacement);
        } else if (p.parent == null) { // return if we are the only node.
            root = null;
        } else { //  No children. Use self as phantom replacement and unlink.
            if (p.color == BLACK)
                fixAfterDeletion(p);

            if (p.parent != null) {
                if (p == p.parent.left)
                    p.parent.left = null;
                else if (p == p.parent.right)
                    p.parent.right = null;
                p.parent = null;
            }
        }
    }

    private void fixAfterDeletion(Entry<K, V> x) {
        System.out.println("before fixAfterDeletion >>>>>>>>>>>>>");
        printLevelOrderLineWithQueue();
        while (x != root && colorOf(x) == BLACK) {
            if (x == leftOf(parentOf(x))) {
                Entry<K,V> sib = rightOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                }

                if (colorOf(leftOf(sib))  == BLACK &&
                    colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(rightOf(sib)) == BLACK) {
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(rightOf(sib), BLACK);
                    rotateLeft(parentOf(x));
                    x = root;
                }
            } else { // symmetric
                Entry<K,V> sib = leftOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                }

                if (colorOf(rightOf(sib)) == BLACK &&
                    colorOf(leftOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }

        setColor(x, BLACK);
        System.out.println("");
        System.out.println("after fixAfterDeletion >>>>>>>>>>>>>");

    }

    private Entry<K, V> successor(Entry<K, V> t) {
        if (t == null)
            return null;
        else if (t.right != null) {
            Entry<K,V> p = t.right;
            while (p.left != null)
                p = p.left;
            return p;
        } else {
            Entry<K,V> p = t.parent;
            Entry<K,V> ch = t;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    private Entry<K,V> leftOf(Entry<K, V> p) {
        return (p == null) ? null: p.left;
    }

    private void rotateRight(Entry<K, V> p) {
        if (p != null) {
            System.out.println(">>>>>>"+p.key+p.value+">>rotateRight");
            Entry<K,V> l = p.left;
            p.left = l.right;
            if (l.right != null) l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else p.parent.left = l;
            l.right = p;
            p.parent = l;

            printLevelOrderLineWithQueue();
            System.out.println("");
        }
    }

    private void rotateLeft(Entry<K,V> p) {
        if (p != null) {
            System.out.println(">>>>>>"+p.key+p.value+">>rotateLeft");
            Entry<K,V> r = p.right;
            p.right = r.left;
            if (r.left != null)
                r.left.parent = p;
            r.parent = p.parent;
            if (p.parent == null)
                root = r;
            else if (p.parent.left == p)
                p.parent.left = r;
            else
                p.parent.right = r;
            r.left = p;
            p.parent = r;

            printLevelOrderLineWithQueue();
            System.out.println("");
        }
    }

    private Entry<K, V> rightOf(Entry<K, V> p) {
        return (p == null) ? null: p.right;
    }

    private void setColor(Entry<K, V> p, boolean c) {
        if (p != null)
            System.out.println(">>>>>"+p.key+p.value+": "+(p.color==RED?"red":"black")+"---->"+(c==RED?"red":"black"));
            p.color = c;
    }

    private boolean colorOf(Entry<K, V> p) {
        return (p == null ? BLACK : p.color);
    }

    private Entry<K, V> parentOf(Entry<K, V> p) {
        return (p == null ? null: p.parent);
    }


    public void printLevelOrderLineWithQueue() {
        Queue<Entry<K,V>> queue = new LinkedList<>();
        Entry<K,V> node = root;
        if (node == null) {
            return;
        }
        Entry<K,V> line = new Entry<K,V>();
        line.value = null;// mark to print next line;
        queue.clear();
        queue.add(node);
        queue.add(line);
        Entry<K,V> temp = null;
        while (queue.size()>=2) {
            temp = queue.poll();
            if (temp.value == null){
                System.out.println("");
                queue.add(line);
            } else {
                System.out.print("," + temp.key + temp.value + "(" + (temp.color==RED ? "red" : "black") +")");
            }
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
        System.out.println("");
    }

}
