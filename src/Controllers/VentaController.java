/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author juanba
 */
public class VentaController {
    
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
    
    private int ticketNumero;
    private int actualID;
    
    
    public VentaController() throws SQLException {
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }
    }
    
    public void siguienteTicket() throws SQLException {
        String selectSQL = "SELECT * FROM ventas ORDER BY pk_ventaid DESC LIMIT 1";
        statement = postgresConnection.createStatement();
        resultSet = statement.executeQuery(selectSQL);
        while (resultSet.next()) {
            setTicketNumero((resultSet.getInt("numeroticket")) + 1);
        }
    }
    
    public void calculateActualIDFromDatabase() throws SQLException {
        String selectSQL = "SELECT * FROM ventas ORDER BY pk_ventaid DESC LIMIT 1";
        statement = postgresConnection.createStatement();
        resultSet = statement.executeQuery(selectSQL);
        while (resultSet.next()) {
            setActualID((resultSet.getInt("pk_ventaid")));
        }
    }

    public int getTicketNumero() {
        return ticketNumero;
    }

    public void setTicketNumero(int ticketNumero) {
        this.ticketNumero = ticketNumero;
    }

    public int getActualID() {
        return actualID;
    }

    public void setActualID(int actualID) {
        this.actualID = actualID;
    }
    
}
