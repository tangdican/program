package com.github.tomdican.program.designpattern.Creation.builder;

public class User {

    public static void main(String[] args) {
        Builder builder = new CarBuilder();
        Car car = (Car) builder.getBuilded();
    }

}
