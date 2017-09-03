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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "participante_has_rol", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParticipanteHasRol.findAll", query = "SELECT p FROM ParticipanteHasRol p"),
    @NamedQuery(name = "ParticipanteHasRol.findByParticipanteHasRolcol", query = "SELECT p FROM ParticipanteHasRol p WHERE p.participanteHasRolcol = :participanteHasRolcol")})
public class ParticipanteHasRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "participante_has_rolcol")
    private Integer participanteHasRolcol;
    @JoinColumn(name = "programa_id", referencedColumnName = "id")
    @ManyToOne
    private Programa programaId;
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Rol rolId;
    @JoinColumn(name = "participante_idparticipante", referencedColumnName = "idparticipante")
    @ManyToOne(optional = false)
    private Participante participanteIdparticipante;

    public ParticipanteHasRol() {
    }

    public ParticipanteHasRol(Integer participanteHasRolcol) {
        this.participanteHasRolcol = participanteHasRolcol;
    }

    public Integer getParticipanteHasRolcol() {
        return participanteHasRolcol;
    }

    public void setParticipanteHasRolcol(Integer participanteHasRolcol) {
        this.participanteHasRolcol = participanteHasRolcol;
    }

    public Programa getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Programa programaId) {
        this.programaId = programaId;
    }

    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
    }

    public Participante getParticipanteIdparticipante() {
        return participanteIdparticipante;
    }

    public void setParticipanteIdparticipante(Participante participanteIdparticipante) {
        this.participanteIdparticipante = participanteIdparticipante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (participanteHasRolcol != null ? participanteHasRolcol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParticipanteHasRol)) {
            return false;
        }
        ParticipanteHasRol other = (ParticipanteHasRol) object;
        if ((this.participanteHasRolcol == null && other.participanteHasRolcol != null) || (this.participanteHasRolcol != null && !this.participanteHasRolcol.equals(other.participanteHasRolcol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.ParticipanteHasRol[ participanteHasRolcol=" + participanteHasRolcol + " ]";
    }
    
}
