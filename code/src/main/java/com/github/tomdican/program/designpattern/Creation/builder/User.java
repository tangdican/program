package com.github.tomdican.program.designpattern.Creation.builder;

/**
 * example: stringbuilder
 */
public class User {

    public static void main(String[] args) {
        Car car = new Car.Builder().color("red").build();
    }

}
