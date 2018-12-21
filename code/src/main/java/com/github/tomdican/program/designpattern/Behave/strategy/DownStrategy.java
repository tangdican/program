package com.github.tomdican.program.designpattern.Behave.strategy;

public class DownStrategy implements Strategy {

    @Override
    public void execute() {
        System.out.println("fly down down!");
    }
}
