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
@Table(name = "programa", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programa.findAll", query = "SELECT p FROM Programa p"),
    @NamedQuery(name = "Programa.findById", query = "SELECT p FROM Programa p WHERE p.id = :id"),
    @NamedQuery(name = "Programa.findByNombre", query = "SELECT p FROM Programa p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Programa.findByDescripcion", query = "SELECT p FROM Programa p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Programa.findByModalidad", query = "SELECT p FROM Programa p WHERE p.modalidad = :modalidad"),
    @NamedQuery(name = "Programa.findByTipoformacion", query = "SELECT p FROM Programa p WHERE p.tipoformacion = :tipoformacion")})
public class Programa implements Serializable {
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
    @Size(max = 255)
    @Column(name = "modalidad")
    private String modalidad;
    @Size(max = 255)
    @Column(name = "tipoformacion")
    private String tipoformacion;
    @JoinTable(name = "programa_has_representante", joinColumns = {
        @JoinColumn(name = "programa_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "representante_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Representante> representanteList;
    @OneToMany(mappedBy = "programaId")
    private List<Respuestas> respuestasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programaId")
    private List<Administrativo> administrativoList;
    @JoinColumn(name = "facultad_id", referencedColumnName = "id")
    @ManyToOne
    private Facultad facultadId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programaId")
    private List<Estudiante> estudianteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programaId")
    private List<Directorprograma> directorprogramaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programaId")
    private List<Muestraestudiante> muestraestudianteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programaId")
    private List<Egresado> egresadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programaId")
    private List<Proceso> procesoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programaId")
    private List<Empleador> empleadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programaId")
    private List<Docente> docenteList;
    @OneToMany(mappedBy = "programaId")
    private List<ParticipanteHasRol> participanteHasRolList;

    public Programa() {
    }

    public Programa(Integer id) {
        this.id = id;
    }

    public Programa(Integer id, String nombre) {
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

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getTipoformacion() {
        return tipoformacion;
    }

    public void setTipoformacion(String tipoformacion) {
        this.tipoformacion = tipoformacion;
    }

    @XmlTransient
    public List<Representante> getRepresentanteList() {
        return representanteList;
    }

    public void setRepresentanteList(List<Representante> representanteList) {
        this.representanteList = representanteList;
    }

    @XmlTransient
    public List<Respuestas> getRespuestasList() {
        return respuestasList;
    }

    public void setRespuestasList(List<Respuestas> respuestasList) {
        this.respuestasList = respuestasList;
    }

    @XmlTransient
    public List<Administrativo> getAdministrativoList() {
        return administrativoList;
    }

    public void setAdministrativoList(List<Administrativo> administrativoList) {
        this.administrativoList = administrativoList;
    }

    public Facultad getFacultadId() {
        return facultadId;
    }

    public void setFacultadId(Facultad facultadId) {
        this.facultadId = facultadId;
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
    public List<Muestraestudiante> getMuestraestudianteList() {
        return muestraestudianteList;
    }

    public void setMuestraestudianteList(List<Muestraestudiante> muestraestudianteList) {
        this.muestraestudianteList = muestraestudianteList;
    }

    @XmlTransient
    public List<Egresado> getEgresadoList() {
        return egresadoList;
    }

    public void setEgresadoList(List<Egresado> egresadoList) {
        this.egresadoList = egresadoList;
    }

    @XmlTransient
    public List<Proceso> getProcesoList() {
        return procesoList;
    }

    public void setProcesoList(List<Proceso> procesoList) {
        this.procesoList = procesoList;
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

    @XmlTransient
    public List<ParticipanteHasRol> getParticipanteHasRolList() {
        return participanteHasRolList;
    }

    public void setParticipanteHasRolList(List<ParticipanteHasRol> participanteHasRolList) {
        this.participanteHasRolList = participanteHasRolList;
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
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Programa[ id=" + id + " ]";
    }
    
}
