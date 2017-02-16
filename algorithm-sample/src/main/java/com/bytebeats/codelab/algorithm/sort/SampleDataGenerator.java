package com.bytebeats.codelab.algorithm.sort;

import java.io.*;
import java.security.SecureRandom;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-15 14:14
 */
public class SampleDataGenerator {

    private File dir = new File("D:/sort/input");

    private void genSampleData(long count){

        SecureRandom random = new SecureRandom();

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dir, "input.txt")), "UTF-8"));
            for(long i=0;i<count; i++){
                bw.write(String.valueOf(random.nextInt(10000000)));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        new SampleDataGenerator().genSampleData(5000000L);
    }
}
