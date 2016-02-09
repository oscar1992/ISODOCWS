/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.endpoint;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.proveedores.logic.FormasPagoLogic;
import co.com.siscomputo.proveedores.persistencia.TipoCuentaEntity;
import co.com.siscomputo.proveedores.logic.LineaLogic;
import co.com.siscomputo.proveedores.logic.TipoCuentaLogic;
import co.com.siscomputo.proveedores.persistencia.FormasPagoEntity;
import co.com.siscomputo.proveedores.persistencia.LineaEntity;
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
     * Metodo web que permite ingresar un tipo de cuenta
     *
     * @param tipoCuenta
     * @return
     */
    @WebMethod(operationName = "insertarTipoCuenta")
    public TipoCuentaEntity insertaTipoCuenta(@WebParam(name = "tipoCuenta") TipoCuentaEntity tipoCuenta) {
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
    public ObjetoRetornaEntity actualizarTipoCuenta(@WebParam(name = "tipoCuenta") TipoCuentaEntity tipoCuenta) {
        Valida valida = new Valida();
        if ("OK".equalsIgnoreCase(valida.valida(tipoCuenta.getIdTipoCuenta(), "IdTipoCuenta"))) {
            TipoCuentaLogic logica = new TipoCuentaLogic();
            return logica.actualizarTipoCuenta(tipoCuenta);
        } else {
            tipoCuenta.setTrazaRespuesta(valida.valida(tipoCuenta.getIdTipoCuenta(), "IdTipoCuenta"));
            return tipoCuenta;
        }
    }

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
    public FormasPagoEntity insertarFormaPago(@WebParam(name = "formaPago") FormasPagoEntity formaPago) {
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
     * @return 
     */
    @WebMethod(operationName = "listaFormaPago")
    public ObjetoRetornaEntity listaFormaPago() {
        FormasPagoLogic pagoLogic = new FormasPagoLogic();
        return pagoLogic.listaFormaPago();
    }

}
