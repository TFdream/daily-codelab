package com.bytebeats.concurrent.queue;

/**
 * 阻塞队列接口
 *
 * @author Ricky Fung
 * @create 2017-03-26 17:28
 */
public interface IBlockingQueue<T> {

    void put(T data) throws InterruptedException;

    T take() throws InterruptedException;
}
