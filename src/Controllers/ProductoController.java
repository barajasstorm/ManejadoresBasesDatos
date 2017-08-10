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
 * @author juanba
 */
public class ProductoController extends Producto {

    Postgres postgres = new Postgres();
    Connection connection = postgres.connect();
    ResultSet resultSet = null;
    Statement statement = null;

    public boolean agregarProducto(String nombre, double precioCompra, double precioVenta, int existencias, int stockMinimo) throws SQLException {

        //search for product
        int encontrado = buscarProducto(nombre);

        //insert product into database
        if (encontrado != -1) {
            System.out.print("Producto no insertado");
            return true;
        } else {
            //Insert product into database after validation
            String insertSQL = "insert into productos (nombre,preciocompra,precioventa,existencias,stockminimo,activo) values ('" + nombre + "','" + precioCompra + ",'" + precioVenta + "','" + existencias + "','" + stockMinimo + "',1);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
            return false;
        }
    }

    public void borrarProducto(String nombreProd) throws SQLException {
        //search for product
        int encontrado = buscarProducto(nombreProd);

        //delete if found
        if (encontrado > 0) {
            String insertSQL = "DELETE FROM productos WHERE pk_productoid = " + encontrado;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
        }
    }

    public int buscarProducto(String nombreProd) throws SQLException {
        String selectSQL = "SELECT * FROM productos";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(selectSQL);

        while (resultSet.next()) {
            if (resultSet.getString("nombre").equals(nombreProd)) {
                this.nombre = resultSet.getString("nombre");
                this.precioCompra = resultSet.getDouble("preciocompra");
                this.precioVenta = resultSet.getDouble("precioventa");
                this.existencias = resultSet.getInt("existencias");
                this.stockMin = resultSet.getInt("stockminimo");
                this.activo = resultSet.getInt("activo");
                return resultSet.getInt("pk_productoid");
            }
        }
        return -1;
    }

    public void modificarProducto(String nombre, double precioCompra, double precioVenta, int existencias, int stockMinimo) throws SQLException {
        int encontrado = buscarProducto(nombre);

        //delete if found
        if (encontrado > 0) {
            System.out.print(encontrado);
            String insertSQL = "UPDATE productos SET nombre = '" + nombre + "', preciocompra = '" + precioCompra + "', precioventa = '" + precioVenta + "', existencias = '" + existencias + "', stockminimo = '" + stockMinimo + "' WHERE pk_productoid = " + encontrado;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
        }
    }

    public DefaultTableModel todosProductosDisplay() throws SQLException {
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

    public DefaultTableModel todosProductosBajosDisplay() throws SQLException {
        Postgres postgres = new Postgres();
        Connection connection = postgres.connect();

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Precio Compra", "Precio Venta", "Existencias", "Stock Minimo"}, 0);
        String sql = "SELECT * FROM productos WHERE existencias < stockminimo";

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

    public DefaultTableModel todosProductosVentasDisplay() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
