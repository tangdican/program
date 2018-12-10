package com.github.tomdican.program.designpattern.Behave.mediator;

public class MemberImpl extends Member {

    Party party;
    @Override
    void joinedParty(Party party) {
        this.party = party;
    }

    @Override
    void partyAction(Action action) {
        System.out.println(action.getDescription());
    }

    @Override
    void act(Action action) {
        if (party != null) {
            party.act(this, action);
        }
    }
}
