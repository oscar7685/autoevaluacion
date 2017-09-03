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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "factor", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factor.findAll", query = "SELECT f FROM Factor f"),
    @NamedQuery(name = "Factor.findById", query = "SELECT f FROM Factor f WHERE f.id = :id"),
    @NamedQuery(name = "Factor.findByCodigo", query = "SELECT f FROM Factor f WHERE f.codigo = :codigo"),
    @NamedQuery(name = "Factor.findByNombre", query = "SELECT f FROM Factor f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Factor.findByModeloOptimizada", query = "SELECT DISTINCT f FROM Factor f JOIN f.caracteristicaList ca WHERE f.modeloId = :modelo and size(ca.preguntaList)> 0"),
    @NamedQuery(name = "Factor.findByModelo", query = "SELECT f FROM Factor f WHERE f.modeloId = :modelo")})
public class Factor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factorId")
    private List<Ponderacionfactor> ponderacionfactorList;
    @JoinColumn(name = "modelo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Modelo modeloId;
    @OneToMany(mappedBy = "factorId")
    private List<Caracteristica> caracteristicaList;

    public Factor() {
    }

    public Factor(Integer id) {
        this.id = id;
    }

    public Factor(Integer id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Ponderacionfactor> getPonderacionfactorList() {
        return ponderacionfactorList;
    }

    public void setPonderacionfactorList(List<Ponderacionfactor> ponderacionfactorList) {
        this.ponderacionfactorList = ponderacionfactorList;
    }

    public Modelo getModeloId() {
        return modeloId;
    }

    public void setModeloId(Modelo modeloId) {
        this.modeloId = modeloId;
    }

    @XmlTransient
    public List<Caracteristica> getCaracteristicaList() {
        return caracteristicaList;
    }

    public void setCaracteristicaList(List<Caracteristica> caracteristicaList) {
        this.caracteristicaList = caracteristicaList;
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
        if (!(object instanceof Factor)) {
            return false;
        }
        Factor other = (Factor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Factor[ id=" + id + " ]";
    }
    
}
