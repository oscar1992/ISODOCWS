/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.AreaEntity;
import co.com.siscomputo.administracion.persistencia.EmpresaEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioEntity;
import java.io.Serializable;
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
@Table(name = "PRO_TCONT")
public class ContratosEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "CONT_CONT")
    private Integer IdContrato;

    @JoinColumn(name = "CONT_PROV")
    @ManyToOne
    private ProveedoresEntity idProveedorContrato;

    @JoinColumn(name = "CONT_TIPO")
    @ManyToOne
    private TipoProveedorEntity idTipoProveedorContrato;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CONT_FINI")
    private java.util.Date fechaInicialContrato;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CONT_FFIN")
    private java.util.Date fechafinalContrato;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CONT_FEJE")
    private java.util.Date plazoEjecucion;

    @JoinColumn(name = "CONT_USUA")
    @ManyToOne
    private UsuarioEntity supervisorContrato;

    @JoinColumn(name = "CONT_MONE")
    @ManyToOne
    private TipoMonedaEntity idTipoMoneda;

    @Column(name = "CONT_VALO")
    private String valorContrato;

    @JoinColumn(name = "CONT_LINEA")
    @ManyToOne
    private LineaEntity idLineaProveedor;

    @JoinColumn(name = "CONT_EMPR")
    @ManyToOne
    private EmpresaEntity idEmpresaContrato;

    @JoinColumn(name = "CONT_AREA")
    @ManyToOne
    private AreaEntity idAreaContrato;

    @Column(name = "CONT_PROC")
    private Integer procesoContrato;

    @Column(name = "CONT_OBJE")
    private String objetoContrato;

    @Column(name = "CONT_ESTA")
    private String estadoContrato;

    @Column(name = "CONT_DOCU")
    private String documentoContrato;


    public String getEstadoContrato() {
        return estadoContrato;
    }

    public void setEstadoContrato(String estadoContrato) {
        this.estadoContrato = estadoContrato;
    }

    public Integer getIdContrato() {
        return IdContrato;
    }

    public void setIdContrato(Integer IdContrato) {
        this.IdContrato = IdContrato;
    }

    public ProveedoresEntity getIdProveedorContrato() {
        return idProveedorContrato;
    }

    public void setIdProveedorContrato(ProveedoresEntity idProveedorContrato) {
        this.idProveedorContrato = idProveedorContrato;
    }

    public TipoProveedorEntity getIdTipoProveedorContrato() {
        return idTipoProveedorContrato;
    }

    public void setIdTipoProveedorContrato(TipoProveedorEntity idTipoProveedorContrato) {
        this.idTipoProveedorContrato = idTipoProveedorContrato;
    }

    public java.util.Date getFechaInicialContrato() {
        return fechaInicialContrato;
    }

    public void setFechaInicialContrato(java.util.Date fechaInicialContrato) {
        this.fechaInicialContrato = fechaInicialContrato;
    }

    public java.util.Date getFechafinalContrato() {
        return fechafinalContrato;
    }

    public void setFechafinalContrato(java.util.Date fechafinalContrato) {
        this.fechafinalContrato = fechafinalContrato;
    }

    public java.util.Date getPlazoEjecucion() {
        return plazoEjecucion;
    }

    public void setPlazoEjecucion(java.util.Date plazoEjecucion) {
        this.plazoEjecucion = plazoEjecucion;
    }

    public UsuarioEntity getSupervisorContrato() {
        return supervisorContrato;
    }

    public void setSupervisorContrato(UsuarioEntity supervisorContrato) {
        this.supervisorContrato = supervisorContrato;
    }

    public TipoMonedaEntity getIdTipoMoneda() {
        return idTipoMoneda;
    }

    public void setIdTipoMoneda(TipoMonedaEntity idTipoMoneda) {
        this.idTipoMoneda = idTipoMoneda;
    }

    public String getValorContrato() {
        return valorContrato;
    }

    public void setValorContrato(String valorContrato) {
        this.valorContrato = valorContrato;
    }

    public LineaEntity getIdLineaProveedor() {
        return idLineaProveedor;
    }

    public void setIdLineaProveedor(LineaEntity idLineaProveedor) {
        this.idLineaProveedor = idLineaProveedor;
    }

    public EmpresaEntity getIdEmpresaContrato() {
        return idEmpresaContrato;
    }

    public void setIdEmpresaContrato(EmpresaEntity idEmpresaContrato) {
        this.idEmpresaContrato = idEmpresaContrato;
    }

    public AreaEntity getIdAreaContrato() {
        return idAreaContrato;
    }

    public void setIdAreaContrato(AreaEntity idAreaContrato) {
        this.idAreaContrato = idAreaContrato;
    }

    public Integer getProcesoContrato() {
        return procesoContrato;
    }

    public void setProcesoContrato(Integer procesoContrato) {
        this.procesoContrato = procesoContrato;
    }

    public String getObjetoContrato() {
        return objetoContrato;
    }

    public void setObjetoContrato(String objetoContrato) {
        this.objetoContrato = objetoContrato;
    }

    public String getDocumentoContrato() {
        return documentoContrato;
    }

    public void setDocumentoContrato(String documentoContrato) {
        this.documentoContrato = documentoContrato;
    }
    
}

