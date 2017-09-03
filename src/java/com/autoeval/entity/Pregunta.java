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
 * @author PAPEN
 */
@Entity
@Table(name = "pregunta", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
    @NamedQuery(name = "Pregunta.findById", query = "SELECT p FROM Pregunta p WHERE p.id = :id"),
    @NamedQuery(name = "Pregunta.findByCodigo", query = "SELECT p FROM Pregunta p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Pregunta.findByPregunta", query = "SELECT p FROM Pregunta p WHERE p.pregunta = :pregunta"),
    @NamedQuery(name = "Pregunta.findByTipo", query = "SELECT p FROM Pregunta p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Pregunta.findByCaracteristica", query = "SELECT p FROM Pregunta p WHERE  :caracteristica MEMBER OF p.caracteristicaList"),
    @NamedQuery(name = "Pregunta.findByModelo", query = "SELECT p FROM Pregunta p WHERE p.modeloId = :modelo")})
public class Pregunta implements Serializable, Comparable<Pregunta> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "pregunta")
    private String pregunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 45)
    @Column(name = "repetir")
    private String repetir;
    @ManyToMany(mappedBy = "preguntaList")
    private List<Encuesta> encuestaList;
    @JoinTable(name = "pregunta_has_caracteristica", joinColumns = {
        @JoinColumn(name = "pregunta_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "caracteristica_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Caracteristica> caracteristicaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preguntaId")
    private List<Respuestas> respuestasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preguntaId")
    private List<Respuesta> respuestaList;
    @OneToMany(mappedBy = "preguntaPadre")
    private List<Pregunta> preguntaList;
    @JoinColumn(name = "pregunta_padre", referencedColumnName = "id")
    @ManyToOne
    private Pregunta preguntaPadre;
    @JoinColumn(name = "modelo_id", referencedColumnName = "id")
    @ManyToOne
    private Modelo modeloId;
    @JoinColumn(name = "indicador_id", referencedColumnName = "id")
    @ManyToOne
    private Indicador indicadorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preguntaCondicionada")
    private List<Condicion> condicionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preguntaCondicionadora")
    private List<Condicion> condicionList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preguntaId")
    private List<Resultadoevaluacion> resultadoevaluacionList;

    public Pregunta() {
    }

    public Pregunta(Integer id) {
        this.id = id;
    }

    public Pregunta(Integer id, String pregunta, String tipo) {
        this.id = id;
        this.pregunta = pregunta;
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

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRepetir() {
        return repetir;
    }

    public void setRepetir(String repetir) {
        this.repetir = repetir;
    }

    @XmlTransient
    public List<Encuesta> getEncuestaList() {
        return encuestaList;
    }

    public void setEncuestaList(List<Encuesta> encuestaList) {
        this.encuestaList = encuestaList;
    }

    @XmlTransient
    public List<Caracteristica> getCaracteristicaList() {
        return caracteristicaList;
    }

    public void setCaracteristicaList(List<Caracteristica> caracteristicaList) {
        this.caracteristicaList = caracteristicaList;
    }

    @XmlTransient
    public List<Respuestas> getRespuestasList() {
        return respuestasList;
    }

    public void setRespuestasList(List<Respuestas> respuestasList) {
        this.respuestasList = respuestasList;
    }

    @XmlTransient
    public List<Respuesta> getRespuestaList() {
        return respuestaList;
    }

    public void setRespuestaList(List<Respuesta> respuestaList) {
        this.respuestaList = respuestaList;
    }

    @XmlTransient
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    public Pregunta getPreguntaPadre() {
        return preguntaPadre;
    }

    public void setPreguntaPadre(Pregunta preguntaPadre) {
        this.preguntaPadre = preguntaPadre;
    }

    public Modelo getModeloId() {
        return modeloId;
    }

    public void setModeloId(Modelo modeloId) {
        this.modeloId = modeloId;
    }

    public Indicador getIndicadorId() {
        return indicadorId;
    }

    public void setIndicadorId(Indicador indicadorId) {
        this.indicadorId = indicadorId;
    }

    @XmlTransient
    public List<Condicion> getCondicionList() {
        return condicionList;
    }

    public void setCondicionList(List<Condicion> condicionList) {
        this.condicionList = condicionList;
    }

    @XmlTransient
    public List<Condicion> getCondicionList1() {
        return condicionList1;
    }

    public void setCondicionList1(List<Condicion> condicionList1) {
        this.condicionList1 = condicionList1;
    }

    @XmlTransient
    public List<Resultadoevaluacion> getResultadoevaluacionList() {
        return resultadoevaluacionList;
    }

    public void setResultadoevaluacionList(List<Resultadoevaluacion> resultadoevaluacionList) {
        this.resultadoevaluacionList = resultadoevaluacionList;
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
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
        public int compareTo(Pregunta o) {
        if (id < o.id) {
            return -1;
        }
        if (id > o.id) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Pregunta[ id=" + id + " ]";
    }
}
