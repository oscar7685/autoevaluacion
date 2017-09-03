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
@Table(name = "ponderacionindicador", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ponderacionindicador.findAll", query = "SELECT p FROM Ponderacionindicador p"),
    @NamedQuery(name = "Ponderacionindicador.findByIdponderacionindicador", query = "SELECT p FROM Ponderacionindicador p WHERE p.idponderacionindicador = :idponderacionindicador"),
    @NamedQuery(name = "Ponderacionindicador.findByPonderacion", query = "SELECT p FROM Ponderacionindicador p WHERE p.ponderacion = :ponderacion")})
public class Ponderacionindicador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idponderacionindicador")
    private Integer idponderacionindicador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ponderacion")
    private Double ponderacion;
    @JoinColumn(name = "indicador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Indicador indicadorId;
    @JoinColumn(name = "proceso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proceso procesoId;

    public Ponderacionindicador() {
    }

    public Ponderacionindicador(Integer idponderacionindicador) {
        this.idponderacionindicador = idponderacionindicador;
    }

    public Integer getIdponderacionindicador() {
        return idponderacionindicador;
    }

    public void setIdponderacionindicador(Integer idponderacionindicador) {
        this.idponderacionindicador = idponderacionindicador;
    }

    public Double getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(Double ponderacion) {
        this.ponderacion = ponderacion;
    }

    public Indicador getIndicadorId() {
        return indicadorId;
    }

    public void setIndicadorId(Indicador indicadorId) {
        this.indicadorId = indicadorId;
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
        hash += (idponderacionindicador != null ? idponderacionindicador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ponderacionindicador)) {
            return false;
        }
        Ponderacionindicador other = (Ponderacionindicador) object;
        if ((this.idponderacionindicador == null && other.idponderacionindicador != null) || (this.idponderacionindicador != null && !this.idponderacionindicador.equals(other.idponderacionindicador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Ponderacionindicador[ idponderacionindicador=" + idponderacionindicador + " ]";
    }
    
}
