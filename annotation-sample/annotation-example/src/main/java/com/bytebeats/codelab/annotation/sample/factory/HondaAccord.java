package com.bytebeats.codelab.annotation.sample.factory;

import com.bytebeats.codelab.annotation.Factory;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2016-12-28 16:02
 */
@Factory(id = "Accord", type = Car.class)
public class HondaAccord implements Car {

    @Override
    public double getPrice() {
        return 17.8f;
    }

    @Override
    public void run() {
        System.out.println("Honda Accord running...");
    }
}
