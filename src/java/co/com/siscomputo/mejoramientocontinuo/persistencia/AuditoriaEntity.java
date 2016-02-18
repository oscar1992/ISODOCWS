/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.mejoramientocontinuo.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioEntity;
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
@Table(name = "MJ_TAUDI")
public class AuditoriaEntity extends ObjetoRetornaEntity implements Serializable {
    
    
    @Id
    @Column(name ="AUDI_AUDI")
    private Integer idAuditoria;    
    
    @JoinColumn(name = "AUDI_AUTO")
    @ManyToOne
    private UsuarioEntity autorAuditoria;
    
    @Column(name = "AUDI_ESTA")
    private String estadoAuditoria;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "AUDI_FECC")
    private java.util.Date fechaAuditoria;
    
    @Column(name = "AUDI_CODI")
    private String codigoAuditoria;
    
    @Column(name = "AUDI_UNID")
    private String unidadAuditoria;
    
    
    @Column(name = "AUDI_SEDE")
    private String sedeAuditoria;
    
    
    @Temporal(TemporalType.DATE)
    @Column(name = "AUDI_FECI")
    private java.util.Date fechaInicioAuditoria;
    
    
    @Temporal(TemporalType.DATE)
    @Column(name = "AUDI_FECF")
    private java.util.Date fechaFinalAuditoria;

    public Integer getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public UsuarioEntity getAutorAuditoria() {
        return autorAuditoria;
    }

    public void setAutorAuditoria(UsuarioEntity autorAuditoria) {
        this.autorAuditoria = autorAuditoria;
    }

    public String getEstadoAuditoria() {
        return estadoAuditoria;
    }

    public void setEstadoAuditoria(String estadoAuditoria) {
        this.estadoAuditoria = estadoAuditoria;
    }

    public Date getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(Date fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public String getCodigoAuditoria() {
        return codigoAuditoria;
    }

    public void setCodigoAuditoria(String codigoAuditoria) {
        this.codigoAuditoria = codigoAuditoria;
    }

    public String getUnidadAuditoria() {
        return unidadAuditoria;
    }

    public void setUnidadAuditoria(String unidadAuditoria) {
        this.unidadAuditoria = unidadAuditoria;
    }

    public String getSedeAuditoria() {
        return sedeAuditoria;
    }

    public void setSedeAuditoria(String sedeAuditoria) {
        this.sedeAuditoria = sedeAuditoria;
    }

    public Date getFechaInicioAuditoria() {
        return fechaInicioAuditoria;
    }

    public void setFechaInicioAuditoria(Date fechaInicioAuditoria) {
        this.fechaInicioAuditoria = fechaInicioAuditoria;
    }

    public Date getFechaFinalAuditoria() {
        return fechaFinalAuditoria;
    }

    public void setFechaFinalAuditoria(Date fechaFinalAuditoria) {
        this.fechaFinalAuditoria = fechaFinalAuditoria;
    }
    
    
    
    
}
