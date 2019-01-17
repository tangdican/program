package com.github.tomdican.program.middleware.springaop;

public class ProxyImplement implements InterfaceA {
    private InterfaceA interfaceA;

    public ProxyImplement() {
        interfaceA = new RealImplement();
    }

    public void exec() {
        System.out.println("dosomethings before");

        interfaceA.exec();

        System.out.println("dosomethings after");
    }
}
