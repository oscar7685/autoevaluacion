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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author acreditacion
 */
@Entity
@Table(name = "evaluarcaracteristica", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluarcaracteristica.findAll", query = "SELECT e FROM Evaluarcaracteristica e"),
    @NamedQuery(name = "Evaluarcaracteristica.findByIdevaluarcaracteristica", query = "SELECT e FROM Evaluarcaracteristica e WHERE e.idevaluarcaracteristica = :idevaluarcaracteristica"),
    @NamedQuery(name = "Evaluarcaracteristica.findByEvaluacion", query = "SELECT e FROM Evaluarcaracteristica e WHERE e.evaluacion = :evaluacion")})
public class Evaluarcaracteristica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevaluarcaracteristica")
    private Integer idevaluarcaracteristica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "evaluacion")
    private float evaluacion;
    @JoinColumn(name = "proceso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proceso procesoId;
    @JoinColumn(name = "caracteristica_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Caracteristica caracteristicaId;

    public Evaluarcaracteristica() {
    }

    public Evaluarcaracteristica(Integer idevaluarcaracteristica) {
        this.idevaluarcaracteristica = idevaluarcaracteristica;
    }

    public Evaluarcaracteristica(Integer idevaluarcaracteristica, float evaluacion) {
        this.idevaluarcaracteristica = idevaluarcaracteristica;
        this.evaluacion = evaluacion;
    }

    public Integer getIdevaluarcaracteristica() {
        return idevaluarcaracteristica;
    }

    public void setIdevaluarcaracteristica(Integer idevaluarcaracteristica) {
        this.idevaluarcaracteristica = idevaluarcaracteristica;
    }

    public float getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(float evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Proceso getProcesoId() {
        return procesoId;
    }

    public void setProcesoId(Proceso procesoId) {
        this.procesoId = procesoId;
    }

    public Caracteristica getCaracteristicaId() {
        return caracteristicaId;
    }

    public void setCaracteristicaId(Caracteristica caracteristicaId) {
        this.caracteristicaId = caracteristicaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevaluarcaracteristica != null ? idevaluarcaracteristica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluarcaracteristica)) {
            return false;
        }
        Evaluarcaracteristica other = (Evaluarcaracteristica) object;
        if ((this.idevaluarcaracteristica == null && other.idevaluarcaracteristica != null) || (this.idevaluarcaracteristica != null && !this.idevaluarcaracteristica.equals(other.idevaluarcaracteristica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Evaluarcaracteristica[ idevaluarcaracteristica=" + idevaluarcaracteristica + " ]";
    }
    
}
