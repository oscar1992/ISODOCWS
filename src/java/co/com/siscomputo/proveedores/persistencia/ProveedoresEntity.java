/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.EmpresaEntity;
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
@Table(name = "PRO_TPROV")
public class ProveedoresEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "PROV_PROV")
    private Integer idProveedor;

    @Column(name = "PROV_NOMB")
    private String nombreProveedor;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PROV_FECH")
    private java.util.Date fechaCreacion;

    @JoinColumn(name = "PROV_TESTA")
    @ManyToOne
    private EstadoProveedorEntity estadoProveedor;

    @Column(name = "PROV_TIDO")
    private String tipoDocumentoProveedor;

    @Column(name = "PROV_CIUD")
    private Integer ciudadProveedor;

    @Column(name = "PROV_TELE")
    private String telefonoProveedor;

    @Column(name = "PROV_CALI")
    private String certificadoCalidadProveedor;

    @Column(name = "PROV_DIRE")
    private String direccionProveedor;

    @Column(name = "PROV_CELU")
    private String celularProveedor;

    @Column(name = "PROV_PWEB")
    private String paginaWebProveedor;

    @Column(name = "PROV_EMAI")
    private String emailProveedor;

    @JoinColumn(name = "PROV_EMPR")
    @ManyToOne
    private EmpresaEntity empresaProveedor;

    @JoinColumn(name = "PROV_USUA")
    @ManyToOne
    private UsuarioEntity usuarioResponsable;

    @Column(name = "PROV_DOCU")
    private String urlDocumento;

    @Column(name = "PROV_CNOM")
    private String nombreContacto;

    @Column(name = "PROV_CCAR")
    private String cargoContacto;

    @Column(name = "PROV_CTEL")
    private String telefonoContacto;

    @Column(name = "PROV_CEMA")
    private String emailContacto;

    @JoinColumn(name = "PROV_TIPO")
    @ManyToOne
    private TipoProveedorEntity idTipoProveedor;

    @Column(name = "PROV_DESC")
    private String actividadEconomicaProveedor;

    @JoinColumn(name = "PROV_TRIB")
    @ManyToOne
    private TipoTributarioEntity idTipoTributario;

    @JoinColumn(name = "PROV_CUEN")
    @ManyToOne
    private TipoCuentaEntity idTipocuenta;

    @JoinColumn(name = "PROV_FORM")
    @ManyToOne
    private FormasPagoEntity idFormaPago;

    @Column(name = "PROV_ESTA")
    private String estadoProveedores;
    
    @JoinColumn(name = "PROV_LINE")
    @ManyToOne
    private LineaEntity lineaProveedores;
    @Column(name = "PROV_TITU")
    private String titularProveedor;
    @Column(name = "PROV_NCUE")
    private String ncuentaProveedor;
    @Column(name = "PROV_BANC")
    private String bancoProveedor;
    

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    public EstadoProveedorEntity getEstadoProveedor() {
        return estadoProveedor;
    }

    public void setEstadoProveedor(EstadoProveedorEntity estadoProveedor) {
        this.estadoProveedor = estadoProveedor;
    }

    public String getTipoDocumentoProveedor() {
        return tipoDocumentoProveedor;
    }

    public void setTipoDocumentoProveedor(String tipoDocumentoProveedor) {
        this.tipoDocumentoProveedor = tipoDocumentoProveedor;
    }

    public Integer getCiudadProveedor() {
        return ciudadProveedor;
    }

    public void setCiudadProveedor(Integer ciudadProveedor) {
        this.ciudadProveedor = ciudadProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getCertificadoCalidadProveedor() {
        return certificadoCalidadProveedor;
    }

    public void setCertificadoCalidadProveedor(String certificadoCalidadProveedor) {
        this.certificadoCalidadProveedor = certificadoCalidadProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getCelularProveedor() {
        return celularProveedor;
    }

    public void setCelularProveedor(String celularProveedor) {
        this.celularProveedor = celularProveedor;
    }

    public String getPaginaWebProveedor() {
        return paginaWebProveedor;
    }

    public void setPaginaWebProveedor(String paginaWebProveedor) {
        this.paginaWebProveedor = paginaWebProveedor;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }

    public EmpresaEntity getEmpresaProveedor() {
        return empresaProveedor;
    }

    public void setEmpresaProveedor(EmpresaEntity empresaProveedor) {
        this.empresaProveedor = empresaProveedor;
    }

    public UsuarioEntity getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(UsuarioEntity usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getCargoContacto() {
        return cargoContacto;
    }

    public void setCargoContacto(String cargoContacto) {
        this.cargoContacto = cargoContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public TipoProveedorEntity getIdTipoProveedor() {
        return idTipoProveedor;
    }

    public void setIdTipoProveedor(TipoProveedorEntity idTipoProveedor) {
        this.idTipoProveedor = idTipoProveedor;
    }

    public String getActividadEconomicaProveedor() {
        return actividadEconomicaProveedor;
    }

    public void setActividadEconomicaProveedor(String actividadEconomicaProveedor) {
        this.actividadEconomicaProveedor = actividadEconomicaProveedor;
    }

    public TipoTributarioEntity getIdTipoTributario() {
        return idTipoTributario;
    }

    public void setIdTipoTributario(TipoTributarioEntity idTipoTributario) {
        this.idTipoTributario = idTipoTributario;
    }

    public TipoCuentaEntity getIdTipocuenta() {
        return idTipocuenta;
    }

    public void setIdTipocuenta(TipoCuentaEntity idTipocuenta) {
        this.idTipocuenta = idTipocuenta;
    }

    public FormasPagoEntity getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(FormasPagoEntity idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getEstadoProveedores() {
        return estadoProveedores;
    }

    public void setEstadoProveedores(String estadoProveedores) {
        this.estadoProveedores = estadoProveedores;
    }

    public LineaEntity getLineaProveedores() {
        return lineaProveedores;
    }

    public void setLineaProveedores(LineaEntity lineaProveedores) {
        this.lineaProveedores = lineaProveedores;
    }

    public String getTitularProveedor() {
        return titularProveedor;
    }

    public void setTitularProveedor(String titularProveedor) {
        this.titularProveedor = titularProveedor;
    }

    public String getNcuentaProveedor() {
        return ncuentaProveedor;
    }

    public void setNcuentaProveedor(String ncuentaProveedor) {
        this.ncuentaProveedor = ncuentaProveedor;
    }

    public String getBancoProveedor() {
        return bancoProveedor;
    }

    public void setBancoProveedor(String bancoProveedor) {
        this.bancoProveedor = bancoProveedor;
    }
    
    
}
