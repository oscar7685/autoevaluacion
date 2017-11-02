/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "participante", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Participante.findAll", query = "SELECT p FROM Participante p"),
    @NamedQuery(name = "Participante.findByIdparticipante", query = "SELECT p FROM Participante p WHERE p.idparticipante = :idparticipante"),
    @NamedQuery(name = "Participante.findByFechainicio", query = "SELECT p FROM Participante p WHERE p.fechainicio = :fechainicio"),
    @NamedQuery(name = "Participante.findByFechafinal", query = "SELECT p FROM Participante p WHERE p.fechafinal = :fechafinal")})
public class Participante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 15)
    @Basic(optional = false)
    @Column(name = "idparticipante")
    private String idparticipante;
    @Size(max = 500)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 15)
    @Column(name = "cedula")
    private String cedula;
    @Size(max = 500)
    @Column(name = "cargo")
    private String cargo;
    @Size(max = 500)
    @Column(name = "ccosto")
    private String ccosto;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainicio;
    @Column(name = "fechafinal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafinal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participanteIdparticipante")
    private List<Respuestas> respuestasList;
    @JoinColumn(name = "proceso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proceso procesoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participanteIdparticipante")
    private List<ParticipanteHasRol> participanteHasRolList;

    public Participante() {
    }

    public Participante(String idparticipante) {
        this.idparticipante = idparticipante;
    }

    public String getIdparticipante() {
        return idparticipante;
    }

    public void setIdparticipante(String idparticipante) {
        this.idparticipante = idparticipante;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getCcosto() {
        return ccosto;
    }
    public void setCcosto(String ccosto) {
        this.ccosto = ccosto;
    }
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }

    @XmlTransient
    public List<Respuestas> getRespuestasList() {
        return respuestasList;
    }

    public void setRespuestasList(List<Respuestas> respuestasList) {
        this.respuestasList = respuestasList;
    }

    public Proceso getProcesoId() {
        return procesoId;
    }

    public void setProcesoId(Proceso procesoId) {
        this.procesoId = procesoId;
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
        hash += (idparticipante != null ? idparticipante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participante)) {
            return false;
        }
        Participante other = (Participante) object;
        if ((this.idparticipante == null && other.idparticipante != null) || (this.idparticipante != null && !this.idparticipante.equals(other.idparticipante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Participante[ idparticipante=" + idparticipante + " ]";
    }
    
}
