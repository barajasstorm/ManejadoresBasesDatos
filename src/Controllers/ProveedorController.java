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
    
    public boolean agregarProveedor(String nombre, String rfc, int telefono, String ciudad, String estado) throws SQLException {

        //search for product
        int encontrado = buscarProveedor(nombre);

        //insert product into database
        if (encontrado != -1) {
            System.out.print("Proveedor no registrado");
            return true;
        } else {
            //Insert product into database after validation
            String insertSQL = "INSERT INTO proveedores (nombre,rfc,telefono,ciudad,estado) VALUES ('" + nombre + "','" + rfc + "','" + telefono + "','" + ciudad + "','" + estado + "');";
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
            String insertSQL = "DELETE FROM proveedores WHERE pk_productoid = " + encontrado;
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
                this.rfc = resultSet.getString("RFC");
                this.telefono = resultSet.getInt("telefono");
                this.ciudad = resultSet.getString("Ciudad");
                this.estado = resultSet.getString("Estado");
                return resultSet.getInt("pk_proveedorid");
            }
        }
        return -1;
    }

    public void modificarProveedor(String nombre, String rfc, int telefono, String ciudad, String estado) throws SQLException {
        int encontrado = buscarProveedor(nombre);

        //delete if found
        if (encontrado > 0) {
            System.out.print(encontrado);
            String insertSQL = "UPDATE proveedores SET nombre = '" + nombre + "', rfc= '" + rfc + "', telefono = '" + telefono + "', ciudad = '" + ciudad + "', estado = '" + estado + "' WHERE pk_proveedorid = " + encontrado;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
        }
    }

    public DefaultTableModel todosProveedoresDisplay() throws SQLException {
        Postgres postgres = new Postgres();
        Connection connection = postgres.connect();

        DefaultTableModel model = new DefaultTableModel(new String[]{"Nombre", "RFC", "Telefono", "Ciudad", "Estado"}, 0);
        String sql = "SELECT * FROM proveedores";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String nombre = resultSet.getString("nombre");
            String rfc = resultSet.getString("rfc");
            String telefono = String.valueOf(resultSet.getInt("telefono"));
            String ciudad = resultSet.getString("ciudad");
            String estado = resultSet.getString("ciudad");
            model.addRow(new Object[]{nombre, rfc, telefono, ciudad, estado});
        }
        return model;
    }

    public boolean agregar(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
