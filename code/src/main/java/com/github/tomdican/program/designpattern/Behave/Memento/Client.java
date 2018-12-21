package com.github.tomdican.program.designpattern.Behave.Memento;

import com.github.tomdican.program.designpattern.Behave.Memento.State.Memento;

public class Client {

    public static void main(String[] args) {

        State state = new State();
        state.setState("1");
        Memento memento = state.saveState();
        state.setState("2");
        System.out.println(state.getState());

        state.restoreState(memento);
        System.out.println(state.getState());
    }


}
