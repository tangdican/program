package com.github.tomdican.program.designpattern.Behave.chainofresponsibility;

public class Request {
    private String name;

    public Request(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
