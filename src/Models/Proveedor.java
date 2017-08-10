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
public class Proveedor {
    
    //Instance Variables
    public String nombre;
    public String rfc;
    public int telefono;
    public String ciudad;
    public String estado;
    
    //Constructors
    public Proveedor() {
    }

    public Proveedor(String nombre, String rfc, int telefono, String ciudad, String estado) {
        this.nombre = nombre;
        this.rfc = rfc;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.estado = estado;
    }
    
    //Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
