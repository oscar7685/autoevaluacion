/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.autoeval.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "metas", catalog = "autoeva", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Metas.findAll", query = "SELECT m FROM Metas m"),
    @NamedQuery(name = "Metas.findByIdmeta", query = "SELECT m FROM Metas m WHERE m.idmeta = :idmeta"),
    @NamedQuery(name = "Metas.findByEstrategia", query = "SELECT m FROM Metas m WHERE m.estrategia = :estrategia"),
    @NamedQuery(name = "Metas.findByMeta", query = "SELECT m FROM Metas m WHERE m.meta = :meta"),
    @NamedQuery(name = "Metas.findByIndicadorCumplimiento", query = "SELECT m FROM Metas m WHERE m.indicadorCumplimiento = :indicadorCumplimiento"),
    @NamedQuery(name = "Metas.findByResponsable", query = "SELECT m FROM Metas m WHERE m.responsable = :responsable"),
    @NamedQuery(name = "Metas.findByFinanciacion", query = "SELECT m FROM Metas m WHERE m.financiacion = :financiacion"),
    @NamedQuery(name = "Metas.findByEstado", query = "SELECT m FROM Metas m WHERE m.estado = :estado"),
    @NamedQuery(name = "Metas.findByPorcentajeCumplimiento", query = "SELECT m FROM Metas m WHERE m.porcentajeCumplimiento = :porcentajeCumplimiento"),
    @NamedQuery(name = "Metas.findByObservaciones", query = "SELECT m FROM Metas m WHERE m.observaciones = :observaciones"),
    @NamedQuery(name = "Metas.findByEstadoInterno", query = "SELECT m FROM Metas m WHERE m.estadoInterno = :estadoInterno"),
    @NamedQuery(name = "Metas.findByFechaInicio", query = "SELECT m FROM Metas m WHERE m.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Metas.findByFechaFinal", query = "SELECT m FROM Metas m WHERE m.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "Metas.findByFechaSeguimiento", query = "SELECT m FROM Metas m WHERE m.fechaSeguimiento = :fechaSeguimiento"),
    @NamedQuery(name = "Metas.findByCorreos", query = "SELECT m FROM Metas m WHERE m.correos = :correos"),
    @NamedQuery(name = "Metas.findByContinuarEnvio", query = "SELECT m FROM Metas m WHERE m.continuarEnvio = :continuarEnvio"),
    @NamedQuery(name = "Metas.findByEnviarCorreoCada", query = "SELECT m FROM Metas m WHERE m.enviarCorreoCada = :enviarCorreoCada")})
public class Metas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmeta")
    private Integer idmeta;
    @Size(max = 2000)
    @Column(name = "estrategia")
    private String estrategia;
    @Size(max = 2000)
    @Column(name = "meta")
    private String meta;
    @Size(max = 2000)
    @Column(name = "indicador_cumplimiento")
    private String indicadorCumplimiento;
    @Size(max = 2000)
    @Column(name = "responsable")
    private String responsable;
    @Size(max = 2000)
    @Column(name = "financiacion")
    private String financiacion;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @Column(name = "porcentaje_cumplimiento")
    private Integer porcentajeCumplimiento;
    @Size(max = 45)
    @Column(name = "observaciones")
    private String observaciones;
    @Size(max = 45)
    @Column(name = "estado_interno")
    private String estadoInterno;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Column(name = "fecha_seguimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaSeguimiento;
    @Size(max = 1000)
    @Column(name = "correos")
    private String correos;
    @Size(max = 45)
    @Column(name = "continuar_envio")
    private String continuarEnvio;
    @Size(max = 45)
    @Column(name = "enviar_correo_cada")
    private String enviarCorreoCada;
    @JoinColumn(name = "objetivos_idobjetivos", referencedColumnName = "idobjetivos")
    @ManyToOne(optional = false)
    private Objetivos objetivosIdobjetivos;

    public Metas() {
    }

    public Metas(Integer idmeta) {
        this.idmeta = idmeta;
    }

    public Integer getIdmeta() {
        return idmeta;
    }

    public void setIdmeta(Integer idmeta) {
        this.idmeta = idmeta;
    }

    public String getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(String estrategia) {
        this.estrategia = estrategia;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getIndicadorCumplimiento() {
        return indicadorCumplimiento;
    }

    public void setIndicadorCumplimiento(String indicadorCumplimiento) {
        this.indicadorCumplimiento = indicadorCumplimiento;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getFinanciacion() {
        return financiacion;
    }

    public void setFinanciacion(String financiacion) {
        this.financiacion = financiacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getPorcentajeCumplimiento() {
        return porcentajeCumplimiento;
    }

    public void setPorcentajeCumplimiento(Integer porcentajeCumplimiento) {
        this.porcentajeCumplimiento = porcentajeCumplimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstadoInterno() {
        return estadoInterno;
    }

    public void setEstadoInterno(String estadoInterno) {
        this.estadoInterno = estadoInterno;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(Date fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    public String getCorreos() {
        return correos;
    }

    public void setCorreos(String correos) {
        this.correos = correos;
    }

    public String getContinuarEnvio() {
        return continuarEnvio;
    }

    public void setContinuarEnvio(String continuarEnvio) {
        this.continuarEnvio = continuarEnvio;
    }

    public String getEnviarCorreoCada() {
        return enviarCorreoCada;
    }

    public void setEnviarCorreoCada(String enviarCorreoCada) {
        this.enviarCorreoCada = enviarCorreoCada;
    }

    public Objetivos getObjetivosIdobjetivos() {
        return objetivosIdobjetivos;
    }

    public void setObjetivosIdobjetivos(Objetivos objetivosIdobjetivos) {
        this.objetivosIdobjetivos = objetivosIdobjetivos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmeta != null ? idmeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metas)) {
            return false;
        }
        Metas other = (Metas) object;
        if ((this.idmeta == null && other.idmeta != null) || (this.idmeta != null && !this.idmeta.equals(other.idmeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autoeval.entity.Metas[ idmeta=" + idmeta + " ]";
    }
    
}
