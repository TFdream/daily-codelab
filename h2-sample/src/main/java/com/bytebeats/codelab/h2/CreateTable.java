package com.bytebeats.codelab.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-05 14:43
 */
public class CreateTable {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("org.h2.Driver");
            conn = DriverManager. getConnection("jdbc:h2:~/test", "sa", "sa");

            stmt = conn.createStatement();

            int result = stmt.executeUpdate("CREATE TABLE t_user (" +
                    "id BIGINT NOT NULL, " +
                    "name VARCHAR(20) NOT NULL," +
                    "password VARCHAR(32) NOT NULL, " +
                    "age SMALLINT, " +
                    "birthday TIMESTAMP," +
                    "PRIMARY KEY (id)" +
                    ");");

            System.out.println("result:"+result);
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
