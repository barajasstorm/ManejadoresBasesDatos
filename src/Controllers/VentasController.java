/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Producto;
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
public class VentasController extends Producto {
        Postgres postgres = new Postgres();
        Connection connection = postgres.connect();
        ResultSet resultSet = null;
        Statement statement = null;
    
    public DefaultTableModel todosProductosVentasDisplay() throws SQLException {
        Postgres postgres = new Postgres();
        Connection connection = postgres.connect();

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Precio Compra", "Precio Venta", "Existencias", "Stock Minimo"}, 0);
        String sql = "SELECT * FROM productos";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("pk_productoid");
            String nombre = resultSet.getString("nombre");
            String preciocompra = String.valueOf(resultSet.getDouble("preciocompra"));
            String precioventa = String.valueOf(resultSet.getDouble("precioventa"));
            String existencias = String.valueOf(resultSet.getInt("existencias"));
            String stockminimo = String.valueOf(resultSet.getInt("stockminimo"));
            model.addRow(new Object[]{id, nombre, preciocompra, precioventa, existencias, stockminimo});
        }
        return model;
    }
}

