/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.Connector;
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
    
    //Instances variables
    private int pk_corteID;
    private double ventasTotales;
    private double saldoAlCorte;
    private int cantidadTickets;
    private int productosVendidos;

    public Corte() throws SQLException {
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }
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
            preparedStatement = postgresConnection.prepareStatement("UPDATE cortes SET ventastotales = ?, cantidadtickets = ?, productosvendidos = ? WHERE pk_corteid = ?");
            preparedStatement.setDouble(1, this.ventasTotales);
            preparedStatement.setInt(2, this.cantidadTickets);
            preparedStatement.setInt(3, this.productosVendidos);
            preparedStatement.setInt(4, corteID);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("UPDATE cortes SET ventastotales = ?, cantidadtickets = ?, productosvendidos = ? WHERE pk_corteid = ?");
            preparedStatement.setDouble(1, this.ventasTotales);
            preparedStatement.setInt(2, this.cantidadTickets);
            preparedStatement.setInt(3, this.productosVendidos);
            preparedStatement.setInt(4, corteID);
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
            
            //insert new corte for future
            preparedStatement = postgresConnection.prepareStatement("insert into cortes (ventastotales,cantidadtickets,productosvendidos) values (NULL, NULL, NULL);");
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("insert into cortes (ventastotales,cantidadtickets,productosvendidos) values (NULL, NULL, NULL);");
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
            
        } catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
