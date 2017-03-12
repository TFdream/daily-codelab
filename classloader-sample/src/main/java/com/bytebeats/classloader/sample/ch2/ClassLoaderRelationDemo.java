package com.bytebeats.classloader.sample.ch2;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-12 17:22
 */
public class ClassLoaderRelationDemo {

    public static void main(String[] args) {

        ClassLoader cl = ClassLoaderRelationDemo.class.getClassLoader();
        while(cl!=null){
            System.out.println(cl);
            cl = cl.getParent();
        }
    }
}
