package com.github.tomdican.program.designpattern.Creation.factory.factorymethod;

import com.github.tomdican.program.designpattern.Creation.factory.Vehicle;
import com.github.tomdican.program.designpattern.Creation.factory.VehicleType;

public abstract class FactoryMethod {

    public String factoryName = "";

    public void factorySay(String factoryName, String vehicleName) {
        System.out.println(factoryName + " createVehicle " + vehicleName);
    }
    public abstract Vehicle createVehicle(VehicleType type);

}
