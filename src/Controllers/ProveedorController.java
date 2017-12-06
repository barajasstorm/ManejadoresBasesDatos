/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Proveedor;
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
public class ProveedorController extends Proveedor {
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
    
    public ProveedorController() throws SQLException {
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }
    }
    
    public boolean agregarProveedor(String nombre, String apellidopaterno, String apellidomaterno, String rfc, String telefono) throws SQLException {

        //search for product
        int encontrado = buscarProveedor(nombre);

        //insert product into database
        if (encontrado != -1) {
            System.out.print("Proveedor no registrado");
            return true;
        } else {
            //Insert product into database after validation         
            preparedStatement = postgresConnection.prepareStatement("INSERT INTO proveedores (nombre, apellidopaterno, apellidomaterno, rfc, telefono) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidopaterno);
            preparedStatement.setString(3, apellidomaterno);
            preparedStatement.setString(4, rfc);
            preparedStatement.setString(5, telefono);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("INSERT INTO proveedores (nombre, apellidopaterno, apellidomaterno, rfc, telefono) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidopaterno);
            preparedStatement.setString(3, apellidomaterno);
            preparedStatement.setString(4, rfc);
            preparedStatement.setString(5, telefono);
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

    public void borrarProveedor(String nombreProveedor) throws SQLException {
        //search for product
        int encontrado = buscarProveedor(nombreProveedor);

        //delete if found
        if (encontrado > 0) {
            
            preparedStatement = postgresConnection.prepareStatement("DELETE FROM proveedores WHERE pk_proveedorid = ?");
            preparedStatement.setInt(1, encontrado);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("DELETE FROM proveedores WHERE pk_proveedorid = ?");
            preparedStatement.setInt(1, encontrado);
            mysqlBegin.executeUpdate();
            mysqlCount = preparedStatement.executeUpdate();
            
            if(postgresCount == 0 || mysqlCount == 0) {
                JOptionPane.showMessageDialog(null, "Transaccion fallo. Error en bases de datos.");
                postgresRollback.executeUpdate();
                mysqlRollback.executeUpdate();
            } else {
                postgresCommit.executeUpdate();
                mysqlCommit.executeUpdate();
                
            }  
            
        }
    }

    public int buscarProveedor(String nombreProveedor) throws SQLException {
        String selectSQL = "SELECT * FROM proveedores";
        statement = postgresConnection.createStatement();
        resultSet = statement.executeQuery(selectSQL);

        while (resultSet.next()) {
            if (resultSet.getString("nombre").equals(nombreProveedor)) {
                this.nombre = resultSet.getString("nombre");
                this.apellidoPaterno = resultSet.getString("apellidopaterno");
                this.apellidoMaterno = resultSet.getString("apellidomaterno");
                this.rfc = resultSet.getString("rfc");
                this.telefono = resultSet.getString("telefono");
                return resultSet.getInt("pk_proveedorid");
            }
        }
        return -1;
    }

    public void modificarProveedor(String nombre, String apellidopaterno, String apellidomaterno, String rfc, String telefono) throws SQLException {
        int encontrado = buscarProveedor(nombre);

        //delete if found
        if (encontrado > 0) {       
            
            preparedStatement = postgresConnection.prepareStatement("UPDATE proveedores SET nombre = ?, apellidopaterno= ?, apellidomaterno = ?, rfc = ?, telefono = ? WHERE pk_proveedorid = ?");
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidopaterno);
            preparedStatement.setString(3, apellidomaterno);
            preparedStatement.setString(4, rfc);
            preparedStatement.setString(5, telefono);
            preparedStatement.setInt(6, encontrado);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("UPDATE proveedores SET nombre = ?, apellidopaterno= ?, apellidomaterno = ?, rfc = ?, telefono = ? WHERE pk_proveedorid = ?");
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidopaterno);
            preparedStatement.setString(3, apellidomaterno);
            preparedStatement.setString(4, rfc);
            preparedStatement.setString(5, telefono);
            preparedStatement.setInt(6, encontrado);
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
    }

    public DefaultTableModel todosProveedoresDisplay() throws SQLException {

        DefaultTableModel model = new DefaultTableModel(new String[]{"Nombre", "Apellido Paterno", "Apellido Materno", "RFC", "Telefono"}, 0);
        String sql = "SELECT * FROM proveedores";

        statement = postgresConnection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String nombre = resultSet.getString("nombre");
            String apellidopaterno = resultSet.getString("apellidopaterno");
            String apellidomaterno = resultSet.getString("apellidomaterno");
            String rfc = resultSet.getString("rfc");
            String telefono = resultSet.getString("telefono");
            model.addRow(new Object[]{nombre, apellidopaterno, apellidomaterno, rfc, telefono});
        }
        return model;
    }  
}
