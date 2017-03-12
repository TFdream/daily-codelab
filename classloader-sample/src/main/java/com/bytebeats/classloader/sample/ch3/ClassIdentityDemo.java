package com.bytebeats.classloader.sample.ch3;

import java.lang.reflect.Method;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-12 17:28
 */
public class ClassIdentityDemo {

    public static void main(String[] args) {

        new ClassIdentityDemo().testClassIdentity();
    }

    public void testClassIdentity() {
        String classDataRootPath = "F:\\github\\daily-codelab\\classloader-sample\\target\\classes";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
        String className = "com.bytebeats.classloader.sample.ch3.Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
