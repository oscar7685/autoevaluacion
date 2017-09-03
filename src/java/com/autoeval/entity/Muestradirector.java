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
@Table(name = "muestradirector", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muestradirector.findAll", query = "SELECT m FROM Muestradirector m"),
    @NamedQuery(name = "Muestradirector.findByMuestraPersonaId", query = "SELECT m FROM Muestradirector m WHERE m.muestrapersonaId= :muestrapersona"),
    @NamedQuery(name = "Muestradirector.findById", query = "SELECT m FROM Muestradirector m WHERE m.id = :id")})
public class Muestradirector implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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

    public Muestradirector() {
    }

    public Muestradirector(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Muestradirector)) {
            return false;
        }
        Muestradirector other = (Muestradirector) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Muestradirector[ id=" + id + " ]";
    }
    
}
