package com.bytebeats.codelab.hsqldb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-05 11:59
 */
public class Demo {

    public static void main(String[] args) throws SQLException {

        new Demo().testMenDB();

        new Demo().testFileDB();
    }

    public void testMenDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");
        printMeta(conn);

        conn.close();
    }

    public void testFileDB() throws SQLException {
        //Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:/opt/db/testdb;ifexists=true", "SA", "");
        Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:/D:db/hsqldb/testdb", "SA", "");
        printMeta(conn);

        conn.close();
    }

    private void printMeta(Connection conn) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();

        System.out.println("databaseProductName:"+meta.getDatabaseProductName());
        System.out.println("databaseProductVersion:"+meta.getDatabaseProductVersion());
        System.out.println("url:"+meta.getURL());
        System.out.println("driverName:"+meta.getDriverName());
        System.out.println("userName:"+meta.getUserName());
        System.out.println("isReadOnly:"+meta.isReadOnly());
    }
}
