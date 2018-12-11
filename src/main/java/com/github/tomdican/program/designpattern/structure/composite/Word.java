package com.github.tomdican.program.designpattern.structure.composite;

public class Word extends Composite {

    public Word(Letter... letters) {
        for (Letter l : letters) {
            this.add(l);
        }
    }

    @Override
    protected void printThisBefore() {
        System.out.print(" ");
    }
}
