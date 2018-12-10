package com.github.tomdican.program.designpattern.Behave.interpreter;

public class Plus implements Expression {

    Expression left;
    Expression right;

    public Plus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public float interpret()  {
        return left.interpret() + right.interpret();
    }
}
