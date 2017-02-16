package com.bytebeats.codelab.annotation.sample.factory;

import com.bytebeats.codelab.annotation.sample.factory.CarFactory;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2016-12-28 16:15
 */
public class FactoryApp {

    public static void main(String[] args) {

        CarStore store = new CarStore();
        Car car = store.order("A4L");
        car.run();

        CarFactory factory = new CarFactory();
        car = factory.create("Q5");
        car.run();
    }
}
