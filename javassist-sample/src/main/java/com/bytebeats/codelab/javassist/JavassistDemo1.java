package com.bytebeats.codelab.javassist;

import com.bytebeats.codelab.javassist.model.Shape;
import javassist.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-16 15:22
 */
public class JavassistDemo1 {

    public static void main(String[] args) {

        try {
            testAddMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testAddMethod() throws NotFoundException, IOException, CannotCompileException, IllegalAccessException {
        CtClass cc = ClassPool.getDefault().get("com.bytebeats.codelab.javassist.model.Shape");

        CtField x = new CtField(CtClass.intType, "x", cc);
        cc.addField(x);

        CtField z = CtField.make("public int z = 0;", cc);
        cc.addField(z);

        CtMethod xmove = CtNewMethod.make(
                "public int xmove(int dx) { x += dx; return x; }",
                cc);
        cc.addMethod(xmove);

        CtMethod move = new CtMethod(CtClass.intType, "move",
                new CtClass[] { CtClass.intType }, cc);
        move.setBody("{ x += $1; return x; }");
        cc.setModifiers(cc.getModifiers() & ~Modifier.ABSTRACT);

        cc.addMethod(move);

        Class clazz = cc.toClass();

        printMethods(clazz);
    }

    public static void testFour() throws NotFoundException, IOException, CannotCompileException, IllegalAccessException, InstantiationException {

        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("com.bytebeats.codelab.Rectangle");
        CtClass parent = pool.get("com.bytebeats.codelab.javassist.model.Shape");
        cc.setSuperclass(parent);

        CtField field = new CtField(pool.get("java.lang.String"), "x", cc);
        field.setModifiers(Modifier.PRIVATE);
        //add field
        cc.addField(field, CtField.Initializer.constant(""));

        CtMethod method = parent.getDeclaredMethod("draw");
        //方法插桩
        CtMethod proxyMethod = new CtMethod(method, cc, null);
        proxyMethod.setModifiers(method.getModifiers());
        proxyMethod.insertBefore("System.out.println(\"before execute...\");");
        proxyMethod.insertAfter("System.out.println(\"after execute...\");");

        //add method
        cc.addMethod(proxyMethod);

        Class c = cc.toClass();
        Shape h = (Shape)c.newInstance();
        h.draw();
    }

    public static void testThree() throws NotFoundException, IOException, CannotCompileException, IllegalAccessException, InstantiationException {

        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("com.bytebeats.codelab.Rectangle");
        cc.setSuperclass(pool.get("com.bytebeats.codelab.javassist.model.Shape"));

        //add method
        CtClass[] parameters = new CtClass[]{pool.get("java.lang.String")};
        CtMethod method = new CtMethod(pool.get("java.lang.String"), "say", parameters, cc);
        method.setModifiers(Modifier.PUBLIC);
        StringBuilder body = new StringBuilder().append("{").append("String str = ").append("\"Hello, world\";").append("return str;").append("}");
        method.setBody(body.toString());
        cc.addMethod(method);

        Class c = cc.toClass();
        Shape h = (Shape)c.newInstance();
        h.draw();

        printMethods(c);
    }

    public static void testTwo() throws NotFoundException, IOException, CannotCompileException, IllegalAccessException, InstantiationException {

        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("com.bytebeats.codelab.Rectangle");
        cc.setSuperclass(pool.get("com.bytebeats.codelab.javassist.model.Shape"));

        Class c = cc.toClass();
        Shape h = (Shape)c.newInstance();
        h.draw();

    }

    public static void testOne() throws NotFoundException, IOException, CannotCompileException {

        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("com.bytebeats.codelab.Rectangle");
        cc.setSuperclass(pool.get("com.bytebeats.codelab.javassist.model.Shape"));
        cc.writeFile(new File("D:/javassist_demo").getPath());
    }

    private static void printMethods(Class type) {

        /**print method**/
        Method[] methods = type.getMethods();
        for (Method m: methods){
            if(m.getDeclaringClass()==Object.class){
                continue;
            }
            System.out.println(type.getName()+"\t"+m.getName());
        }
    }
}
