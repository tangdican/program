package com.github.tomdican.program.designpattern.structure.decorator;

import java.util.stream.Collectors;

public class PrintTextDecorator implements PrintText {

    private PrintText inner;

    public PrintTextDecorator(PrintText inner) {
        this.inner = inner;
    }

    @Override
    public void print(String text) {
        String hex = text.chars()
            .boxed()
            .map(x -> "0x" + Integer.toHexString(x))
            .collect(Collectors.joining(" "));
        inner.print(text + " -> HEX: " + hex);

    }
}
