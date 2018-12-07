package com.github.tomdican.program.designpattern.Creation.factory.factorymethod;

import com.github.tomdican.program.designpattern.Creation.factory.Bike;
import com.github.tomdican.program.designpattern.Creation.factory.Vehicle;
import com.github.tomdican.program.designpattern.Creation.factory.VehicleType;

public class BikeFactory extends FactoryMethod {


    @Override
	public Vehicle createVehicle(VehicleType type) {
        factoryName = "BikeFactory";
		return new Bike();
	}
}