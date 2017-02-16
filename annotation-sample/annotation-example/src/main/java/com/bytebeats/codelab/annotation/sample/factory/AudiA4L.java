package com.bytebeats.codelab.annotation.sample.factory;

import com.bytebeats.codelab.annotation.Factory;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2016-12-28 15:58
 */
@Factory(id = "A4L", type = Car.class)
public class AudiA4L implements Car {

    @Override
    public double getPrice() {
        return 28.6d;
    }

    @Override
    public void run() {
        System.out.println("Audi A4L running...");
    }
}
