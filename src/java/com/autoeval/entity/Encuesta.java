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
@Table(name = "encuesta", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encuesta.findAll", query = "SELECT e FROM Encuesta e"),
    @NamedQuery(name = "Encuesta.findById", query = "SELECT e FROM Encuesta e WHERE e.id = :id"),
    @NamedQuery(name = "Encuesta.findByCodigo", query = "SELECT e FROM Encuesta e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "Encuesta.findByNombre", query = "SELECT e FROM Encuesta e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Encuesta.findByObjetivo", query = "SELECT e FROM Encuesta e WHERE e.objetivo = :objetivo"),
    @NamedQuery(name = "Encuesta.findByInstrucciones", query = "SELECT e FROM Encuesta e WHERE e.instrucciones = :instrucciones"),
    @NamedQuery(name = "Encuesta.findByVersion", query = "SELECT e FROM Encuesta e WHERE e.version = :version"),
    @NamedQuery(name = "Encuesta.findByFecha", query = "SELECT e FROM Encuesta e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "Encuesta.findByPreguntaYModelo", query = "SELECT e FROM Encuesta e WHERE e.modeloId = :modelo and :pregunta MEMBER OF e.preguntaList"),
    @NamedQuery(name = "Encuesta.findByModelo", query = "SELECT e FROM Encuesta e WHERE e.modeloId = :modelo")})
public class Encuesta implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "objetivo")
    private String objetivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "instrucciones")
    private String instrucciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "version")
    private String version;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fecha")
    private String fecha;
    @JoinTable(name = "encuestahaspregunta", joinColumns = {
        @JoinColumn(name = "encuesta_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "pregunta_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Pregunta> preguntaList;
    @JoinColumn(name = "modelo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Modelo modeloId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "encuestaId")
    private List<Asignacionencuesta> asignacionencuestaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "encuestaId")
    private List<Encabezado> encabezadoList;

    public Encuesta() {
    }

    public Encuesta(Integer id) {
        this.id = id;
    }

    public Encuesta(Integer id, String codigo, String nombre, String objetivo, String instrucciones, String version, String fecha) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.objetivo = objetivo;
        this.instrucciones = instrucciones;
        this.version = version;
        this.fecha = fecha;
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

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    public Modelo getModeloId() {
        return modeloId;
    }

    public void setModeloId(Modelo modeloId) {
        this.modeloId = modeloId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encuesta)) {
            return false;
        }
        Encuesta other = (Encuesta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Encuesta[ id=" + id + " ]";
    }
    
}
