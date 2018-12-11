package com.github.tomdican.program.designpattern.structure.facade;

public class CoffeeGrinder {
    public void startGrinding() {
        System.out.println("Grinding...");
    }

    public GroundCoffee stopGrinding () {
        System.out.println("Done grinding");
        return new GroundCoffee();
    }

    class GroundCoffee {
    }
}
