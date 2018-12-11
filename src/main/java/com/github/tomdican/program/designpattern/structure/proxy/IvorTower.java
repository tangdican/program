package com.github.tomdican.program.designpattern.structure.proxy;

public class IvorTower implements Tower {

    @Override
    public void enter(Wizard wizard) {
       System.out.println(wizard.getName()+ " wizard is entering the tower");
    }

    public static void main(String[] args) {
        TowerProxy towerProxy = new TowerProxy(new IvorTower());
        towerProxy.enter(new Wizard("wizard1"));
        towerProxy.enter(new Wizard("wizard2"));
        towerProxy.enter(new Wizard("wizard3"));
        towerProxy.enter(new Wizard("wizard4"));
    }
}
