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
@Table(name = "glosario", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Glosario.findAll", query = "SELECT g FROM Glosario g"),
    @NamedQuery(name = "Glosario.findByIdglosario", query = "SELECT g FROM Glosario g WHERE g.idglosario = :idglosario"),
    @NamedQuery(name = "Glosario.findByPalabra", query = "SELECT g FROM Glosario g WHERE g.palabra = :palabra"),
    @NamedQuery(name = "Glosario.findBySignificado", query = "SELECT g FROM Glosario g WHERE g.significado = :significado")})
public class Glosario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idglosario")
    private Integer idglosario;
    @Size(max = 50)
    @Column(name = "palabra")
    private String palabra;
    @Size(max = 500)
    @Column(name = "significado")
    private String significado;

    public Glosario() {
    }

    public Glosario(Integer idglosario) {
        this.idglosario = idglosario;
    }

    public Integer getIdglosario() {
        return idglosario;
    }

    public void setIdglosario(Integer idglosario) {
        this.idglosario = idglosario;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idglosario != null ? idglosario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Glosario)) {
            return false;
        }
        Glosario other = (Glosario) object;
        if ((this.idglosario == null && other.idglosario != null) || (this.idglosario != null && !this.idglosario.equals(other.idglosario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Glosario[ idglosario=" + idglosario + " ]";
    }
    
}
