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
public class CorteController {
    
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
    
    private int corteNumero;
    
    public CorteController() throws SQLException {
        if(postgresConnection != null && mysqlConnection != null) {
            this.postgresCommit = postgresConnection.prepareStatement("COMMIT;");
            this.postgresBegin = postgresConnection.prepareStatement("BEGIN;");
            this.postgresRollback = postgresConnection.prepareStatement("ROLLBACK;");
            this.mysqlBegin = mysqlConnection.prepareStatement("BEGIN;");
            this.mysqlCommit = mysqlConnection.prepareStatement("COMMIT;");
            this.mysqlRollback = mysqlConnection.prepareStatement("ROLLBACK;");
        }
    }
    
    
    public void numeroCorte() throws SQLException {
        String selectSQL = "SELECT pk_corteid FROM cortes ORDER BY pk_corteid DESC LIMIT 1";
        statement = postgresConnection.createStatement();
        resultSet = statement.executeQuery(selectSQL);
        while (resultSet.next()) {
            setCorteNumero((resultSet.getInt("pk_corteid")));
        }
    }

    public int getCorteNumero() {
        return corteNumero;
    }

    public void setCorteNumero(int corteNumero) {
        this.corteNumero = corteNumero;
    }
    
    
    
}
