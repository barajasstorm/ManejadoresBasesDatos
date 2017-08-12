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
public class VentaController {
    
    Postgres postgres = new Postgres();
    Connection connection = postgres.connect();
    ResultSet resultSet = null;
    Statement statement = null;
    
    private int ticketNumero;
    
    
    public void siguienteTicket() throws SQLException {
        String selectSQL = "SELECT * FROM ventas ORDER BY pk_ventaid DESC LIMIT 1";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(selectSQL);
        while (resultSet.next()) {
            setTicketNumero((resultSet.getInt("numeroticket")) + 1);
        }
    }

    public int getTicketNumero() {
        return ticketNumero;
    }

    public void setTicketNumero(int ticketNumero) {
        this.ticketNumero = ticketNumero;
    }
    
    
    
    
    
    
    
    
    
    
}
