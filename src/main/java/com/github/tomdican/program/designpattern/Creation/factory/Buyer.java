package com.github.tomdican.program.designpattern.Creation.factory;

import com.github.tomdican.program.designpattern.Creation.factory.abstractfactory.AbstractFactory;
import com.github.tomdican.program.designpattern.Creation.factory.abstractfactory.LandFactory;
import com.github.tomdican.program.designpattern.Creation.factory.abstractfactory.SeaFactory;
import com.github.tomdican.program.designpattern.Creation.factory.factorymethod.BikeFactory;
import com.github.tomdican.program.designpattern.Creation.factory.factorymethod.CarFactory;
import com.github.tomdican.program.designpattern.Creation.factory.factorymethod.FactoryMethod;
import com.github.tomdican.program.designpattern.Creation.factory.staticfactory.VehicleFactory;

public class Buyer {

    public static void main(String[] args) {
        // buy bike, static factory vehicle
        VehicleFactory vehicleFactory = new VehicleFactory();
        Vehicle vehicle = vehicleFactory.createVehicle(VehicleType.Bike);
        vehicle.say();

        Vehicle vehicle2 = vehicleFactory.createVehicle(VehicleType.Car);
        vehicle2.say();


        // buy bike from the factory, the factory method createVehicle the wanted factory
        FactoryMethod factory = new BikeFactory();
        Vehicle vehicle3 = factory.createVehicle(VehicleType.Bike);
        factory.factorySay(factory.factoryName, VehicleType.Bike.toString());
        vehicle3.say();

        FactoryMethod factory2 = new CarFactory();
        Vehicle vehicle4 = factory2.createVehicle(VehicleType.Car);
        factory2.factorySay(factory2.factoryName, VehicleType.Car.toString());
        vehicle4.say();


        // buy bike from the factory, which is created by abstract factory
        AbstractFactory abstractFactory = new LandFactory();
        Vehicle vehicle5 = abstractFactory.createVehicle(VehicleType.Bike);
        Vehicle vehicle6 = abstractFactory.createVehicle(VehicleType.Car);
        abstractFactory.factorySay(abstractFactory.factoryName, VehicleType.Bike.toString());
        abstractFactory.factorySay(abstractFactory.factoryName, VehicleType.Car.toString());
        vehicle5.say();
        vehicle6.say();

        AbstractFactory abstractFactory2 = new SeaFactory();
        Vehicle vehicle7 = abstractFactory2.createVehicle(VehicleType.CARRIER);
        Vehicle vehicle8 = abstractFactory2.createVehicle(VehicleType.SUBMARINE);
        abstractFactory.factorySay(abstractFactory.factoryName, VehicleType.CARRIER.toString());
        abstractFactory.factorySay(abstractFactory.factoryName, VehicleType.SUBMARINE.toString());
        vehicle7.say();
        vehicle8.say();

    }
}
