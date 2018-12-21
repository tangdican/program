package com.github.tomdican.program.designpattern.Behave.chainofresponsibility;

public class Received2 extends RequestHandler {

    public Received2(RequestHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request req) {
        if ("2".equals(req.getName())) {
            System.out.println("request received 2 successfully");
            return;
        }
        System.out.println("request received 2 pass");

        super.handleRequest(req);
    }
}
