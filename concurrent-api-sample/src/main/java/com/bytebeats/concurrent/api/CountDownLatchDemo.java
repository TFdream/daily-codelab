package com.bytebeats.concurrent.api;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 任务计时
 *
 * @author Ricky Fung
 * @create 2017-04-24 23:01
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatchDemo demo = new CountDownLatchDemo();
        long time = demo.timeTasks(5, new Runnable() {
            @Override
            public void run() {

                Random random = new Random();
                int time = random.nextInt(1000);
                try {
                    System.out.println("thread "+Thread.currentThread().getName()+ " sleep "+time);
                    TimeUnit.MILLISECONDS.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("耗时:"+time);
    }

    public long timeTasks(int threadNum, final Runnable task) throws InterruptedException {

        final CountDownLatch startGate = new CountDownLatch(1);   //开始
        final CountDownLatch endGate = new CountDownLatch(threadNum);   //结束

        for(int i=0; i<threadNum; i++){

            Thread thread = new Thread(){
                @Override
                public void run() {

                    try {
                        startGate.await();
                        System.out.println("thread "+Thread.currentThread().getName()+ " start");
                        try {
                            task.run();
                            System.out.println("thread "+Thread.currentThread().getName()+ " end");
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
        long start = System.nanoTime();
        startGate.countDown();

        endGate.await();

        long end = System.nanoTime();
        return end - start;
    }
}
