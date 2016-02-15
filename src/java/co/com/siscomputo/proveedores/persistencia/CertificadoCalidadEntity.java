/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
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
@Table(name = "PRO_TCER")
public class CertificadoCalidadEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "CERT_CERT")
    private Integer idCertificado;
    
    @Column(name = "CERT_TIPO")
    private String tipoCertificado;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CERT_FECHA")
    private java.util.Date fechacertificado;
    
    @JoinColumn(name = "CERT_PROCERT" )
    @ManyToOne
    private ProveedoresEntity idProveedor;

    @Column(name = "CERT_ESTADO")
    private String estadoCertificado;

    public String getEstadoCertificado() {
        return estadoCertificado;
    }

    public void setEstadoCertificado(String estadoCertificado) {
        this.estadoCertificado = estadoCertificado;
    }
    
    
    
    public Integer getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(Integer idCertificado) {
        this.idCertificado = idCertificado;
    }

    public String getTipoCertificado() {
        return tipoCertificado;
    }

    public void setTipoCertificado(String tipoCertificado) {
        this.tipoCertificado = tipoCertificado;
    }

    public Date getFechacertificado() {
        return fechacertificado;
    }

    public void setFechacertificado(Date fechacertificado) {
        this.fechacertificado = fechacertificado;
    }

    public ProveedoresEntity getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(ProveedoresEntity idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    
    

}
