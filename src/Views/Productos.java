/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author juanba
 */
@Entity
@Table(name = "productos", catalog = "final", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p")
    , @NamedQuery(name = "Productos.findByPkProductoid", query = "SELECT p FROM Productos p WHERE p.pkProductoid = :pkProductoid")
    , @NamedQuery(name = "Productos.findByNombre", query = "SELECT p FROM Productos p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Productos.findByPreciocompra", query = "SELECT p FROM Productos p WHERE p.preciocompra = :preciocompra")
    , @NamedQuery(name = "Productos.findByPrecioventa", query = "SELECT p FROM Productos p WHERE p.precioventa = :precioventa")
    , @NamedQuery(name = "Productos.findByExistencias", query = "SELECT p FROM Productos p WHERE p.existencias = :existencias")
    , @NamedQuery(name = "Productos.findByStockminimo", query = "SELECT p FROM Productos p WHERE p.stockminimo = :stockminimo")
    , @NamedQuery(name = "Productos.findByActivo", query = "SELECT p FROM Productos p WHERE p.activo = :activo")})
public class Productos implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_productoid")
    private Integer pkProductoid;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "preciocompra")
    private float preciocompra;
    @Basic(optional = false)
    @Column(name = "precioventa")
    private float precioventa;
    @Basic(optional = false)
    @Column(name = "existencias")
    private int existencias;
    @Basic(optional = false)
    @Column(name = "stockminimo")
    private int stockminimo;
    @Basic(optional = false)
    @Column(name = "activo")
    private int activo;

    public Productos() {
    }

    public Productos(Integer pkProductoid) {
        this.pkProductoid = pkProductoid;
    }

    public Productos(Integer pkProductoid, String nombre, float preciocompra, float precioventa, int existencias, int stockminimo, int activo) {
        this.pkProductoid = pkProductoid;
        this.nombre = nombre;
        this.preciocompra = preciocompra;
        this.precioventa = precioventa;
        this.existencias = existencias;
        this.stockminimo = stockminimo;
        this.activo = activo;
    }

    public Integer getPkProductoid() {
        return pkProductoid;
    }

    public void setPkProductoid(Integer pkProductoid) {
        Integer oldPkProductoid = this.pkProductoid;
        this.pkProductoid = pkProductoid;
        changeSupport.firePropertyChange("pkProductoid", oldPkProductoid, pkProductoid);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    public float getPreciocompra() {
        return preciocompra;
    }

    public void setPreciocompra(float preciocompra) {
        float oldPreciocompra = this.preciocompra;
        this.preciocompra = preciocompra;
        changeSupport.firePropertyChange("preciocompra", oldPreciocompra, preciocompra);
    }

    public float getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(float precioventa) {
        float oldPrecioventa = this.precioventa;
        this.precioventa = precioventa;
        changeSupport.firePropertyChange("precioventa", oldPrecioventa, precioventa);
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        int oldExistencias = this.existencias;
        this.existencias = existencias;
        changeSupport.firePropertyChange("existencias", oldExistencias, existencias);
    }

    public int getStockminimo() {
        return stockminimo;
    }

    public void setStockminimo(int stockminimo) {
        int oldStockminimo = this.stockminimo;
        this.stockminimo = stockminimo;
        changeSupport.firePropertyChange("stockminimo", oldStockminimo, stockminimo);
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        int oldActivo = this.activo;
        this.activo = activo;
        changeSupport.firePropertyChange("activo", oldActivo, activo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkProductoid != null ? pkProductoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.pkProductoid == null && other.pkProductoid != null) || (this.pkProductoid != null && !this.pkProductoid.equals(other.pkProductoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Views.Productos[ pkProductoid=" + pkProductoid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
