/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.endpoint;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.proveedores.logic.AnexoEvalucionLogic;



import co.com.siscomputo.proveedores.logic.AnexoProveedorLogic;
import co.com.siscomputo.proveedores.logic.AnexosContratoLogic;

import co.com.siscomputo.proveedores.logic.CertificadoLogic;
import co.com.siscomputo.proveedores.logic.ContratosLogic;
import co.com.siscomputo.proveedores.logic.CuentasProveedoresLogic;
import co.com.siscomputo.proveedores.logic.EstadoProveedorLogic;
import co.com.siscomputo.proveedores.logic.EvaluacionesLogic;
import co.com.siscomputo.proveedores.logic.FiltroContratosLogic;
import co.com.siscomputo.proveedores.logic.FiltroProveedores;
import co.com.siscomputo.proveedores.logic.TipoDocumentoLogic;
import co.com.siscomputo.proveedores.logic.TipoProveedorLogic;
import co.com.siscomputo.proveedores.logic.TipoTributarioLogic;
import co.com.siscomputo.proveedores.persistencia.CuentasProveedoresEntity;
import co.com.siscomputo.proveedores.persistencia.EstadoProveedorEntity;
import co.com.siscomputo.proveedores.logic.FormasPagoLogic;
import co.com.siscomputo.proveedores.persistencia.TipoCuentaEntity;
import co.com.siscomputo.proveedores.logic.LineaLogic;
import co.com.siscomputo.proveedores.logic.PolizaLogic;
import co.com.siscomputo.proveedores.logic.PreguntasEvaluacionLogic;
import co.com.siscomputo.proveedores.logic.ProveedoresLogic;
import co.com.siscomputo.proveedores.logic.TemaEvaluacionLogic;
import co.com.siscomputo.proveedores.logic.TipoCuentaLogic;
import co.com.siscomputo.proveedores.logic.TipoEvaluacionLogic;
import co.com.siscomputo.proveedores.logic.TipoMonedaLogic;
import co.com.siscomputo.proveedores.persistencia.AnexoProveedorEntity;
import co.com.siscomputo.proveedores.persistencia.AnexoContratoEntity;
import co.com.siscomputo.proveedores.persistencia.AnexoEvalucionEntity;
import co.com.siscomputo.proveedores.persistencia.CertificadoCalidadEntity;
import co.com.siscomputo.proveedores.persistencia.ContratosEntity;
import co.com.siscomputo.proveedores.persistencia.EvaluacionesEntity;
import co.com.siscomputo.proveedores.persistencia.FormasPagoEntity;
import co.com.siscomputo.proveedores.persistencia.LineaEntity;
import co.com.siscomputo.proveedores.persistencia.PolizasEntity;
import co.com.siscomputo.proveedores.persistencia.PreguntasEvaluacionEntity;
import co.com.siscomputo.proveedores.persistencia.ProveedoresEntity;
import co.com.siscomputo.proveedores.persistencia.TemaEvaluacionEntity;
import co.com.siscomputo.proveedores.persistencia.TipoDocumentoEntity;
import co.com.siscomputo.proveedores.persistencia.TipoEvaluacionEntity;
import co.com.siscomputo.proveedores.persistencia.TipoMonedaEntity;
import co.com.siscomputo.proveedores.persistencia.TipoProveedorEntity;
import co.com.siscomputo.proveedores.persistencia.TipoTributarioEntity;
import co.com.siscomputo.utilidades.Valida;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author LENOVO
 */
@WebService(serviceName = "Proveedores")
public class Proveedores {

    /**
     * Método que permite insertar un Linea del Proveedor nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarLinea")
    public LineaEntity insertarLinea(@WebParam(name = "objeto") LineaEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getTipoLinea(), "Sede"))) {
            LineaEntity ret = new LineaEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getTipoLinea(), "Sede"));
            return ret;
        } else {
            LineaLogic lineaLogic = new LineaLogic();
            return lineaLogic.insertarLinea(objeto);
        }
    }

    /**
     * Método que permite actualizar un Linea del Proveedor
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarLinea")
    public LineaEntity actualizarLinea(@WebParam(name = "objeto") LineaEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getTipoLinea(), "Sede"))) {
            LineaEntity ret = new LineaEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getTipoLinea(), "Sede"));
            return ret;
        } else {
            LineaLogic metodoRecuperacionLogic = new LineaLogic();
            return metodoRecuperacionLogic.actualizarLinea(objeto);
        }
    }

    /**
     * Método que trae una lista de Linea del Proveedor
     *
     * @return
     */
    @WebMethod(operationName = "listaLinea")
    public ObjetoRetornaEntity listaLinea() {
        LineaLogic lineaLogic = new LineaLogic();
        return lineaLogic.listaLinea();
    }

