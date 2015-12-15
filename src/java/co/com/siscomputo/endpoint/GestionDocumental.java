/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.endpoint;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.gestiondocumental.persistencia.DocumentoEntity;
import co.com.siscomputo.gestiondocumental.logic.DocumentoLogic;
import co.com.siscomputo.gestiondocumental.logic.DocumentoProcesoLogic;
import co.com.siscomputo.gestiondocumental.persistencia.DocumentoProcesoEntity;
import co.com.siscomputo.utilidades.Valida;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author LENOVO
 */
@WebService(serviceName = "GestionDocumental")
public class GestionDocumental {
    /**
     * Método que permite insertar un Documento nuevo
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "insertarDocumento")
    public DocumentoEntity insertarDocumento(@WebParam(name = "objeto") DocumentoEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getTituloDocumento(), "Sede"))) {
            DocumentoEntity ret = new DocumentoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getTituloDocumento(), "Sede"));
            return ret;
        } else {
        DocumentoLogic documentoLogic=new DocumentoLogic();
        return documentoLogic.insertarDocumento(objeto);
        }
}
     /**
     * Método que permite actualizar un Documento
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "actualizarDocumento")
    public DocumentoEntity actualizarDocumento(@WebParam(name = "objeto")DocumentoEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getTituloDocumento(), "Sede"))) {
            DocumentoEntity ret = new DocumentoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getTituloDocumento(), "Sede"));
            return ret;
        } else {
            DocumentoLogic metodoRecuperacionLogic=new DocumentoLogic();
            return metodoRecuperacionLogic.actualizarDocumento(objeto);
        }
    }
    /**
     * Método que trae una lista de Documento
     * @return 
     */
    @WebMethod(operationName = "listaDocumento")
    public ObjetoRetornaEntity listaDocumento(){
        DocumentoLogic documentoLogic=new DocumentoLogic();
        return documentoLogic.listaDocumento();
     }
    /**
     * Método que permite insertar un Usuarios Asignados Sobre el Documento nuevo
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "insertarDocumentoProceso")
    public DocumentoProcesoEntity insertarDocumentoProceso(@WebParam(name = "objeto") DocumentoProcesoEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getAccionDocumentoProceso(), "Sede"))) {
            DocumentoProcesoEntity ret = new DocumentoProcesoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getAccionDocumentoProceso(), "Sede"));
            return ret;
        } else {
        DocumentoProcesoLogic documentoProcesoLogic=new DocumentoProcesoLogic();
        return documentoProcesoLogic.insertarDocumentoProceso(objeto);
        }
}
     /**
     * Método que permite actualizar un Usuarios Asignados Sobre el Documento
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "actualizarDocumentoProceso")
    public DocumentoProcesoEntity actualizarDocumentoProceso(@WebParam(name = "objeto")DocumentoProcesoEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getAccionDocumentoProceso(), "Sede"))) {
            DocumentoProcesoEntity ret = new DocumentoProcesoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getAccionDocumentoProceso(), "Sede"));
            return ret;
        } else {
            DocumentoProcesoLogic metodoRecuperacionLogic=new DocumentoProcesoLogic();
            return metodoRecuperacionLogic.actualizarDocumentoProceso(objeto);
        }
    }
    /**
     * Método que trae una lista de Usuarios Asignados Sobre el Documento
     * @return 
     */
    @WebMethod(operationName = "listaDocumentoProceso")
    public ObjetoRetornaEntity listaDocumentoProceso(){
        DocumentoProcesoLogic documentoProcesoLogic=new DocumentoProcesoLogic();
        return documentoProcesoLogic.listaDocumentoProceso();
     }
    
}