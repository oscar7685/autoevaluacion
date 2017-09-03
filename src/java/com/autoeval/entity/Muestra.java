/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "muestra", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muestra.findAll", query = "SELECT m FROM Muestra m"),
    @NamedQuery(name = "Muestra.findById", query = "SELECT m FROM Muestra m WHERE m.id = :id"),
    @NamedQuery(name = "Muestra.findByTipo", query = "SELECT m FROM Muestra m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "Muestra.findByError", query = "SELECT m FROM Muestra m WHERE m.error = :error"),
    @NamedQuery(name = "Muestra.findByConfianza", query = "SELECT m FROM Muestra m WHERE m.confianza = :confianza"),
    @NamedQuery(name = "Muestra.findByVarianza", query = "SELECT m FROM Muestra m WHERE m.varianza = :varianza")})
public class Muestra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 500)
    @Column(name = "tipo")
    private String tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "error")
    private Float error;
    @Column(name = "confianza")
    private Float confianza;
    @Column(name = "varianza")
    private Float varianza;
    @JoinColumn(name = "proceso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proceso procesoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "muestraId")
    private List<Muestrapersona> muestrapersonaList;

    public Muestra() {
    }

    public Muestra(Integer id) {
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

    public Float getError() {
        return error;
    }

    public void setError(Float error) {
        this.error = error;
    }

    public Float getConfianza() {
        return confianza;
    }

    public void setConfianza(Float confianza) {
        this.confianza = confianza;
    }

    public Float getVarianza() {
        return varianza;
    }

    public void setVarianza(Float varianza) {
        this.varianza = varianza;
    }

    public Proceso getProcesoId() {
        return procesoId;
    }

    public void setProcesoId(Proceso procesoId) {
        this.procesoId = procesoId;
    }

    @XmlTransient
    public List<Muestrapersona> getMuestrapersonaList() {
        return muestrapersonaList;
    }

    public void setMuestrapersonaList(List<Muestrapersona> muestrapersonaList) {
        this.muestrapersonaList = muestrapersonaList;
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
        if (!(object instanceof Muestra)) {
            return false;
        }
        Muestra other = (Muestra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Muestra[ id=" + id + " ]";
    }
    
}
