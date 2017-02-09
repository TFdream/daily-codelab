package com.bytebeats.code.guava.concurrent;

import com.bytebeats.code.guava.domain.User;
import com.google.common.util.concurrent.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-09 17:28
 */
public class ListenableFutureDemo2 {

    public static void main(String[] args) {

        new ListenableFutureDemo2().test();
    }

    private void test(){

        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        int taskNum = 5;
        List<ListenableFuture<User>> queries = new ArrayList<>(taskNum);
        for(int i=0;i<taskNum;i++){
            final long id = i;
            queries.add(service.submit(new Callable<User>() {
                @Override
                public User call() throws Exception {

                    System.out.println("Thread:"+Thread.currentThread().getName()+" do task:"+id);
                    User user = new User();
                    user.setId(id);
                    user.setUsername("ricky_"+id);
                    user.setPassword("pwd_"+id);

                    TimeUnit.SECONDS.sleep(id);

                    System.out.println("Thread:"+Thread.currentThread().getName()+" do task:"+id+" over!");
                    return user;
                }
            }));
        }

        ListenableFuture<List<User>> successfulQueries = Futures.successfulAsList(queries);

        Futures.addCallback(successfulQueries, new FutureCallback<List>() {
            @Override
            public void onSuccess(List list) {
                System.out.println("Thread:"+Thread.currentThread().getName() +" onSuccess list:"+list);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Thread:"+Thread.currentThread().getName() +" onFailure");
            }
        });

        service.shutdown();
    }

}
