package com.bytebeats.classloader.sample.ch4;

/**
 * 下面将通过一个网络类加载器来说明如何通过类加载器来实现组件的动态更新。
 * 即基本的场景是：Java 字节代码（.class）文件存放在服务器上，客户端通过网络的方式获取字节代码并执行。
 * 当有版本更新的时候，只需要替换掉服务器上保存的文件即可。通过类加载器可以比较简单的实现这种需求。
 *
 * @author Ricky Fung
 * @create 2017-03-12 17:39
 */
public class CalculatorTest {

    public static void main(String[] args) {

        String url = "http://localhost:8080/ClassloaderTest/classes";
        NetworkClassLoader ncl = new NetworkClassLoader(url);
        String basicClassName = "com.example.CalculatorBasic";
        String advancedClassName = "com.example.CalculatorAdvanced";
        try {
            Class<?> clazz = ncl.loadClass(basicClassName);
            ICalculator calculator = (ICalculator) clazz.newInstance();
            System.out.println(calculator.getVersion());

            clazz = ncl.loadClass(advancedClassName);
            calculator = (ICalculator) clazz.newInstance();
            System.out.println(calculator.getVersion());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
