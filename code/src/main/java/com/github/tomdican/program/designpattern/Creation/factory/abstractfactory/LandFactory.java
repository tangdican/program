package com.github.tomdican.program.designpattern.Creation.factory.abstractfactory;

import com.github.tomdican.program.designpattern.Creation.factory.Bike;
import com.github.tomdican.program.designpattern.Creation.factory.Car;
import com.github.tomdican.program.designpattern.Creation.factory.Vehicle;
import com.github.tomdican.program.designpattern.Creation.factory.VehicleType;

public class LandFactory extends AbstractFactory {

    @Override
    public void factorySay(String factoryName, String vehicleName) {
        System.out.println(" landFactory "+ factoryName + " createVehicle " + vehicleName);
    }

    @Override
    public Vehicle createVehicle(VehicleType type) {
        factoryName = "abstractFactory";
        return selectVehicle(type);
    }

    @Override
    public Vehicle selectVehicle(VehicleType type) {

        if (type.equals(VehicleType.Bike)) {
            return new Bike();
        }
        if (type.equals(VehicleType.Car)) {
            return new Car();
        }
        else return null;

    }
}
