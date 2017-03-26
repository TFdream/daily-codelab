package com.bytebeats.concurrent;

import com.bytebeats.concurrent.util.Constant;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-26 16:16
 */
public class Producer implements Runnable {
    private IBlockingQueue<String> queue;
    private int consumerNum;

    public Producer(IBlockingQueue<String> queue, int consumerNum) {
        this.queue = queue;
        this.consumerNum = consumerNum;
    }

    @Override
    public void run() {

        for(int i=0; i< 100; i++){
            try {
                queue.put("data_"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i=0; i<consumerNum; i++){   //结束符
            try {
                queue.put(Constant.ENDING_SYMBOL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Producer over");
    }
}
