package com.bytebeats.concurrent.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过Lock和Condition实现阻塞队列
 *
 * @author Ricky Fung
 * @create 2017-03-26 17:08
 */
public class ConditionBlockingQueue<T> implements IBlockingQueue<T> {
    private final Object[] items;
    int putptr, takeptr, count;

    private final Lock lock = new ReentrantLock();
    private final Condition notFull  = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public ConditionBlockingQueue(){
        this(10);
    }
    public ConditionBlockingQueue(int queueSize) {
        if(queueSize<1){
            throw new IllegalArgumentException("queueSize must be positive number");
        }
        items = new Object[queueSize];
    }

    @Override
    public void put(T data) throws InterruptedException {

        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[putptr] = data;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T take() throws InterruptedException {

        lock.lock();
        try {
            while (count == 0) {
                notEmpty.wait();
            }
            T data = (T) items[takeptr];
            if (++takeptr == items.length) {
                takeptr = 0;
            }
            --count;
            notFull.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }
}
