/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.endpoint;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.proveedores.logic.EstadoProveedorLogic;
import co.com.siscomputo.proveedores.logic.LineaLogic;
import co.com.siscomputo.proveedores.logic.TipoDocumentoLogic;
import co.com.siscomputo.proveedores.logic.TipoProveedorLogic;
import co.com.siscomputo.proveedores.logic.TipoTributarioLogic;
import co.com.siscomputo.proveedores.persistencia.EstadoProveedorEntity;
import co.com.siscomputo.proveedores.persistencia.LineaEntity;
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

    /**
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
    public EstadoProveedorEntity insertarEstadoProveedor(@WebParam(name = "objeto") EstadoProveedorEntity objeto) {
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
    public EstadoProveedorEntity actualizarEstadoProveedor(@WebParam(name = "objeto") EstadoProveedorEntity objeto) {
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
    public TipoProveedorEntity insertarTipoProveedor(@WebParam(name = "objeto") TipoProveedorEntity objeto) {
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
    public TipoProveedorEntity actualizarTipoProveedor(@WebParam(name = "objeto") TipoProveedorEntity objeto) {
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
     * @return
     */
    @WebMethod(operationName = "listaTipoProveedor")
    public ObjetoRetornaEntity listaTipoProveedor() {
        TipoProveedorLogic tipoProveedorLogic = new TipoProveedorLogic();
        return tipoProveedorLogic.listaTipoProveedor();
    }
    
    /**
     * Método que permite insertar un Tipo Tributario nuevo
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "insertarTipoTributario")
    public TipoTributarioEntity insertarTipoTributario(@WebParam(name = "objeto") TipoTributarioEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTipoTributario(), "Sede"))) {
            TipoTributarioEntity ret = new TipoTributarioEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTipoTributario(), "Sede"));
            return ret;
        } else {
        TipoTributarioLogic tipoTributarioLogic=new TipoTributarioLogic();
        return tipoTributarioLogic.insertarTipoTributario(objeto);
        }
}
     /**
     * Método que permite actualizar un Tipo Tributario
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "actualizarTipoTributario")
    public TipoTributarioEntity actualizarTipoTributario(@WebParam(name = "objeto")TipoTributarioEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getNombreTipoTributario(), "Sede"))) {
            TipoTributarioEntity ret = new TipoTributarioEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getNombreTipoTributario(), "Sede"));
            return ret;
        } else {
            TipoTributarioLogic metodoRecuperacionLogic=new TipoTributarioLogic();
            return metodoRecuperacionLogic.actualizarTipoTributario(objeto);
        }
    }
    /**
     * Método que trae una lista de Tipo Tributario
     * @return 
     */
    @WebMethod(operationName = "listaTipoTributario")
    public ObjetoRetornaEntity listaTipoTributario(){
        TipoTributarioLogic tipoTributarioLogic=new TipoTributarioLogic();
        return tipoTributarioLogic.listaTipoTributario();
     }
    
}
