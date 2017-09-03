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
@Table(name = "fuente", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fuente.findAll", query = "SELECT f FROM Fuente f"),
    @NamedQuery(name = "Fuente.findById", query = "SELECT f FROM Fuente f WHERE f.id = :id"),
    @NamedQuery(name = "Fuente.findByNombre", query = "SELECT f FROM Fuente f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Fuente.findByDescripcion", query = "SELECT f FROM Fuente f WHERE f.descripcion = :descripcion")})
public class Fuente implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuenteId")
    private List<Administrativo> administrativoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuenteId")
    private List<Estudiante> estudianteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuenteId")
    private List<Directorprograma> directorprogramaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuenteId")
    private List<Asignacionencuesta> asignacionencuestaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuenteId")
    private List<Encabezado> encabezadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuenteId")
    private List<Egresado> egresadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuenteId")
    private List<Agenciagubernamental> agenciagubernamentalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuenteId")
    private List<Empleador> empleadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuenteId")
    private List<Docente> docenteList;

    public Fuente() {
    }

    public Fuente(Integer id) {
        this.id = id;
    }

    public Fuente(Integer id, String nombre) {
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
    public List<Administrativo> getAdministrativoList() {
        return administrativoList;
    }

    public void setAdministrativoList(List<Administrativo> administrativoList) {
        this.administrativoList = administrativoList;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    @XmlTransient
    public List<Directorprograma> getDirectorprogramaList() {
        return directorprogramaList;
    }

    public void setDirectorprogramaList(List<Directorprograma> directorprogramaList) {
        this.directorprogramaList = directorprogramaList;
    }

    @XmlTransient
    public List<Asignacionencuesta> getAsignacionencuestaList() {
        return asignacionencuestaList;
    }

    public void setAsignacionencuestaList(List<Asignacionencuesta> asignacionencuestaList) {
        this.asignacionencuestaList = asignacionencuestaList;
    }

    @XmlTransient
    public List<Encabezado> getEncabezadoList() {
        return encabezadoList;
    }

    public void setEncabezadoList(List<Encabezado> encabezadoList) {
        this.encabezadoList = encabezadoList;
    }

    @XmlTransient
    public List<Egresado> getEgresadoList() {
        return egresadoList;
    }

    public void setEgresadoList(List<Egresado> egresadoList) {
        this.egresadoList = egresadoList;
    }

    @XmlTransient
    public List<Agenciagubernamental> getAgenciagubernamentalList() {
        return agenciagubernamentalList;
    }

    public void setAgenciagubernamentalList(List<Agenciagubernamental> agenciagubernamentalList) {
        this.agenciagubernamentalList = agenciagubernamentalList;
    }

    @XmlTransient
    public List<Empleador> getEmpleadorList() {
        return empleadorList;
    }

    public void setEmpleadorList(List<Empleador> empleadorList) {
        this.empleadorList = empleadorList;
    }

    @XmlTransient
    public List<Docente> getDocenteList() {
        return docenteList;
    }

    public void setDocenteList(List<Docente> docenteList) {
        this.docenteList = docenteList;
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
        if (!(object instanceof Fuente)) {
            return false;
        }
        Fuente other = (Fuente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Fuente[ id=" + id + " ]";
    }
    
}
