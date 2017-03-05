package com.bytebeats.codelab.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-05 13:08
 */
public class InsertQueryDemo {

    public static void main(String[] args) {

        Connection con = null;
        Statement stmt = null;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver" );
            con = DriverManager.getConnection("jdbc:hsqldb:file:/D:db/hsqldb/testdb", "SA", "");
            stmt = con.createStatement();
            int result = stmt.executeUpdate("INSERT INTO t_user VALUES (1,'ricky', '12345', 28, '1989-09-15 15:00:00');");
            System.out.println(result);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
