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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanba
 */
public class Usuario extends Postgres {
    //Instance Variables
    public String nombre;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public String username;
    public String password;
    public int pk_usuarioID;
    
    Postgres postgres = new Postgres();
    Connection connection = postgres.connect();
    ResultSet resultSet = null;
    Statement statement = null;

    public Usuario() {
    }

    public Usuario(String nombre, String apellidoPaterno, String apellidoMaterno, String username, String password, int pk_usuarioID) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.username = username;
        this.password = password;
        this.pk_usuarioID = pk_usuarioID;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPk_usuarioID() {
        return pk_usuarioID;
    }

    public void setPk_usuarioID(int pk_usuarioID) {
        this.pk_usuarioID = pk_usuarioID;
    }
    
    public void loadUserDetails(int pk_usuarioID) {
        try {
            String selectSQL = "SELECT * FROM usuarios WHERE pk_usuarioid = " + pk_usuarioID;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            
            while (resultSet.next()) {
                this.pk_usuarioID = resultSet.getInt("pk_usuarioid");
                this.username = resultSet.getString("username");
                this.password = resultSet.getString("password");
                this.nombre = resultSet.getString("nombre");
                this.apellidoPaterno = resultSet.getString("apellidopaterno");
                this.apellidoMaterno = resultSet.getString("apellidomaterno");  
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}