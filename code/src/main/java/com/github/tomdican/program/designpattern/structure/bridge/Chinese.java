package com.github.tomdican.program.designpattern.structure.bridge;

public class Chinese implements Bridge {

    @Override
    public void say() {
        System.out.println("我在说中文");
    }
}
