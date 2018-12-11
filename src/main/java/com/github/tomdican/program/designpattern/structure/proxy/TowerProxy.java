package com.github.tomdican.program.designpattern.structure.proxy;

public class TowerProxy implements Tower {

    private static final int NUM_WIZARDS_ALLOWED = 3;

    private int numWizards;

    private Tower tower;

    public TowerProxy(Tower tower) {
        this.tower = tower;
    }

    @Override
    public void enter(Wizard wizard) {
        if (numWizards < NUM_WIZARDS_ALLOWED) {
            tower.enter(wizard);
            numWizards++;
        } else {
            System.out.println("max "+ NUM_WIZARDS_ALLOWED + " wizards had entered");
        }
    }
}
