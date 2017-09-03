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
@Table(name = "ponderacioncaracteristica", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ponderacioncaracteristica.findAll", query = "SELECT p FROM Ponderacioncaracteristica p"),
    @NamedQuery(name = "Ponderacioncaracteristica.findById", query = "SELECT p FROM Ponderacioncaracteristica p WHERE p.id = :id"),
    @NamedQuery(name = "Ponderacioncaracteristica.findByNivelimportancia", query = "SELECT p FROM Ponderacioncaracteristica p WHERE p.nivelimportancia = :nivelimportancia"),
    @NamedQuery(name = "Ponderacioncaracteristica.findByPonderacion", query = "SELECT p FROM Ponderacioncaracteristica p WHERE p.ponderacion = :ponderacion"),
    @NamedQuery(name = "Ponderacioncaracteristica.findByCaracteristicaYProceso", query = "SELECT p FROM Ponderacioncaracteristica p WHERE p.caracteristicaId= :caracteristica and p.procesoId= :proceso"),
    @NamedQuery(name = "Ponderacioncaracteristica.findByJustificacion", query = "SELECT p FROM Ponderacioncaracteristica p WHERE p.justificacion = :justificacion")})
public class Ponderacioncaracteristica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nivelimportancia")
    private float nivelimportancia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ponderacion")
    private double ponderacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "justificacion")
    private String justificacion;
    @JoinColumn(name = "caracteristica_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Caracteristica caracteristicaId;
    @JoinColumn(name = "proceso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proceso procesoId;

    public Ponderacioncaracteristica() {
    }

    public Ponderacioncaracteristica(Integer id) {
        this.id = id;
    }

    public Ponderacioncaracteristica(Integer id, float nivelimportancia, double ponderacion, String justificacion) {
        this.id = id;
        this.nivelimportancia = nivelimportancia;
        this.ponderacion = ponderacion;
        this.justificacion = justificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getNivelimportancia() {
        return nivelimportancia;
    }

    public void setNivelimportancia(float nivelimportancia) {
        this.nivelimportancia = nivelimportancia;
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

    public Caracteristica getCaracteristicaId() {
        return caracteristicaId;
    }

    public void setCaracteristicaId(Caracteristica caracteristicaId) {
        this.caracteristicaId = caracteristicaId;
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
        if (!(object instanceof Ponderacioncaracteristica)) {
            return false;
        }
        Ponderacioncaracteristica other = (Ponderacioncaracteristica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Ponderacioncaracteristica[ id=" + id + " ]";
    }
    
}
