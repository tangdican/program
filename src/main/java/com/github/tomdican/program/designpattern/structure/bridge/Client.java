package com.github.tomdican.program.designpattern.structure.bridge;


public class Client {

    public static void main(String[] args) {
        Bridge bridge = new Chinese();
        bridge.say();

        Bridge bridge1 = new American();
        bridge1.say();
    }

}
