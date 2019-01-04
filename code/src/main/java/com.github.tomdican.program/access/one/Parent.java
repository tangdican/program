package com.github.tomdican.program.access.one;

public abstract class Parent {

    protected final void showMe() {
        System.out.println("showMe parent class");
    }

    // package-private,can not access it outside the package
    final void showMe2() {
        System.out.println("showMe2 parent class");
    }
}
