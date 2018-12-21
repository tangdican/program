package com.github.tomdican.program.designpattern.structure.composite;

import java.util.ArrayList;
import java.util.List;

public abstract class Composite {
    List<Composite> children = new ArrayList<>();

    void add(Composite composite) {
        children.add(composite);
    }

    protected void printThisBefore() {}

    protected void printThisAfter() {}

    /**
     * Print
     */
    public void print() {
        printThisBefore();
        for (Composite letter : children) {
            letter.print();
        }
        printThisAfter();
    }

}
