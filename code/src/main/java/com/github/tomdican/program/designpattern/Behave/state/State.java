package com.github.tomdican.program.designpattern.Behave.state;

public abstract class State {

    private Mammoth mammoth;

    public State(Mammoth mammoth) {
        this.mammoth = mammoth;
    }

    abstract void onEnterState();

    abstract void observe();

}
