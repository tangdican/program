package com.github.tomdican.program.designpattern.Behave.visitor;

public class Soldier extends Unit {
    public Soldier(Unit... children) {
        super(children);
    }

    @Override
    public void accept(Visitor visitor) {
        if (SoldierVisitor.class.equals(visitor.getClass())) {
            visitor.visit(this);
        }
        super.accept(visitor);
    }
}
