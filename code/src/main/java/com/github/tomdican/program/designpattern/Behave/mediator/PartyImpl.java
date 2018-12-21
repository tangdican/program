package com.github.tomdican.program.designpattern.Behave.mediator;

public class PartyImpl implements Party{

    private Member members;


    @Override
    public void addMember(Member member) {
        Member head = members;

        if (head == null) {
            members = member;
            member.joinedParty(this);
            return;
        } else if (head.equals(member)) {
            return ;
        }

        while (head.next != null) {
            if (head.next.equals(member)) {
                return;
            }
            head = head.next;
        }

        if (head.next == null) {
            head.next = member;
        }
        member.joinedParty(this);
    }

    @Override
    public void act(Member actor, Action action) {
        Member next = members;
        while (next != null) {
            if (next.equals(actor)) {
                break;
            }
            next = next.next;
        }

        if (next != null && next.next != null) {
            next.next.partyAction(action);
        } else if (next != null) {
            next.partyAction(action);
        }

    }
}
