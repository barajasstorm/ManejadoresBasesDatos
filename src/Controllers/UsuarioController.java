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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author gerardomartinez
 */
public class UsuarioController extends Usuario {
    
    Connector connector = new Connector();
    Connection postgresConnection = connector.getPostgresConnection();
    Connection mysqlConnection = connector.getMysqlConnection();
    PreparedStatement postgresBegin;
    PreparedStatement postgresCommit;
    PreparedStatement postgresRollback;
    PreparedStatement mysqlBegin;
    PreparedStatement mysqlCommit;
    PreparedStatement mysqlRollback;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    boolean mysql = false;
    boolean postgres = false;
    int mysqlCount = 0;
    int postgresCount = 0;
    
    public UsuarioController() throws SQLException {
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }
    }
    
    public boolean agregarUsuario(String username, String password, String nombre, String apellidoPaterno, String apellidoMaterno) throws SQLException {

        //search for product
        int encontrado = buscarUsuario(username);

        //insert product into database
        if (encontrado != -1) {
            System.out.print("Usuario no registrado");
            return true;
        } else {
            //Insert product into database after validation                   
            preparedStatement = postgresConnection.prepareStatement("INSERT INTO usuarios (username, password, nombre, apellidopaterno, apellidomaterno) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nombre);
            preparedStatement.setString(4, apellidoPaterno);
            preparedStatement.setString(5, apellidoMaterno);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("INSERT INTO usuarios (username, password, nombre, apellidopaterno, apellidomaterno) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nombre);
            preparedStatement.setString(4, apellidoPaterno);
            preparedStatement.setString(5, apellidoMaterno);
            mysqlBegin.executeUpdate();
            mysqlCount = preparedStatement.executeUpdate();
            
            if(postgresCount == 0 || mysqlCount == 0) {
                JOptionPane.showMessageDialog(null, "Transaccion fallo. Error en bases de datos.");
                postgresRollback.executeUpdate();
                mysqlRollback.executeUpdate();
            } else {
                postgresCommit.executeUpdate();
                mysqlCommit.executeUpdate();
                System.out.println("Transaction was successful.");
            }         
            return false;
        }
    }

    public void borrarUsuario(String usuarioid) throws SQLException {
  
            preparedStatement = postgresConnection.prepareStatement("DELETE FROM usuarios WHERE pk_usuarioid = ?");
            preparedStatement.setString(1, usuarioid);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = postgresConnection.prepareStatement("DELETE FROM usuarios WHERE pk_usuarioid = ?");
            preparedStatement.setString(1, usuarioid);
            mysqlBegin.executeUpdate();
            mysqlCount = preparedStatement.executeUpdate();
            
            if(postgresCount == 0 || mysqlCount == 0) {
                JOptionPane.showMessageDialog(null, "Transaccion fallo. Error en bases de datos.");
                postgresRollback.executeUpdate();
                mysqlRollback.executeUpdate();
            } else {
                postgresCommit.executeUpdate();
                mysqlCommit.executeUpdate();
                System.out.println("Transaction was successful.");
            }         
            
    }

    public int buscarUsuario(String username) throws SQLException {
        String selectSQL = "SELECT * FROM usuarios";
        statement = postgresConnection.createStatement();
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

            preparedStatement = postgresConnection.prepareStatement("UPDATE usuarios SET username = ?, password= ?, nombre = ?, apellidopaterno = ?, apellidomaterno = ? WHERE pk_usuarioid = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nombre);
            preparedStatement.setString(4, apellidoPaterno);
            preparedStatement.setString(5, apellidoMaterno);
            preparedStatement.setInt(6, usuarioid);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("UPDATE usuarios SET username = ?, password= ?, nombre = ?, apellidopaterno = ?, apellidomaterno = ? WHERE pk_usuarioid = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nombre);
            preparedStatement.setString(4, apellidoPaterno);
            preparedStatement.setString(5, apellidoMaterno);
            preparedStatement.setInt(6, usuarioid);
            mysqlBegin.executeUpdate();
            mysqlCount = preparedStatement.executeUpdate();
            
            if(postgresCount == 0 || mysqlCount == 0) {
                JOptionPane.showMessageDialog(null, "Transaccion fallo. Error en bases de datos.");
                postgresRollback.executeUpdate();
                mysqlRollback.executeUpdate();
            } else {
                postgresCommit.executeUpdate();
                mysqlCommit.executeUpdate();
                System.out.println("Transaction was successful.");
            }         
            
    }
 

    public DefaultTableModel todosUsuariosDisplay() throws SQLException {

        DefaultTableModel model = new DefaultTableModel(new String[]{"Username", "Nombre", "Apellido Paterno", "Apellido Materno"}, 0);
        String sql = "SELECT * FROM usuarios";

        statement = postgresConnection.createStatement();
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
