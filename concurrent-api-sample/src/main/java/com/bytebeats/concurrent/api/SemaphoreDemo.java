package com.bytebeats.concurrent.api;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-04-24 23:22
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        int num = 10;

        ExecutorService pool = Executors.newFixedThreadPool(num);
        // 只能5个线程同时访问
        final Semaphore semaphore = new Semaphore(5);
        // 模拟N个用户停车
        for (int i = 0; i < num; i++) {
            final int id = i+1;
            Runnable task = new Runnable() {
                @Override
                public void run() {

                    try {
                        semaphore.acquire();    // 获取许可
                        System.out.println("用户:" + id+" 获得许可开始停车啦! | "+Thread.currentThread().getName());
                        int time = new Random().nextInt(1000);
                        TimeUnit.MILLISECONDS.sleep(time);
                        System.out.println("当前可用许可数量: " + semaphore.availablePermits()+" | "+Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();//释放
                    }
                }
            };
            pool.execute(task);
        }

        pool.shutdown();
    }

}
