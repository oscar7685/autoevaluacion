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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "objetivos", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetivos.findAll", query = "SELECT o FROM Objetivos o"),
    @NamedQuery(name = "Objetivos.findByIdobjetivos", query = "SELECT o FROM Objetivos o WHERE o.idobjetivos = :idobjetivos"),
    @NamedQuery(name = "Objetivos.findByObjetivo", query = "SELECT o FROM Objetivos o WHERE o.objetivo = :objetivo")})
public class Objetivos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idobjetivos")
    private Integer idobjetivos;
    @Size(max = 2000)
    @Column(name = "objetivo")
    private String objetivo;
    @JoinColumn(name = "hallazgo_idhallazgo", referencedColumnName = "idhallazgo")
    @ManyToOne(optional = false)
    private Hallazgo hallazgoIdhallazgo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivosIdobjetivos")
    private List<Metas> metasList;

    public Objetivos() {
    }

    public Objetivos(Integer idobjetivos) {
        this.idobjetivos = idobjetivos;
    }

    public Integer getIdobjetivos() {
        return idobjetivos;
    }

    public void setIdobjetivos(Integer idobjetivos) {
        this.idobjetivos = idobjetivos;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Hallazgo getHallazgoIdhallazgo() {
        return hallazgoIdhallazgo;
    }

    public void setHallazgoIdhallazgo(Hallazgo hallazgoIdhallazgo) {
        this.hallazgoIdhallazgo = hallazgoIdhallazgo;
    }

    @XmlTransient
    public List<Metas> getMetasList() {
        return metasList;
    }

    public void setMetasList(List<Metas> metasList) {
        this.metasList = metasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idobjetivos != null ? idobjetivos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objetivos)) {
            return false;
        }
        Objetivos other = (Objetivos) object;
        if ((this.idobjetivos == null && other.idobjetivos != null) || (this.idobjetivos != null && !this.idobjetivos.equals(other.idobjetivos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Objetivos[ idobjetivos=" + idobjetivos + " ]";
    }
    
}
