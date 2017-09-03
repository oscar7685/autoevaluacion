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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "respuestas", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respuestas.findAll", query = "SELECT r FROM Respuestas r"),
    @NamedQuery(name = "Respuestas.findByIdrespuestas", query = "SELECT r FROM Respuestas r WHERE r.idrespuestas = :idrespuestas"),
    @NamedQuery(name = "Respuestas.findByPreguntaRol", query = "SELECT r FROM Respuestas r JOIN r.participanteIdparticipante p  JOIN p.participanteHasRolList rl WHERE r.preguntaId = :idPregunta and rl.rolId =:idRol"),
    @NamedQuery(name = "Respuestas.findByEncuesta", query = "SELECT r FROM Respuestas r JOIN r.preguntaId p JOIN p.encuestaList en WHERE :idEncuesta MEMBER OF p.encuestaList"),
    @NamedQuery(name = "Respuestas.findByFactor", query = "SELECT r FROM Respuestas r JOIN r.preguntaId p JOIN p.preguntaPadre padre JOIN padre.caracteristicaList ca  WHERE ca.factorId= :idFactor"),
    @NamedQuery(name = "Respuestas.findByPreguntaRolxPrograma", query = "SELECT r FROM Respuestas r JOIN r.participanteIdparticipante p JOIN p.participanteHasRolList rl WHERE r.preguntaId = :idPregunta and rl.rolId =:idRol and r.programaId =:idPrograma"),
    @NamedQuery(name = "Respuestas.findByRespuesta", query = "SELECT r FROM Respuestas r WHERE r.respuesta = :respuesta")})
public class Respuestas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrespuestas")
    private Integer idrespuestas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "respuesta")
    private int respuesta;
    @JoinColumn(name = "programa_id", referencedColumnName = "id")
    @ManyToOne
    private Programa programaId;
    @JoinColumn(name = "participante_idparticipante", referencedColumnName = "idparticipante")
    @ManyToOne(optional = false)
    private Participante participanteIdparticipante;
    @JoinColumn(name = "pregunta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pregunta preguntaId;

    public Respuestas() {
    }

    public Respuestas(Integer idrespuestas) {
        this.idrespuestas = idrespuestas;
    }

    public Respuestas(Integer idrespuestas, int respuesta) {
        this.idrespuestas = idrespuestas;
        this.respuesta = respuesta;
    }

    public Integer getIdrespuestas() {
        return idrespuestas;
    }

    public void setIdrespuestas(Integer idrespuestas) {
        this.idrespuestas = idrespuestas;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public Programa getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Programa programaId) {
        this.programaId = programaId;
    }

    public Participante getParticipanteIdparticipante() {
        return participanteIdparticipante;
    }

    public void setParticipanteIdparticipante(Participante participanteIdparticipante) {
        this.participanteIdparticipante = participanteIdparticipante;
    }

    public Pregunta getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Pregunta preguntaId) {
        this.preguntaId = preguntaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrespuestas != null ? idrespuestas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestas)) {
            return false;
        }
        Respuestas other = (Respuestas) object;
        if ((this.idrespuestas == null && other.idrespuestas != null) || (this.idrespuestas != null && !this.idrespuestas.equals(other.idrespuestas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Respuestas[ idrespuestas=" + idrespuestas + " ]";
    }
    
}
