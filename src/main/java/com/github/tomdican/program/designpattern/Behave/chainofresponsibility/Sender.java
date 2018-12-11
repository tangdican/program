package com.github.tomdican.program.designpattern.Behave.chainofresponsibility;

/**
 * Avoid coupling the sender of a request to its receiver by giving more than one object a chance to handle the request. Chain the receiving objects and pass the request along the chain until an object handles it.
 */
public class Sender {

    public static void main(String[] args) {
        Request request = new Request("2");
        Received received = new Received(new Received2(new Received3(null)));
        received.handleRequest(request);
    }

}
