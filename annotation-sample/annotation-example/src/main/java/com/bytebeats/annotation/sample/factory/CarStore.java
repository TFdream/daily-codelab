package com.bytebeats.annotation.sample.factory;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2016-12-28 16:10
 */
public class CarStore {

    public Car order(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name of the meal is null!");
        }

        if ("A4L".equals(name)) {
            return new AudiA4L();
        } else if ("C200L".equals(name)) {
            return new BenzC200L();
        } else if ("X5".equals(name)) {
            return new BMWX5();
        } else if ("Q5".equals(name)) {
            return new AudiQ5();
        } else if ("Accord".equals(name)) {
            return new HondaAccord();
        } else if ("Camry".equals(name)) {
            return new ToyotaCamry();
        }

        throw new IllegalArgumentException("Unknown car '" + name + "'");
    }
}
