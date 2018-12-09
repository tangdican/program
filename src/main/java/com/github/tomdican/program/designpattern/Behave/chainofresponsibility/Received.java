package com.github.tomdican.program.designpattern.Behave.chainofresponsibility;

public class Received extends RequestHandler {

    public Received(RequestHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request req) {
        if ("1".equals(req.getName())) {
            System.out.println("request received 1 successfully");
            return;
        }

        System.out.println("request received 1 pass");
        super.handleRequest(req);
    }
}
