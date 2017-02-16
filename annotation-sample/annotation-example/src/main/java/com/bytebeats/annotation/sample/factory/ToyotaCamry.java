package com.bytebeats.annotation.sample.factory;

import com.bytebeats.code.annotation.processor.Factory;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2016-12-28 16:02
 */
@Factory(id = "Camry", type = Car.class)
public class ToyotaCamry implements Car {

    @Override
    public double getPrice() {
        return 18.6d;
    }

    @Override
    public void run() {
        System.out.println("Toyota Camry running...");
    }
}
