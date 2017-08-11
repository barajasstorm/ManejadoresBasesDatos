/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author gerardomartinez
 */
public class UsuarioController extends Usuario {
    Postgres postgres = new Postgres();
    Connection connection = postgres.connect();
    ResultSet resultSet = null;
    Statement statement = null;
    
    public boolean agregarUsuario(String username, String password, String nombre, String apellidoPaterno, String apellidoMaterno) throws SQLException {

        //search for product
        int encontrado = buscarUsuario(username);

        //insert product into database
        if (encontrado != -1) {
            System.out.print("Usuario no registrado");
            return true;
        } else {
            //Insert product into database after validation
            String insertSQL = "INSERT INTO usuarios (username, password, nombre, apellidopaterno, apellidomaterno) VALUES ('" + username + "','" + password + "','" + nombre + "','" + apellidoPaterno + "','" + apellidoMaterno + "');";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
            return false;
        }
    }

    public void borrarUsuario(String usuarioid) throws SQLException {
            System.out.print(usuarioid);
            String insertSQL = "DELETE FROM usuarios WHERE pk_usuarioid = " + usuarioid;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
    }

    public int buscarUsuario(String username) throws SQLException {
        String selectSQL = "SELECT * FROM usuarios";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(selectSQL);

        while (resultSet.next()) {
            if (resultSet.getString("username").equals(username)) {
                this.username = resultSet.getString("nombre");
                this.apellidoPaterno = resultSet.getString("apellidopaterno");
                this.apellidoMaterno = resultSet.getString("apellidomaterno");
                this.password = resultSet.getString("password");
                this.nombre = resultSet.getString("nombre");
                this.pk_usuarioID = resultSet.getInt("pk_usuarioid");
                return resultSet.getInt("pk_usuarioid");
            }
        }
        return -1;
    }
    
    
    

    public void modificarUsuario(int usuarioid, String username, String password, String nombre, String apellidoPaterno, String apellidoMaterno) throws SQLException {
            String insertSQL = "UPDATE usuarios SET username = '" + username + "', password= '" + password + "', nombre = '" + nombre + "', apellidopaterno = '" + apellidoPaterno + "', apellidomaterno = '" + apellidoMaterno + "' WHERE pk_usuarioid = " + usuarioid;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
    }
 

    public DefaultTableModel todosUsuariosDisplay() throws SQLException {
        Postgres postgres = new Postgres();
        Connection connection = postgres.connect();

        DefaultTableModel model = new DefaultTableModel(new String[]{"Username", "Nombre", "Apellido Paterno", "Apellido Materno"}, 0);
        String sql = "SELECT * FROM usuarios";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String nombre = resultSet.getString("nombre");
            String apellidoPaterno = resultSet.getString("apellidopaterno");
            String apellidoMaterno = resultSet.getString("apellidomaterno");
            model.addRow(new Object[]{username, nombre, apellidoPaterno, apellidoMaterno});
        }
        return model;
    }  
}
