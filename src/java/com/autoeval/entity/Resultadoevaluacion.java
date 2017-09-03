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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "resultadoevaluacion", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultadoevaluacion.findAll", query = "SELECT r FROM Resultadoevaluacion r"),
    @NamedQuery(name = "Resultadoevaluacion.findById", query = "SELECT r FROM Resultadoevaluacion r WHERE r.id = :id"),
    @NamedQuery(name = "Resultadoevaluacion.findByEncabezado", query = "SELECT r FROM Resultadoevaluacion r WHERE r.encabezadoId = :encabezado order by r.preguntaId.id asc"),
    @NamedQuery(name = "Resultadoevaluacion.findResultadosxPreguntaxEncuestaxProceso", query = "SELECT r FROM Resultadoevaluacion r WHERE r.preguntaId=:pregunta and r.encabezadoId.encuestaId=:encuesta and r.encabezadoId.procesoId=:proceso and r.encabezadoId.estado='terminado' and (r.respuesta='5' or r.respuesta='4' or r.respuesta='3' or r.respuesta='2' or r.respuesta='1')"),
    @NamedQuery(name = "Resultadoevaluacion.findResultadosxPreguntaxEncuestaxProcesoxValor", query = "SELECT r FROM Resultadoevaluacion r WHERE r.preguntaId=:pregunta and r.encabezadoId.encuestaId=:encuesta and r.encabezadoId.procesoId=:proceso and r.encabezadoId.estado='terminado' and r.respuesta=:valor"),
    @NamedQuery(name = "Resultadoevaluacion.findResultadosxPreguntaxEncuestaxProcesoxValor1", query = "SELECT SUM(CASE WHEN r.respuesta = 1 THEN 1 ELSE 0 END) AS Result1, SUM(CASE WHEN r.respuesta = 2 THEN 1 ELSE 0 END) AS Result2, SUM(CASE WHEN r.respuesta = 3 THEN 1 ELSE 0 END) AS Result3, SUM(CASE WHEN r.respuesta = 4 THEN 1 ELSE 0 END) AS Result4, SUM(CASE WHEN r.respuesta = 5 THEN 1 ELSE 0 END) AS Result5, SUM(CASE WHEN r.respuesta = 0 THEN 1 ELSE 0 END) AS Result0 FROM Resultadoevaluacion r  WHERE r.preguntaId=:pregunta and r.encabezadoId.procesoId=:proceso and r.encabezadoId.estado='terminado'"),
    @NamedQuery(name = "Resultadoevaluacion.findResultadosxPreguntaxEncuestaxProcesoxValor2", query = "SELECT SUM(CASE WHEN r.respuesta = 1 THEN 1 ELSE 0 END) AS Result1, SUM(CASE WHEN r.respuesta = 2 THEN 1 ELSE 0 END) AS Result2, SUM(CASE WHEN r.respuesta = 3 THEN 1 ELSE 0 END) AS Result3, SUM(CASE WHEN r.respuesta = 4 THEN 1 ELSE 0 END) AS Result4, SUM(CASE WHEN r.respuesta = 5 THEN 1 ELSE 0 END) AS Result5, SUM(CASE WHEN r.respuesta = 0 THEN 1 ELSE 0 END) AS Result0 FROM Resultadoevaluacion r  WHERE r.preguntaId=:pregunta and r.encabezadoId.procesoId=:proceso and r.encabezadoId.estado='terminado' and r.encabezadoId.encuestaId=:encuesta"),
    @NamedQuery(name = "Resultadoevaluacion.findByRespuesta", query = "SELECT r FROM Resultadoevaluacion r WHERE r.respuesta = :respuesta")})
public class Resultadoevaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 1000)
    @Column(name = "respuesta")
    private String respuesta;
    @Size(max = 1000)
    @Column(name = "respuestaAbierta")
    private String respuestaAbierta;
    @JoinColumn(name = "pregunta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pregunta preguntaId;
    @JoinColumn(name = "encabezado_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Encabezado encabezadoId;

    public Resultadoevaluacion() {
    }

    public Resultadoevaluacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuestaAbierta() {
        return respuestaAbierta;
    }

    public void setRespuestaAbierta(String respuestaAbierta) {
        this.respuestaAbierta = respuestaAbierta;
    }

    public Pregunta getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Pregunta preguntaId) {
        this.preguntaId = preguntaId;
    }

    public Encabezado getEncabezadoId() {
        return encabezadoId;
    }

    public void setEncabezadoId(Encabezado encabezadoId) {
        this.encabezadoId = encabezadoId;
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
        if (!(object instanceof Resultadoevaluacion)) {
            return false;
        }
        Resultadoevaluacion other = (Resultadoevaluacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Resultadoevaluacion[ id=" + id + " ]";
    }
    
}
