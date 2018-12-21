package com.github.tomdican.program.designpattern.Behave.state;

public class AngryState extends State {

    public AngryState(Mammoth mammoth) {
        super(mammoth);
    }
    @Override
    void onEnterState() {
        System.out.println("change state");
    }

    @Override
    void observe() {
        System.out.println("angry");

    }
}
