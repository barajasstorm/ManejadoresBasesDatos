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
public class Compra {
    
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
    
    private int pk_compraID;
    private int fk_proveedorID;
    private int fk_productoID;
    private int cantidad;

    public Compra() throws SQLException {
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }
    }

    public Compra(int fk_proveedorID, int fk_productoID, int cantidad) throws SQLException {
        this.fk_proveedorID = fk_proveedorID;
        this.fk_productoID = fk_productoID;
        this.cantidad = cantidad;
        
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }
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
            //Insert product into database after validation                      
            preparedStatement = postgresConnection.prepareStatement("insert into compras (fk_proveedorid, fk_productoid, cantidad) values (?,?,?)");
            preparedStatement.setInt(1, this.fk_proveedorID);
            preparedStatement.setInt(2, this.fk_productoID);
            preparedStatement.setInt(3, this.cantidad);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("insert into compras (fk_proveedorid, fk_productoid, cantidad) values (?,?,?)");
            preparedStatement.setInt(1, this.fk_proveedorID);
            preparedStatement.setInt(2, this.fk_productoID);
            preparedStatement.setInt(3, this.cantidad);
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
