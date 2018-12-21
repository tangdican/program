package com.github.tomdican.program.designpattern.Creation.prototype;

public class Car implements Clone{
    private String name;

    void setName(String name) {
        this.name = name;
    }
    @Override
    public Object clone() {
        Car car = new Car();
        car.setName(name);
        return car;
    }
}
