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
public class Producto {
    
    //Instance Variables
    public int pk_ProductoId;
    public String nombre;
    public double precioCompra;
    public double precioVenta;
    public int existencias;
    public int stockMin;
    public int activo;

    //Constructors
    public Producto() {
    }

    public Producto(int pk_ProductoId, String nombre, double precioCompra, double precioVenta, int existencias, int stockMin, int activo) {
        this.pk_ProductoId = pk_ProductoId;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.existencias = existencias;
        this.stockMin = stockMin;
        this.activo = activo;
    }
    
    //Getters and Setters
    public int getPk_ProductoId() {
        return pk_ProductoId;
    }

    public void setPk_ProductoId(int pk_ProductoId) {
        this.pk_ProductoId = pk_ProductoId;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    

}
