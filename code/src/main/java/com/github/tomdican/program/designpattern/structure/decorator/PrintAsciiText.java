package com.github.tomdican.program.designpattern.structure.decorator;

public class PrintAsciiText implements PrintText {

    @Override
    public void print(String text) {
        System.out.println("Print ASCII: " + text);
    }
}
