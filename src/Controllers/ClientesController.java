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
    Postgres postgres = new Postgres();
    Connection connection = postgres.connect();
    ResultSet resultSet = null;
    Statement statement = null;

    public boolean agregarCliente(String nombre, String apellidoPaterno, String apellidoMaterno, String rfc, String telefono) throws SQLException {

        //search for product
        int encontrado = buscarCliente(nombre);

        //insert product into database
        if (encontrado != -1) {
            System.out.print("Cliente no registrado");
            return true;
        } else {
            //Insert product into database after validation
            String insertSQL = "INSERT INTO clientes (nombre,apellidoPaterno,apellidoMaterno,rfc,telefono) VALUES ('" + nombre + "','" + apellidoPaterno + "','" + apellidoMaterno + "','" + rfc + "','" + telefono + "');";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
            return false;
        }
    }

    public void borrarCliente(String nombreCliente) throws SQLException {
        //search for product
        int encontrado = buscarCliente(nombreCliente);

        //delete if found
        if (encontrado > 0) {
            String insertSQL = "DELETE FROM clientes WHERE pk_clienteid = " + encontrado;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
        }
    }

    public int buscarCliente(String nombreCliente) throws SQLException {
        String selectSQL = "SELECT * FROM clientes";
        statement = connection.createStatement();
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
            System.out.print(encontrado);
            String insertSQL = "UPDATE clientes SET nombre = '" + nombre + "', apellidopaterno = '" + apellidoPaterno + "', apellidomaterno = '" + apellidoMaterno + "', rfc = '" + rfc + "', telefono = '" + telefono + "' WHERE pk_clienteid = " + encontrado;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
        }
    }

    public DefaultTableModel todosClientesDisplay() throws SQLException {
        Postgres postgres = new Postgres();
        Connection connection = postgres.connect();

        DefaultTableModel model = new DefaultTableModel(new String[]{"Nombre", "Apellido Paterno", "Apellido Materno", "RFC", "Telefono"}, 0);
        String sql = "SELECT * FROM clientes";

        statement = connection.createStatement();
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