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
@Table(name = "ponderacionfactor", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ponderacionfactor.findAll", query = "SELECT p FROM Ponderacionfactor p"),
    @NamedQuery(name = "Ponderacionfactor.findById", query = "SELECT p FROM Ponderacionfactor p WHERE p.id = :id"),
    @NamedQuery(name = "Ponderacionfactor.findByPonderacion", query = "SELECT p FROM Ponderacionfactor p WHERE p.ponderacion = :ponderacion"),
    @NamedQuery(name = "Ponderacionfactor.findByFactorYProceso", query = "SELECT p FROM Ponderacionfactor p WHERE p.factorId= :factor and p.procesoId= :proceso"),
    @NamedQuery(name = "Ponderacionfactor.findByJustificacion", query = "SELECT p FROM Ponderacionfactor p WHERE p.justificacion = :justificacion")})
public class Ponderacionfactor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ponderacion")
    private double ponderacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "justificacion")
    private String justificacion;
    @JoinColumn(name = "factor_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Factor factorId;
    @JoinColumn(name = "proceso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proceso procesoId;

    public Ponderacionfactor() {
    }

    public Ponderacionfactor(Integer id) {
        this.id = id;
    }

    public Ponderacionfactor(Integer id, double ponderacion, String justificacion) {
        this.id = id;
        this.ponderacion = ponderacion;
        this.justificacion = justificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(double ponderacion) {
        this.ponderacion = ponderacion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Factor getFactorId() {
        return factorId;
    }

    public void setFactorId(Factor factorId) {
        this.factorId = factorId;
    }

    public Proceso getProcesoId() {
        return procesoId;
    }

    public void setProcesoId(Proceso procesoId) {
        this.procesoId = procesoId;
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
        if (!(object instanceof Ponderacionfactor)) {
            return false;
        }
        Ponderacionfactor other = (Ponderacionfactor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Ponderacionfactor[ id=" + id + " ]";
    }
    
}
