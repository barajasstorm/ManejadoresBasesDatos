/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Cliente;
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
public class ClientesController extends Cliente {
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
    
    public ClientesController() throws SQLException {
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }
    }
    

    public boolean agregarCliente(String nombre, String apellidoPaterno, String apellidoMaterno, String rfc, String telefono) throws SQLException {

        //search for product
        int encontrado = buscarCliente(nombre);

        //insert product into database
        if (encontrado != -1) {
            System.out.print("Cliente no registrado");
            return true;
        } else {
            //Insert product into database after validation
            preparedStatement = postgresConnection.prepareStatement("INSERT INTO clientes (nombre,apellidoPaterno,apellidoMaterno,rfc,telefono) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidoPaterno);
            preparedStatement.setString(3, apellidoMaterno);
            preparedStatement.setString(4, rfc);
            preparedStatement.setString(5, telefono);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("INSERT INTO clientes (nombre,apellidoPaterno,apellidoMaterno,rfc,telefono) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidoPaterno);
            preparedStatement.setString(3, apellidoMaterno);
            preparedStatement.setString(4, rfc);
            preparedStatement.setString(5, telefono);
            mysqlBegin.executeUpdate();
            mysqlCount = preparedStatement.executeUpdate();
            
            if(postgresCount == 0 || mysqlCount == 0) {
                System.out.println("Transaction failed.");
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

    public void borrarCliente(String nombreCliente) throws SQLException {
        //search for product
        int encontrado = buscarCliente(nombreCliente);

        //delete if found
        if (encontrado > 0) {
            
            preparedStatement = postgresConnection.prepareStatement("DELETE FROM clientes WHERE pk_clienteid = ?");
            preparedStatement.setInt(1, encontrado);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("DELETE FROM clientes WHERE pk_clienteid = ?");
            preparedStatement.setInt(1, encontrado);
            mysqlBegin.executeUpdate();
            mysqlCount = preparedStatement.executeUpdate();
            
            if(postgresCount == 0 || mysqlCount == 0) {
                System.out.println("Transaction failed.");
                postgresRollback.executeUpdate();
                mysqlRollback.executeUpdate();
            } else {
                postgresCommit.executeUpdate();
                mysqlCommit.executeUpdate();
                System.out.println("Transaction was successful.");
            }
        }
    }

    public int buscarCliente(String nombreCliente) throws SQLException {
        String selectSQL = "SELECT * FROM clientes";
        statement = postgresConnection.createStatement();
        resultSet = statement.executeQuery(selectSQL);

        while (resultSet.next()) {
            if (resultSet.getString("nombre").equals(nombreCliente)) {
                this.nombre = resultSet.getString("nombre");
                this.apellidoPaterno = resultSet.getString("apellidopaterno");
                this.apellidoMaterno = resultSet.getString("apellidomaterno");
                this.rfc = resultSet.getString("rfc");
                this.telefono = resultSet.getString("telefono");
                return resultSet.getInt("pk_clienteid");
            }
        }
        return -1;
    }

    public void modificarCliente(String nombre, String apellidoPaterno, String apellidoMaterno, String rfc, String telefono) throws SQLException {
        int encontrado = buscarCliente(nombre);

        //delete if found
        if (encontrado > 0) {

            preparedStatement = postgresConnection.prepareStatement("UPDATE clientes SET nombre = ?, apellidopaterno = ?, apellidomaterno = ?, rfc = ?, telefono = ? WHERE pk_clienteid = ?");
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidoPaterno);
            preparedStatement.setString(3, apellidoMaterno);
            preparedStatement.setString(4, rfc);
            preparedStatement.setString(5, telefono);
            preparedStatement.setInt(6, encontrado);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("UPDATE clientes SET nombre = ?, apellidopaterno = ?, apellidomaterno = ?, rfc = ?, telefono = ? WHERE pk_clienteid = ?");
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidoPaterno);
            preparedStatement.setString(3, apellidoMaterno);
            preparedStatement.setString(4, rfc);
            preparedStatement.setString(5, telefono);
            preparedStatement.setInt(6, encontrado);
            mysqlBegin.executeUpdate();
            mysqlCount = preparedStatement.executeUpdate();
            
            if(postgresCount == 0 || mysqlCount == 0) {
                System.out.println("Transaction failed.");
                postgresRollback.executeUpdate();
                mysqlRollback.executeUpdate();
            } else {
                postgresCommit.executeUpdate();
                mysqlCommit.executeUpdate();
                System.out.println("Transaction was successful.");
            }

        }
    }

    public DefaultTableModel todosClientesDisplay() throws SQLException {

        DefaultTableModel model = new DefaultTableModel(new String[]{"Nombre", "Apellido Paterno", "Apellido Materno", "RFC", "Telefono"}, 0);
        String sql = "SELECT * FROM clientes";

        statement = postgresConnection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String nombre = resultSet.getString("nombre");
            String apellidoPaterno = resultSet.getString("apellidopaterno");
            String apellidoMaterno = resultSet.getString("apellidomaterno");
            String rfc = resultSet.getString("rfc");
            String telefono = resultSet.getString("telefono");
            model.addRow(new Object[]{nombre, apellidoPaterno, apellidoMaterno, rfc, telefono});
        }
        return model;
    }

}