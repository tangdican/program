package com.github.tomdican.program.designpattern.structure.facade;

import com.github.tomdican.program.designpattern.structure.facade.CoffeeGrinder.GroundCoffee;

public class CoffeeMaker {
    public CoffeeCup finishBrewing() {
        System.out.println("Done brewing. Enjoy!");
        return null;
    }

    public void pourWater(Water water) {
        System.out.println("Pouring water...");
    }

    public void placeCup(CoffeeCup cup) {
        System.out.println("Placing the cup...");
    }

    public void startBrewing(GroundCoffee groundCoffee) {
        System.out.println("Brewing...");
    }

    static class Water {
    }

    static class CoffeeCup {
    }
}
