
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.mejoramientocontinuo.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.ProcesoEntity;
import co.com.siscomputo.administracion.persistencia.RolesEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MJ_TACRA")
public class AccionesRapidasEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "ACRA_TACRA")
    private Integer idAccionesR;

    @Temporal(TemporalType.DATE)
    @Column(name = "ACRA_FECI")
    private java.util.Date fechaInicioAuditoria;

    @Temporal(TemporalType.DATE)
    @Column(name = "ACRA_FECF")
    private java.util.Date fechaFinAuditoria;

    @Column(name = "ACRA_ESTA")
    private String estadoAuditoria;

    @Column(name = "ACRA_AVAN")
    private Integer avanceAuditoria;

    @JoinColumn(name = "ACRA_CARG")
    @ManyToOne
    private RolesEntity cargoAuditoria;

    @Column(name = "ACRA_RESP")
    private String responsableAuditoria;

    @JoinColumn(name = "ACRA_PROC")
    @ManyToOne 
    private ProcesoEntity procesoAuditoria;

    @Column(name = "ACRA_ORIG")
    private String origenAuditoria;

    @Column(name = "ACRA_UNI")
    private String unidadNegocio;

    @Column(name = "ACRA_EMPR")
    private String empresaAuditoria;

    @Column(name = "ACRA_CAUS")
    private String causaAuditoria;

    @Column(name = "ACRA_APRO")
    private String rutaArchivo;

    @Column(name = "ACRA_CREA")
    private String creadorAuditoria;

    public Integer getIdAccionesR() {
        return idAccionesR;
    }

    public void setIdAccionesR(Integer idAccionesR) {
        this.idAccionesR = idAccionesR;
    }

    public Date getFechaInicioAuditoria() {
        return fechaInicioAuditoria;
    }

    public void setFechaInicioAuditoria(Date fechaInicioAuditoria) {
        this.fechaInicioAuditoria = fechaInicioAuditoria;
    }

    public Date getFechaFinAuditoria() {
        return fechaFinAuditoria;
    }

    public void setFechaFinAuditoria(Date fechaFinAuditoria) {
        this.fechaFinAuditoria = fechaFinAuditoria;
    }

    public String getEstadoAuditoria() {
        return estadoAuditoria;
    }

    public void setEstadoAuditoria(String estadoAuditoria) {
        this.estadoAuditoria = estadoAuditoria;
    }

    public Integer getAvanceAuditoria() {
        return avanceAuditoria;
    }

    public void setAvanceAuditoria(Integer avanceAuditoria) {
        this.avanceAuditoria = avanceAuditoria;
    }

    public RolesEntity getCargoAuditoria() {
        return cargoAuditoria;
    }

    public void setCargoAuditoria(RolesEntity cargoAuditoria) {
        this.cargoAuditoria = cargoAuditoria;
    }

    public String getResponsableAuditoria() {
        return responsableAuditoria;
    }

    public void setResponsableAuditoria(String responsableAuditoria) {
        this.responsableAuditoria = responsableAuditoria;
    }

    public ProcesoEntity getProcesoAuditoria() {
        return procesoAuditoria;
    }

    public void setProcesoAuditoria(ProcesoEntity procesoAuditoria) {
        this.procesoAuditoria = procesoAuditoria;
    }

    

    public String getOrigenAuditoria() {
        return origenAuditoria;
    }

    public void setOrigenAuditoria(String origenAuditoria) {
        this.origenAuditoria = origenAuditoria;
    }

    public String getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(String unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public String getEmpresaAuditoria() {
        return empresaAuditoria;
    }

    public void setEmpresaAuditoria(String empresaAuditoria) {
        this.empresaAuditoria = empresaAuditoria;
    }

    public String getCausaAuditoria() {
        return causaAuditoria;
    }

    public void setCausaAuditoria(String causaAuditoria) {
        this.causaAuditoria = causaAuditoria;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getCreadorAuditoria() {
        return creadorAuditoria;
    }

    public void setCreadorAuditoria(String creadorAuditoria) {
        this.creadorAuditoria = creadorAuditoria;
    }

}
