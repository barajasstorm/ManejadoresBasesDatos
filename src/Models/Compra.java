/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.Postgres;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanba
 */
public class Compra {
    
    Postgres postgres = new Postgres();
    Connection connection = postgres.connect();
    ResultSet resultSet = null;
    Statement statement = null;
    
    private int pk_compraID;
    private int fk_proveedorID;
    private int fk_productoID;
    private int cantidad;

    public Compra() {
    }

    public Compra(int fk_proveedorID, int fk_productoID, int cantidad) {
        this.fk_proveedorID = fk_proveedorID;
        this.fk_productoID = fk_productoID;
        this.cantidad = cantidad;
    }
    
    public Compra(int pk_compraID, int fk_proveedorID, int fk_productoID, int cantidad) {
        this.pk_compraID = pk_compraID;
        this.fk_proveedorID = fk_proveedorID;
        this.fk_productoID = fk_productoID;
        this.cantidad = cantidad;
    }

    public int getPk_compraID() {
        return pk_compraID;
    }

    public void setPk_compraID(int pk_compraID) {
        this.pk_compraID = pk_compraID;
    }

    public int getFk_proveedorID() {
        return fk_proveedorID;
    }

    public void setFk_proveedorID(int fk_proveedorID) {
        this.fk_proveedorID = fk_proveedorID;
    }

    public int getFk_productoID() {
        return fk_productoID;
    }

    public void setFk_productoID(int fk_productoID) {
        this.fk_productoID = fk_productoID;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public void saveToDatabase() {
        try {
            String insertSQL = "insert into compras (fk_proveedorid, fk_productoid, cantidad) values ('" + this.fk_proveedorID + "','" + this.fk_productoID + "','" + this.cantidad + "');";

            //Insert product into database after validation           
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
