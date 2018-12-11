package com.github.tomdican.program.designpattern.structure.composite;

import java.util.ArrayList;
import java.util.List;

public class Messenger {

    static Composite message() {
        List<Word> words = new ArrayList<>();

        words.add(new Word(
            new Letter('H'),
            new Letter('i')
        ));
        words.add(new Word(
            new Letter('b'),
            new Letter('o'),
            new Letter('y')
        ));

        return new Sentence(words);
    }

    public static void main(String[] args) {
        Composite message = Messenger.message();
        message.print();
    }
}
