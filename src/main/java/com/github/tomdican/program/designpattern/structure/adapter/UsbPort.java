package com.github.tomdican.program.designpattern.structure.adapter;

public class UsbPort implements Port{

    @Override
    public void print() {
        System.out.println("usb port");
    }
}
