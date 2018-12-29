package ch6.item40;

import java.util.HashSet;
import java.util.Set;

// Can you spot the bug? (Page 188)
public class Bigram {
    private final char first;
    private final char second;

    public Bigram(char first, char second) {
        this.first  = first;
        this.second = second;
    }

    // override rightly
//    public boolean equals(Object b) {
//        return ((Bigram)b).first == first && ((Bigram)b).second == second;
//    }

    // not override
    public boolean equals(Bigram b) {
        return b.first == first && b.second == second;
    }

    public int hashCode() {
        return 31 * first + second;
    }

    public static void main(String[] args) {
        Set<Bigram> s = new HashSet<>();
        s.add(new Bigram('a', 'a'));
        s.add(new Bigram('a', 'a'));
        for (int i = 0; i < 10; i++)
            for (char ch = 'a'; ch <= 'z'; ch++)
                s.add(new Bigram(ch, ch));
        System.out.println(s.size());
    }
}
