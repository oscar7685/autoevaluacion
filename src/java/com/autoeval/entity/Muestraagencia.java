/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "muestraagencia", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muestraagencia.findAll", query = "SELECT m FROM Muestraagencia m"),
    @NamedQuery(name = "Muestraagencia.findById", query = "SELECT m FROM Muestraagencia m WHERE m.id = :id"),
    @NamedQuery(name = "Muestraagencia.findByDescripcion", query = "SELECT m FROM Muestraagencia m WHERE m.descripcion = :descripcion")})
public class Muestraagencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "muestrapersona_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Muestrapersona muestrapersonaId;

    public Muestraagencia() {
    }

    public Muestraagencia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Muestrapersona getMuestrapersonaId() {
        return muestrapersonaId;
    }

    public void setMuestrapersonaId(Muestrapersona muestrapersonaId) {
        this.muestrapersonaId = muestrapersonaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Muestraagencia)) {
            return false;
        }
        Muestraagencia other = (Muestraagencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Muestraagencia[ id=" + id + " ]";
    }
    
}
