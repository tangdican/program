package com.github.tomdican.program.designpattern.Behave.mediator;

/**
 *
 * java.util.concurrent.Executor#execute()
 *
 */
public class Client {

    public static void main(String[] args) {
        MemberImpl member = new MemberImpl();
        MemberImpl member1 = new MemberImpl();
        MemberImpl member2 = new MemberImpl();
        MemberImpl member3 = new MemberImpl();

        Party party = new PartyImpl();
        party.addMember(member);
        party.addMember(member1);
        party.addMember(member2);
        party.addMember(member3);

        member.act(Action.ENEMY);
        member1.act(Action.GOLD);
        member2.act(Action.HUNT);
        member3.act(Action.TALE);
    }

}
