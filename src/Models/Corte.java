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
public class Corte {
    
    Postgres postgres = new Postgres();
    Connection connection = postgres.connect();
    ResultSet resultSet = null;
    Statement statement = null;
    
    //Instances variables
    private int pk_corteID;
    private double ventasTotales;
    private double saldoAlCorte;
    private int cantidadTickets;
    private int productosVendidos;

    public Corte() {
    }
    
    public Corte(double ventasTotales, int cantidadTickets, int productosVendidos) {
        this.ventasTotales = ventasTotales;
        this.cantidadTickets = cantidadTickets;
        this.productosVendidos = productosVendidos;
    }

    public Corte(int pk_corteID, double ventasTotales, double saldoAlCorte, int cantidadTickets, int productosVendidos) {
        this.pk_corteID = pk_corteID;
        this.ventasTotales = ventasTotales;
        this.saldoAlCorte = saldoAlCorte;
        this.cantidadTickets = cantidadTickets;
        this.productosVendidos = productosVendidos;
    }

    public int getPk_corteID() {
        return pk_corteID;
    }

    public void setPk_corteID(int pk_corteID) {
        this.pk_corteID = pk_corteID;
    }

    public double getVentasTotales() {
        return ventasTotales;
    }

    public void setVentasTotales(double ventasTotales) {
        this.ventasTotales = ventasTotales;
    }

    public double getSaldoAlCorte() {
        return saldoAlCorte;
    }

    public void setSaldoAlCorte(double saldoAlCorte) {
        this.saldoAlCorte = saldoAlCorte;
    }

    public int getCantidadTickets() {
        return cantidadTickets;
    }

    public void setCantidadTickets(int cantidadTickets) {
        this.cantidadTickets = cantidadTickets;
    }

    public int getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(int productosVendidos) {
        this.productosVendidos = productosVendidos;
    }
    
    
    public void saveToDatabase(int corteID) {
        try {
            //update current corte and close
            String insertSQL = "UPDATE cortes SET ventastotales = '" + this.ventasTotales + "', cantidadtickets = '" + this.cantidadTickets + "', productosvendidos = '" + this.productosVendidos + "' WHERE pk_corteid = " + corteID;         
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
            
            //insert new corte for future
            insertSQL = "insert into cortes (ventastotales,cantidadtickets,productosvendidos) values (NULL, NULL, NULL);";         
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
