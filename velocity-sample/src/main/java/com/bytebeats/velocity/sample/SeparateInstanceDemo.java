package com.bytebeats.velocity.sample;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import java.io.StringWriter;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-03-10 13:48
 */
public class SeparateInstanceDemo {

    public static void main(String[] args) {

        //1. create a new instance of the engine
        VelocityEngine ve = new VelocityEngine();

        //2. configure the engine
        ve.setProperty(VelocityEngine.RESOURCE_LOADER, "classpath");

        //3. initialize the engine
        ve.init();

        VelocityContext context = new VelocityContext();
        context.put("name", "Velocity");

        Template template = ve.getTemplate("foo.vm");
        StringWriter sw = new StringWriter();
        template.merge(context, sw);

        System.out.println("content:"+sw.toString());
    }
}
