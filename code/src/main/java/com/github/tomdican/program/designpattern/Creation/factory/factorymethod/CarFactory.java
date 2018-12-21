package com.github.tomdican.program.designpattern.Creation.factory.factorymethod;

import com.github.tomdican.program.designpattern.Creation.factory.Car;
import com.github.tomdican.program.designpattern.Creation.factory.Vehicle;
import com.github.tomdican.program.designpattern.Creation.factory.VehicleType;

public class CarFactory extends FactoryMethod {

    @Override
	public Vehicle createVehicle(VehicleType type){
        factoryName = "CarFactory";
        return new Car();
	}
}