package com.bytebeats.velocity.sample;

import java.io.StringWriter;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.MethodInvocationException;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-03-10 13:44
 */
public class SingletonModelDemo {

    public static void main(String[] args) {

        Velocity.init();

        VelocityContext context = new VelocityContext();
        context.put("name", "Velocity");

        Template template = null;
        try {
            template = Velocity.getTemplate("mytemplate.vm");
        } catch( ResourceNotFoundException e ) {
            // couldn't find the template
        } catch( ParseErrorException pee ) {
            // syntax error: problem parsing the template
        } catch( MethodInvocationException mie ) {
            // something invoked in the template
            // threw an exception
        } catch( Exception e ) {

        }

        StringWriter sw = new StringWriter();
        template.merge(context, sw);

        System.out.println("content:"+sw.toString());
    }
}
