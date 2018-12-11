package com.github.tomdican.program.designpattern.Behave.visitor;

public class Unit {
    private Unit[] children;

    public Unit(Unit... children) {
        this.children = children;
    }

    /**
     * Accept visitor
     */
    public void accept(Visitor visitor) {
        for (Unit child : children) {
            child.accept(visitor);
        }
    }
}
