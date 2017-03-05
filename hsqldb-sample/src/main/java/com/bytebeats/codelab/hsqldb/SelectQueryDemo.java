package com.bytebeats.codelab.hsqldb;

import java.sql.*;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-05 13:13
 */
public class SelectQueryDemo {

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet result = null;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:file:/D:db/hsqldb/testdb", "SA", "");
            stmt = con.createStatement();
            result = stmt.executeQuery(
                    "SELECT id, name, password, age, birthday FROM t_user");

            while(result.next()){
                System.out.println(result.getInt("id")+" | "+
                        result.getString("name")+" | "+
                        result.getString("password")+" | "+
                        result.getShort("age")+" | "+
                        result.getTimestamp("birthday"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
