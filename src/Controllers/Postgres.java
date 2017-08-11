/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author uidd7535
 */
public class Postgres {

    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    public static final String DATABASE_URL = "jdbc:postgresql://localhost/final";
    private final String USER = "postgres";
    private final String PASSWORD = "password";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public boolean login(String username, String password) {
        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from usuarios");

            while (resultSet.next()) {
                if (resultSet.getString("username").equals(username) && resultSet.getString("password").equals(password)) {
                    return true;
                }
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Err: " + ex.getMessage());
        }
        return false;
    }

}
