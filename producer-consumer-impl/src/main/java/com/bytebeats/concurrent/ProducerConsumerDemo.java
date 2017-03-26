package com.bytebeats.concurrent;

import com.bytebeats.concurrent.queue.ConditionBlockingQueue;
import com.bytebeats.concurrent.queue.TraditionalBlockingQueue;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-26 16:21
 */
public class ProducerConsumerDemo {

    public static void main(String[] args) {

        //new ProducerConsumerDemo().testRun(new TraditionalBlockingQueue<String>());
        new ProducerConsumerDemo().testRun(new ConditionBlockingQueue<String>());
    }

    public void testRun(IBlockingQueue<String> queue){

        Thread producer = new Thread(new Producer(queue, 2));
        producer.start();

        Thread consumer1 = new Thread(new Consumer(queue));
        consumer1.start();
        Thread consumer2 = new Thread(new Consumer(queue));
        consumer2.start();
    }
}
