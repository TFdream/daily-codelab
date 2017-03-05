package com.bytebeats.codelab.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ricky Fung
 * @create 2017-03-05 11:59
 */
public class CreateTableDemo {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver" );
            conn = DriverManager.getConnection("jdbc:hsqldb:file:/D:db/hsqldb/testdb", "SA", "");
            stmt = conn.createStatement();

            String sql = "CREATE TABLE t_user (" +
                    "id BIGINT NOT NULL, " +
                    "name VARCHAR(20) NOT NULL," +
                    "password VARCHAR(32) NOT NULL, " +
                    "age SMALLINT, " +
                    "birthday TIMESTAMP," +
                    "PRIMARY KEY (id)" +
                    ");";

            System.out.println(sql);

            int result = stmt.executeUpdate(sql);

            System.out.println("result:"+result);
        } catch (Exception e) {
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
