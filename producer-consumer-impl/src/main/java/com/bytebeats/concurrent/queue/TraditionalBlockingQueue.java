package com.bytebeats.concurrent.queue;

import com.bytebeats.concurrent.IBlockingQueue;
import java.util.LinkedList;

/**
 * 使用Object.wait()/notifyAll()实现的阻塞队列
 *
 * @author Ricky Fung
 * @create 2017-03-26 16:18
 */
public class TraditionalBlockingQueue<T> implements IBlockingQueue<T> {
    private int queueSize;
    private final LinkedList<T> list = new LinkedList<T>();
    private final Object lock = new Object();

    public TraditionalBlockingQueue(){
        this(10);
    }
    public TraditionalBlockingQueue(int queueSize) {
        if(queueSize<1){
            throw new IllegalArgumentException("queueSize must be positive number");
        }
        this.queueSize = queueSize;
    }

    @Override
    public void put(T data) throws InterruptedException {

        synchronized (lock){
            while(list.size()>=queueSize) {
                lock.wait();
            }
            list.addLast(data);
            lock.notifyAll();
        }
    }

    @Override
    public T take() throws InterruptedException {

        synchronized(lock){
            while(list.size()<=0) {
                lock.wait();
            }
            T data = list.removeFirst();
            lock.notifyAll();
            return data;
        }
    }
}
