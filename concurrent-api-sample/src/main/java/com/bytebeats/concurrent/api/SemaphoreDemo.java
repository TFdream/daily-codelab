package com.bytebeats.concurrent.api;

import java.util.concurrent.Semaphore;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-04-24 23:22
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(4);

        while (true) {
            try {
                semaphore.acquire();
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

}
