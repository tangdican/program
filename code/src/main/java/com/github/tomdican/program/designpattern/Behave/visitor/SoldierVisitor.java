package com.github.tomdican.program.designpattern.Behave.visitor;

import com.github.tomdican.program.Util;

public class SoldierVisitor implements Visitor {

    @Override
    public void visit(Unit unit) {
        Util.println("greetings soldier");
    }
}
