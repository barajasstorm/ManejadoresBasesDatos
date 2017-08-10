/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.Postgres;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

/**
 *
 * @author juanba
 */
public class Usuario extends Postgres {
    //Instance Variables
    private int pk_UsuarioID;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String userName;
    private String password;
    
    
    //Constructors
    public Usuario() {
    }

    public Usuario(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    public Usuario(String nombre, String apellidoPaterno, String apellidoMaterno, String userName, String password, int pk_usuarioid) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.userName = userName;
        this.password = password;
        this.pk_UsuarioID = pk_usuarioid;
    }

    
    //Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPk_usuarioid() {
        return pk_UsuarioID;
    }

    public void setPk_usuarioid(int pk_usuarioid) {
        this.pk_UsuarioID = pk_UsuarioID;
    }
    
    public void login() throws ClassNotFoundException {
        
        Statement statement = null; // query statement
        Connection connection = null; // manages connection

        try {
            Class.forName(JDBC_DRIVER); // load database driver class

            // establish connection to database
            connection
                    = DriverManager.getConnection(DATABASE_URL, "postgres", "password");

            // create Statement for querying database
            statement = connection.createStatement();

            // query database
            ResultSet resultSet = statement.executeQuery(
                    "SELECT pk_usuarioid, nombre FROM usuarios");

            // process query results
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            System.out.println("Users of Database:");

            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.printf("%-8s\t", metaData.getColumnName(i));
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    System.out.printf("%-8s\t", resultSet.getObject(i));
                }
                System.out.println();
            } // end while
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } // end catch
        catch (ClassNotFoundException classNotFound) {
            classNotFound.printStackTrace();
            System.exit(1);
        } // end catch
        finally // ensure statement and connection are closed properly
        {
            try {
                statement.close();
                connection.close();
            } // end try                                               
            catch (Exception exception) {
                exception.printStackTrace();
                System.exit(1);
            } // end catch                                             
        } // end finally
        
    }

    
}
