package com.bytebeats.codelab.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-05 14:57
 */
public class InsertQuery {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("org.h2.Driver");
            conn = DriverManager. getConnection("jdbc:h2:~/test", "sa", "sa");

            stmt = conn.createStatement();
            int update = stmt.executeUpdate("INSERT INTO t_user VALUES (1,'ricky', '12345', 28, '1989-09-15 15:00:00');");
            System.out.println(update);

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
