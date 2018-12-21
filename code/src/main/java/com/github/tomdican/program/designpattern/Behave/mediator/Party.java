package com.github.tomdican.program.designpattern.Behave.mediator;

public interface Party {
    void addMember(Member member);

    void act(Member actor, Action action);
}
