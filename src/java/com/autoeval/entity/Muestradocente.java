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
@Table(name = "muestradocente", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muestradocente.findAll", query = "SELECT m FROM Muestradocente m"),
    @NamedQuery(name = "Muestradocente.findById", query = "SELECT m FROM Muestradocente m WHERE m.id = :id"),
    @NamedQuery(name = "Muestradocente.findByMuestraPersonaId", query = "SELECT m FROM Muestradocente m WHERE m.muestrapersonaId = :muestrapersona"),
    @NamedQuery(name = "Muestradocente.findByMuestraConEncabezado", query = "SELECT m FROM Muestradocente m join m.muestrapersonaId mp join mp.encabezadoList en WHERE en.muestrapersonaId = mp and en.procesoId=:proceso"),
    @NamedQuery(name = "Muestradocente.findByMuestraSinEncabezado", query = "SELECT m2 FROM Muestradocente m2 where m2.id NOT IN (SELECT m.id FROM Muestradocente m join m.muestrapersonaId mp join mp.encabezadoList en WHERE en.muestrapersonaId = mp and en.procesoId=:proceso) and m2.muestrapersonaId.muestraId.procesoId =:proceso"),
    @NamedQuery(name = "Muestradocente.findByTipo", query = "SELECT m FROM Muestradocente m WHERE m.tipo = :tipo")})
public class Muestradocente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "muestrapersona_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Muestrapersona muestrapersonaId;
    @Size(max = 45)
    @Column(name = "tp")
    private String tp;

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }
    public Muestradocente() {
    }

    public Muestradocente(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof Muestradocente)) {
            return false;
        }
        Muestradocente other = (Muestradocente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Muestradocente[ id=" + id + " ]";
    }
}
