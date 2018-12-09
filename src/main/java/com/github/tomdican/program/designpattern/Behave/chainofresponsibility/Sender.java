package com.github.tomdican.program.designpattern.Behave.chainofresponsibility;

public class Sender {

    public static void main(String[] args) {
        Request request = new Request("2");
        Received received = new Received(new Received2(new Received3(null)));
        received.handleRequest(request);
    }

}
