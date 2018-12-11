package com.github.tomdican.program.designpattern.structure.facade;

public class Buyer {

    public static void main(String[] args) throws InterruptedException {

        CoffeeMachineFacade coffeeMachineFacade = new CoffeeMachineFacade();
        coffeeMachineFacade.serveCoffee();

    }

}
