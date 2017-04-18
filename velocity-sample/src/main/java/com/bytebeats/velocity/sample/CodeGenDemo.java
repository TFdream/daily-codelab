package com.bytebeats.velocity.sample;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-03-09 16:36
 */
public class CodeGenDemo {

    public static void main(String[] args) throws IOException {

        new CodeGenDemo().genCode();
    }

    public void genCode() throws IOException {

        Properties props = new Properties();
        props.load(this.getClass().getResourceAsStream("/vm.properties"));
        Velocity.init(props);

        VelocityContext context = new VelocityContext();
        context.put("name", "Velocity");

        Template template = Velocity.getTemplate("/vm/hello.vm");

        StringWriter sw = new StringWriter();
        template.merge( context, sw);

        System.out.println(""+sw.toString());
    }

    public void testSingletonModel() throws IOException {
        Properties props = new Properties();
        props.load(this.getClass().getResourceAsStream("/vm.properties"));
        Velocity.init(props);

        VelocityContext context = new VelocityContext();

        Template template = Velocity.getTemplate("hello.vm");
    }

    public void testSeparateInstance() throws IOException {

        Properties props = new Properties();
        props.load(this.getClass().getResourceAsStream("/vm.properties"));
        VelocityEngine ve = new VelocityEngine(props);

        ve.init();
    }
}
