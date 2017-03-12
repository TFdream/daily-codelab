package com.bytebeats.classloader.sample.ch5;

import java.io.File;
import java.net.MalformedURLException;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-12 17:44
 */
public class URLClassloaderDemo {

    public static void main(String[] args) throws MalformedURLException {

        ExtensionClassLoader loader = new ExtensionClassLoader(new File(""));
        try {
            loader.loadClass("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
