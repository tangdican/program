package com.github.tomdican.program.designpattern.Behave.chainofresponsibility;

public class Received3 extends RequestHandler {

    public Received3(RequestHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request req) {
        if ("3".equals(req.getName())) {
            System.out.println("request received 3 successfully");
            return;
        }
        System.out.println("request received 3 pass");
        super.handleRequest(req);

    }
}
