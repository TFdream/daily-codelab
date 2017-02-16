package com.bytebeats.annotation.sample.factory;

import com.bytebeats.code.annotation.processor.Factory;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2016-12-28 15:59
 */
@Factory(id = "Q5", type = Car.class)
public class AudiQ5 implements Car {

    @Override
    public double getPrice() {
        return 38.6d;
    }

    @Override
    public void run() {
        System.out.println("Audi Q5 running...");
    }
}
