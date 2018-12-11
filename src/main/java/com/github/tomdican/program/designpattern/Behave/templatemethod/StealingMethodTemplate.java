package com.github.tomdican.program.designpattern.Behave.templatemethod;

import com.github.tomdican.program.Util;

public abstract class StealingMethodTemplate {
    protected abstract String pickTarget();

    protected abstract void confuseTarget(String target);

    protected abstract void stealTheItem(String target);

    /**
     * Steal
     */
    public void steal() {
        String target = pickTarget();
        Util.println("The target has been chosen as " + target);
        confuseTarget(target);
        stealTheItem(target);
    }
}
