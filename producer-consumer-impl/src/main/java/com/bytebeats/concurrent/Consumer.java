package com.bytebeats.concurrent;

import com.bytebeats.concurrent.queue.IBlockingQueue;
import com.bytebeats.concurrent.util.Constant;

import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-26 16:16
 */
public class Consumer implements Runnable {
    private IBlockingQueue<String> queue;

    public Consumer(IBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        while (true) {
            String data = null;
            try {
                data = queue.take();
                System.out.println("Consumer "+Thread.currentThread().getName()+" consume:"+data);
                if (Constant.ENDING_SYMBOL.equals(data)) {
                    break;
                }
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Consumer over");
    }
}
