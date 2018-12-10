package com.github.tomdican.program.designpattern.Behave.state;

public class Mammoth {
    private State state;

    public Mammoth() {
        state = new SmileState(this);
    }

    /**
     * Makes time pass for the mammoth
     */
    public void timePasses() {
        if (state.getClass().equals(SmileState.class)) {
            changeStateTo(new AngryState(this));
        } else {
            changeStateTo(new SmileState(this));
        }
    }

    private void changeStateTo(State newState) {
        this.state = newState;
        this.state.onEnterState();
    }

    public void observe() {
        this.state.observe();
    }

    public static void main(String[] args) {
        Mammoth mammoth = new Mammoth();
        mammoth.timePasses();
        mammoth.observe();
        mammoth.timePasses();
        mammoth.observe();

    }
}
