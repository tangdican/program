package com.github.tomdican.program.designpattern.structure.bridge;

public class American implements Bridge {

    @Override
    public void say() {
        System.out.println("i am speaking english");
    }
}
