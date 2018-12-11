package com.github.tomdican.program.designpattern.structure.flyweight;

public class PoisonPotion implements Potion {

    @Override
    public void drink() {
       System.out.println("drinking poison potion");
    }
}
