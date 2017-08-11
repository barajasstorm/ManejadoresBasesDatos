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
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author gerardomartinez
 */
public class ProveedorController extends Proveedor {
    Postgres postgres = new Postgres();
    Connection connection = postgres.connect();
    ResultSet resultSet = null;
    Statement statement = null;
    
    public boolean agregarProveedor(String nombre, String apellidopaterno, String apellidomaterno, String rfc, String telefono) throws SQLException {

        //search for product
        int encontrado = buscarProveedor(nombre);

        //insert product into database
        if (encontrado != -1) {
            System.out.print("Proveedor no registrado");
            return true;
        } else {
            //Insert product into database after validation
            String insertSQL = "INSERT INTO proveedores (nombre, apellidopaterno, apellidomaterno, rfc, telefono) VALUES ('" + nombre + "','" + apellidopaterno + "','" + apellidomaterno + "','" + rfc + "','" + telefono + "');";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
            return false;
        }
    }

    public void borrarProveedor(String nombreProveedor) throws SQLException {
        //search for product
        int encontrado = buscarProveedor(nombreProveedor);

        //delete if found
        if (encontrado > 0) {
            String insertSQL = "DELETE FROM proveedores WHERE pk_proveedorid = " + encontrado;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
        }
    }

    public int buscarProveedor(String nombreProveedor) throws SQLException {
        String selectSQL = "SELECT * FROM proveedores";
        statement = connection.createStatement();
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
            System.out.print(encontrado);
            String insertSQL = "UPDATE proveedores SET nombre = '" + nombre + "', apellidopaterno= '" + apellidopaterno + "', apellidomaterno = '" + apellidomaterno + "', rfc = '" + rfc + "', telefono = '" + telefono + "' WHERE pk_proveedorid = " + encontrado;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
        }
    }

    public DefaultTableModel todosProveedoresDisplay() throws SQLException {
        Postgres postgres = new Postgres();
        Connection connection = postgres.connect();

        DefaultTableModel model = new DefaultTableModel(new String[]{"Nombre", "Apellido Paterno", "Apellido Materno", "RFC", "Telefono"}, 0);
        String sql = "SELECT * FROM proveedores";

        statement = connection.createStatement();
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
