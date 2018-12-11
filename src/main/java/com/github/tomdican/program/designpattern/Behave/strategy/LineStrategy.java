package com.github.tomdican.program.designpattern.Behave.strategy;

public class LineStrategy implements Strategy {

    @Override
    public void execute() {
        System.out.println("fly line line!");
    }
}
