package com.github.tomdican.program.designpattern.Behave.visitor;

public class Commander extends Unit {
    public Commander(Unit... children) {
        super(children);
    }

    @Override
    public void accept(Visitor visitor) {
        if (CommanderVisitor.class.equals(visitor.getClass())) {
            visitor.visit(this);
        }
        super.accept(visitor);
    }
}
