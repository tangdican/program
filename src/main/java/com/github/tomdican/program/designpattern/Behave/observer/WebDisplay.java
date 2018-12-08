package com.github.tomdican.program.designpattern.Behave.observer;

public class WebDisplay implements Observer{

    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("Current conditions: " + temp
            + "F degrees and " + humidity + "% humidity ,pressure:" + pressure);
    }

}
