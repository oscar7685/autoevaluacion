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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "instrumento", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instrumento.findAll", query = "SELECT i FROM Instrumento i"),
    @NamedQuery(name = "Instrumento.findById", query = "SELECT i FROM Instrumento i WHERE i.id = :id"),
    @NamedQuery(name = "Instrumento.findByNombre", query = "SELECT i FROM Instrumento i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Instrumento.findByDescripcion", query = "SELECT i FROM Instrumento i WHERE i.descripcion = :descripcion")})
public class Instrumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinTable(name = "instrumentohasindicador", joinColumns = {
        @JoinColumn(name = "instrumento_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "indicador_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Indicador> indicadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instrumentoId")
    private List<Numericadocumental> numericadocumentalList;

    public Instrumento() {
    }

    public Instrumento(Integer id) {
        this.id = id;
    }

    public Instrumento(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Indicador> getIndicadorList() {
        return indicadorList;
    }

    public void setIndicadorList(List<Indicador> indicadorList) {
        this.indicadorList = indicadorList;
    }

    @XmlTransient
    public List<Numericadocumental> getNumericadocumentalList() {
        return numericadocumentalList;
    }

    public void setNumericadocumentalList(List<Numericadocumental> numericadocumentalList) {
        this.numericadocumentalList = numericadocumentalList;
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
        if (!(object instanceof Instrumento)) {
            return false;
        }
        Instrumento other = (Instrumento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Instrumento[ id=" + id + " ]";
    }
    
}
