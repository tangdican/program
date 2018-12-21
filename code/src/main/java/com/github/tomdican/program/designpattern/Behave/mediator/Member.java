package com.github.tomdican.program.designpattern.Behave.mediator;

public abstract class Member {
    public Member next = null;

    abstract void joinedParty(Party party);

    abstract void partyAction(Action action);

    abstract void act(Action action);
}
