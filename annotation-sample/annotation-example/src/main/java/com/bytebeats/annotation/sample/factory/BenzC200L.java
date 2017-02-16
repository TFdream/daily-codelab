package com.bytebeats.annotation.sample.factory;

import com.bytebeats.code.annotation.processor.Factory;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2016-12-28 16:00
 */
@Factory(id = "C200L", type = Car.class)
public class BenzC200L implements Car {

    @Override
    public double getPrice() {
        return 29.8d;
    }

    @Override
    public void run() {
        System.out.println("Benz C200L running...");
    }
}
