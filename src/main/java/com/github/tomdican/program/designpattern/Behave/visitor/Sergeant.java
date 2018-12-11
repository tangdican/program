package com.github.tomdican.program.designpattern.Behave.visitor;

public class Sergeant extends Unit {
    public Sergeant(Unit... children) {
        super(children);
    }

    @Override
    public void accept(Visitor visitor) {
        if (SergeantVisitor.class.equals(visitor.getClass())) {
            visitor.visit(this);
        }
        super.accept(visitor);
    }
}
