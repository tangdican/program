package com.github.tomdican.program.designpattern.Behave.templatemethod;

import com.github.tomdican.program.Util;

public class HitRunMethod extends StealingMethodTemplate {

    @Override
    protected String pickTarget() {
        return "old woman";
    }

    @Override
    protected void confuseTarget(String target) {
        Util.println("Approach the target from behind.");
    }

    @Override
    protected void stealTheItem(String target) {
        Util.println("Grab the handbag and run away fast!");
    }
}