    /**
     * Método que permite insertar un Tipo de Documento nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarTipoDocumento")
    public TipoDocumentoEntity insertarTipoDocumento(@WebParam(name = "objeto") TipoDocumentoEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getTipoTipoDocumento(), "Sede"))) {
            TipoDocumentoEntity ret = new TipoDocumentoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getTipoTipoDocumento(), "Sede"));
            return ret;
        } else {
            TipoDocumentoLogic tipoDocumentoLogic = new TipoDocumentoLogic();
            return tipoDocumentoLogic.insertarTipoDocumento(objeto);
        }
    }

    /**
     * Método que permite actualizar un Tipo de Documento
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarTipoDocumento")
    public TipoDocumentoEntity actualizarTipoDocumento(@WebParam(name = "objeto") TipoDocumentoEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getTipoTipoDocumento(), "Sede"))) {
            TipoDocumentoEntity ret = new TipoDocumentoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getTipoTipoDocumento(), "Sede"));
            return ret;
        } else {
            TipoDocumentoLogic metodoRecuperacionLogic = new TipoDocumentoLogic();
            return metodoRecuperacionLogic.actualizarTipoDocumento(objeto);
        }
    }


    /*
     * Método que trae una lista de Tipo de Documento
     *
     * @return
     */
    @WebMethod(operationName = "listaTipoDocumento")
    public ObjetoRetornaEntity listaTipoDocumento() {
        TipoDocumentoLogic tipoDocumentoLogic = new TipoDocumentoLogic();
        return tipoDocumentoLogic.listaTipoDocumento();
    }

    /**
     * Método que permite insertar un Estado de Proveedor nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarEstadoProveedor")
    public EstadoProveedorEntity insertarEstadoProveedor(@WebParam(name = "objeto") EstadoProveedorEntity objeto
    ) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getTipoEstadoProveedor(), "Sede"))) {
            EstadoProveedorEntity ret = new EstadoProveedorEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getTipoEstadoProveedor(), "Sede"));
            return ret;
        } else {
            EstadoProveedorLogic estadoProveedorLogic = new EstadoProveedorLogic();
            return estadoProveedorLogic.insertarEstadoProveedor(objeto);
        }
    }

    /**
     * Método que permite actualizar un Estado de Proveedor
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarEstadoProveedor")
    public EstadoProveedorEntity actualizarEstadoProveedor(@WebParam(name = "objeto") EstadoProveedorEntity objeto
    ) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getTipoEstadoProveedor(), "Sede"))) {
            EstadoProveedorEntity ret = new EstadoProveedorEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getTipoEstadoProveedor(), "Sede"));
            return ret;
        } else {
            EstadoProveedorLogic metodoRecuperacionLogic = new EstadoProveedorLogic();
            return metodoRecuperacionLogic.actualizarEstadoProveedor(objeto);
        }
    }

    /**
     * Método que trae una lista de Estado de Proveedor
     *
     * @return
     */
    @WebMethod(operationName = "listaEstadoProveedor")
    public ObjetoRetornaEntity listaEstadoProveedor() {
        EstadoProveedorLogic estadoProveedorLogic = new EstadoProveedorLogic();
        return estadoProveedorLogic.listaEstadoProveedor();
    }

    /**
     * Método que permite insertar un Tipo de Proveedor nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarTipoProveedor")
    public TipoProveedorEntity insertarTipoProveedor(@WebParam(name = "objeto") TipoProveedorEntity objeto
    ) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTipoProveedor(), "Sede"))) {
            TipoProveedorEntity ret = new TipoProveedorEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTipoProveedor(), "Sede"));
            return ret;
        } else {
            TipoProveedorLogic tipoProveedorLogic = new TipoProveedorLogic();
            return tipoProveedorLogic.insertarTipoProveedor(objeto);
        }
    }

    /**
     * Método que permite actualizar un Tipo de Proveedor
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarTipoProveedor")
    public TipoProveedorEntity actualizarTipoProveedor(@WebParam(name = "objeto") TipoProveedorEntity objeto
    ) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTipoProveedor(), "Sede"))) {
            TipoProveedorEntity ret = new TipoProveedorEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTipoProveedor(), "Sede"));
            return ret;
        } else {
            TipoProveedorLogic metodoRecuperacionLogic = new TipoProveedorLogic();
            return metodoRecuperacionLogic.actualizarTipoProveedor(objeto);
        }
    }

    /**
     * Método que trae una lista de Tipo de Proveedor
     *
     * @return
     */
    @WebMethod(operationName = "listaTipoProveedor")
    public ObjetoRetornaEntity listaTipoProveedor() {
        TipoProveedorLogic tipoProveedorLogic = new TipoProveedorLogic();
        return tipoProveedorLogic.listaTipoProveedor();
    }

    /**
     * Método que permite insertar un Tipo Tributario nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarTipoTributario")
    public TipoTributarioEntity insertarTipoTributario(@WebParam(name = "objeto") TipoTributarioEntity objeto
    ) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTipoTributario(), "Sede"))) {
            TipoTributarioEntity ret = new TipoTributarioEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTipoTributario(), "Sede"));
            return ret;
        } else {
            TipoTributarioLogic tipoTributarioLogic = new TipoTributarioLogic();
            return tipoTributarioLogic.insertarTipoTributario(objeto);
        }
    }

    /**
     * Método que permite actualizar un Tipo Tributario
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarTipoTributario")
    public TipoTributarioEntity actualizarTipoTributario(@WebParam(name = "objeto") TipoTributarioEntity objeto
    ) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTipoTributario(), "Sede"))) {
            TipoTributarioEntity ret = new TipoTributarioEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTipoTributario(), "Sede"));
            return ret;
        } else {
            TipoTributarioLogic metodoRecuperacionLogic = new TipoTributarioLogic();
            return metodoRecuperacionLogic.actualizarTipoTributario(objeto);
        }
    }

    /**
     * Método que trae una lista de Tipo Tributario
     *
     * @return
     */
    @WebMethod(operationName = "listaTipoTributario")
    public ObjetoRetornaEntity listaTipoTributario() {
        TipoTributarioLogic tipoTributarioLogic = new TipoTributarioLogic();
        return tipoTributarioLogic.listaTipoTributario();
    }

