package com.bytebeats.classloader.sample.ch3;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-12 17:29
 */
public class Sample {
    private Sample instance;

    public void setSample(Object instance) {
        this.instance = (Sample) instance;
    }
}
