package com.bytebeats.codelab.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-05 15:44
 */
public class Demo {

    public void openMemoryDB(){

        Connection conn = null;
        try{
            Class.forName("org.h2.Driver");
            conn = DriverManager. getConnection("jdbc:h2:mem:test_mem", "sa", "sa");

            // do your business

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void openFileDB(){

        Connection conn = null;
        try{
            Class.forName("org.h2.Driver");
            conn = DriverManager. getConnection("jdbc:h2:~/test", "sa", "sa");

            // do your business

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
