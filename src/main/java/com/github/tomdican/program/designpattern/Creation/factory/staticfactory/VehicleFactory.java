package com.github.tomdican.program.designpattern.Creation.factory.staticfactory;


import com.github.tomdican.program.designpattern.Creation.factory.Bike;
import com.github.tomdican.program.designpattern.Creation.factory.Car;
import com.github.tomdican.program.designpattern.Creation.factory.Vehicle;
import com.github.tomdican.program.designpattern.Creation.factory.VehicleType;

public class VehicleFactory {

    private static String factoryName = "vehiclefactory";

    public void factorySay(String vehicleName) {
        System.out.println(factoryName + " createVehicle " + vehicleName);

    }

	public Vehicle createVehicle(VehicleType type){

        factorySay(type.toString());

		if (type.equals(VehicleType.Bike)) {
            return new Bike();
        }
		if (type.equals(VehicleType.Car)) {
            return new Car();
        }
		else return null;
	}

}