    /**
     * Método que permite insertar un Tipo de Cuenta de Proveedor nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarCuentasProveedores")
    public CuentasProveedoresEntity insertarCuentasProveedores(@WebParam(name = "objeto") CuentasProveedoresEntity objeto
    ) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreCuentasProveedores(), "Sede"))) {
            CuentasProveedoresEntity ret = new CuentasProveedoresEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreCuentasProveedores(), "Sede"));
            return ret;
        } else {
            CuentasProveedoresLogic cuentasProveedoresLogic = new CuentasProveedoresLogic();
            return cuentasProveedoresLogic.insertarCuentasProveedores(objeto);
        }
    }

    /**
     * Método que permite actualizar un Tipo de Cuenta de Proveedor
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarCuentasProveedores")
    public CuentasProveedoresEntity actualizarCuentasProveedores(@WebParam(name = "objeto") CuentasProveedoresEntity objeto
    ) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreCuentasProveedores(), "Sede"))) {
            CuentasProveedoresEntity ret = new CuentasProveedoresEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreCuentasProveedores(), "Sede"));
            return ret;
        } else {
            CuentasProveedoresLogic metodoRecuperacionLogic = new CuentasProveedoresLogic();
            return metodoRecuperacionLogic.actualizarCuentasProveedores(objeto);
        }
    }

    /**
     * Método que trae una lista de Tipo de Cuenta de Proveedor
     *
     * @return
     */
    @WebMethod(operationName = "listaCuentasProveedores")
    public ObjetoRetornaEntity listaCuentasProveedores() {
        CuentasProveedoresLogic cuentasProveedoresLogic = new CuentasProveedoresLogic();
        return cuentasProveedoresLogic.listaCuentasProveedores();
    }

    /**
     * Metodo para insertar un metodo de pago
     *
     * @param formaPago
     * @return
     */
    @WebMethod(operationName = "insertarFormaPago")
    public FormasPagoEntity insertarFormaPago(@WebParam(name = "formaPago") FormasPagoEntity formaPago
    ) {
        Valida validac = new Valida();
        if ("OK".equalsIgnoreCase(validac.valida(formaPago.getEstadoFormaPago(), "estado"))) {
            FormasPagoLogic pagoLogic = new FormasPagoLogic();
            formaPago = pagoLogic.insertarFormasPago(formaPago);
        } else {
            formaPago.setNumeroRespuesta(0);
            formaPago.setTrazaRespuesta(validac.valida(formaPago.getEstadoFormaPago(), "estado"));
            return formaPago;
        }
        return formaPago;
    }

    /**
     * Metodo para actualizar un metodo de pago
     *
     * @param formaPago
     * @return
     */
    @WebMethod(operationName = "actualizarFormaPago")
    public FormasPagoEntity actualizarFormaPago(@WebParam(name = "formaPago") FormasPagoEntity formaPago) {
        Valida valida = new Valida();
        if ("OK".equalsIgnoreCase(valida.valida(formaPago.getIdFormasPagos(), "IdFormaPago"))) {
            FormasPagoLogic pagoLogic = new FormasPagoLogic();
            return pagoLogic.actualizarFormasPago(formaPago);
        } else {
            formaPago.setTrazaRespuesta(valida.valida(formaPago.getIdFormasPagos(), "IdFormaPago"));
            return formaPago;
        }
    }

    /**
     * Metodo para traer todos las formas de pago
     *
     * @return
     */
    @WebMethod(operationName = "listaFormaPago")
    public ObjetoRetornaEntity listaFormaPago() {
        FormasPagoLogic pagoLogic = new FormasPagoLogic();
        return pagoLogic.listaFormaPago();

    }

