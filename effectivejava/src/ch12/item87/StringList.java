package ch12.item87;

import ch12.Util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// StringList with a reasonable custom serialized form  - Page 349
public final class StringList implements Serializable {
    private transient int size   = 0;
    private transient Entry head = null;

    // No longer Serializable!
    private static class Entry {
        String data;
        Entry  next;
        Entry  previous;
    }

    // Appends the specified string to the list
    public final void add(String s) {
        size++;
        Entry entry = new Entry();
        entry.data = s;
        if (head == null) {
            head = entry;
            return;
        }
        entry.next = head;
        head.previous = entry;
        head = entry;
    }

    @Override
    public final String toString() {

        StringBuffer s = new StringBuffer();
        s.append("[");
        for (Entry e = head; e != null; e = e.next)
            s.append(e.data+",");
        String substring = s.substring(0, s.length() - 1);
        substring += "]";
        return substring;
    }


    /**
     * Serialize this {@code StringList} instance.
     *
     * @serialData The size of the list (the number of strings
     * it contains) is emitted ({@code int}), followed by all of
     * its elements (each a {@code String}), in the proper
     * sequence.
     */
    private void writeObject(ObjectOutputStream s)
            throws IOException {
        s.defaultWriteObject();
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (Entry e = head; e != null; e = e.next)
            s.writeObject(e.data);
    }

    private void readObject(ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        int numElements = s.readInt();

        // Read in all elements and insert them in list
        for (int i = 0; i < numElements; i++)
            add((String) s.readObject());
    }

    // Remainder omitted


    public static void main(String[] args) {
        StringList stringList = new StringList();
        stringList.add("abc");
        stringList.add("bcd");
        stringList.add("cdf");
        stringList.add("dfg");

        byte[] serialize = Util.serialize(stringList);
        StringList deserialize = (StringList)Util.deserialize(serialize);
        System.out.println(deserialize.toString());
    }
}
