package com.bytebeats.codelab.javassist;

import javassist.*;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-16 18:07
 */
public class JavassistDemo2 {

    public static void main(String[] args) {

        try {
            testAddMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testAddMethod() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.importPackage("");

        CtClass point = pool.get("Point");
        CtMethod m = CtNewMethod.make(
                "public int xmove(int dx) { x += dx; }",
                point);
        point.addMethod(m);

    }

    public static void testAddField() throws Exception {
        CtClass point = ClassPool.getDefault().get("Point");
        CtField x = new CtField(CtClass.intType, "x", point);
        point.addField(x);

        CtField y = new CtField(CtClass.intType, "y", point);
        point.addField(y, "0");    // initial value is 0.

        CtField f = CtField.make("public int z = 0;", point);
        point.addField(f);
    }

    public static void testImport() throws Exception {

        ClassPool pool = ClassPool.getDefault();
        pool.importPackage("java.awt");
        CtClass cc = pool.makeClass("Test");
        CtField f = CtField.make("public Point p;", cc);
        cc.addField(f);
    }
}
