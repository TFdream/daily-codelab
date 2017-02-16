package com.bytebeats.codelab.yaml;

import com.bytebeats.codelab.yaml.model.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-14 16:05
 */
public class JacksonYAMLTest {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            Configuration configuration = mapper.readValue(JacksonYAMLTest.class.getResourceAsStream("/sample.yml"), Configuration.class);
            System.out.println(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
