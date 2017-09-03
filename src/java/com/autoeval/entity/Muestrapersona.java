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
@Table(name = "muestrapersona", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muestrapersona.findAll", query = "SELECT m FROM Muestrapersona m"),
    @NamedQuery(name = "Muestrapersona.findById", query = "SELECT m FROM Muestrapersona m WHERE m.id = :id"),
    @NamedQuery(name = "Muestrapersona.findByCedula", query = "SELECT m FROM Muestrapersona m WHERE m.cedula = :cedula and m.muestraId.procesoId.fechacierre = '--' and m.muestraId.procesoId.fechainicio <> 'En Configuración' and size(m.encabezadoList) = 0"),
    @NamedQuery(name = "Muestrapersona.findByNombre", query = "SELECT m FROM Muestrapersona m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Muestrapersona.findByApellido", query = "SELECT m FROM Muestrapersona m WHERE m.apellido = :apellido"),
    @NamedQuery(name = "Muestrapersona.findByPassword", query = "SELECT m FROM Muestrapersona m WHERE m.password = :password"),
    @NamedQuery(name = "Muestrapersona.findByMuestraId", query = "SELECT m FROM Muestrapersona m WHERE m.muestraId = :muestra"),
    @NamedQuery(name = "Muestrapersona.findByMail", query = "SELECT m FROM Muestrapersona m WHERE m.mail = :mail")})
public class Muestrapersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cedula")
    private String cedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "mail")
    private String mail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "muestrapersonaId")
    private List<Muestradocente> muestradocenteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "muestrapersonaId")
    private List<Muestraadministrativo> muestraadministrativoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "muestrapersonaId")
    private List<Muestradirector> muestradirectorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "muestrapersonaId")
    private List<Muestraegresado> muestraegresadoList;
    @JoinColumn(name = "muestra_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Muestra muestraId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "muestrapersonaId")
    private List<Muestraagencia> muestraagenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "muestrapersonaId")
    private List<Encabezado> encabezadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "muestrapersonaId")
    private List<Muestraestudiante> muestraestudianteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "muestrapersonaId")
    private List<Muestraempleador> muestraempleadorList;

    public Muestrapersona() {
    }

    public Muestrapersona(Integer id) {
        this.id = id;
    }

    public Muestrapersona(Integer id, String cedula, String nombre, String apellido, String password, String mail) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.mail = mail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @XmlTransient
    public List<Muestradocente> getMuestradocenteList() {
        return muestradocenteList;
    }

    public void setMuestradocenteList(List<Muestradocente> muestradocenteList) {
        this.muestradocenteList = muestradocenteList;
    }

    @XmlTransient
    public List<Muestraadministrativo> getMuestraadministrativoList() {
        return muestraadministrativoList;
    }

    public void setMuestraadministrativoList(List<Muestraadministrativo> muestraadministrativoList) {
        this.muestraadministrativoList = muestraadministrativoList;
    }

    @XmlTransient
    public List<Muestradirector> getMuestradirectorList() {
        return muestradirectorList;
    }

    public void setMuestradirectorList(List<Muestradirector> muestradirectorList) {
        this.muestradirectorList = muestradirectorList;
    }

    @XmlTransient
    public List<Muestraegresado> getMuestraegresadoList() {
        return muestraegresadoList;
    }

    public void setMuestraegresadoList(List<Muestraegresado> muestraegresadoList) {
        this.muestraegresadoList = muestraegresadoList;
    }

    public Muestra getMuestraId() {
        return muestraId;
    }

    public void setMuestraId(Muestra muestraId) {
        this.muestraId = muestraId;
    }

    @XmlTransient
    public List<Muestraagencia> getMuestraagenciaList() {
        return muestraagenciaList;
    }

    public void setMuestraagenciaList(List<Muestraagencia> muestraagenciaList) {
        this.muestraagenciaList = muestraagenciaList;
    }

    @XmlTransient
    public List<Encabezado> getEncabezadoList() {
        return encabezadoList;
    }

    public void setEncabezadoList(List<Encabezado> encabezadoList) {
        this.encabezadoList = encabezadoList;
    }

    @XmlTransient
    public List<Muestraestudiante> getMuestraestudianteList() {
        return muestraestudianteList;
    }

    public void setMuestraestudianteList(List<Muestraestudiante> muestraestudianteList) {
        this.muestraestudianteList = muestraestudianteList;
    }

    @XmlTransient
    public List<Muestraempleador> getMuestraempleadorList() {
        return muestraempleadorList;
    }

    public void setMuestraempleadorList(List<Muestraempleador> muestraempleadorList) {
        this.muestraempleadorList = muestraempleadorList;
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
        if (!(object instanceof Muestrapersona)) {
            return false;
        }
        Muestrapersona other = (Muestrapersona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Muestrapersona[ id=" + id + " ]";
    }
    
}
