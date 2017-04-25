package com.bytebeats.concurrent.api;

import java.util.Random;
import java.util.concurrent.*;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-04-24 23:13
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        int parties = 5;
        ExecutorService pool = Executors.newFixedThreadPool(parties);

        final CyclicBarrier barrier = new CyclicBarrier(parties);

        for(int i=0; i<parties; i++){

            Runnable task = new Runnable() {
                @Override
                public void run() {

                    try {
                        int time = new Random().nextInt(5000);
                        TimeUnit.MILLISECONDS.sleep(time);

                        System.out.println("thread "+Thread.currentThread().getName()+" 到达集合点");
                        barrier.await();    //到达集合点, 数量加1
                        System.out.println("thread "+Thread.currentThread().getName()+" 通过集合点继续往后执行");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            };

            pool.execute(task);
        }

        pool.shutdown();
    }

}
