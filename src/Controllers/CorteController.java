/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author juanba
 */
public class CorteController {
    
    Postgres postgres = new Postgres();
    Connection connection = postgres.connect();
    ResultSet resultSet = null;
    Statement statement = null;
    
    private int corteNumero;
    
    
    public void numeroCorte() throws SQLException {
        String selectSQL = "SELECT pk_corteid FROM cortes ORDER BY pk_corteid DESC LIMIT 1";
        statement = connection.createStatement();
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
