/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.endpoint;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.AccionEntity;
import co.com.siscomputo.gestiondocumental.persistencia.DocumentoEntity;
import co.com.siscomputo.gestiondocumental.logic.DocumentoLogic;
import co.com.siscomputo.gestiondocumental.logic.DocumentoProcesoLogic;
import co.com.siscomputo.gestiondocumental.logic.DocumentoRolLogic;
import co.com.siscomputo.gestiondocumental.logic.UsuarioDocumentoLogic;
import co.com.siscomputo.gestiondocumental.persistencia.DocumentoProcesoEntity;
import co.com.siscomputo.gestiondocumental.persistencia.DocumentoRolEntity;
import co.com.siscomputo.gestiondocumental.persistencia.UsuarioDocumentoEntity;
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
    /**
     * Método que trae una lista de Documentos por Acción     *
     * @param accion
     * @return
     */
    @WebMethod(operationName = "listaDocumentoPorAccion")
    public ObjetoRetornaEntity listaDocumentoPorAccion(@WebParam(name = "AccionE") AccionEntity accion) {
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(accion.getIdAccion(), "Acción"))) {
            ObjetoRetornaEntity ret = new ObjetoRetornaEntity();
            ret.setTrazaRespuesta(valida.valida(accion.getIdAccion(), "Acción"));
            return ret;
        } else {
            DocumentoLogic documentoLogic = new DocumentoLogic();
            return documentoLogic.DocumentosPorAccion(accion);
        }
    }
    
    /**
     * Método que permite insertar un Documentos Rol nuevo
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "insertarDocumentoRol")
    public DocumentoRolEntity insertarDocumentoRol(@WebParam(name = "objeto") DocumentoRolEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getRolesentityDocumentoRol(), "Sede"))) {
            DocumentoRolEntity ret = new DocumentoRolEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getRolesentityDocumentoRol(), "Sede"));
            return ret;
        } else {
        DocumentoRolLogic documentoRolLogic=new DocumentoRolLogic();
        return documentoRolLogic.insertarDocumentoRol(objeto);
        }
}
     /**
     * Método que permite actualizar un Documentos Rol
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "actualizarDocumentoRol")
    public DocumentoRolEntity actualizarDocumentoRol(@WebParam(name = "objeto")DocumentoRolEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getRolesentityDocumentoRol(), "Sede"))) {
            DocumentoRolEntity ret = new DocumentoRolEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getRolesentityDocumentoRol(), "Sede"));
            return ret;
        } else {
            DocumentoRolLogic metodoRecuperacionLogic=new DocumentoRolLogic();
            return metodoRecuperacionLogic.actualizarDocumentoRol(objeto);
        }
    }
    /**
     * Método que trae una lista de Documentos Rol
     * @return 
     */
    @WebMethod(operationName = "listaDocumentoRol")
    public ObjetoRetornaEntity listaDocumentoRol(){
        DocumentoRolLogic documentoRolLogic=new DocumentoRolLogic();
        return documentoRolLogic.listaDocumentoRol();
     
    }
    
    /**
     * Método que permite insertar un Relación Usuario Docuemento nuevo
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "insertarUsuarioDocumento")
    public UsuarioDocumentoEntity insertarUsuarioDocumento(@WebParam(name = "objeto") UsuarioDocumentoEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getUsuarioUsuarioDocumento(), "Sede"))) {
            UsuarioDocumentoEntity ret = new UsuarioDocumentoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getUsuarioUsuarioDocumento(), "Sede"));
            return ret;
        } else {
        UsuarioDocumentoLogic usuarioDocumentoLogic=new UsuarioDocumentoLogic();
        return usuarioDocumentoLogic.insertarUsuarioDocumento(objeto);
        }
}
     /**
     * Método que permite actualizar un Relación Usuario Docuemento
     * @param objeto
     * @return 
     */
    @WebMethod(operationName = "actualizarUsuarioDocumento")
    public UsuarioDocumentoEntity actualizarUsuarioDocumento(@WebParam(name = "objeto")UsuarioDocumentoEntity objeto){
        Valida valida = new Valida();
        if (!"Ok".equalsIgnoreCase(valida.valida(objeto.getUsuarioUsuarioDocumento(), "Sede"))) {
            UsuarioDocumentoEntity ret = new UsuarioDocumentoEntity();
            ret.setTrazaRespuesta(valida.valida(objeto.getUsuarioUsuarioDocumento(), "Sede"));
            return ret;
        } else {
            UsuarioDocumentoLogic metodoRecuperacionLogic=new UsuarioDocumentoLogic();
            return metodoRecuperacionLogic.actualizarUsuarioDocumento(objeto);
        }
    }
    /**
     * Método que trae una lista de Relación Usuario Docuemento
     * @return 
     */
    @WebMethod(operationName = "listaUsuarioDocumento")
    public ObjetoRetornaEntity listaUsuarioDocumento(){
        UsuarioDocumentoLogic usuarioDocumentoLogic=new UsuarioDocumentoLogic();
        return usuarioDocumentoLogic.listaUsuarioDocumento();
     
    }
}
