package com.github.tomdican.program.designpattern.Behave.observer;

public class Station {

    public static void main(String[] args) {

        WebDisplay webDisplay = new WebDisplay();
        Observed weatherServer = new WeatherServer();
        weatherServer.registerObserver(webDisplay);
        webDisplay.update(1,2,3);


    }


}
