package com.github.tomdican.program.designpattern.Behave.templatemethod;

public class Thief {
    private StealingMethodTemplate method;

    public Thief(StealingMethodTemplate method) {
        this.method = method;
    }

    public void steal() {
        method.steal();
    }

    public static void main(String[] args) {
        Thief thief = new Thief(new HitRunMethod());
        thief.steal();
    }
}
