package com.github.tomdican.program.designpattern.Creation.builder;

public class CarBuilder implements Builder {
    private Car car;

    @Override
    public void build() {
        car = new Car();
    }

    @Override
    public Object getBuilded() {
        return car;
    }
}
