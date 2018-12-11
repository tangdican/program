package com.github.tomdican.program.designpattern.Behave.visitor;

import com.github.tomdican.program.Util;

public class CommanderVisitor implements Visitor {

    @Override
    public void visit(Unit unit) {
        Util.println("greetings commander");
    }
}
