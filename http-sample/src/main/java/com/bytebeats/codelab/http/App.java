package com.bytebeats.codelab.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        OkHttpClient okHttpClient = (OkHttpClient) context.getBean("okHttpClient");

        new App().run(okHttpClient);
    }

    public void run(OkHttpClient client) throws Exception {
        Request request = new Request.Builder()
                .url("https://www.baidu.com/")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());
    }
}
