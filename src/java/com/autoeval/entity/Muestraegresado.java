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
@Table(name = "muestraegresado", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muestraegresado.findAll", query = "SELECT m FROM Muestraegresado m"),
    @NamedQuery(name = "Muestraegresado.findByMuestraPersonaId", query = "SELECT m FROM Muestraegresado m WHERE m.muestrapersonaId = :muestrapersona"),
    @NamedQuery(name = "Muestraegresado.findById", query = "SELECT m FROM Muestraegresado m WHERE m.id = :id")})
public class Muestraegresado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "muestrapersona_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Muestrapersona muestrapersonaId;
    @Size(max = 45)
    @Column(name = "tp")
    private String tp;

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }
    public Muestraegresado() {
    }

    public Muestraegresado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof Muestraegresado)) {
            return false;
        }
        Muestraegresado other = (Muestraegresado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Muestraegresado[ id=" + id + " ]";
    }
    
}
