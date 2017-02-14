package com.bytebeats.code.yaml;

import com.bytebeats.code.yaml.model.Configuration;
import com.bytebeats.code.yaml.model.Connection;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/**
 * Hello world!
 */
public class SnakeYAMLTest {

    public static void main(String[] args) {

        testParse();

        //testDump();
    }

    public static void testParse(){

        Yaml yaml = new Yaml();
        try {
            InputStream in = SnakeYAMLTest.class.getResourceAsStream("/sample.yml");
            Configuration config = yaml.loadAs(in, Configuration.class );
            System.out.println(config);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void testDump() {
        Configuration config = new Configuration();
        config.setReleased(new Date());
        config.setVersion("1.0");

        Connection connection = new Connection();
        connection.setDriverClass("com.mysql.jdbc.Driver");
        connection.setUrl("jdbc:mysql://localhost:3306/db");
        connection.setUsername("root");
        connection.setPassword("root");
        connection.setPoolSize(10);
        config.setConnection(connection);

        config.setProtocols(Arrays.asList("http", "https"));
        config.setDevelopers(new HashMap<String, String>());

        Yaml yaml = new Yaml();
        String output = yaml.dump(config);
        System.out.println(output);
    }
}
