package com.github.tomdican.program.designpattern.structure.composite;

public class Letter extends Composite {

    private char c;

    public Letter(char c) {
        this.c = c;
    }

    @Override
    protected void printThisBefore() {
        System.out.print(c);
    }
}
