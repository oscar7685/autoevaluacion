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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "asignacionencuesta", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignacionencuesta.findAll", query = "SELECT a FROM Asignacionencuesta a"),
    @NamedQuery(name = "Asignacionencuesta.findByEncuesta", query = "SELECT a FROM Asignacionencuesta a WHERE a.encuestaId = :encuesta"),
    @NamedQuery(name = "Asignacionencuesta.findByEncuestayFuenteyModelo", query = "SELECT a FROM Asignacionencuesta a WHERE a.encuestaId = :encuesta and a.fuenteId= :fuente and a.modeloId= :modelo"),
    @NamedQuery(name = "Asignacionencuesta.findById", query = "SELECT a FROM Asignacionencuesta a WHERE a.id = :id")})
public class Asignacionencuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "modelo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Modelo modeloId;
    @JoinColumn(name = "encuesta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Encuesta encuestaId;
    @JoinColumn(name = "fuente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fuente fuenteId;

    public Asignacionencuesta() {
    }

    public Asignacionencuesta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Modelo getModeloId() {
        return modeloId;
    }

    public void setModeloId(Modelo modeloId) {
        this.modeloId = modeloId;
    }

    public Encuesta getEncuestaId() {
        return encuestaId;
    }

    public void setEncuestaId(Encuesta encuestaId) {
        this.encuestaId = encuestaId;
    }

    public Fuente getFuenteId() {
        return fuenteId;
    }

    public void setFuenteId(Fuente fuenteId) {
        this.fuenteId = fuenteId;
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
        if (!(object instanceof Asignacionencuesta)) {
            return false;
        }
        Asignacionencuesta other = (Asignacionencuesta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Asignacionencuesta[ id=" + id + " ]";
    }
    
}
