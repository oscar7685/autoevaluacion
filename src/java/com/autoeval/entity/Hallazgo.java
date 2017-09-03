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
@Table(name = "hallazgo", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hallazgo.findAll", query = "SELECT h FROM Hallazgo h"),
    @NamedQuery(name = "Hallazgo.findByIdhallazgo", query = "SELECT h FROM Hallazgo h WHERE h.idhallazgo = :idhallazgo"),
    @NamedQuery(name = "Hallazgo.findByHallazgo", query = "SELECT h FROM Hallazgo h WHERE h.hallazgo = :hallazgo"),
    @NamedQuery(name = "Hallazgo.findByTipo", query = "SELECT h FROM Hallazgo h WHERE h.tipo = :tipo")})
public class Hallazgo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhallazgo")
    private Integer idhallazgo;
    @Size(max = 3000)
    @Column(name = "hallazgo")
    private String hallazgo;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hallazgoIdhallazgo")
    private List<Objetivos> objetivosList;
    @JoinColumn(name = "caracteristica_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Caracteristica caracteristicaId;
    @JoinColumn(name = "proceso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proceso procesoId;

    public Hallazgo() {
    }

    public Hallazgo(Integer idhallazgo) {
        this.idhallazgo = idhallazgo;
    }

    public Integer getIdhallazgo() {
        return idhallazgo;
    }

    public void setIdhallazgo(Integer idhallazgo) {
        this.idhallazgo = idhallazgo;
    }

    public String getHallazgo() {
        return hallazgo;
    }

    public void setHallazgo(String hallazgo) {
        this.hallazgo = hallazgo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Objetivos> getObjetivosList() {
        return objetivosList;
    }

    public void setObjetivosList(List<Objetivos> objetivosList) {
        this.objetivosList = objetivosList;
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
        hash += (idhallazgo != null ? idhallazgo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hallazgo)) {
            return false;
        }
        Hallazgo other = (Hallazgo) object;
        if ((this.idhallazgo == null && other.idhallazgo != null) || (this.idhallazgo != null && !this.idhallazgo.equals(other.idhallazgo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Hallazgo[ idhallazgo=" + idhallazgo + " ]";
    }
    
}
