package com.github.tomdican.program.designpattern.structure.composite;

import java.util.List;

public class Sentence extends Composite {
    public Sentence(List<Word> words) {
        for (Word w : words) {
            this.add(w);
        }
    }

    @Override
    protected void printThisAfter() {
        System.out.print(".");
    }
}
