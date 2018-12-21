package com.github.tomdican.program.designpattern.structure.decorator;

public class Display {

    public static void main(String[] args) {
        final String text = "text";
        final PrintText object = new PrintAsciiText();
        final PrintText printer = new PrintTextDecorator(object);

        object.print(text);
        printer.print(text);
    }

}
