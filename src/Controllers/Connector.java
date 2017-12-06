/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author juanba
 */
public class Connector {

    public static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    public static final String POSTGRES_DATABASE_URL = "jdbc:postgresql://localhost/final";
    private final String POSTGRES_USER = "postgres";
    private final String POSTGRES_PASSWORD = "password";
    
    
    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String MYSQL_DATABASE_URL = "jdbc:mysql://127.0.0.1/final?autoReconnect=true&useSSL=false";
    private final String MYSQL_USER = "root";
    private final String MYSQL_PASSWORD = "password1";
    
    public boolean mysqlSuccess;
    public boolean postgresSuccess;
    
    
    private static Connector instance;
    private Connection postgresConnection = null;
    private Connection mysqlConnection = null;
    
    //Constructor creates connection to postgres and mysql database
    public Connector() {
        try {
            postgresConnection = DriverManager.getConnection(POSTGRES_DATABASE_URL, POSTGRES_USER, POSTGRES_PASSWORD); 
            mysqlConnection = DriverManager.getConnection(MYSQL_DATABASE_URL, MYSQL_USER, MYSQL_PASSWORD); 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Get instance of connection if none exist
    public static Connector getInstance(){
        if(instance == null){
            instance = new Connector();
        }
        return instance;
    }
    
    //Get postgres connection
    public Connection getPostgresConnection() {
        
        return postgresConnection;
        
    }
    
    //Get mysql connection
    public Connection getMysqlConnection() {
        
        return mysqlConnection;
        
    }

    

}
