/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.Postgres;
import java.sql.Connection;
import static java.sql.JDBCType.NULL;
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
public class Venta {
    
    Postgres postgres = new Postgres();
    Connection connection = postgres.connect();
    ResultSet resultSet = null;
    Statement statement = null;
    
    //Instance Variables
    private int pk_ventaID;
    private int fk_usuarioID;
    private int fk_corteID;
    private int fk_clienteID;
    private int numeroTicket;
    private int dia;
    private int mes;
    private int ano;
    private String hora;
    private int cantidadArticulos;
    private double total;

    public Venta() {
        this.fk_clienteID = 0;
    }
    
    public Venta(int numeroTicket) {
        this.numeroTicket = numeroTicket;
        this.fk_clienteID = 0;
    }
    
    public Venta(int pk_ventaID, int fk_usuarioID, int fk_corteID, int fk_clienteID, int numeroTicket, int dia, int mes, int ano, String hora, int cantidadArticulos, double total) {
        this.pk_ventaID = pk_ventaID;
        this.fk_usuarioID = fk_usuarioID;
        this.fk_corteID = fk_corteID;
        this.fk_clienteID = fk_clienteID;
        this.numeroTicket = numeroTicket;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.cantidadArticulos = cantidadArticulos;
        this.total = total;
    }

    public Postgres getPostgres() {
        return postgres;
    }

    public void setPostgres(Postgres postgres) {
        this.postgres = postgres;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public int getPk_ventaID() {
        return pk_ventaID;
    }

    public void setPk_ventaID(int pk_ventaID) {
        this.pk_ventaID = pk_ventaID;
    }

    public int getFk_usuarioID() {
        return fk_usuarioID;
    }

    public void setFk_usuarioID(int fk_usuarioID) {
        this.fk_usuarioID = fk_usuarioID;
    }

    public int getFk_corteID() {
        return fk_corteID;
    }

    public void setFk_corteID(int fk_corteID) {
        this.fk_corteID = fk_corteID;
    }

    public int getFk_clienteID() {
        return fk_clienteID;
    }

    public void setFk_clienteID(int fk_clienteID) {
        this.fk_clienteID = fk_clienteID;
    }

    public int getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getCantidadArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulos(int cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void saveToDatabase() {
        try {
            String insertSQL;
            if(this.fk_clienteID == 0) {
                insertSQL = "insert into ventas (fk_usuarioid,fk_corteid,numeroticket,dia,mes,ano,hora,cantarticulos,total) values ('" + this.fk_usuarioID + "','" + this.fk_corteID + "','" + this.numeroTicket + "','" + this.dia + "','" + this.mes + "','" + this.ano + "','" + this.hora + "','" + this.cantidadArticulos + "','" + this.total + "');";
            } else {
                insertSQL = "insert into ventas (fk_usuarioid,fk_corteid,fk_clienteid,numeroticket,dia,mes,ano,hora,cantarticulos,total) values ('" + this.fk_usuarioID + "','" + this.fk_corteID + "','" + this.fk_clienteID + "','" + this.numeroTicket + "','" + this.dia + "','" + this.mes + "','" + this.ano + "','" + this.hora + "','" + this.cantidadArticulos + "','" + this.total + "');";
            }
            //Insert product into database after validation           
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
}    

