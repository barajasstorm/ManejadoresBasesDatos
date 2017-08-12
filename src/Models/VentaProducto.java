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
public class VentaProducto {
    
    Postgres postgres = new Postgres();
    Connection connection = postgres.connect();
    ResultSet resultSet = null;
    Statement statement = null;
    
    private int pk_ventaproductoid;
    private int fk_ventaid;
    private int fk_productoid;
    private int cantidad;
    private double importeProducto;

    public VentaProducto() {
    }

    public VentaProducto(int pk_ventaproductoid, int fk_ventaid, int fk_productoid, int cantidad, double importeProducto) {
        this.pk_ventaproductoid = pk_ventaproductoid;
        this.fk_ventaid = fk_ventaid;
        this.fk_productoid = fk_productoid;
        this.cantidad = cantidad;
        this.importeProducto = importeProducto;
    }

    public VentaProducto(int fk_ventaid, int fk_productoid, int cantidad, double importeProducto) {
        this.fk_ventaid = fk_ventaid;
        this.fk_productoid = fk_productoid;
        this.cantidad = cantidad;
        this.importeProducto = importeProducto;
    }

    public int getPk_ventaproductoid() {
        return pk_ventaproductoid;
    }

    public void setPk_ventaproductoid(int pk_ventaproductoid) {
        this.pk_ventaproductoid = pk_ventaproductoid;
    }

    public int getFk_ventaid() {
        return fk_ventaid;
    }

    public void setFk_ventaid(int fk_ventaid) {
        this.fk_ventaid = fk_ventaid;
    }

    public int getFk_productoid() {
        return fk_productoid;
    }

    public void setFk_productoid(int fk_productoid) {
        this.fk_productoid = fk_productoid;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporteProducto() {
        return importeProducto;
    }

    public void setImporteProducto(double importeProducto) {
        this.importeProducto = importeProducto;
    }
    
    public void saveToDatabase() {
        try {
            String insertSQL = "insert into ventasproductos (fk_ventaid,fk_productoid,cantidad,importeproducto) values ('" + this.fk_ventaid + "','" + this.fk_productoid + "','" + this.cantidad + "','" + this.importeProducto  + "');";

            //Insert product into database after validation           
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
