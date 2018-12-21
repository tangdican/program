package com.github.tomdican.program.designpattern.structure.adapter;

public class Adapter {

    public static Port adapt(Port port) {
        if (UsbPort.class.equals(port.getClass())) {
            return new HMDIPort();
        } else if (HMDIPort.class.equals(port.getClass())) {
            return new UsbPort();
        }
        return null;
    }

    public static void main(String[] args) {
        Port port = Adapter.adapt(new UsbPort());
        port.print();
    }
}
