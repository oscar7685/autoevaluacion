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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "muestraestudiante", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muestraestudiante.findAll", query = "SELECT m FROM Muestraestudiante m"),
    @NamedQuery(name = "Muestraestudiante.findById", query = "SELECT m FROM Muestraestudiante m WHERE m.id = :id"),
    @NamedQuery(name = "Muestraestudiante.findByCodigo", query = "SELECT m FROM Muestraestudiante m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Muestraestudiante.findBySemestre", query = "SELECT m FROM Muestraestudiante m WHERE m.semestre = :semestre"),
    @NamedQuery(name = "Muestraestudiante.findByPeriodo", query = "SELECT m FROM Muestraestudiante m WHERE m.periodo = :periodo"),
    @NamedQuery(name = "Muestraestudiante.findByMuestraPersonaId", query = "SELECT m FROM Muestraestudiante m WHERE m.muestrapersonaId = :muestrapersona"),
    @NamedQuery(name = "Muestraestudiante.findByAnio", query = "SELECT m FROM Muestraestudiante m WHERE m.anio = :anio")})
public class Muestraestudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "semestre")
    private String semestre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "periodo")
    private String periodo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "anio")
    private String anio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "programa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Programa programaId;
    @JoinColumn(name = "muestrapersona_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Muestrapersona muestrapersonaId;
    @Size(max = 45)
    @Column(name = "tp")
    private String tp;
    @Size(max = 45)
    @Column(name = "curso")
    private String curso;

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }
    public Muestraestudiante() {
    }

    public Muestraestudiante(Integer id) {
        this.id = id;
    }

    public Muestraestudiante(Integer id, String codigo, String semestre, String periodo, String anio, String tipo) {
        this.id = id;
        this.codigo = codigo;
        this.semestre = semestre;
        this.periodo = periodo;
        this.anio = anio;
        this.tipo = tipo;
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

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Programa getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Programa programaId) {
        this.programaId = programaId;
    }

    public Muestrapersona getMuestrapersonaId() {
        return muestrapersonaId;
    }

    public void setMuestrapersonaId(Muestrapersona muestrapersonaId) {
        this.muestrapersonaId = muestrapersonaId;
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
        if (!(object instanceof Muestraestudiante)) {
            return false;
        }
        Muestraestudiante other = (Muestraestudiante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Muestraestudiante[ id=" + id + " ]";
    }
    
}
