/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.autoeval.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "representante", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Representante.findAll", query = "SELECT r FROM Representante r"),
    @NamedQuery(name = "Representante.findById", query = "SELECT r FROM Representante r WHERE r.id = :id"),
    @NamedQuery(name = "Representante.findByNombre", query = "SELECT r FROM Representante r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Representante.findByApellido", query = "SELECT r FROM Representante r WHERE r.apellido = :apellido"),
    @NamedQuery(name = "Representante.findByPassword", query = "SELECT r FROM Representante r WHERE r.password = :password"),
    @NamedQuery(name = "Representante.findByRol", query = "SELECT r FROM Representante r WHERE r.rol = :rol"),
    @NamedQuery(name = "Representante.findByMail", query = "SELECT r FROM Representante r WHERE r.mail = :mail")})
public class Representante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rol")
    private String rol;
    @Size(max = 45)
    @Column(name = "mail")
    private String mail;
    @ManyToMany(mappedBy = "representanteList")
    private List<Programa> programaList;
    @JoinTable(name = "representantehasprivilegio", joinColumns = {
        @JoinColumn(name = "representante_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "privilegio_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Privilegio> privilegioList;

    public Representante() {
    }

    public Representante(Integer id) {
        this.id = id;
    }

    public Representante(Integer id, String nombre, String apellido, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @XmlTransient
    public List<Programa> getProgramaList() {
        return programaList;
    }

    public void setProgramaList(List<Programa> programaList) {
        this.programaList = programaList;
    }

    @XmlTransient
    public List<Privilegio> getPrivilegioList() {
        return privilegioList;
    }

    public void setPrivilegioList(List<Privilegio> privilegioList) {
        this.privilegioList = privilegioList;
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
        if (!(object instanceof Representante)) {
            return false;
        }
        Representante other = (Representante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Representante[ id=" + id + " ]";
    }
    
}
