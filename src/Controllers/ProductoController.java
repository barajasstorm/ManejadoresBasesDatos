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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanba
 */
public class ProductoController extends Producto {

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

    public ProductoController() throws SQLException {     
        
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }
       
            
            
            
        
    }

    public boolean agregarProducto(String nombre, double precioCompra, double precioVenta, int existencias, int stockMinimo) throws SQLException {

        //search for product
        int encontrado = buscarProducto(nombre);
        //insert product into database
        if (encontrado != -1) {
            System.out.print("Producto no insertado");
            return true;
        } else {
            //Insert product into database after validation         
            preparedStatement = postgresConnection.prepareStatement("insert into productos (nombre,preciocompra,precioventa,existencias,stockminimo,activo) values (?,?,?,?,?,?);");
            preparedStatement.setString(1, nombre);
            preparedStatement.setDouble(2, precioCompra);
            preparedStatement.setDouble(3, precioVenta);
            preparedStatement.setInt(4, existencias);
            preparedStatement.setInt(5, stockMinimo);
            preparedStatement.setInt(6,1);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("insert into productos (nombre,preciocompra,precioventa,existencias,stockminimo,activo) values (?,?,?,?,?,?);");
            preparedStatement.setString(1, nombre);
            preparedStatement.setDouble(2, precioCompra);
            preparedStatement.setDouble(3, precioVenta);
            preparedStatement.setInt(4, existencias);
            preparedStatement.setInt(5, stockMinimo);
            preparedStatement.setInt(6,1);
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

    public void borrarProducto(String nombreProd) throws SQLException {
        //search for product
        int encontrado = buscarProducto(nombreProd);

        //delete if found
        if (encontrado > 0) {
            //String insertSQL = "DELETE FROM productos WHERE pk_productoid = " + encontrado;
            preparedStatement = postgresConnection.prepareStatement("DELETE FROM productos WHERE pk_productoid = ? ");
            preparedStatement.setInt(1, encontrado);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("DELETE FROM productos WHERE pk_productoid = ? ");
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

    public int buscarProducto(String nombreProd) throws SQLException {
        
        String selectSQL = "SELECT * FROM productos";
        statement = postgresConnection.createStatement();
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
            preparedStatement = postgresConnection.prepareStatement("UPDATE productos SET nombre = ?, preciocompra = ?, precioventa = ?, existencias = ?, stockminimo = ? WHERE pk_productoid = ?;");
            preparedStatement.setString(1, nombre);
            preparedStatement.setDouble(2, precioCompra);
            preparedStatement.setDouble(3, precioVenta);
            preparedStatement.setInt(4, existencias);
            preparedStatement.setInt(5, stockMinimo);
            preparedStatement.setInt(6, encontrado);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("UPDATE productos SET nombre = ?, preciocompra = ?, precioventa = ?, existencias = ?, stockminimo = ? WHERE pk_productoid = ?;");
            preparedStatement.setString(1, nombre);
            preparedStatement.setDouble(2, precioCompra);
            preparedStatement.setDouble(3, precioVenta);
            preparedStatement.setInt(4, existencias);
            preparedStatement.setInt(5, stockMinimo);
            preparedStatement.setInt(6, encontrado);
            mysqlBegin.executeUpdate();
            mysqlCount = preparedStatement.executeUpdate();
            
            if(postgresCount == 0) {
                System.out.println("postgres fallo");
            }
            
            if(mysqlCount == 0) {
                System.out.println("Mysql fallo");
            }
            
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
    
    public void modificarVentaProducto(String nombre, int existencias) throws SQLException {
        int encontrado = buscarProducto(nombre);

        //delete if found
        if (encontrado > 0) {
            
            preparedStatement = postgresConnection.prepareStatement("UPDATE productos SET existencias = ? WHERE pk_productoid = ?");
            preparedStatement.setInt(1, existencias);
            preparedStatement.setInt(2, encontrado);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("UPDATE productos SET existencias = ? WHERE pk_productoid = ?");
            preparedStatement.setInt(1, existencias);
            preparedStatement.setInt(2, encontrado);
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

    public DefaultTableModel todosProductosDisplay() throws SQLException {


        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Precio Compra", "Precio Venta", "Existencias", "Stock Minimo"}, 0);
        String sql = "SELECT * FROM productos";

        statement = postgresConnection.createStatement();
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


        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Precio Compra", "Precio Venta", "Existencias", "Stock Minimo"}, 0);
        String sql = "SELECT * FROM productos WHERE existencias < stockminimo";

        statement = postgresConnection.createStatement();
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
