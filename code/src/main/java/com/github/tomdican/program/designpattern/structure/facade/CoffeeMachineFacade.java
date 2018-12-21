package com.github.tomdican.program.designpattern.structure.facade;

import static com.github.tomdican.program.designpattern.structure.facade.CoffeeMaker.*;

import com.github.tomdican.program.designpattern.structure.facade.CoffeeMaker.CoffeeCup;
import com.github.tomdican.program.designpattern.structure.facade.CoffeeMaker.Water;

public class CoffeeMachineFacade {


    public CoffeeCup serveCoffee() throws InterruptedException {
        CoffeeGrinder grinder = new CoffeeGrinder();
        CoffeeMaker brewer = new CoffeeMaker();
        CoffeeCup cup = new CoffeeCup();

        grinder.startGrinding();
        Thread.sleep(500);//wait for grind size coarse

        brewer.placeCup(cup);
        brewer.pourWater(new Water());
        brewer.startBrewing(grinder.stopGrinding());
        Thread.sleep(1000);//wait for the brewing process

        return brewer.finishBrewing();
    }
}
