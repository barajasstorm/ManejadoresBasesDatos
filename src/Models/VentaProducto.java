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
public class VentaProducto {
    
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
    
    private int pk_ventaproductoid;
    private int fk_ventaid;
    private int fk_productoid;
    private int cantidad;
    private double importeProducto;

    public VentaProducto() throws SQLException {
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }
    }

    public VentaProducto(int pk_ventaproductoid, int fk_ventaid, int fk_productoid, int cantidad, double importeProducto) throws SQLException {
        this.pk_ventaproductoid = pk_ventaproductoid;
        this.fk_ventaid = fk_ventaid;
        this.fk_productoid = fk_productoid;
        this.cantidad = cantidad;
        this.importeProducto = importeProducto;
        
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }
    }

    public VentaProducto(int fk_ventaid, int fk_productoid, int cantidad, double importeProducto) throws SQLException {
        this.fk_ventaid = fk_ventaid;
        this.fk_productoid = fk_productoid;
        this.cantidad = cantidad;
        this.importeProducto = importeProducto;
        
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }
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
            
            //Insert product into database after validation           
            preparedStatement = postgresConnection.prepareStatement("insert into ventasproductos (fk_ventaid,fk_productoid,cantidad,importeproducto) values (?,?,?,?)");
            preparedStatement.setInt(1, this.fk_ventaid);
            preparedStatement.setInt(2, this.fk_productoid);
            preparedStatement.setInt(3, this.cantidad);
            preparedStatement.setDouble(4, this.importeProducto);
            postgresBegin.executeUpdate();
            postgresCount = preparedStatement.executeUpdate();
            
            preparedStatement = mysqlConnection.prepareStatement("insert into ventasproductos (fk_ventaid,fk_productoid,cantidad,importeproducto) values (?,?,?,?)");
            preparedStatement.setInt(1, this.fk_ventaid);
            preparedStatement.setInt(2, this.fk_productoid);
            preparedStatement.setInt(3, this.cantidad);
            preparedStatement.setDouble(4, this.importeProducto);
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