    /**
     * Metodo para insertar un proveedor
     *
     * @param objProveedor
     * @return
     */
    @WebMethod(operationName = "insertarProveedor")
    public ProveedoresEntity insertarProveedor(@WebParam(name = "proveedor") ProveedoresEntity objProveedor) {
        try {
            Valida validac = new Valida();
            if ("OK".equalsIgnoreCase(validac.valida(objProveedor.getEstadoProveedor(), "estado"))) {
                ProveedoresLogic logicaP = new ProveedoresLogic();
                objProveedor = logicaP.InsertarProveedor(objProveedor);
            } else {
                objProveedor.setNumeroRespuesta(0);
                objProveedor.setTrazaRespuesta(validac.valida(objProveedor.getEstadoProveedor(), "estado"));
                return objProveedor;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objProveedor;
    }

    /**
     * Metodo para actualizar los proveedores
     *
     * @param objProveedor
     * @return
     */
    @WebMethod(operationName = "actualizarProveedor")
    public ProveedoresEntity actualizaProveedor(@WebParam(name = "proveedor") ProveedoresEntity objProveedor) {
        try {
            Valida valida = new Valida();
            if ("OK".equalsIgnoreCase(valida.valida(objProveedor.getIdProveedor(), "idProveedor"))) {
                ProveedoresLogic logica = new ProveedoresLogic();
                return logica.actualizarProveedores(objProveedor);
            } else {
                objProveedor.setTrazaRespuesta(valida.valida(objProveedor.getIdTipoProveedor(), "IdProveedor"));
                return objProveedor;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return objProveedor;
    }

    /**
     * Metodo para traer todos los proveedores
     *
     * @return
     */
    @WebMethod(operationName = "listaProveedor")
    public ObjetoRetornaEntity listaProveedor() {
        ProveedoresLogic logica = new ProveedoresLogic();
        return logica.listaProveedores();
    }
    /**
     * Método que filtra los proveedores por criterios
     * @param idTipoEstado
     * @param idCiudad
     * @param idLinea
     * @param idEmpresa
     * @param idResponsable
     * @param idTipoProveedor
     * @param idTibutaria
     * @param idTipoCuenta
     * @param idFormaPago
     * @param fecha1
     * @param fecha2
     * @return 
     */
    @WebMethod(operationName = "proveedoresFiltardos")
    public ObjetoRetornaEntity proveedoresFiltardos(@WebParam(name = "idTipoEstado") Integer idTipoEstado, @WebParam(name = "idCiudad") Integer idCiudad, @WebParam(name = "idLinea") Integer idLinea, @WebParam(name = "idEmpresa") Integer idEmpresa, @WebParam(name = "idResponsable") Integer idResponsable, @WebParam(name = "idTipoProveedor") Integer idTipoProveedor, @WebParam(name = "idTibutaria") Integer idTibutaria, @WebParam(name = "idTipoCuenta") Integer idTipoCuenta, @WebParam(name = "idFormaPago") Integer idFormaPago, @WebParam(name = "fechai") Date fecha1, @WebParam(name = "fechaf") Date fecha2){
        FiltroProveedores filtroProveedores=new FiltroProveedores();
        return filtroProveedores.filtrarProvedores(idTipoEstado, idCiudad, idLinea, idEmpresa, idResponsable, idTipoProveedor, idTibutaria, idTipoCuenta, idFormaPago, fecha1, fecha2);
    }
    
    
    /**
     * Método que permite insertar un Tipo de Cuenta nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarTipoCuenta")
    public TipoCuentaEntity insertarTipoCuenta(@WebParam(name = "objeto") TipoCuentaEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getTipoTipoCuenta(), "Sede"))) {
            TipoCuentaEntity ret = new TipoCuentaEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getTipoTipoCuenta(), "Sede"));
            return ret;
        } else {
            TipoCuentaLogic tipoCuentaLogic = new TipoCuentaLogic();
            return tipoCuentaLogic.insertarTipoCuenta(objeto);
        }
    }

    /**
     * Método que permite actualizar un Tipo de Cuenta
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarTipoCuenta")
    public TipoCuentaEntity actualizarTipoCuenta(@WebParam(name = "objeto") TipoCuentaEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getTipoTipoCuenta(), "Sede"))) {
            TipoCuentaEntity ret = new TipoCuentaEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getTipoTipoCuenta(), "Sede"));
            return ret;
        } else {
            TipoCuentaLogic metodoRecuperacionLogic = new TipoCuentaLogic();
            return metodoRecuperacionLogic.actualizarTipoCuenta(objeto);
        }
    }

    /**
     * Método que trae una lista de Tipo de Cuenta
     *
     * @return
     */
    @WebMethod(operationName = "listaTipoCuenta")
    public ObjetoRetornaEntity listaTipoCuenta() {
        TipoCuentaLogic tipoCuentaLogic = new TipoCuentaLogic();
        return tipoCuentaLogic.listaTipoCuenta();
    }

    /**
     * Metodo que sirve para insertar un tipo de moneda
     *
     * @param objMoneda
     * @return
     */
    @WebMethod(operationName = "insertarTipoMoneda")
    public TipoMonedaEntity insertarTipoMoneda(@WebParam(name = "tipoMoneda") TipoMonedaEntity objMoneda) {
        try {
            Valida validac = new Valida();
            if ("OK".equalsIgnoreCase(validac.valida(objMoneda.getEstadoMoneda(), "Estado"))) {
                TipoMonedaLogic logica = new TipoMonedaLogic();
                objMoneda = logica.InsertarTipoMoneda(objMoneda);
            } else {
                objMoneda.setNumeroRespuesta(0);
                objMoneda.setTrazaRespuesta(validac.valida(objMoneda.getEstadoMoneda(), "Estado"));
                return objMoneda;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objMoneda;
    }

    /**
     * Metodo para actualizar los tipos de moneda
     *
     * @param ObjMoneda
     * @return
     */
    @WebMethod(operationName = "actualizarMoneda")
    public TipoMonedaEntity actualizarTipoMoneda(@WebParam(name = "tipoMoneda") TipoMonedaEntity ObjMoneda) {
        try {
            Valida validac = new Valida();
            if ("OK".equalsIgnoreCase(validac.valida(ObjMoneda.getIdMoneda(), "IdTipoMoneda"))) {
                TipoMonedaLogic logica = new TipoMonedaLogic();
                ObjMoneda = logica.actualizarTipoMoneda(ObjMoneda);
            } else {
                ObjMoneda.setNumeroRespuesta(0);
                ObjMoneda.setTrazaRespuesta(validac.valida(ObjMoneda.getIdMoneda(), "IdTipoMoneda"));
                return ObjMoneda;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ObjMoneda;
    }

    /**
     * Metodo para traer todos los tipos de moneda
     *
     * @return
     */
    @WebMethod(operationName = "listaTipoMoneda")
    public ObjetoRetornaEntity listaTipoMoneda() {
        TipoMonedaLogic logica = new TipoMonedaLogic();
        return logica.listaTipoMoneda();
    }

    /**
     * Metodo para insertar un contrato
     *
     * @param objContratos
     * @return
     */
    @WebMethod(operationName = "insertarContrato")
    public ContratosEntity insertarContrato(@WebParam(name = "contrato") ContratosEntity objContratos) {
        try {
            Valida validac = new Valida();
            if ("OK".equalsIgnoreCase(validac.valida(objContratos.getEstadoContrato(), "Estado"))) {
                ContratosLogic logic = new ContratosLogic();
                objContratos = logic.insertarContrato(objContratos);
                objContratos.setNumeroRespuesta(23);
                objContratos.setTrazaRespuesta("Insercion correcta");
            } else {
                objContratos.setNumeroRespuesta(0);
                objContratos.setTrazaRespuesta(validac.valida(objContratos.getEstadoContrato(), "Estado"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objContratos;
    }

    /**
     * Metodo para actualizar contrato
     *
     * @param objContratos
     * @return
     */
    @WebMethod(operationName = "actualizarContrato")
    public ContratosEntity actualizarContratos(@WebParam(name = "contrato") ContratosEntity objContratos) {
        try {
            Valida validac = new Valida();
            if ("OK".equalsIgnoreCase(validac.valida(objContratos.getIdContrato(), "IdEstado"))) {
                ContratosLogic logic = new ContratosLogic();
                objContratos = logic.actualizarContrato(objContratos);
                objContratos.setNumeroRespuesta(23);
                objContratos.setTrazaRespuesta("Actualización correcta");
            } else {
                objContratos.setNumeroRespuesta(0);
                objContratos.setTrazaRespuesta(validac.valida(objContratos.getIdContrato(), "IdEstado"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objContratos;
    }

    /**
     * Metodo para traer todos los contratos
     *
     * @return
     */
    @WebMethod(operationName = "listaContratos")
    public ObjetoRetornaEntity listaContratos() {
        ContratosLogic logica = new ContratosLogic();
        return logica.listaContratos();
    }

    /**
     * Metodo para insertar una poliza
     *
     * @param objPolizas
     * @return
     */
    @WebMethod(operationName = "insertarPoliza")
    public PolizasEntity insertarPoliza(@WebParam(name = "PolizaEntity") PolizasEntity objPolizas) {
        try {
            Valida valida = new Valida();
            if ("OK".equalsIgnoreCase(valida.valida(objPolizas.getIdPoliza(), "idPoliza"))) {
                PolizaLogic poliza = new PolizaLogic();
                objPolizas = poliza.actualizarPoliza(objPolizas);
            } else {
                objPolizas.setNumeroRespuesta(0);
                objPolizas.setTrazaRespuesta(valida.valida(objPolizas.getIdPoliza(), "idPoliza"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objPolizas;
    }

    /**
     * Metodo para actualizar una poliza
     *
     * @param objPolizas
     * @return
     */
    @WebMethod(operationName = "actualizarPoliza")
    public PolizasEntity actualizarPoliza(@WebParam(name = "PolizaEntity") PolizasEntity objPolizas) {
        try {
            Valida valida = new Valida();
            if ("OK".equalsIgnoreCase(valida.valida(objPolizas.getIdPoliza(), "idPoliza"))) {
                PolizaLogic logica = new PolizaLogic();
                objPolizas = logica.actualizarPoliza(objPolizas);
            } else {
                objPolizas.setNumeroRespuesta(0);
                objPolizas.setTrazaRespuesta(valida.valida(objPolizas.getIdPoliza(), "idPoliza"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objPolizas;
    }

    /**
     * Metodo para traer todas las polizas
     *
     * @return
     */
    @WebMethod(operationName = "listaPoliza")
    public ObjetoRetornaEntity listaPolizas() {
        PolizaLogic poliza = new PolizaLogic();
        return poliza.listaPolizas();
    }

    /**
     * Metodo para insertar un certificado de calidad
     *
     * @param ObjCertificado
     * @return
     */
    @WebMethod(operationName = "insertarCertificado")
    public CertificadoCalidadEntity insertarCertificado(@WebParam(name = "CertificadoEntity") CertificadoCalidadEntity ObjCertificado) {
        try {
            Valida valida = new Valida();
            String res = valida.valida(ObjCertificado.getTipoCertificado(), "tipo");
            if ("OK".equalsIgnoreCase(res)) {
                CertificadoLogic logica = new CertificadoLogic();
                ObjCertificado = logica.insertarCertificadoCalidad(ObjCertificado);
            } else {
                ObjCertificado.setNumeroRespuesta(0);
                ObjCertificado.setTrazaRespuesta(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ObjCertificado;
    }

    /**
     * Metodo para actualizar un certificado
     *
     * @param ObjCertificado
     * @return
     */
    @WebMethod(operationName = "actualizarCertificado")
    public CertificadoCalidadEntity actualizarCertificado(@WebParam(name = "CertificadoEntity") CertificadoCalidadEntity ObjCertificado) {
        try {
            Valida valida = new Valida();
            String res = valida.valida(ObjCertificado.getIdCertificado(), "idCertificado");
            if ("OK".equalsIgnoreCase(res)) {
                CertificadoLogic logica = new CertificadoLogic();
                ObjCertificado = logica.actualizarCertificados(ObjCertificado);
            } else {
                ObjCertificado.setNumeroRespuesta(0);
                ObjCertificado.setTrazaRespuesta(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ObjCertificado.setNumeroRespuesta(0);
            ObjCertificado.setTrazaRespuesta(e.getMessage());
        }
        return ObjCertificado;
    }

    /**
     * Metodo para listar los certificados
     *
     * @return
     */
    @WebMethod(operationName = "listarCertificado")
    public ObjetoRetornaEntity listaCertificado() {
        ObjetoRetornaEntity obj = new ObjetoRetornaEntity();
        try {
            CertificadoLogic logica = new CertificadoLogic();
            obj = logica.listaCertificados();
        } catch (Exception e) {
            e.printStackTrace();
            obj.setNumeroRespuesta(0);
            obj.setTrazaRespuesta(e.getMessage());
        }
        return obj;
    }

    /**
     * Método que permite insertar un Anexos del proveedor nuevo
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "insertarAnexoProveedor")
    public AnexoProveedorEntity insertarAnexoProveedor(@WebParam(name = "objeto") AnexoProveedorEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getProveedorAnexoProveedor(), "Sede"))) {
            AnexoProveedorEntity ret = new AnexoProveedorEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getProveedorAnexoProveedor(), "Sede"));
            return ret;
        } else {
            AnexoProveedorLogic anexoProveedorLogic = new AnexoProveedorLogic();
            return anexoProveedorLogic.insertarAnexoProveedor(objeto);
        }
    }

    /**
     * Método que permite actualizar un Anexos del proveedor
     *
     * @param objeto
     * @return
     */
    @WebMethod(operationName = "actualizarAnexoProveedor")
    public AnexoProveedorEntity actualizarAnexoProveedor(@WebParam(name = "objeto") AnexoProveedorEntity objeto) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getProveedorAnexoProveedor(), "Sede"))) {
            AnexoProveedorEntity ret = new AnexoProveedorEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getProveedorAnexoProveedor(), "Sede"));
            return ret;
        } else {
            AnexoProveedorLogic metodoRecuperacionLogic = new AnexoProveedorLogic();
            return metodoRecuperacionLogic.actualizarAnexoProveedor(objeto);
        }
    }

    /**
     * Método que trae una lista de Anexos del proveedor
     *
     * @return
     */
    @WebMethod(operationName = "listaAnexoProveedor")
    public ObjetoRetornaEntity listaAnexoProveedor() {
        AnexoProveedorLogic anexoProveedorLogic = new AnexoProveedorLogic();
        return anexoProveedorLogic.listaAnexoProveedor();
    }

    /**
     * Metodo para filtrar contratos por TipoProveedor,Estado,TipoContrato y
     * fecha de vencimiento
     *
     * @param IdTipoProveedor
     * @param estadoContrato
     * @param IdProveedor
     * @param fechafinal
     * @return
     */
    @WebMethod(operationName = "FiltroContratos")
    public ObjetoRetornaEntity FiltroContratos(@WebParam(name = "IdTipoProveedor") Integer IdTipoProveedor, @WebParam(name = "estadoContrato") String estadoContrato, @WebParam(name = "idProveedorContrato") Integer IdProveedor, @WebParam(name = "fechafinalContrato") String fechafinal) {
        FiltroContratosLogic retorn = new FiltroContratosLogic();
        return retorn.filtrarContratos(IdTipoProveedor, estadoContrato, IdProveedor, fechafinal);

    }

    /**
     * Metodo para insertar un anexo de contrato
     *
     * @param objAnexo
     * @return
     */
    @WebMethod(operationName = "InsertarAnexoContrato")
    public AnexoContratoEntity insertarAnexoContrato(@WebParam(name = "objAnexo") AnexoContratoEntity objAnexo) {
        try {
            Valida valida = new Valida();
            String respuesta = valida.valida(objAnexo.getUbicacionAnexo(), "ubicación");
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objAnexo.getIdContratoAnexo(), "idcontrato");

            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                AnexosContratoLogic logica = new AnexosContratoLogic();
                objAnexo = logica.InsertarAnexo(objAnexo);

            } else {
                objAnexo.setTrazaRespuesta(respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objAnexo;
    }

    /**
     * Metodo para actualizar un anexo de contrato
     *
     * @param objAnexo
     * @return
     */
    @WebMethod(operationName = "actualizarAnexoContrato")
    public AnexoContratoEntity actualizarAnexoContrato(@WebParam(name = "objAnexo") AnexoContratoEntity objAnexo) {
        Valida valida = new Valida();
        String respuesta = valida.valida(objAnexo.getUbicacionAnexo(), "ubicación");
        if ("OK".equalsIgnoreCase(respuesta)) {
            respuesta = valida.valida(objAnexo.getIdContratoAnexo(), "idcontrato");
        }
        if ("OK".equalsIgnoreCase(respuesta)) {
            respuesta = valida.valida(objAnexo.getIdAnexo(), "id");

        }
        try {
            if ("OK".equalsIgnoreCase(respuesta)) {
                AnexosContratoLogic logica = new AnexosContratoLogic();
                objAnexo = logica.actualizarAnexoContrato(objAnexo);
            } else {
                objAnexo.setTrazaRespuesta(respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objAnexo;
    }

    /**
     * Metodo para listar los anexos de los contratos
     *
     * @return
     */
    @WebMethod(operationName = "listarAnexosContratos")
    public ObjetoRetornaEntity listarAnexoContrato() {
        ObjetoRetornaEntity obj = new ObjetoRetornaEntity();
        try {
            AnexosContratoLogic logica = new AnexosContratoLogic();
            return logica.listarAnexos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * Metodo para insertar un tipo de evaluacion
     *
     * @param objTipoE
     * @return
     */
    @WebMethod(operationName = "InsertarTipoEvaluacion")
    public TipoEvaluacionEntity insertarTipoEvaluacion(@WebParam(name = "TipoEvaluacion") TipoEvaluacionEntity objTipoE) {
        try {
            Valida valida = new Valida();
            String respuesta = valida.valida(objTipoE.getTipoEvaluacion(), "Tipo Evaluacion");
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objTipoE.getRangoMedioEvaluacion(), "Rango medio");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objTipoE.getRangoBajoEvaluacion(), "Rango bajo");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objTipoE.getRangoAltoEvaluacion(), "Rango alto");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                TipoEvaluacionLogic logica = new TipoEvaluacionLogic();
                objTipoE = logica.insertarTipoEvaluacion(objTipoE);
            } else {
                objTipoE.setTrazaRespuesta(respuesta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return objTipoE;

    }

    /**
     * Metodo para actualizar un tipo de evaluacion
     *
     * @param objTipoE
     * @return
     */
    @WebMethod(operationName = "actualizarTipoEvaluacion")
    public TipoEvaluacionEntity actualizarTipoEvaluacion(@WebParam(name = "TipoEvaluacion") TipoEvaluacionEntity objTipoE) {
        try {
            Valida valida = new Valida();
            String respuesta = valida.valida(objTipoE.getTipoEvaluacion(), "Tipo Evaluacion");

            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objTipoE.getRangoMedioEvaluacion(), "Rango medio");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objTipoE.getRangoBajoEvaluacion(), "Rango bajo");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objTipoE.getRangoAltoEvaluacion(), "Rango alto");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objTipoE.getIdTipoEvaluacion(), "ID");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                TipoEvaluacionLogic logica = new TipoEvaluacionLogic();
                objTipoE = logica.actualizarTipoEvaluacion(objTipoE);
            } else {
                objTipoE.setTrazaRespuesta(respuesta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return objTipoE;

    }

    /**
     * Metodo para listar los tipos de evaluacion
     *
     * @return
     */
    @WebMethod(operationName = "listarTipoDeEvaluacion")
    public ObjetoRetornaEntity listarTiposDeEvaluacion() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        try {
            TipoEvaluacionLogic logica = new TipoEvaluacionLogic();
            retorno = logica.listarTipoEvaluaciones();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }


    /**
     * Metodo para insertar un tema de evaluacion
     *
     * @param objtema
     * @return
     */
    @WebMethod(operationName = "InsertarTemaEvaluacion")
    public TemaEvaluacionEntity insertarTemaEvaluacion(@WebParam(name = "TemaEvaluacion") TemaEvaluacionEntity objtema) {

        try {
            Valida valida = new Valida();
            String respuesta = "";
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objtema.getMaxRespuestaEvaluacion(), "maximo respuesta");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objtema.getNombreTemaEvaluacion(), "nombre tema");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objtema.getPorcentajeEvaluacion(), "porcentaje tema");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                TemaEvaluacionLogic logica = new TemaEvaluacionLogic();
                objtema = logica.insertarTemaEvaluacion(objtema);
            } else {
                objtema.setTrazaRespuesta(respuesta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objtema;
    }

    @WebMethod(operationName = "ActualizarTemaEvaluacion")
    public TemaEvaluacionEntity ActualizarTemaEvaluacion(@WebParam(name = "TemaEvaluacion") TemaEvaluacionEntity objte) {
        try {
            Valida valida = new Valida();
            String respuesta = "";
            respuesta = valida.valida(objte.getIdTemaEvaluacion(), "id tema evaluacion");
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objte.getMaxRespuestaEvaluacion(), "maximo respuesta");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objte.getNombreTemaEvaluacion(), "nombre tema");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objte.getPorcentajeEvaluacion(), "porcentaje tema");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                TemaEvaluacionLogic logica = new TemaEvaluacionLogic();
                objte = logica.actualizarTemaEvaluacion(objte);
            } else {
                objte.setTrazaRespuesta(respuesta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return objte;
    }

    /**
     * Metodo para listar los temas de evaluacion
     *
     * @return
     */
    @WebMethod(operationName = "ListarTemasEvaluacion")
    public ObjetoRetornaEntity listaTemaEvaluacion() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        try {
            TemaEvaluacionLogic logica = new TemaEvaluacionLogic();
            retorno = logica.listaTemasEvaluacion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;

    }

    /**
     * Metodo para insertar una pregunta de evaluacion
     *
     * @param objPre
     * @return
     */
    @WebMethod(operationName = "InsertarPreguntas")
    public PreguntasEvaluacionEntity insertarPreguntas(@WebParam(name = "PreguntasEvaluacion") PreguntasEvaluacionEntity objPre) {
        try {
            Valida valida = new Valida();
            String respuesta = "";
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objPre.getDescripcionDetallada(), "Descripcion");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objPre.getPesoItem(), "PesoItem");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objPre.getPregunta(), "Pregunta");
            }

            if ("OK".equalsIgnoreCase(respuesta)) {
                PreguntasEvaluacionLogic logica = new PreguntasEvaluacionLogic();
                objPre = logica.insertarPregunta(objPre);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objPre.setTrazaRespuesta(e.getMessage());
        }
        return objPre;
    }

    /**
     * Metodo para actualizar una pregunta
     *
     * @param objPre
     * @return
     */
    @WebMethod(operationName = "ActualizarPreguntas")
    public PreguntasEvaluacionEntity actualizarPreguntas(@WebParam(name = "PreguntasEvaluacion") PreguntasEvaluacionEntity objPre) {
        try {
            Valida valida = new Valida();
            String respuesta = "";
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objPre.getDescripcionDetallada(), "Descripcion");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objPre.getPesoItem(), "PesoItem");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objPre.getPregunta(), "Pregunta");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                respuesta = valida.valida(objPre.getIdPregunta(), "idPregunta");
            }
            if ("OK".equalsIgnoreCase(respuesta)) {
                PreguntasEvaluacionLogic logica = new PreguntasEvaluacionLogic();
                objPre = logica.actualizarPreguntaEvaluacion(objPre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objPre;
    }

    /**
     * Metodo para listar los temas de evaluacion
     *
     * @return
     */
    @WebMethod(operationName = "ListarPreguntasEvaluacion")
    public ObjetoRetornaEntity listaPreguntasEvaluacion() {
        ObjetoRetornaEntity retorno = new ObjetoRetornaEntity();
        try {
            PreguntasEvaluacionLogic logica = new PreguntasEvaluacionLogic();
            retorno = logica.listarPreguntas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;

    }

    
    /**
     * Método que permite insertar un Evaluacion nuevo
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "insertarEvaluaciones")
    public EvaluacionesEntity insertarEvaluaciones(@WebParam(name = "objeto") EvaluacionesEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getFechaEvaluaciones(), "fecha"))) {
            EvaluacionesEntity ret = new EvaluacionesEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getFechaEvaluaciones(), "fecha"));
            return ret;
        } else {
        EvaluacionesLogic evaluacionesLogic=new EvaluacionesLogic();
        return evaluacionesLogic.insertarEvaluaciones(objeto);
        }
}
     /**
     * Método que permite actualizar un Evaluacion
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "actualizarEvaluaciones")
    public EvaluacionesEntity actualizarEvaluaciones(@WebParam(name = "objeto")EvaluacionesEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getFechaEvaluaciones(), "fecha"))) {
            EvaluacionesEntity ret = new EvaluacionesEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getFechaEvaluaciones(), "fecha"));
            return ret;
        } else {
            EvaluacionesLogic metodoRecuperacionLogic=new EvaluacionesLogic();
            return metodoRecuperacionLogic.actualizarEvaluaciones(objeto);
        }
    }
    /**
     * Método que trae una lista de Evaluacion
     * @return 
     */
    @WebMethod(operationName = "listaEvaluaciones")
    public ObjetoRetornaEntity listaEvaluaciones(){
        EvaluacionesLogic evaluacionesLogic=new EvaluacionesLogic();
        return evaluacionesLogic.listaEvaluaciones();
     }
    
    
    /**
     * Método que permite insertar un anexo nuevo
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "insertarAnexoEvalucion")
    public AnexoEvalucionEntity insertarAnexoEvalucion(@WebParam(name = "objeto") AnexoEvalucionEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getRutaAnexoEvalucion(), "ruta"))) {
            AnexoEvalucionEntity ret = new AnexoEvalucionEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getRutaAnexoEvalucion(), "ruta"));
            return ret;
        } else {
        AnexoEvalucionLogic anexoEvalucionLogic=new AnexoEvalucionLogic();
        return anexoEvalucionLogic.insertarAnexoEvalucion(objeto);
        }
}
     /**
     * Método que permite actualizar un anexo
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "actualizarAnexoEvalucion")
    public AnexoEvalucionEntity actualizarAnexoEvalucion(@WebParam(name = "objeto")AnexoEvalucionEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getRutaAnexoEvalucion(), "Ruta"))) {
            AnexoEvalucionEntity ret = new AnexoEvalucionEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getRutaAnexoEvalucion(), "Ruta"));
            return ret;
        } else {
            AnexoEvalucionLogic metodoRecuperacionLogic=new AnexoEvalucionLogic();
            return metodoRecuperacionLogic.actualizarAnexoEvalucion(objeto);
        }
    }
    /**
     * Método que trae una lista de anexo
     * @return 
     */
    @WebMethod(operationName = "listaAnexoEvalucion")
    public ObjetoRetornaEntity listaAnexoEvalucion(){
        AnexoEvalucionLogic anexoEvalucionLogic=new AnexoEvalucionLogic();
        return anexoEvalucionLogic.listaAnexoEvalucion();
     }
    
}
