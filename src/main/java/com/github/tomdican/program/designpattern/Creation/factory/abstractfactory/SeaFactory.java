package com.github.tomdican.program.designpattern.Creation.factory.abstractfactory;

import com.github.tomdican.program.designpattern.Creation.factory.Carrier;
import com.github.tomdican.program.designpattern.Creation.factory.Submarine;
import com.github.tomdican.program.designpattern.Creation.factory.Vehicle;
import com.github.tomdican.program.designpattern.Creation.factory.VehicleType;

public class SeaFactory extends AbstractFactory {

    @Override
    public void factorySay(String factoryName, String vehicleName) {
        System.out.println(" seaFactory "+ factoryName + " createVehicle " + vehicleName);

    }

    @Override
    public Vehicle createVehicle(VehicleType type) {
        factoryName = "abstractFactory";
        return selectVehicle(type);
    }

    @Override
    public Vehicle selectVehicle(VehicleType type) {

        if (type.equals(VehicleType.CARRIER)) {
            return new Carrier();
        }
        if (type.equals(VehicleType.SUBMARINE)) {
            return new Submarine();
        }
        else return null;

    }
}
