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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "muestraempleador", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muestraempleador.findAll", query = "SELECT m FROM Muestraempleador m"),
    @NamedQuery(name = "Muestraempleador.findById", query = "SELECT m FROM Muestraempleador m WHERE m.id = :id"),
    @NamedQuery(name = "Muestraempleador.findByEmpresa", query = "SELECT m FROM Muestraempleador m WHERE m.empresa = :empresa"),
    @NamedQuery(name = "Muestraempleador.findByMuestraPersonaId", query = "SELECT m FROM Muestraempleador m WHERE m.muestrapersonaId = :muestrapersona"),
    @NamedQuery(name = "Muestraempleador.findByCargo", query = "SELECT m FROM Muestraempleador m WHERE m.cargo = :cargo")})
public class Muestraempleador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "empresa")
    private String empresa;
    @Size(max = 255)
    @Column(name = "cargo")
    private String cargo;
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
    public Muestraempleador() {
    }

    public Muestraempleador(Integer id) {
        this.id = id;
    }

    public Muestraempleador(Integer id, String empresa) {
        this.id = id;
        this.empresa = empresa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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
        if (!(object instanceof Muestraempleador)) {
            return false;
        }
        Muestraempleador other = (Muestraempleador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Muestraempleador[ id=" + id + " ]";
    }
    
}
