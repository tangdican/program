package com.github.tomdican.program.middleware.springaop.basic;

public class Main {
    public static void main(String[] args){
        InterfaceA interfaceA = new ProxyImplement() ;
        interfaceA.exec();
    }
}
