package com.bytebeats.annotation.sample.factory;

import com.bytebeats.code.annotation.processor.Factory;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2016-12-28 16:09
 */
@Factory(id = "X5", type = Car.class)
public class BMWX5 implements Car {

    @Override
    public double getPrice() {
        return 55.6d;
    }

    @Override
    public void run() {
        System.out.println("BMW X5 running...");
    }
}
