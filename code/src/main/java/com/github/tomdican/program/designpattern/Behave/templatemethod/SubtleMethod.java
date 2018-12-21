package com.github.tomdican.program.designpattern.Behave.templatemethod;

import com.github.tomdican.program.Util;

public class SubtleMethod extends StealingMethodTemplate {

    @Override
    protected String pickTarget() {
        return "shop keeper";
    }

    @Override
    protected void confuseTarget(String target) {
        Util.println("Approach the target with tears running and hug him");
    }

    @Override
    protected void stealTheItem(String target) {
        Util.println("Grab the handbag and run away fast!");
    }
}
