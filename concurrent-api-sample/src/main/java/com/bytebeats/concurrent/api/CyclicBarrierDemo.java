package com.bytebeats.concurrent.api;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-04-24 23:13
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {


    }

    public void solve(int threadNum, final Runnable task){

        final CyclicBarrier barrier = new CyclicBarrier(threadNum);

        for(int i=0; i<threadNum; i++){

            Thread t = new Thread(){
                @Override
                public void run() {

                    try {
                        task.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            barrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }

    }
}
