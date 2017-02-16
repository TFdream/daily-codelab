package com.bytebeats.codelab.guava.concurrent;

import com.bytebeats.codelab.guava.domain.User;
import com.google.common.util.concurrent.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-09 17:28
 */
public class ListenableFutureDemo {

    public static void main(String[] args) {

        new ListenableFutureDemo().test();
    }

    private void test(){

        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture<User> login = service.submit(new Callable<User>() {
            public User call() throws Exception{
                return getUserByName();
            }
        });

        Futures.addCallback(login, new FutureCallback<User>() {
            // we want this handler to run immediately after we push the big red button!
            public void onSuccess(User user) {
                System.out.println("Thread:"+Thread.currentThread().getName()+" onSuccess");
                System.out.println("登录成功..."+user);
            }
            public void onFailure(Throwable throwable) {
                System.out.println("Thread:"+Thread.currentThread().getName()+" onFailure");
                throwable.printStackTrace();
                System.out.println("登录失败..."); // escaped the explosion!
            }
        });

        service.shutdown();
    }

    private User getUserByName() throws InterruptedException {

        System.out.println("Thread:"+Thread.currentThread().getName()+" do work");

        User user = new User();
        user.setId(15L);
        user.setUsername("ricky");
        user.setPassword("12345");

        TimeUnit.SECONDS.sleep(3);

        if(System.currentTimeMillis()%2==0){
            throw new IllegalArgumentException("非法用户");
        }
        return user;
    }
}
