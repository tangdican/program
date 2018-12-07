package com.github.tomdican.program.designpattern.Creation.factory.abstractfactory;

import com.github.tomdican.program.designpattern.Creation.factory.Vehicle;
import com.github.tomdican.program.designpattern.Creation.factory.VehicleType;

public abstract class AbstractFactory {

    public String factoryName = "";

    public abstract void factorySay(String factoryName, String vehicleName);
    public abstract Vehicle createVehicle(VehicleType type);
    public abstract Vehicle selectVehicle(VehicleType type);

}
