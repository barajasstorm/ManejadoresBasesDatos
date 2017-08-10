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
public class Compras {
    //Instance Variables
    private String proveedor;
    private String producto;
    private double cantidad;
    
    //Constructores

    public Compras() {
    }

    public Compras(String proveedor, String producto, double cantidad) {
        this.proveedor = proveedor;
        this.producto = producto;
        this.cantidad = cantidad;
    }
    
    //Getters and Setters
    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    
}
