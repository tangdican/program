package com.github.tomdican.program.designpattern.Creation.builder;

public class Car {
    private String color;
    Car(Builder builder) {
        this.color = builder.color;
    }

    static class Builder {
        private String color;
        Builder color(String color){
            this.color = color;
            return this;
        }
        Car build() {
            return new Car(this);
        }
    }

}
