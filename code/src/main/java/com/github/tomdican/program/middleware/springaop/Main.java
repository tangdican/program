package com.github.tomdican.program.middleware.springaop;

public class Main {
    public static void main(String[] args){
        InterfaceA interfaceA = new ProxyImplement() ;
        interfaceA.exec();
    }
}
