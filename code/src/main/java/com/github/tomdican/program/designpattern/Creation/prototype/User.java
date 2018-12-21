package com.github.tomdican.program.designpattern.Creation.prototype;

public class User{

    public static void main(String[] args) {
        Car car = new Car();
        car.setName("red");
        Car car2 = (Car) car.clone();
    }

}
