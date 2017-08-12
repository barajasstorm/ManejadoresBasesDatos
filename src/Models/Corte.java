/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author juanba
 */
public class Corte {
    
    //Instances variables
    private int pk_corteID;
    private double ventasTotales;
    private double saldoAlCorte;
    private int cantidadTickets;
    private int productosVendidos;

    public Corte() {
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
    
    
    
    
}
