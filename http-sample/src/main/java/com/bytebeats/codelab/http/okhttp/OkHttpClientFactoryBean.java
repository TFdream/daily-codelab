package com.bytebeats.codelab.http.okhttp;

import com.bytebeats.codelab.http.util.StringUtils;
import okhttp3.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-04 22:18
 */
public class OkHttpClientFactoryBean implements FactoryBean, DisposableBean {
    private int connectTimeout;
    private int readTimeout;
    private int writeTimeout;

    /**http proxy config**/
    private String host;
    private int port;
    private String username;
    private String password;


    /**OkHttpClient instance**/
    private OkHttpClient client;

    @Override
    public Object getObject() throws Exception {

        ConnectionPool pool = new ConnectionPool(5, 10, TimeUnit.SECONDS);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
                .connectionPool(pool);

        if(StringUtils.isNotBlank(host) && port>0){
            Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(host, port));
            builder.proxy(proxy);
        }

        if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
            Authenticator proxyAuthenticator = new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    String credential = Credentials.basic(username, password);
                    return response.request().newBuilder()
                            .header("Proxy-Authorization", credential)
                            .build();
                }
            };
            builder.proxyAuthenticator(proxyAuthenticator);
        }

        client = builder.build();
        return client;
    }

    @Override
    public Class<?> getObjectType() {
        return OkHttpClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void destroy() throws Exception {
        if(client!=null){
            client.connectionPool().evictAll();
            client.dispatcher().executorService().shutdown();
        }
        client = null;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
