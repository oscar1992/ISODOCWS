/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.endpoint;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.proveedores.logic.LineaLogic;
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

}
