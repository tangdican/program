package com.github.tomdican.program.designpattern.structure.adapter;

public class HMDIPort implements Port{
    @Override
    public void print() {
        System.out.println("hmdi port");
    }
}
