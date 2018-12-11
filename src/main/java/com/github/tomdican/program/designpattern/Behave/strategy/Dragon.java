package com.github.tomdican.program.designpattern.Behave.strategy;

public class Dragon {
    private Strategy strategy;

    public Dragon(Strategy strategy) {
        this.strategy = strategy;
    }

    public void changeStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void fly() {
        strategy.execute();
    }

    public static void main(String[] args) {
        UpStrategy upStrategy = new UpStrategy();
        Dragon dragon = new Dragon(upStrategy);
        dragon.fly();

        LineStrategy lineStrategy = new LineStrategy();
        dragon.changeStrategy(lineStrategy);
        dragon.fly();

        dragon.changeStrategy(new DownStrategy());
        dragon.fly();
    }
}
