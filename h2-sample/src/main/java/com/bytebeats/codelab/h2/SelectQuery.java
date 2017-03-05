package com.bytebeats.codelab.h2;

import java.sql.*;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-05 14:57
 */
public class SelectQuery {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try{
            Class.forName("org.h2.Driver");
            conn = DriverManager. getConnection("jdbc:h2:~/test", "sa", "sa");

            stmt = conn.prepareStatement("SELECT id, name, password, age, birthday FROM t_user WHERE id=?");
            stmt.setLong(1, 1);

            result = stmt.executeQuery();
            while(result.next()){
                System.out.println(result.getInt("id")+" | "+
                        result.getString("name")+" | "+
                        result.getString("password")+" | "+
                        result.getShort("age")+" | "+
                        result.getTimestamp("birthday"));
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
