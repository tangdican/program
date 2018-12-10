package com.github.tomdican.program.designpattern.Behave.interpreter;

public class Number implements Expression {

    private float number;

    public Number(float number) {
        this.number = number;
    }

    public Number(String number) {
        this.number = Float.parseFloat(number);
    }

    @Override
    public float interpret() {
        return number;
    }
}
