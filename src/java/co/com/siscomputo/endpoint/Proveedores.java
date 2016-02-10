/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.endpoint;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.proveedores.logic.CuentasProveedoresLogic;
import co.com.siscomputo.proveedores.logic.EstadoProveedorLogic;
import co.com.siscomputo.proveedores.logic.TipoDocumentoLogic;
import co.com.siscomputo.proveedores.logic.TipoProveedorLogic;
import co.com.siscomputo.proveedores.logic.TipoTributarioLogic;
import co.com.siscomputo.proveedores.persistencia.CuentasProveedoresEntity;
import co.com.siscomputo.proveedores.persistencia.EstadoProveedorEntity;
import co.com.siscomputo.proveedores.logic.FormasPagoLogic;
import co.com.siscomputo.proveedores.persistencia.TipoCuentaEntity;
import co.com.siscomputo.proveedores.logic.LineaLogic;
import co.com.siscomputo.proveedores.logic.ProveedoresLogic;
import co.com.siscomputo.proveedores.logic.TipoCuentaLogic;
import co.com.siscomputo.proveedores.persistencia.FormasPagoEntity;
import co.com.siscomputo.proveedores.persistencia.LineaEntity;
import co.com.siscomputo.proveedores.persistencia.ProveedoresEntity;
import co.com.siscomputo.proveedores.persistencia.TipoDocumentoEntity;
import co.com.siscomputo.proveedores.persistencia.TipoProveedorEntity;
import co.com.siscomputo.proveedores.persistencia.TipoTributarioEntity;
import co.com.siscomputo.utilidades.Valida;
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
     /*
     * Metodo web que permite ingresar un tipo de cuenta
     *
     * @param tipoCuenta
     * @return
     */

    @WebMethod(operationName = "insertarTipoCuenta")
    public TipoCuentaEntity insertaTipoCuenta(@WebParam(name = "tipoCuenta") TipoCuentaEntity tipoCuenta
    ) {
        Valida valida = new Valida();
        if ("OK".equalsIgnoreCase(valida.valida(tipoCuenta.getEstadoCuenta(), "estado"))) {
            TipoCuentaLogic logica = new TipoCuentaLogic();
            logica.insertarTipoCuenta(tipoCuenta);
        } else {
            tipoCuenta.setTrazaRespuesta(valida.valida(tipoCuenta.getIdTipoCuenta(), "estado"));
            tipoCuenta.setNumeroRespuesta(0);
            return tipoCuenta;
        }
        return tipoCuenta;
    }

    /**
     * Metodo para actualizar el tipo de cuenta de un proveedor
     *
     * @param tipoCuenta
     * @return
     */
    @WebMethod(operationName = "actualizarTipoCuenta")
    public ObjetoRetornaEntity actualizarTipoCuenta(@WebParam(name = "tipoCuenta") TipoCuentaEntity tipoCuenta
    ) {
        Valida valida = new Valida();
        if ("OK".equalsIgnoreCase(valida.valida(tipoCuenta.getIdTipoCuenta(), "IdTipoCuenta"))) {
            TipoCuentaLogic logica = new TipoCuentaLogic();
            return logica.actualizarTipoCuenta(tipoCuenta);
        } else {
            tipoCuenta.setTrazaRespuesta(valida.valida(tipoCuenta.getIdTipoCuenta(), "IdTipoCuenta"));
            return tipoCuenta;

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
   
    /*
    /**
     * Metodo para traer todos los tipos de cuenta
     *
     * @return   
    */

    @WebMethod(operationName = "listaTipoCuenta")

    public ObjetoRetornaEntity listaTipoCuenta() {
        TipoCuentaLogic tipoCuentaLogic = new TipoCuentaLogic();
        return tipoCuentaLogic.listaTipoCuenta();
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
            pagoLogic.insertarFormasPago(formaPago);
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
                logicaP.InsertarProveedor(objProveedor);
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
}
